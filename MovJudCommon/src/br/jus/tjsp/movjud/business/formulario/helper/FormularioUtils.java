package br.jus.tjsp.movjud.business.formulario.helper;

import br.jus.tjsp.movjud.business.formula.utils.FormulaCalculo;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoValidacaoType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormularioUtils {

    public static final String REDIRECT_GERENCIAR_FORMULARIO = "voltar";

    public FormularioUtils() {
    }

    public static boolean dominioBIExistenteNoFormulario(FormularioDTO formularioDTO, CampoDTO dominioBI) {
        boolean emUso = false;
        if(dominioBI!=null && dominioBI.getCodigoBI()!=null && !dominioBI.getCodigoBI().isEmpty()){
            for(SecaoDTO secao : formularioDTO.getListaSecoes()){
                for(GrupoDTO grupo : secao.getListaGrupos()){
                    if(grupo.getDominioBI()!=null && grupo.getDominioBI().trim().equals(dominioBI.getCodigoBI().trim())){
                        emUso = true;
                        break;
                    }else{
                        for(CampoDTO campo: grupo.getListaCampos()){
                            if(campo.getCodigoBI() != null && campo.getCodigoBI().equals(dominioBI.getCodigoBI()) &&
                                !campo.equals(dominioBI)){
                                emUso = true;
                                break;
                            }else{
                                emUso = dominioBIExistenteEmCampos(campo, dominioBI);
                                if(emUso)break;
                            }
                        }
                    }
                }
                if(emUso)break;
            }
        }
        return emUso;
    }
    
    public static boolean dominioBIExistenteNoFormulario(FormularioDTO formularioDTO, GrupoDTO dominioBI) {
        boolean emUso = false;
        if(dominioBI!=null && dominioBI.getDominioBI()!=null && !dominioBI.getDominioBI().isEmpty()){
            for(SecaoDTO secao : formularioDTO.getListaSecoes()){
                for(GrupoDTO grupo : secao.getListaGrupos()){
                    if(grupo.getDominioBI()!=null && grupo.getDominioBI().trim().equals(dominioBI.getDominioBI().trim())
                       && !grupo.equals(dominioBI)){
                        emUso = true;
                        break;
                    }else{
                        for(CampoDTO campo: grupo.getListaCampos()){
                            if(campo.getCodigoBI() != null && campo.getCodigoBI().equals(dominioBI.getDominioBI())){
                                emUso = true;
                                break;
                            }else{
                                emUso = dominioBIExistenteEmCampos(campo, new CampoDTO(dominioBI.getDominioBI()));
                                if(emUso)break;
                            }
                        }
                    }
                }
                if(emUso)break;
            }
        }
        return emUso;
    }
    
    public static boolean dominioBIExistenteEmCampos(CampoDTO campoDTO, CampoDTO dominioBI){
        boolean emUso = false;
        for(CampoDTO subCampo : campoDTO.getListaCampos()){
            if(subCampo.getCodigoBI() != null &&  subCampo.getCodigoBI().equals(dominioBI.getCodigoBI())
                && subCampo.getCodigoCampo()!=null && !subCampo.equals(dominioBI)){
                emUso = true;
                break;
            }else{
                emUso = dominioBIExistenteEmCampos(subCampo, dominioBI);
                if(emUso)break;
            }
        }
        return emUso;
    }

    public static SubSecaoDTO encontrarSubSecaoPorMagistrado(Long idMagistrado, SecaoDTO secaoMagistrado) {
        int i = 0;
        for (SubSecaoDTO subSecaoDTO : secaoMagistrado.getListaSubSecoes()) {
            if (subSecaoDTO.getIdMagistrado().equals(idMagistrado)) {
                break;
            }
            i++;
        }
        if(i < secaoMagistrado.getListaSubSecoes().size())
            return secaoMagistrado.getListaSubSecoes().get(i);
        else
            return null;
    }

    public static List<CampoDTO> recuperarCamposFormulario(FormularioDTO formulario) {
        return recuperarCamposFormularioPorTipo(formulario, Arrays.asList(TipoCampoType.values()));
    }

    public static List<CampoDTO> recuperarMetadadosCampoFormularioAplicaveisFormula(FormularioDTO formulario) {
        List<TipoCampoType> tiposCampoFormula = new ArrayList<TipoCampoType>();
        tiposCampoFormula.add(TipoCampoType.NUMERO);
        tiposCampoFormula.add(TipoCampoType.FORMULA);

        return recuperarMetadadosCampoFormularioPorTipo(formulario, tiposCampoFormula);
    }
    
    public static List<CampoDTO> recuperarMetadadosCampoFormulario(FormularioDTO formulario) {
        return recuperarMetadadosCampoFormularioPorTipo(formulario, Arrays.asList(TipoCampoType.values()));
    }

    private static List<CampoDTO> recuperarMetadadosCampoFormularioPorTipo(FormularioDTO formulario, List<TipoCampoType> tiposCampo) {
        List<CampoDTO> listaCampos = new ArrayList<CampoDTO>();
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            for (GrupoDTO grupo : secao.getListaGrupos()) {
                if (grupo.getListaCampos() != null) {
                    listaCampos.addAll(recuperarMetadadosCampo(grupo.getListaCampos(), secao, grupo, tiposCampo));
                }
            }
        }
        return listaCampos;
    }

    public static List<CampoDTO> recuperarMetadadosCampo(List<CampoDTO> listaCampos, SecaoDTO secao, GrupoDTO grupo, List<TipoCampoType> tiposCampo) {
        List<CampoDTO> listaCamposRetorno = new ArrayList<CampoDTO>();
        if (listaCampos != null) {
            for (CampoDTO campo : listaCampos) {
                if (tiposCampo == null || tiposCampo.contains(campo.getTipoCampo())) {
                    // campo.setDescricaoCampoFormula("[" + campo.getCodigoCampo() + "] - " + secao.getLabelSecao() + ", " + grupo.getLabelGrupo() + ", " + campo.getLabelCampo());
                    listaCamposRetorno.add(campo);
                }
                if (campo.getListaCampos() != null) {
                    listaCamposRetorno.addAll(recuperarMetadadosCampo(campo.getListaCampos(), secao, grupo, tiposCampo));
                }
            }
        }
        return listaCamposRetorno;
    }

    public static List<CampoDTO> recuperarCamposFormularioAplicaveisFormula(FormularioDTO formulario) {
        List<TipoCampoType> tiposCampoFormula = new ArrayList<TipoCampoType>();
        tiposCampoFormula.add(TipoCampoType.NUMERO);
        tiposCampoFormula.add(TipoCampoType.FORMULA);

        return recuperarCamposFormularioPorTipo(formulario, tiposCampoFormula);
    }

    public static List<CampoDTO> recuperarCamposFormularioPorTipo(FormularioDTO formulario, List<TipoCampoType> tiposCampo) {
        List<CampoDTO> listaCampos = new ArrayList<CampoDTO>();
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            for (SubSecaoDTO subSecao : secao.getListaSubSecoes()) {
                if (subSecao.getListaGrupos() != null) {
                    for (GrupoDTO grupo : subSecao.getListaGrupos()) {
                        if (grupo.getListaCampos() != null) {
                            listaCampos.addAll(recuperarCampos(grupo.getListaCampos(), subSecao, grupo, tiposCampo));
                        }
                    }
                }
            }
        }
        return listaCampos;
    }


    public static List<CampoDTO> recuperarCampos(List<CampoDTO> listaCampos, SubSecaoDTO secao, GrupoDTO grupo, List<TipoCampoType> tiposCampo) {
        List<CampoDTO> listaCamposRetorno = new ArrayList<CampoDTO>();
        if (listaCampos != null) {
            for (CampoDTO campo : listaCampos) {
                if (tiposCampo == null || tiposCampo.contains(campo.getTipoCampo())) {
                    // campo.setDescricaoCampoFormula("[" + campo.getCodigoCampo() + "] - " + secao.getLabelSecao() + ", " + grupo.getLabelGrupo() + ", " + campo.getLabelCampo());
                    listaCamposRetorno.add(campo);
                }
                if (campo.getListaCampos() != null) {
                    listaCamposRetorno.addAll(recuperarCampos(campo.getListaCampos(), secao, grupo, tiposCampo));
                }
            }
        }
        return listaCamposRetorno;
    }

    public static int encontrarIndiceGrupo(FormularioDTO formulario, String codigoSecao, String codigoGrupo) {
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getCodigoSecao().equalsIgnoreCase(codigoSecao)) {
                int index = 0;
                if (secao.getListaGrupos() != null) {
                    for (GrupoDTO grupo : secao.getListaGrupos()) {
                        if (grupo.getCodigoGrupo().equalsIgnoreCase(codigoGrupo)) {
                            return index;
                        }
                        index++;
                    }
                }
            }
        }
        return 0;
    }

    public static CampoDTO encontrarCadCampo(FormularioDTO formulario, String id) {
        CampoDTO retorno = null;
        if (formulario != null) {
            mainLoop:
            for (SecaoDTO secao : formulario.getListaSecoes()) {
                for (SubSecaoDTO subSecao : secao.getListaSubSecoes()) {
                    if (subSecao.getListaGrupos() != null) {
                        for (GrupoDTO grupo : subSecao.getListaGrupos()) {
                            if (grupo.getListaCampos() != null) {
                                for (CampoDTO campo : grupo.getListaCampos()) {
                                    if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                                        retorno = campo;
                                        break mainLoop;
                                    } else {
                                        if (campo.getListaCampos() != null) {
                                            retorno = encontrarSubCampo(id, campo.getListaCampos());
                                            if (retorno != null) {
                                                break mainLoop;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static List<CampoDTO> encontrarCadCampoDasSubSecoes(FormularioDTO formulario, String id) {
        List<CampoDTO> listaCamposSubSecao = new ArrayList<CampoDTO>();
        mainLoop:
        if (formulario != null) {
            for (SecaoDTO secao : formulario.getListaSecoes()) {
                for (SubSecaoDTO subSecao : secao.getListaSubSecoes()) {
                    if (subSecao.getListaGrupos() != null) {
                        for (GrupoDTO grupo : subSecao.getListaGrupos()) {
                            if (grupo.getListaCampos() != null) {
                                for (CampoDTO campo : grupo.getListaCampos()) {
                                    CampoDTO campoEncontrado = null;
                                    if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                                        campoEncontrado = campo;
                                    } else {
                                        if (campo.getListaCampos() != null) {
                                            campoEncontrado = encontrarSubCampo(id, campo.getListaCampos());
                                        }
                                    }

                                    if (campoEncontrado != null) {
                                        listaCamposSubSecao.add(campoEncontrado);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return listaCamposSubSecao;
    }

    public static CampoDTO encontrarCampo(FormularioDTO formulario, String id) {
        CampoDTO retorno = null;
        mainLoop:
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getListaGrupos() != null) {
                for (GrupoDTO grupo : secao.getListaGrupos()) {
                    if (grupo.getListaCampos() != null) {
                        for (CampoDTO campo : grupo.getListaCampos()) {
                            if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                                retorno = campo;
                                break mainLoop;
                            } else {
                                if (campo.getListaCampos() != null) {
                                    retorno = encontrarSubCampo(id, campo.getListaCampos());
                                    if (retorno != null) {
                                        break mainLoop;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static CampoDTO encontrarCampo(SubSecaoDTO subSecao, String id) {
        CampoDTO retorno = null;
        mainLoop:
        for (GrupoDTO grupo : subSecao.getListaGrupos()) {
            if (grupo.getListaCampos() != null) {
                for (CampoDTO campo : grupo.getListaCampos()) {
                    if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                        retorno = campo;
                        break mainLoop;
                    } else {
                        if (campo.getListaCampos() != null) {
                            retorno = encontrarSubCampo(id, campo.getListaCampos());
                            if (retorno != null) {
                                break mainLoop;
                            }
                        }
                    }
                }
            }

        }
        return retorno;
    }

    public static SubSecaoDTO encontrarSubSecao(FormularioDTO formulario, SubSecaoDTO subSecao) {
        SubSecaoDTO retorno = null;
        if (subSecao != null) {
            for (SecaoDTO secaoDTO : formulario.getListaSecoes()) {
                for (SubSecaoDTO subSecaoAnteriorDTO : secaoDTO.getListaSubSecoes()) {
                    if (subSecaoAnteriorDTO.getCodigoSubSecao().equals(SecaoType.MATERIA.getCodigoSecao())) {
                        if (subSecaoAnteriorDTO.getTipoMateria().equals(subSecao.getTipoMateria())) {
                            retorno = subSecaoAnteriorDTO;
                        }
                    } else if (subSecaoAnteriorDTO.getCodigoSubSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
                        if (subSecaoAnteriorDTO.getIdMagistrado().equals(subSecao.getIdMagistrado())) {
                            retorno = subSecaoAnteriorDTO;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static CampoDTO encontrarCampo(String id, GrupoDTO grupo) {
        CampoDTO retorno = null;
        for (CampoDTO campo : grupo.getListaCampos()) {
            if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                retorno = campo;
                break;
            } else {
                if (campo.getListaCampos() != null) {
                    retorno = encontrarSubCampo(id, campo.getListaCampos());
                    if (retorno != null) {
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public static CampoDTO encontrarSubCampo(String id, List<CampoDTO> listaSubcampos) {
        CampoDTO retorno = null;
        if (listaSubcampos != null || !listaSubcampos.isEmpty()) {
            for (CampoDTO campo : listaSubcampos) {
                if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                    retorno = campo;
                    break;
                } else {
                    if (campo.getListaCampos() != null) {
                        retorno = encontrarSubCampo(id, campo.getListaCampos());
                        if (retorno != null) {
                            break;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static CampoDTO encontrarCampoPai(String id, GrupoDTO grupo) {
        CampoDTO retorno = null;
        for (CampoDTO campo : grupo.getListaCampos()) {
            if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                retorno = campo;
                break;
            } else {
                if (campo.getListaCampos() != null) {
                    retorno = encontrarSubCampoPai(id, campo);
                    if (retorno != null) {
                        break;
                    }
                }
            }
        }
        return retorno;
    }

    public static CampoDTO encontrarSubCampoPai(String id, CampoDTO campoPai) {
        CampoDTO retorno = null;
        if (campoPai.getListaCampos() != null || !campoPai.getListaCampos().isEmpty()) {
            for (CampoDTO campo : campoPai.getListaCampos()) {
                if (campo.getCodigoCampo().equalsIgnoreCase(id)) {
                    retorno = campoPai;
                    break;
                } else {
                    if (campo.getListaCampos() != null) {
                        retorno = encontrarSubCampoPai(id, campo);
                        if (retorno != null) {
                            break;
                        }
                    }
                }
            }
        }
        return retorno;
    }

    public static GrupoDTO encontrarGrupoPorCampo(FormularioDTO formulario, String codigoSecao, String codigoCampo) {
        CampoDTO campoAux = null;
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getCodigoSecao().equalsIgnoreCase(codigoSecao)) {
                if (secao.getListaGrupos() != null) {
                    for (GrupoDTO grupo : secao.getListaGrupos()) {
                        campoAux = encontrarCampo(codigoCampo, grupo);
                        if (campoAux != null) {
                            return grupo;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static GrupoDTO encontrarGrupoPorCampo(FormularioDTO formulario, String codigoCampo) {
        CampoDTO campoAux = null;
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getListaGrupos() != null) {
                for (GrupoDTO grupo : secao.getListaGrupos()) {
                    campoAux = encontrarCampo(codigoCampo, grupo);
                    if (campoAux != null) {
                        return grupo;
                    }
                }
            }
        }
        return null;
    }

    public static GrupoDTO encontrarGrupoPorCodigo(FormularioDTO formulario, String codigoGrupo) {
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getListaGrupos() != null) {
                for (GrupoDTO grupo : secao.getListaGrupos()) {
                    if (grupo.getCodigoGrupo().equalsIgnoreCase(codigoGrupo)) {
                        return grupo;
                    }
                }
            }
        }
        return null;
    }

    public static SecaoDTO encontrarSecaoPorCodigo(FormularioDTO formulario, String codigoSecao) {
        for (SecaoDTO secao : formulario.getListaSecoes()) {
            if (secao.getCodigoSecao().equalsIgnoreCase(codigoSecao))
                return secao;
        }
        return null;
    }

    public static SecaoDTO gerarCodigoGrupos(SecaoDTO secaoDTO) {
        if (secaoDTO.getListaGrupos() != null && secaoDTO.getListaGrupos().size() > 0) {
            int i = 1;
            for (GrupoDTO grupo : secaoDTO.getListaGrupos()) {
                grupo.setCodigoGrupo(secaoDTO.getCodigoSecao() + "G" + i);
                if (grupo.getLabelGrupo() == null)
                    grupo.setLabelGrupo(grupo.getCodigoGrupo());
                gerarCodigoCampos(grupo);
                i++;
            }
        }
        return secaoDTO;
    }

    public static GrupoDTO gerarCodigoCampos(GrupoDTO grupo) {
        Integer ultimoValor = new Integer(0);
        if (grupo.getListaCampos() != null && grupo.getListaCampos().size() > 0) {
            for (CampoDTO campo : grupo.getListaCampos()) {
                if(campo.getCodigoCampo() != null && campo.getCodigoCampo().isEmpty() == false){
                   Integer checkCodigoCampo = new Integer(campo.getCodigoCampo().substring(campo.getCodigoCampo().lastIndexOf("C")+1));
                   ultimoValor = checkCodigoCampo.intValue() > ultimoValor ? checkCodigoCampo.intValue() : ultimoValor;
                }
            }
            
            int i = ultimoValor.intValue() + 1;
            for (CampoDTO campo : grupo.getListaCampos()) {
                if(campo.getCodigoCampo() == null || campo.getCodigoCampo().isEmpty()){
                    campo.setCodigoCampo(grupo.getCodigoGrupo() + "C" + i);
                    if (campo.getLabelCampo() == null)
                        campo.setLabelCampo(campo.getCodigoCampo());
                    i++;
                }
                campo = codigoCampos(campo);
            }
        }
        
        /* if (grupo.getListaCampos() != null && grupo.getListaCampos().size() > 0) {
            int i = 1;
            for (CampoDTO campo : grupo.getListaCampos()) {
                campo.setCodigoCampo(grupo.getCodigoGrupo() + "C" + i);
                if (campo.getLabelCampo() == null)
                    campo.setLabelCampo(campo.getCodigoCampo());
                campo = codigoCampos(campo);
                i++;
            }
        }*/
        return grupo;
    }

    public static CampoDTO codigoCampos(CampoDTO campoPai) {
        //int i = 1;
        /*if (campoPai.getListaCampos() != null) {
            for (CampoDTO campoIndice : campoPai.getListaCampos()) {
                campoIndice.setCodigoCampo(campoPai.getCodigoCampo() + "C" + i);
                if (campoIndice.getLabelCampo() == null)
                    campoIndice.setLabelCampo(campoIndice.getCodigoCampo());
                if (campoIndice.getListaCampos() != null) {
                    campoIndice = codigoCampos(campoIndice);
                }
                i++;
            }
        }*/
        
        Integer ultimoValor = new Integer(0);
        if (campoPai.getListaCampos() != null && campoPai.getListaCampos().size() > 0) {
            for (CampoDTO campoIndice : campoPai.getListaCampos()) {
                if(campoIndice.getCodigoCampo() != null && campoIndice.getCodigoCampo().isEmpty() == false){
                   Integer checkCodigoCampo = new Integer(campoIndice.getCodigoCampo().substring(campoIndice.getCodigoCampo().lastIndexOf("C")+1));
                   ultimoValor = checkCodigoCampo.intValue() > ultimoValor ? checkCodigoCampo.intValue() : ultimoValor;
                }
            }
            
            int i = ultimoValor.intValue() + 1;
            for (CampoDTO campoIndice : campoPai.getListaCampos()) {
                if(campoIndice.getCodigoCampo() == null || campoIndice.getCodigoCampo().isEmpty()){
                    campoIndice.setCodigoCampo(campoPai.getCodigoCampo() + "C" + i);
                    if (campoIndice.getLabelCampo() == null)
                        campoIndice.setLabelCampo(campoIndice.getCodigoCampo());
                    i++;
                }
                if (campoIndice.getListaCampos() != null) {
                    campoIndice = codigoCampos(campoIndice);
                }
            }
        }
        
        return campoPai;
    }

    /* public static GrupoDTO gerarIndices(GrupoDTO grupo) {
        if (grupo.getListaCampos() != null && grupo.getListaCampos().size() > 0) {
            int i = 1;
            for (CampoDTO campo : grupo.getListaCampos()) {
                campo.setIndiceCampo(i + ".");
                campo = indiceCampos(campo);
                i++;
            }
        }
        return grupo;
    } */

    public static SecaoDTO gerarIndices(SecaoDTO secao) {
        if (secao.getListaGrupos() != null && secao.getListaGrupos().size() > 0) {
            for (GrupoDTO grupo : secao.getListaGrupos()) {
                if (grupo.getListaCampos() != null && grupo.getListaCampos().size() > 0) {
                    int i = 1;
                    for (CampoDTO campo : grupo.getListaCampos()) {
                        campo.setIndiceCampo(i + ".");
                        campo = indiceCampos(campo);
                        i++;
                    }
                }
            }
        }
        return secao;
    }

    public static CampoDTO indiceCampos(CampoDTO campoPai) {
        int i = 1;
        if (campoPai.getListaCampos() != null) {
            for (CampoDTO campoIndice : campoPai.getListaCampos()) {
                campoIndice.setIndiceCampo(campoPai.getIndiceCampo() + i + ".");
                if (campoIndice.getListaCampos() != null) {
                    campoIndice = indiceCampos(campoIndice);
                }
                i++;
            }
        }
        return campoPai;
    }

    public static CampoDTO criarCampo(String idPai, String indicePai, Long ordem, String hint, boolean requerido, List<CampoDTO> listaCampos, GrupoDTO grupo, CampoDTO campoPai) {
        CampoDTO campo = new CampoDTO();

        if (grupo != null)
            campo.setGrupo(grupo);

        if (campoPai != null)
            campo.setCampoPai(campoPai);


        //campo.setCodigoCampo(idPai+"C"+(listaCampos.size()+1));
        campo.setLabelCampo(campo.getCodigoCampo());
        campo.setOrdemCampo(ordem);
        campo.setHint(hint);
        campo.setRequerido(requerido);
        campo.setTipoCampo(TipoCampoType.NUMERO);
        campo.setListaCampos(new ArrayList<CampoDTO>());
        return campo;
    }

    public static SecaoDTO criarSecaoPorTipo(SecaoType tipoSecao, FormularioDTO formulario) {
        SecaoDTO secaoDTO = null;
        if (tipoSecao.equals(SecaoType.DADOS_UNIDADES)) {
            secaoDTO = FormularioUtils.criarSecao(formulario, SecaoType.DADOS_UNIDADES);
        } else if (tipoSecao.equals(SecaoType.MAGISTRADO)) {
            secaoDTO = FormularioUtils.criarSecao(formulario, SecaoType.MAGISTRADO);
        } else if (tipoSecao.equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS)) {
            secaoDTO = FormularioUtils.criarSecao(formulario, SecaoType.ESTABELECIMENTOS_PRISIONAIS);
        } else if (tipoSecao.equals(SecaoType.MATERIA)) {
            secaoDTO = FormularioUtils.criarSecao(formulario, SecaoType.MATERIA);
        } else if (tipoSecao.equals(SecaoType.REUS)) {
            secaoDTO = FormularioUtils.criarSecao(formulario, SecaoType.REUS);
        }
        return secaoDTO;
    }

    private static SecaoDTO criarSecao(FormularioDTO formularioDTO, SecaoType secao) {
        SecaoDTO secaoDTO = new SecaoDTO();

        secaoDTO.setFormulario(formularioDTO);
        secaoDTO.setCodigoSecao(secao.getCodigoSecao());
        secaoDTO.setLabelSecao(secao.getLabelSecao());
        secaoDTO.setListaGrupos(new ArrayList<GrupoDTO>());
        secaoDTO.setOrdemSecao(secao.getOrdemSecao());
        return secaoDTO;
    }

    public static GrupoDTO criarGrupo(SecaoDTO secao) {
        GrupoDTO grupo = new GrupoDTO();
        int sequencia = 0;

        grupo.setSecao(secao);
        if (secao.getListaGrupos() != null)
            sequencia = secao.getListaGrupos().size();
        grupo.setCodigoGrupo(secao.getCodigoSecao() + "G" + (sequencia + 1));
        grupo.setLabelGrupo(grupo.getCodigoGrupo());
        grupo.setListaCampos(new ArrayList<CampoDTO>());
        grupo.getListaCampos().add(FormularioUtils.criarCampo(grupo.getCodigoGrupo(), null, Long.valueOf(grupo.getListaCampos().size()), null, false, grupo.getListaCampos(), grupo, null));
        return grupo;
    }

    public static Map<TipoValidacaoType, List<ValidacaoDTO>> recuperarMensagensValidacaoFormula(FormularioDTO formulario, FormularioDTO formularioMesAnterior) {
        Map<TipoValidacaoType, List<ValidacaoDTO>> retorno = new HashMap<TipoValidacaoType, List<ValidacaoDTO>>();

        FormulaCalculo.aplicarValidacoesCamposDoFormulario(formulario, formularioMesAnterior);

        List<CampoDTO> listaCampos = recuperarCamposFormulario(formulario);

        List<ValidacaoDTO> listaErro = new ArrayList<ValidacaoDTO>();
        List<ValidacaoDTO> listaAviso = new ArrayList<ValidacaoDTO>();
        List<ValidacaoDTO> listaConfirmacao = new ArrayList<ValidacaoDTO>();

        for (CampoDTO campo : listaCampos) {
            if (campo.getListaValidacoes() != null && !campo.getListaValidacoes().isEmpty()) {
                for (ValidacaoDTO validacao : campo.getListaValidacoes()) {
                    if (TipoValidacaoType.VALIDACAO_ERRO.equals(validacao.getTipoValidacao())) {
                        if (!validacao.isStatusValidacao())
                            listaErro.add(validacao);
                    } else if (TipoValidacaoType.VALIDACAO_AVISO.equals(validacao.getTipoValidacao())) {
                        if (!validacao.isStatusValidacao())
                            listaAviso.add(validacao);
                    } else if (TipoValidacaoType.VALIDACAO_CONFIRMACAO.equals(validacao.getTipoValidacao())) {
                        //inserindo flag de validação aceita = false para validação na tela
                        if (!validacao.isStatusValidacao()) {
                            listaConfirmacao.add(validacao);
                        }
                    }
                }
            }
        }

        //ValidacaoDTO val1 = new ValidacaoDTO();
        //val1.setValidacaoAceita(false);
        //val1.setMensagem("Teste");
        //listaConfirmacao.add(val1);

        retorno.put(TipoValidacaoType.VALIDACAO_ERRO, listaErro);
        retorno.put(TipoValidacaoType.VALIDACAO_AVISO, listaAviso);
        retorno.put(TipoValidacaoType.VALIDACAO_CONFIRMACAO, listaConfirmacao);

        return retorno;
    }
}
