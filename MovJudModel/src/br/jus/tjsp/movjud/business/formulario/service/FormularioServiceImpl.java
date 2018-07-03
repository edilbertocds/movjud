package br.jus.tjsp.movjud.business.formulario.service;

import br.jus.tjsp.movjud.business.cache.FormularioCache;
import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.CompetenciaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.HistoricoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.LiberacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ProcessosConclusosCpcDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ReuDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SegmentoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SubSecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoConclusoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoRegraDTO;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioConverter;
import br.jus.tjsp.movjud.business.utils.helper.IndicadorProgressoUtil;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.MetadadosTipoSituacaoType;
import br.jus.tjsp.movjud.persistence.base.types.TipoSituacaoType;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Formulario;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosGrupoCampo;
import br.jus.tjsp.movjud.persistence.entity.MetadadosSecao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoSituacao;
import br.jus.tjsp.movjud.persistence.entity.PeriodoProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;
import br.jus.tjsp.movjud.persistence.entity.PreCarga;
import br.jus.tjsp.movjud.persistence.entity.ProcessoConcluso;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorio;
import br.jus.tjsp.movjud.persistence.entity.ReuProvisorioHistorico;
import br.jus.tjsp.movjud.persistence.entity.Secao;
import br.jus.tjsp.movjud.persistence.entity.TipoConcluso;
import br.jus.tjsp.movjud.persistence.entity.TipoFilaProcesso;
import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;
import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.EstabelecimentoPrisionalDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.PeriodoProcessoConclusoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.PermissaoUnidadeTemporariaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ProcessoConclusoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UnidadeDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.FormularioDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.FormularioVinculacaoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosCampoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosFormularioDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosGrupoCampoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosGrupoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosTipoRegraDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.MetadadosTipoSituacaoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.PreCargaDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.ReuProvisorioDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.ReuProvisorioHistoricoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoAreaDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoCompetenciaDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoConclusoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoMateriaDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoMotivoBaixaDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoNaturezaPrisaoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoSegmentoDAO;
import br.jus.tjsp.movjud.persistence.formulario.dao.TipoSituacaoDAO;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

@Stateless(name="FormularioService", mappedName="MovJudModel")
public class FormularioServiceImpl implements FormularioService{
    final static Logger logger = Logger.getLogger(FormularioServiceImpl.class);

    @EJB
    private MetadadosCampoDAO metadadosCampoDAO;
    
    @EJB
    private MetadadosGrupoDAO metadadosGrupoDAO;
    
    @EJB
    private MetadadosFormularioDAO metadadosFormularioDAO;
    
    @EJB
    private MetadadosGrupoCampoDAO metadadosGrupoCampoDAO;
    
    @EJB
    private TipoCompetenciaDAO tipoCompetenciaDAO;
    
    @EJB
    private TipoAreaDAO tipoAreaDAO;
    
    @EJB
    private TipoSegmentoDAO tipoSegmentoDAO;
    
    @EJB
    private MetadadosTipoSituacaoDAO metadadosTipoSituacaoDAO;
    
    @EJB
    private MetadadosTipoRegraDAO metadadosTipoRegraDAO;
    
    @EJB
    private FormularioVinculacaoDAO formularioVinculacaoDAO;
    
    @EJB
    private UnidadeDAO unidadeDAO;
    
    @EJB
    private UsuarioDAO usuarioDAO;
    
    @EJB
    private TipoMateriaDAO tipoMateriaDAO;
    
    @EJB
    private FormularioDAO formularioDAO;
    
    @EJB
    private TipoNaturezaPrisaoDAO tipoNaturezaPrisaoDAO;
    
    @EJB
    private TipoMotivoBaixaDAO tipoMotivoBaixaDAO;
    
    @EJB
    private EstabelecimentoPrisionalDAO estabelecimentoPrisionalDAO;
    
    @EJB
    private TipoConclusoDAO tipoConclusoDAO;
    
    @EJB
    private TipoSituacaoDAO tipoSituacaoDAO;
    
    @EJB
    private PreCargaDAO preCargaDAO;
    
    @EJB
    private ProcessoConclusoDAO processoConclusoDAO;
    
    @EJB
    private PeriodoProcessoConclusoDAO periodoProcessoConclusoDAO;
    
    @EJB
    private PermissaoUnidadeTemporariaDAO permissaoUnidadeTemporariaDAO;
    
    @EJB
    private ReuProvisorioHistoricoDAO reuProvisorioHistoricoDAO;
    
    @EJB
    private ReuProvisorioDAO reuProvisorioDAO;

    public FormularioServiceImpl() {
    }

    @Override
    public MetadadosCampo salvarMetadadosCampo(MetadadosCampo metadadosCampo) {
        return metadadosCampoDAO.salvar(metadadosCampo);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public FormularioDTO criarCopiaFomularioDTO(FormularioDTO formularioDTO){
        return FormularioConverter.parseMetadadosFormularioParaFormularioDTO(FormularioConverter.parseFormularioDTOParaMetadadosFormulario(formularioDTO, false));
    }

    @Override
    public FormularioDTO salvarMetadadosFormulario(FormularioDTO formularioDTO, FormularioDTO formularioHistorico) {
        if(formularioDTO.getMetadadoSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().
                        equals(MetadadosTipoSituacaoType.EM_USO.getCodigo())){
            /**
             * Salvar historico
             */
            formularioHistorico.setMetadadoSituacaoFormularioDTO(
                    MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(
                        listarMetadadosTipoSituacao(), MetadadosTipoSituacaoType.HISTORICO));
            persistirMetadadosFormulario(formularioHistorico);
            /**
             * Salvar nova versao
             */
            formularioDTO = persistirMetadadosFormulario(formularioDTO);
        }else{
            formularioDTO = persistirMetadadosFormulario(formularioDTO);
        }
        return formularioDTO;
    }
    
    @Override
    public FormularioDTO liberarVersaoMetadadosFormulario(FormularioDTO formularioDTO){
        MetadadosFormulario metadadosFormularioNovo = FormularioConverter.parseFormularioDTOParaMetadadosFormulario(formularioDTO, false);
        MetadadosFormulario metadadosFormularioEmUso = metadadosFormularioDAO.recuperarMetadadosFormularioEmUso(metadadosFormularioNovo);
        if(metadadosFormularioEmUso!=null){
            metadadosFormularioEmUso.setMetadadosTipoSituacao(FormularioConverter.
                                                    parseMetadadoSituacaoFormularioDTOParaMetadadosTipoSituacao(
                                                    MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(
                                                        listarMetadadosTipoSituacao(), 
                                                        MetadadosTipoSituacaoType.HISTORICO)));
            FormularioConverter.parseMetadadosFormularioParaFormularioDTO(metadadosFormularioDAO.salvar(metadadosFormularioEmUso));
        }
        return FormularioConverter.parseMetadadosFormularioParaFormularioDTO(metadadosFormularioDAO.salvar(metadadosFormularioNovo));
    }
    
    @Override
    public FormularioDTO salvarMetadadosFormulario(FormularioDTO formularioDTO) {
        return persistirMetadadosFormulario(formularioDTO);
    }

    public FormularioDTO persistirMetadadosFormulario(FormularioDTO formularioDTO) {
        if(formularioDTO.getMetadadoSituacaoFormularioDTO()==null){
            formularioDTO.setMetadadoSituacaoFormularioDTO(
                    MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(
                        listarMetadadosTipoSituacao(), MetadadosTipoSituacaoType.EM_EDICAO));
            formularioDTO = FormularioConverter.parseMetadadosFormularioParaFormularioDTO(
                                        metadadosFormularioDAO.salvar(
                                            FormularioConverter.parseFormularioDTOParaMetadadosFormulario(
                                                                    formularioDTO, false)));
        }
        /**
         * Gerar Histórico
        */  
        else if(formularioDTO.getMetadadoSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equals(
                                                    MetadadosTipoSituacaoType.HISTORICO.getCodigo())){    
            formularioDTO.setMetadadoSituacaoFormularioDTO(
                    MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(
                        listarMetadadosTipoSituacao(), MetadadosTipoSituacaoType.EM_USO));
                    metadadosFormularioDAO.salvar(
                        FormularioConverter.
                            parseFormularioDTOParaMetadadosFormulario(
                            formularioDTO, false));            
                
        }
        /**
         * Nova versão de formulário
         */
        else if(formularioDTO.getMetadadoSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equals(
                                                    MetadadosTipoSituacaoType.EM_USO.getCodigo())){
            MetadadosFormulario novoMdForm = FormularioConverter.parseFormularioDTOParaMetadadosFormulario(
                                        formularioDTO, true);
            /**
             * Ajustar os vinculos
            */            
            
            FormularioVinculacao filtroVinculacao = new FormularioVinculacao();
            MetadadosFormulario mdForm = new MetadadosFormulario();
            mdForm.setIdMetadadosFormulario(formularioDTO.getIdMetadadosFormulario());
            filtroVinculacao.setMetadadosFormulario(mdForm);
            List<FormularioVinculacao> vinculosFormlarios = formularioVinculacaoDAO.listarComFiltro(filtroVinculacao);
            
            if(vinculosFormlarios!=null){
                for(FormularioVinculacao formularioVinculacao : vinculosFormlarios){
                    FormularioVinculacao novoFormularioVinculacao = new FormularioVinculacao();
                    novoFormularioVinculacao.setMetadadosFormulario(novoMdForm);
                    novoFormularioVinculacao.setUnidade(formularioVinculacao.getUnidade());
                    novoFormularioVinculacao.setListaMetadadosTipoRegra(formularioVinculacao.getListaMetadadosTipoRegra());
                    novoFormularioVinculacao.setDescricaoRegras(formularioVinculacao.getDescricaoRegras());
                    novoFormularioVinculacao.setFlagTipoSituacao(formularioVinculacao.getFlagTipoSituacao());
                    novoMdForm.getFormulariosVinculacao().add(novoFormularioVinculacao);
                }
            }
            
            /**
             * Ajustar os vinculos
             */
            novoMdForm.setNumeroVersao(novoMdForm.getNumeroVersao()+1);
            novoMdForm.setMetadadosTipoSituacao(FormularioConverter.
                                                parseMetadadoSituacaoFormularioDTOParaMetadadosTipoSituacao(
                                                MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(
                                                    listarMetadadosTipoSituacao(), 
                                                    MetadadosTipoSituacaoType.EM_EDICAO)));
            formularioDTO = FormularioConverter.parseMetadadosFormularioParaFormularioDTO(
                                        metadadosFormularioDAO.salvar(novoMdForm));
        }
        /**
         * Liberação de formulário
         */
        else if(formularioDTO.getMetadadoSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equals(
                                                    MetadadosTipoSituacaoType.EM_EDICAO.getCodigo())){         
            formularioDTO = FormularioConverter.parseMetadadosFormularioParaFormularioDTO(
                                        metadadosFormularioDAO.salvar(
                                            FormularioConverter.parseFormularioDTOParaMetadadosFormulario(
                                                                    formularioDTO, false)));    
        }
        return formularioDTO;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FormularioDTO> listarMetadadosFormulariosComFiltro(FormularioDTO formularioDTO, Paginacao paginacao) {
        List<MetadadosFormulario> listaFormularios = metadadosFormularioDAO.listarMetadadosFormularioOrdenadoPorDescricaoSource(FormularioConverter.parseFormularioDTOParaMetadadosFormulario(formularioDTO, false), paginacao);
        return FormularioConverter.parseListaMetadadosFormularioParaListaFormularioDTO(listaFormularios);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<MetadadosGrupoCampo> listarMetadadosGrupoCamposComFiltro(CampoDTO campoDTO) {
        return metadadosGrupoCampoDAO.listarComFiltro(FormularioConverter.parseCampoDTOParaMetadadosGrupoCampo(campoDTO, false));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<CompetenciaDTO> listarTipoCompetencia() {
        return FormularioConverter.parseListaTipoCompetenciaParaListaCompetenciaDTO(tipoCompetenciaDAO.listarTipoCompetenciaOrdenadoPorNome());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<MateriaDTO> listarTipoAreas() {
        return FormularioConverter.parseListaTipoAreaParaListaAreaDTO(tipoAreaDAO.listarTipoAreaOrdenadoPorNome());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SegmentoDTO> listarTipoSegmentos() {
        return FormularioConverter.parseListaTipoSegmentoParaListaSegmentoDTO(tipoSegmentoDAO.listarTipoSegmenoOrdenadoPorNome());
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<MetadadoSituacaoFormularioDTO> listarMetadadosTipoSituacao() {
        return FormularioConverter.parseListaMetadadosTipoSituacaoParaSituacaoFormularioDTO(metadadosTipoSituacaoDAO.listar());
    }

    @Override
    public void excluirMetadadosFormulario(FormularioDTO formularioDTO) {
        metadadosFormularioDAO.excluir(FormularioConverter.parseFormularioDTOParaMetadadosFormulario(formularioDTO, false));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoRegraDTO> listarMetadadosTipoRegra() {
        return FormularioConverter.parseListaMetadadosTipoRegraParaListaTipoRegraDTO(metadadosTipoRegraDAO.listarMetadadosTipoRegraPorNome());
    }

    @Override
    public TipoRegraDTO salvarMetadadosTipoRegra(TipoRegraDTO tipoRegraDTO) {
        return FormularioConverter.parseMetadadosTipoRegraParaTipoRegraDTO(metadadosTipoRegraDAO.salvar(FormularioConverter.parseTipoRegraDTOParaMetadadosTipoRegra(tipoRegraDTO)));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<CampoDTO> listarMetadadosCamposComFiltro(CampoDTO campoDTO) {
        return FormularioConverter.parseListaMetadadosCampoParaListaCampoDTO(metadadosCampoDAO.listarComFiltro(FormularioConverter.parseCampoDTOParaMetadadosCampo(campoDTO, false)));
    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<CampoDTO> listarMetadadosCamposReaproveitamentoComPaginacao(CampoDTO campoDTO, Paginacao paginacao, List<CampoDTO> listaCampos) {
        List<Long> listaCodigoCampos = null;
        if(listaCampos!=null){
            listaCodigoCampos = new ArrayList<Long>();
            for(CampoDTO campo : listaCampos){
                if(campo.getIdMetadadosCampo()!=null)listaCodigoCampos.add(campo.getIdMetadadosCampo());
            }
        }
        return FormularioConverter.parseListaMetadadosCampoParaListaCampoDTO(metadadosCampoDAO.recuperarCampoParaReaproveitamento(FormularioConverter.parseCampoDTOParaMetadadosCampo(campoDTO, false),paginacao, listaCodigoCampos));

    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FormularioVinculacao> listarFormularioVinculacao(FormularioVinculacao filter) {
	return formularioVinculacaoDAO.listarComFiltro(filter);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<MetadadosFormulario> listarMetadadosFormulariosEmUso() {
	MetadadosFormulario metadadosFormulario = new MetadadosFormulario();
	MetadadosTipoSituacao tipoSituacao = new MetadadosTipoSituacao(); 
	tipoSituacao.setTipoSituacao(ConstantesMovjud.SITUACAO_METADADO_EM_USO);
	metadadosFormulario.setMetadadosTipoSituacao(tipoSituacao);
	
	return metadadosFormularioDAO.listarMetadadosFormularioOrdenadoPorDescricaoNome(metadadosFormulario);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<MetadadosTipoRegra> listarTiposRegra() {
	MetadadosTipoRegra tipoRegra = new MetadadosTipoRegra();
	tipoRegra.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
	
	return metadadosTipoRegraDAO.listarMetadadosFormularioOrdenadoPorDescricao(tipoRegra);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Unidade obterUnidade(Unidade unidade) {
	return unidadeDAO.procurarPorId(unidade.getIdUnidade());
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoMateriaDTO> listarTipoMaterias() {
        return FormularioConverter.parseListaTipoMateriaParaListaTipoMateriaDTO(tipoMateriaDAO.listarTipoMateriaOrdenadoPorNome());
    }

    @Override
    public TipoMateriaDTO salvarTipoMateriaDTO(TipoMateriaDTO tipoMateriaDTO) {
        return FormularioConverter.parseTipoMateriaParaTipoMateriaDTO(tipoMateriaDAO.salvar(FormularioConverter.parseTipoMateriaDTOParaTipoMateria(tipoMateriaDTO)));
    }
   
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FormularioDTO> listarFormulariosComFiltro(FormularioDTO formularioDTO, Paginacao paginacao) {
        return FormularioConverter.parseListaFormularioParaListaFormularioDTO(formularioDAO.listarFormulariosPorCodigo(FormularioConverter.parseFormularioDTOParaFormulario(formularioDTO, false), paginacao));
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FormularioDTO> listarFormulariosDoUsuario(FormularioDTO filtro, Usuario usuario, Paginacao paginacao) {
	return FormularioConverter.parseListaFormularioParaListaFormularioDTO(formularioDAO.listarFormulariosDoUsuario(FormularioConverter.parseFormularioDTOParaFormulario(filtro, false), usuario, paginacao));
    }
    
    
   
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Formulario> listarFormulariosDaUnidade(Unidade unidade) {
	Formulario formulario = new Formulario();
	formulario.setUnidade(unidade);
	return formularioDAO.listarFormulariosOrdenadoPorDescricao(formulario);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Formulario> listarFormulariosMesAnoReferenciaDaUnidade(Unidade unidade) {
        Formulario formulario = new Formulario();
        formulario.setUnidade(unidade);
        formulario.setAno(FormularioConverter.anoCorrente());
        formulario.setMes(FormularioConverter.mesAnterior());
        return formularioDAO.listarFormulariosOrdenadoPorDescricao(formulario);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> listarUnidadesComFiltroPaginacao(Unidade unidade, Paginacao paginacao) {
	//return unidadeDAO.listarComFiltro(unidade, paginacao);
	String[] campoOrdenacao = {"nomeUnidade","foro.nomeForo"};
        Boolean[] ascOrdenacao = {true,true};
        return unidadeDAO.listarComFiltroOrdenacao(unidade, campoOrdenacao, ascOrdenacao, paginacao);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarUsuarioMagistradoPorNomeComPaginacao(Usuario filter, Paginacao paginacao) {
        return usuarioDAO.listarUsuarioMagistradoPorNomeComPaginacao(filter, paginacao);
    }
    
    @Override
    public Unidade salvarUnidade(Unidade unidade) {
	return unidadeDAO.salvar(unidade);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoNaturezaPrisao> listarTipoNaturezaPrisaoOrdenadoPorNome() {
        return tipoNaturezaPrisaoDAO.listarTipoNaturezaPrisaoOrdenadoPorNome(new TipoNaturezaPrisao(ConstantesMovjud.FLAG_SITUACAO_ATIVA));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoMotivoBaixa> listarTipoMotivoBaixaOrdenadoPorNome() {
        return tipoMotivoBaixaDAO.listarTipoMotivoBaixaOrdenadoPorNome();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<EstabelecimentoPrisional> listarEstabelecimentoPrisionalOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional) {
        return estabelecimentoPrisionalDAO.listarEstabelecimentosPrisionaisOrdenadoPorNome(estabelecimentoPrisional);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<EstabelecimentoPrisional> listarEstabelecimentoPrisionalOrdenadoPorNomePaginacao(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                                 Paginacao paginacao) {
        return estabelecimentoPrisionalDAO.listarEstabelecimentosPrisionaisOrdenadoPorNome(estabelecimentoPrisional,paginacao);

    }
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Unidade> carregarUnidadesDevedoras() {
        // RECUPERA OS FORMULARIOS DEVEDORES
        List<Formulario> listaFormulariosDevedores = formularioDAO
            .listarFormulariosPorSituacoes(TipoSituacaoType.ABERTO.getCodigo(),
                                           TipoSituacaoType.CONCLUIDO.getCodigo(),
                                           TipoSituacaoType.EM_PREENCHIMENTO.getCodigo(),
                                           TipoSituacaoType.DEVOLVIDO.getCodigo());
        
        // RECUPERA AS UNIDADES DEVEDORAS
        List<Unidade> listaUnidadesDevedoras = new ArrayList<Unidade>();
        if (listaFormulariosDevedores != null) {
            for (Formulario formularioDevedor : listaFormulariosDevedores) {
                if (!listaUnidadesDevedoras.contains(formularioDevedor.getUnidade())) {
                    listaUnidadesDevedoras.add(formularioDevedor.getUnidade());
                }
                
                // PREENCHE OS FORMULARIOS PENDENTES
                Unidade unidade = listaUnidadesDevedoras.get(listaUnidadesDevedoras.indexOf(formularioDevedor.getUnidade()));
                StringBuilder valorAdicionar = new StringBuilder();
                valorAdicionar.append(unidade.getFormulariosPendentes() == null ? "" : unidade.getFormulariosPendentes());
                if(unidade.getFormulariosPendentes() != null && !unidade.getFormulariosPendentes().toString().trim().equals("")){
                    valorAdicionar.append(", ");
                }
                valorAdicionar.append(formularioDevedor.getMetadadosFormulario().getDescricaoNome());
                unidade.setFormulariosPendentes(valorAdicionar.toString());                
            }
        }
        return listaUnidadesDevedoras;
    }

    @Override
    public FormularioDTO salvarFormulario(FormularioDTO formularioDTO, SecaoDTO secaoMagistrado, SecaoDTO secaoReus, SubSecaoDTO subsecaoCpcDTO) {
        
        Map<Long,ProcessoConclusoDTO> processoConclusoDTOMap = new HashMap<Long,ProcessoConclusoDTO>();
        List<SubSecaoDTO> subSecaoDTOList = new ArrayList<SubSecaoDTO>();
        if(subsecaoCpcDTO!=null)
            subSecaoDTOList.add(subsecaoCpcDTO);
        if(secaoMagistrado!=null && secaoMagistrado.getListaSubSecoes()!= null && !secaoMagistrado.getListaSubSecoes().isEmpty())
            subSecaoDTOList.addAll(secaoMagistrado.getListaSubSecoes());
        
        if(subSecaoDTOList!=null && !subSecaoDTOList.isEmpty()){
            for(SubSecaoDTO subsecaoDTO : subSecaoDTOList){
                for(ProcessoConclusoDTO processoConclusoDTO : subsecaoDTO.getListaProcessosConclusosDeletarSubsequentes()){
                    processoConclusoDAO.deletarProcessosConclusosSubsequentes(new ProcessoConcluso(new Unidade(formularioDTO.getIdUnidade()), 
                                                                                                   new Usuario(subsecaoDTO.getIdMagistrado()), 
                                                                                                   processoConclusoDTO.getAno(), processoConclusoDTO.getMes(),
                                                                                                   processoConclusoDTO.getNumeroProcesso(),
                                                                                                   processoConclusoDTO.getSrcFormulario()));
                }
                for(ProcessoConclusoDTO processoConclusoDTO : subsecaoDTO.getListaProcessosConclusosDeletarAtualESubsequentes()){
                    processoConclusoDAO.deletarProcessosConclusosAtualESubsequentes(new ProcessoConcluso(new Unidade(formularioDTO.getIdUnidade()), 
                                                                                                   new Usuario(subsecaoDTO.getIdMagistrado()), 
                                                                                                   processoConclusoDTO.getAno(), processoConclusoDTO.getMes(),
                                                                                                   processoConclusoDTO.getNumeroProcesso(),
                                                                                                   processoConclusoDTO.getSrcFormulario()));
                }
                for(ProcessoConclusoDTO processoConclusoDTO : subsecaoDTO.getListaProcessosConclusos()){
                    processoConclusoDTOMap.put(processoConclusoDTO.getId(), processoConclusoDTO);
                    /*ProcessoConcluso pc = FormularioConverter.parseProcessoConclusoDTOParaProcessoConcluso(processoConclusoDTO);
                    if(processoConclusoDTO.getIdMagistradoProcesso() == null || processoConclusoDTO.getIdMagistradoProcesso().longValue() < 1)
                        pc.setUsuario(null);
                    if(processoConclusoDTO.getDataBaixa() != null && processoConclusoDTO.getDtDataBaixa() == null)
                        pc.setDtDataBaixa(Calendar.getInstance().getTime());
                    processoConclusoDAO.salvar(pc);*/
                }
            }
        }
        
        if(processoConclusoDTOMap != null && !processoConclusoDTOMap.isEmpty()){
            for(ProcessoConclusoDTO processoConclusoDTO : processoConclusoDTOMap.values()){
                ProcessoConcluso pc = FormularioConverter.parseProcessoConclusoDTOParaProcessoConcluso(processoConclusoDTO);
                if(processoConclusoDTO.getIdMagistradoProcesso() == null || processoConclusoDTO.getIdMagistradoProcesso().longValue() < 1)
                    pc.setUsuario(null);
                processoConclusoDAO.salvar(pc);
            }
        }
        
        if(secaoReus!=null){
            for(SubSecaoDTO subsecaoDTO : secaoReus.getListaSubSecoes()){
                for(ReuDTO reuDTO : subsecaoDTO.getListaReusHistoricoDeletar()){ 
                    reuProvisorioHistoricoDAO.deletarHistoricoReu(new ReuProvisorioHistorico(new ReuProvisorio(reuDTO.getIdReuProvisorio())));
                    reuProvisorioDAO.deletarReuProvisorio(new ReuProvisorio(reuDTO.getIdReuProvisorio()));
                }
                for(ReuDTO reuDTO : subsecaoDTO.getListaReus()){
                    reuProvisorioDAO.salvar(FormularioConverter.parseReuDTOParaReuProvisorio(reuDTO));
                }
            }
        }
        return FormularioConverter.parseFormularioParaFormularioDTO(formularioDAO.salvar(FormularioConverter.parseFormularioDTOParaFormulario(formularioDTO, false)));
    }

    public void atualizarMagistradosFormularios(List<FormularioDTO> listaFormularios, Usuario usuario){
        for(FormularioDTO form:listaFormularios){
            form.setIdMagistrado(usuario.getIdUsuario());
            form.setNomeMagistrado(usuario.getNome());
            salvarFormulario(form, null, null, null);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<TipoConclusoDTO> listarTipoProcessoConclusoPorDescricao() {
        return FormularioConverter.parseListaTipoConclusoParaListaProcessoConclusoDTO(tipoConclusoDAO.listarTipoConclusoPorDescricao());
    }
    
    @Override
    public TipoConclusoDTO obterTipoConclusoPorId(Long Id) {
        TipoConcluso tipoConcluso = tipoConclusoDAO.procurarPorId(Id);
        return FormularioConverter.parseTipoConclusoParaProcessoConclusoDTO(tipoConcluso);
    }

    @Override
    public List<FormularioDTO> listarFormulariosGeral(List<String> listaTipoSituacao, FormularioDTO filtro) {
        // RECUPERA OS FORMULARIOS
        List<Formulario> listaFormularios = new ArrayList<Formulario>();
        if (ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(filtro.getPerfilConsulta())) {
            PermissaoUnidadeTemporaria permissao = new PermissaoUnidadeTemporaria();
            Usuario usuario = new Usuario();
            usuario.setNome(filtro.getNomeUsuarioPreenchimento());
            permissao.setUsuario(usuario);
            List<PermissaoUnidadeTemporaria> listaPermissoes = permissaoUnidadeTemporariaDAO.listarComFiltro(permissao);
            if (listaPermissoes != null && listaPermissoes.size() > 0) {
                listaFormularios =
                    formularioDAO.listarFormularioGeral(FormularioConverter.parseFormularioDTOParaFormulario(filtro,
                                                                                                             false),
                                                        listaTipoSituacao, listaPermissoes);
            }
        } else {
            listaFormularios =
                formularioDAO.listarFormularioGeral(FormularioConverter.parseFormularioDTOParaFormulario(filtro, false),
                                                    listaTipoSituacao);
        }
        return FormularioConverter.parseListaFormularioParaListaFormularioDTO(listaFormularios);
    }

    @Override
        public List<FormularioDTO> listarFormulariosGeralComPaginacao(List<String> listaTipoSituacao, FormularioDTO filtro,
                                                                      Paginacao paginacao, Usuario usuarioLogado) {
            // RECUPERA OS FORMULARIOS
            List<Formulario> listaFormularios = new ArrayList<Formulario>();
            if (ConstantesMovjud.PERFIL_COD_FUNCIONARIO.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                PermissaoUnidadeTemporaria permissao = new PermissaoUnidadeTemporaria();
                permissao.setUsuario(usuarioLogado);
                List<PermissaoUnidadeTemporaria> listaPermissoes =
                    permissaoUnidadeTemporariaDAO.listarPermissaoUnidadeTemporariaPorUsuarioEDataAtual(permissao);
                if (listaPermissoes != null && listaPermissoes.size() > 0) {
                    listaFormularios =
                        formularioDAO.listarFormularioGeralComPaginacaoPermissao(FormularioConverter.parseFormularioDTOParaFormulario(filtro,
                                                                                                                             false),
                                                                        paginacao, listaTipoSituacao, listaPermissoes);
                }
            } else if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
                Unidade unidade = new Unidade();
                unidade.setUsuario(usuarioLogado);
                List<Unidade> listaUnidade = unidadeDAO.listarComFiltro(unidade);
                    
                if (listaUnidade != null && listaUnidade.size() > 0) {
                    listaFormularios =
                        formularioDAO.listarFormularioGeralComPaginacaoUnidade(FormularioConverter.parseFormularioDTOParaFormulario(filtro,
                                                                                                                             false),
                                                                        paginacao, listaTipoSituacao, listaUnidade);
                }
            } else {
                listaFormularios =
                    formularioDAO.listarFormularioGeralComPaginacao(FormularioConverter.parseFormularioDTOParaFormulario(filtro,
                                                                                                                         false),
                                                                    paginacao, listaTipoSituacao);
            }
            return FormularioConverter.parseListaFormularioParaListaFormularioDTO(listaFormularios, true);
        }


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<SituacaoFormularioDTO> listarTipoSituacao() {
        return FormularioConverter.parseListaTipoSituacaoParaSituacaoFormularioDTO(tipoSituacaoDAO.listar());
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PreCarga> listarCamposPreCarga(PreCarga preCarga) {
        return preCargaDAO.listarComFiltro(preCarga);
    }

    @Override
    public void liberarFormulariosTodasUnidades(IndicadorProgressoUtil indicadorProgresso) {
        // <epr> 1.Criar instância fora do loop
        SituacaoFormularioDTO situacaoFormularioDTO = TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(
                                                            listarTipoSituacao(), TipoSituacaoType.ABERTO);
        // </epr>
        List<Unidade> listaUnidades = unidadeDAO.listarUnidadesComVinculo();
        for(Unidade unidade : listaUnidades){
            // <epr> 2. Passar instância criada fora do loop
            liberarFormulariosParaUnidade(unidade, situacaoFormularioDTO);
            // <epr/>
            indicadorProgresso.setValorAtual(indicadorProgresso.getValorAtual()+1);
        }
    }

    @Override
    public void liberarFormulariosParaUnidade(Unidade unidade, SituacaoFormularioDTO situacaoFormularioDTO) {
        List<Formulario> listaFormulariosUnidade = listarFormulariosMesAnoReferenciaDaUnidade(unidade);
        FormularioDTO novoFormulario = null;
        int numForms = unidade.getFormularios() == null ? 0 : unidade.getFormularios().size();
        int numFormsVinc = unidade.getFormulariosVinculacao() == null ? 0 : unidade.getFormulariosVinculacao().size();
        for(FormularioVinculacao formularioVinculacao : unidade.getFormulariosVinculacao()){
            if(formularioVinculacao.getMetadadosFormulario().getMetadadosTipoSituacao().getTipoSituacao().equals(MetadadosTipoSituacaoType.EM_USO.getCodigo())){
                boolean novo = true;
                if(listaFormulariosUnidade!=null){
                    for(Formulario formulario : listaFormulariosUnidade){
                        if(formulario.getMetadadosFormulario().getDescricaoSourceFormulario().equals(formularioVinculacao.getMetadadosFormulario().getDescricaoSourceFormulario())) {
                            novo = false;
                            
                            // 20180524-1
                            List<Secao> listaSecoesPrincipais = new ArrayList<Secao>();
                            for (Secao secao : formulario.getSecoes()) {
                                if (secao.getSecaoPai() == null || secao.getSecaoPai().equals(secao)) {
                                    listaSecoesPrincipais.add(secao);
                                }
                            }
                            formulario.setSecoes(listaSecoesPrincipais);
                            // /20180524-1
                            
                            novoFormulario = FormularioConverter.parseFormularioParaFormularioDTO(formulario);
                            //novoFormulario = FormularioConverter.parseFormularioParaFormularioDTOliberacao(formulario);
                            
                            // 20180524-2
                            novoFormulario.setListaSecoes(FormularioConverter.parseListaSecoesParaListaSecoesDTO(formulario.getSecoes(),
                                                                                            novoFormulario.getListaSecoes(),
                                                                                            novoFormulario));
                            // /20180524-2
                            break;
                        }
                    }
                }
                if(novo){
                    Formulario formulario = new Formulario(formularioVinculacao.getMetadadosFormulario(), unidade);
                    MetadadosFormulario metadadosFormulario = formularioVinculacao.getMetadadosFormulario();
                    
                    // 20180528-1
                    novoFormulario = FormularioConverter.parseFormularioParaFormularioDTO(formulario);
                    List<SecaoDTO> listaSecoesPrincipais = new ArrayList<SecaoDTO>();
                    for (MetadadosSecao secao : metadadosFormulario.getMetadadosSecoes()) {
                        SecaoDTO secaoDTO = FormularioConverter.parseEntidadeSecaoParaSecaoDTO(secao, novoFormulario);
                        listaSecoesPrincipais.add(secaoDTO);
                    }
                    novoFormulario.setListaSecoes(listaSecoesPrincipais);
                    // /20180528-1
                    
                    
                    //novoFormulario = FormularioConverter.parseFormularioParaFormularioDTOliberacao(formulario);
                    
                    /*
                    // 20180524-4
                    novoFormulario.setListaSecoes(FormularioConverter.parseListaSecoesParaListaSecoesDTO(formulario.getSecoes(),
                                                                                    novoFormulario.getListaSecoes(),
                                                                                    novoFormulario));
                    // /20180524-4
                    */
                    
                    if(situacaoFormularioDTO == null) {
                        novoFormulario.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(
                                                            listarTipoSituacao(), TipoSituacaoType.ABERTO));
                    } else {
                        novoFormulario.setNovaSituacaoFormulario(/* <epr> 3. comentado: TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(
                                                                listarTipoSituacao(), TipoSituacaoType.ABERTO)*/ situacaoFormularioDTO);                        
                    }
                    List<PreCarga> listaCamposPreCarga = listarCamposPreCarga(new PreCarga(novoFormulario.getAno().intValue(), null, null, novoFormulario.getCodigoFormulario(), null, novoFormulario.getMes().intValue(), unidade, null, null));
                    novoFormulario = incluirValoresPreCarga(novoFormulario, listaCamposPreCarga);
                    formularioDAO.salvar(FormularioConverter.parseFormularioDTOParaFormulario(novoFormulario, false));
                } else  {
                    novoFormulario.setNovaSituacaoFormulario(TipoSituacaoType.recuperarSituacaoFormularioPorCodigo(
                                                            listarTipoSituacao(), TipoSituacaoType.ABERTO));
                    novoFormulario.setIdUnidade(unidade.getIdUnidade());
                    formularioDAO.salvar(FormularioConverter.parseFormularioDTOParaFormulario(novoFormulario, false));
                }
            }
        }
    }

    public static FormularioDTO incluirValoresPreCarga(FormularioDTO formularioDTO, List<PreCarga> listaCamposPreCarga){
        if(listaCamposPreCarga != null && !listaCamposPreCarga.isEmpty()) {
            for(SecaoDTO secaoDTO : formularioDTO.getListaSecoes()){
                for(SubSecaoDTO subSecaoDTO : secaoDTO.getListaSubSecoes()){
                    for(GrupoDTO grupoDTO : subSecaoDTO.getListaGrupos()){
                        // <epr> teste com otimizacao por stream.search
                        //  incluirValoresPreCargaCampos(grupoDTO.getListaCampos(), listaCamposPreCarga);
                        incluirValoresPreCargaCamposSearch(grupoDTO.getListaCampos(), listaCamposPreCarga);
                        // </epr>
                    }
                }
            }
        }
        return formularioDTO;
    }
    
    @Override
    public SubSecaoDTO incluirValoresPreCarga(SubSecaoDTO subSecaoDTO, List<PreCarga> listaCamposPreCarga){
        if(listaCamposPreCarga!=null && !listaCamposPreCarga.isEmpty()){
            for(GrupoDTO grupoDTO : subSecaoDTO.getListaGrupos()){
                incluirValoresPreCargaCampos(grupoDTO.getListaCampos(), listaCamposPreCarga);
            }
        }
        return subSecaoDTO;
    }
    
    public static List<CampoDTO> incluirValoresPreCargaCampos(List<CampoDTO> listaCampos, List<PreCarga> listaCamposPreCarga){
        for(CampoDTO campoDTO : listaCampos){
            for(PreCarga preCarga : listaCamposPreCarga){
                if(campoDTO.getCodigoBI()!=null && preCarga.getCodigoDominioBi()!=null && campoDTO.getCodigoBI().equals(preCarga.getCodigoDominioBi())){
                    campoDTO.setValorCampo(Long.toString(preCarga.getValorCampo()));
                }
            }
            if(campoDTO.getListaCampos()!=null && !campoDTO.getListaCampos().isEmpty())incluirValoresPreCargaCampos(campoDTO.getListaCampos(), listaCamposPreCarga);
        }        
        return listaCampos;
    }
    
    public static List<CampoDTO> incluirValoresPreCargaCamposSearch(List<CampoDTO> listaCampos, List<PreCarga> listaCamposPreCarga) {
        for(CampoDTO campoDTO : listaCampos) {
            Optional<PreCarga> preCarga = listaCamposPreCarga.stream().filter(p -> p.getCodigoDominioBi() != null && p.getCodigoDominioBi().equals(campoDTO.getCodigoBI())).findFirst();
            if(preCarga.isPresent()){
                campoDTO.setValorCampo(Long.toString(preCarga.get().getValorCampo()));
                continue;
            }
            if(campoDTO.getListaCampos() != null && !campoDTO.getListaCampos().isEmpty()) {
                incluirValoresPreCargaCamposSearch(campoDTO.getListaCampos(), listaCamposPreCarga);
            }
        }
        return listaCampos;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public LiberacaoFormularioDTO countFormulariosLiberados() {
        LiberacaoFormularioDTO totalizadores = new LiberacaoFormularioDTO();
        totalizadores.setNumeroMetadadosFormularioEmUso(metadadosFormularioDAO.countTotalMetadadosFormulariosEmUso());
        totalizadores.setNumeroUnidadesVinculadas(formularioVinculacaoDAO.countTotalUnidadesVinculadas());
        totalizadores.setNumeroTotalFormulariosVinculados(formularioVinculacaoDAO.countTotalFormulariosVinculados());
        totalizadores.setNumeroFormulariosAnoMesReferencia(formularioDAO.countFormulariosAnoMesReferencia(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH)));
        totalizadores.setNumeroUnidadesXFormularios(totalizadores.getNumeroTotalFormulariosVinculados()-totalizadores.getNumeroFormulariosAnoMesReferencia());
        return totalizadores;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ProcessoConclusoDTO> listarProcessosConclusosMagistradoPorUnidade(ProcessoConclusoDTO processoConclusoDTO) {
        List<ProcessoConcluso> listaProcessosConclusosCompleta = new ArrayList<ProcessoConcluso>();
        List<BigDecimal> listaNumeroProcessos = null;
        ProcessoConcluso filtroProcesso = FormularioConverter.parseProcessoConclusoDTOParaProcessoConcluso(processoConclusoDTO);
        List<ProcessoConcluso> processosConclusosMesAtual = processoConclusoDAO.listarProcessosConclusosAnoMesReferencia(filtroProcesso);
        if(processosConclusosMesAtual!=null){
            listaNumeroProcessos = new ArrayList<BigDecimal>();
            for(ProcessoConcluso processoConclusoMesAtual : processosConclusosMesAtual){
                listaNumeroProcessos.add(processoConclusoMesAtual.getNumeroProcesso());
            }
            listaProcessosConclusosCompleta.addAll(processosConclusosMesAtual);
        }
        List<ProcessoConcluso> processosConclusosMesAnterior = processoConclusoDAO.listarProcessosConclusosMesAnterior(filtroProcesso, listaNumeroProcessos);

        if(processosConclusosMesAnterior != null){
            for(ProcessoConcluso processoConclusoMesAnterior : processosConclusosMesAnterior){
                processoConclusoMesAnterior.setMes(processoConclusoDTO.getMes());
                processoConclusoMesAnterior.setIdProcessoConcluso(null);
                listaProcessosConclusosCompleta.add(processoConclusoMesAnterior);
            }
        }
        return FormularioConverter.parseListaProcessosConclusosParaListaProcessosConclusosDTO(listaProcessosConclusosCompleta);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ProcessosConclusosCpcDTO listarProcessosConclusosPorUnidade(ProcessoConclusoDTO processoConclusoDTO) {
        List<ProcessoConcluso> listaProcessosConclusosCompletaUnidade = new ArrayList<ProcessoConcluso>();
        List<BigDecimal> listaNumeroProcessos = null;
        ProcessoConcluso filtroProcesso = FormularioConverter.parseProcessoConclusoDTOParaProcessoConcluso(processoConclusoDTO);
        List<ProcessoConcluso> processosConclusosMesAtualUnidade = processoConclusoDAO.listarProcessosConclusosAnoMesReferenciaUnidade(filtroProcesso);
        
        if(processosConclusosMesAtualUnidade!=null){
            listaNumeroProcessos = new ArrayList<BigDecimal>();
            for(ProcessoConcluso processoConclusoMesAtualUnidade : processosConclusosMesAtualUnidade){
                listaNumeroProcessos.add(processoConclusoMesAtualUnidade.getNumeroProcesso());
            }
            listaProcessosConclusosCompletaUnidade.addAll(processosConclusosMesAtualUnidade);
        }
        List<ProcessoConcluso> processosConclusosMesAnteriorUnidade = processoConclusoDAO.listarProcessosConclusosMesAnteriorUnidade(filtroProcesso, listaNumeroProcessos);
        
        HashMap <String, Integer> countProcessosConclusosAnoMesReferenciaUnidade = processoConclusoDAO.countProcessosConclusosAnoMesReferenciaUnidade(filtroProcesso, listaNumeroProcessos);
        List<TipoFilaProcesso> listaTipoFilaProcesso = processoConclusoDAO.listarTipoFilaProcesso();
        
        if(processosConclusosMesAnteriorUnidade != null){
            for(ProcessoConcluso processoConclusoMesAnteriorUnidade : processosConclusosMesAnteriorUnidade){
                processoConclusoMesAnteriorUnidade.setMes(processoConclusoDTO.getMes());
                processoConclusoMesAnteriorUnidade.setIdProcessoConcluso(null);
                listaProcessosConclusosCompletaUnidade.add(processoConclusoMesAnteriorUnidade);
            }
        }
        
        List<ProcessoConclusoDTO> processoConclusoDTOList = FormularioConverter.parseListaProcessosConclusosParaListaProcessosConclusosDTO(listaProcessosConclusosCompletaUnidade);
        
        ProcessosConclusosCpcDTO pcCpc = new ProcessosConclusosCpcDTO();
        pcCpc.setListaProcessosConclusos(processoConclusoDTOList);
        pcCpc.setListaTipoFilaProcesso(countProcessosConclusosAnoMesReferenciaUnidade);
        pcCpc.setListaTipoFilaProcessoDTO(FormularioConverter.parseListaTipoFilaProcessoParaListaTipoFilaProcessoDTO(listaTipoFilaProcesso));
        
        return pcCpc;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ProcessoConclusoDTO> listarProcessosConclusosMesAnterior(ProcessoConclusoDTO filtroProcesso,
                                                                         List<BigDecimal> listaNumeroProcessos) {
        return FormularioConverter.parseListaProcessosConclusosParaListaProcessosConclusosDTO(processoConclusoDAO.listarProcessosConclusosMesAnterior(FormularioConverter.parseProcessoConclusoDTOParaProcessoConcluso(filtroProcesso), listaNumeroProcessos));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Usuario> listarMagistradosProcessosConclusosUnidade(Long unidade, Integer ano, Integer mes, String sourceFormulario) {
        List<Usuario> listaCompletaMagistrado = new ArrayList<Usuario>();
        ProcessoConcluso processoConcluso = new ProcessoConcluso();
        processoConcluso.setUnidade(new Unidade(unidade));
        processoConcluso.setAno(ano);
        processoConcluso.setMes(mes);
        processoConcluso.setSourceFormulario(sourceFormulario);
        List<Usuario> listaMagistradosUnidade = processoConclusoDAO.listarMagistradosComProcessosConclusosNaUnidade(processoConcluso);
        List<Long> idsMagistrados = null;
        if(listaMagistradosUnidade!=null){
            idsMagistrados = new ArrayList<Long>();
            for(Usuario magistrado : listaMagistradosUnidade){
                idsMagistrados.add(magistrado.getIdUsuario());
            }
            listaCompletaMagistrado.addAll(listaMagistradosUnidade);
        }
        List<Usuario> listaMagistradosUnidadeMesAnterior = processoConclusoDAO.listarMagistradosComProcessosConclusosNaUnidadeMesAnterior(processoConcluso, idsMagistrados);
        if(listaMagistradosUnidadeMesAnterior!=null){
            for(Usuario magistradoMesAnterior : listaMagistradosUnidadeMesAnterior){
                listaCompletaMagistrado.add(magistradoMesAnterior);
            }
        }
        return listaCompletaMagistrado;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public boolean validarPeriodoConclusoNoMesAnoReferencia(PeriodoProcessoConcluso filtro) {
        boolean retorno = false;
        List<PeriodoProcessoConcluso> processosConclusos = periodoProcessoConclusoDAO.listarComFiltro(filtro);
        if(processosConclusos!=null && processosConclusos.size()>0){
            retorno = true;
        }
        return retorno;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public PeriodoProcessoConcluso recuperarPeriodoProcessoConclusoPorAnoMes(PeriodoProcessoConcluso filtro) {
        PeriodoProcessoConcluso processosConcluso = periodoProcessoConclusoDAO.procurarComFiltro(filtro);
        return processosConcluso;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public PeriodoProcessoConcluso recuperarAnoMesPorPeriodoProcessoConcluso(PeriodoProcessoConcluso filtro) {
        PeriodoProcessoConcluso processosConcluso = periodoProcessoConclusoDAO.procurarComFiltro(filtro);
        return processosConcluso;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ProcessoConclusoDTO> listarProcessosConclusosMesesSubsequentes(ProcessoConclusoDTO filtroProcesso) {
        return FormularioConverter.parseListaProcessosConclusosParaListaProcessosConclusosDTO(processoConclusoDAO.listarProcessosConclusosMesesSubsequentes(new ProcessoConcluso(
                                                                                                                                                                new Unidade(filtroProcesso.getIdUnidadeProcesso()),
                                                                                                                                                                new Usuario(filtroProcesso.getIdMagistradoProcesso()),
                                                                                                                                                                filtroProcesso.getAno(),
                                                                                                                                                                filtroProcesso.getMes(),
                                                                                                                                                                filtroProcesso.getNumeroProcesso(),
                                                                                                                                                                filtroProcesso.getSrcFormulario())
                                                                                                                                                            ));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<FormularioDTO> listarFormulariosComFiltros(List<Long> listaAno, List<Long> listaMes, Long idUnidade, String descricaoSourceFormulario) {
        
        return FormularioConverter.parseListaFormularioParaListaFormularioDTO(formularioDAO.listarFormulariosComFiltros(listaAno, listaMes, idUnidade, descricaoSourceFormulario));
    }

    @Override
    public List<ReuDTO> listarReusHistoricoMesAnoReferencia(List<ReuDTO> listaReusDTO, FormularioDTO formularioDTO) {
        if(listaReusDTO!=null){
            for(ReuDTO reuDTO : listaReusDTO){
                List<ReuProvisorioHistorico> listaReuProvisorioHistorico = reuProvisorioHistoricoDAO.listarComFiltro(new ReuProvisorioHistorico(formularioDTO.getMes().intValue(), formularioDTO.getAno().intValue(), new ReuProvisorio(reuDTO.getIdReuProvisorio())));
                reuDTO = FormularioConverter.parseListaReuProvisoriosParaReuDTOMesAnoReferencia(reuDTO, listaReuProvisorioHistorico);
            }
        }
        return listaReusDTO;
    }

    @Override
    public boolean validarReuHistoricoMesesAnteriores(ReuProvisorioHistorico reuHist) {
        boolean retorno = false;
        if(reuProvisorioHistoricoDAO.countReusHistoricoMesesAnterior(reuHist)>=1L)retorno = true;
        return retorno;
    }
    
    @Override
    public List<ReuDTO> listarReusProvisorioUnidade(ReuProvisorio filtro, Integer ano, Integer mes){
        return FormularioConverter.parseListaReusProvisoriosParaListaReusDTO(reuProvisorioDAO.listarReusProvisoriosUnidade(filtro, ano, mes),
                                                                             ano, mes);
    }

    @Override
    public Long countUtilizacaoCampo(CampoDTO filtro) {
        Long count = metadadosCampoDAO.countUtilizacaoCampo(new MetadadosCampo(filtro.getIdMetadadosCampo()));
        return count;
    }

    @Override
    public FormularioDTO recuperarFormularioMesAnterior(FormularioDTO formularioAtualDTO) {
        /*
        Formulario formularioAtual = FormularioConverter.parseFormularioDTOParaFormulario(formularioAtualDTO, false);
        FormularioDTO formularioMesAnterior = FormularioConverter.parseFormularioParaFormularioDTO(formularioDAO.recuperarFormularioMesAnterior(formularioAtual));
        if(formularioMesAnterior != null)
            formularioMesAnterior.setFutureListaSecoes(asyncCompleteFormularioDTO(formularioMesAnterior));
        return formularioMesAnterior;
        */
        FormularioDTO formularioMesAnterior = FormularioConverter.parseFormularioParaFormularioDTO(
            formularioDAO.recuperarFormularioMesAnterior(formularioAtualDTO.getCodigoFormulario(), 
                                                         formularioAtualDTO.getIdUnidade(),
                                                         formularioAtualDTO.getMes(),
                                                         formularioAtualDTO.getAno())
        );

        if(formularioMesAnterior != null)
            formularioMesAnterior.setFutureListaSecoes(asyncCompleteFormularioDTO(formularioMesAnterior));

        return formularioMesAnterior;
    }

    @Override
    public FormularioDTO recuperarFormularioMesAnoReferencia(FormularioDTO filtro) {
        Formulario filtroFormulario = formularioDAO.recuperarFormularioAnoMesReferencia(FormularioConverter.parseFormularioDTOParaFormulario(filtro, false));
        FormularioDTO formularioDTO = FormularioConverter.parseFormularioParaFormularioDTO(filtroFormulario);
        formularioDTO.setFutureListaSecoes(asyncCompleteFormularioDTO(formularioDTO));
        return formularioDTO;
    }

    @Override
    public FormularioDTO recuperarPrimeiroFormularioUnidade(FormularioDTO filtro) {
        Formulario filtroFormulario = formularioDAO.recuperarPrimeiroFormularioUnidade(FormularioConverter.parseFormularioDTOParaFormulario(filtro, false));
        FormularioDTO formularioDTO = FormularioConverter.parseFormularioParaFormularioDTO(filtroFormulario);
        formularioDTO.setFutureListaSecoes(asyncCompleteFormularioDTO(formularioDTO));
        return formularioDTO;
    }
    
    @Override
    public Formulario recuperarFormularioPorIdFormulario(Long idFormulario) {
        return formularioDAO.recuperarFormularioPorIdFormulario(idFormulario);
    }
    
    @Override
    public FormularioDTO recuperarFormularioDTOPorIdFormulario(Long idFormulario) {
        Formulario formulario = recuperarFormularioPorIdFormulario(idFormulario);
        FormularioDTO formularioDTO = FormularioConverter.parseFormularioParaFormularioDTO(formulario);
        formularioDTO.setFutureListaSecoes(asyncCompleteFormularioDTO(formularioDTO));        
        formularioDTO.setFutureListaHistoricoFormulario(asyncCompleteHistoricoFormularioDTO(formularioDTO));

        return formularioDTO;
    }

    @Override
    public boolean dominioBIExistente(CampoDTO dominioBI) {
        boolean retorno = true;
        if(dominioBI.getCodigoBI() !=null && !dominioBI.getCodigoBI().isEmpty()){
            List<MetadadosGrupo> listaMetadadosGrupo = metadadosGrupoDAO.listarComFiltro(new MetadadosGrupo(dominioBI.getCodigoBI()));
            List<MetadadosCampo> listaMetadadosCampo = metadadosCampoDAO.listarComFiltro(new MetadadosCampo(dominioBI.getCodigoBI()));
            if((listaMetadadosGrupo !=null && !listaMetadadosGrupo.isEmpty()) || (listaMetadadosCampo !=null && !listaMetadadosCampo.isEmpty())){
                if(listaMetadadosCampo !=null){
                    for(MetadadosCampo metadadosCampo : listaMetadadosCampo){
                        if(metadadosCampo.getIdMetadadosCampo().equals(dominioBI.getIdMetadadosCampo())){
                            retorno = false;
                        }
                    }
                }
            }else{
                retorno = false;
            }
        }else{
            retorno = false;
        }
        return retorno;
    }

    @Override
    public boolean dominioBIExistente(GrupoDTO dominioBI) {
        boolean retorno = true;
        if(dominioBI.getDominioBI() !=null && !dominioBI.getDominioBI().isEmpty()){
            
            List<MetadadosGrupo> listaMetadadosGrupo = metadadosGrupoDAO.listarComFiltro(new MetadadosGrupo(dominioBI.getDominioBI()));
            List<MetadadosCampo> listaMetadadosCampo = metadadosCampoDAO.listarComFiltro(new MetadadosCampo(dominioBI.getDominioBI()));
            if((listaMetadadosGrupo !=null && !listaMetadadosGrupo.isEmpty()) || (listaMetadadosCampo !=null && !listaMetadadosCampo.isEmpty())){
                if(listaMetadadosGrupo!=null){
                    for(MetadadosGrupo metadadosGrupo : listaMetadadosGrupo){
                        if(metadadosGrupo.getIdMetadadosGrupo().equals(dominioBI.getIdMetadadosGrupo())){
                            retorno = false;
                        }
                    }
                }
            }else{
                retorno = false;
            }
        }else{
            retorno = false;
        }
        return retorno;
    }
    
    @Override
    public void updateSituacaoFormulario(Long idFormulario, Long idSituacaoAntiga, Long idSituacaoNova, Long idUsuario, String motivo) {
        formularioDAO.updateSituacaoFormulario(idFormulario, idSituacaoAntiga, idSituacaoNova, idUsuario, motivo);
    }

    @Asynchronous
    public Future<List<SecaoDTO>> asyncCompleteFormularioDTO(FormularioDTO formularioDTO) {
        List<Secao> listaSecoesPrincipais = new ArrayList<Secao>();
        Formulario formulario = recuperarFormularioPorIdFormulario(formularioDTO.getIdFormulario());
        List<SecaoDTO> result = null; //new ArrayList<SecaoDTO>();
        
        for (Secao secao : formulario.getSecoes()) {
            if (secao.getSecaoPai() == null || secao.getSecaoPai().equals(secao)) {
                listaSecoesPrincipais.add(secao);
            }
        }
        
        formulario.setSecoes(listaSecoesPrincipais);
        MetadadosFormulario metadadosFormulario = formulario.getMetadadosFormulario();

        //Verifica se o metadados do formulario esta em cache, caso esteja o utiliza para nao refazer uma 
        //nova consulta ao banco de dados
        //result = FormularioCache.obtemSecaoMetaDadosEmCache(metadadosFormulario.getIdMetadadosFormulario());
        
        if (result == null && metadadosFormulario.getMetadadosSecoes() != null) {
            result = FormularioConverter.parseListaMetadadosSecaoParaListaSecaoDTO(metadadosFormulario.getMetadadosSecoes(),
                            formularioDTO);

            //Adiciona metadados do formulario em cache para melhor performance na proxima consulta ao mesmo, ressaltando
            //que um metadado de um formulario nao sofre alteracao constante
            //FormularioCache.adicionaSecaoMetaDadosEmCache(metadadosFormulario.getIdMetadadosFormulario(), result);
        }

        /*
        if (result == null && metadadosFormulario.getMetadadosSecoes() != null) {
            int size = metadadosFormulario.getMetadadosSecoes().size();
            for(int j = 0; j < size ; j++) {
                result.add(metadadosFormulario.getMetadadosSecoes().get(j).createSecaoDTO(formularioDTO));
            }           
            
            //Adiciona metadados do formulario em cache para melhor performance na proxima consulta ao mesmo, ressaltando
            //que um metadado de um formulario nao sofre alteracao constante
            FormularioCache.adicionaSecaoMetaDadosEmCache(metadadosFormulario.getIdMetadadosFormulario(), result);
        }     
        */

        result = FormularioConverter.parseListaSecoesParaListaSecoesDTO(formulario.getSecoes(), result, formularioDTO);
        
        return new AsyncResult<List<SecaoDTO>>(result);
    }

    @Asynchronous
    public Future<List<HistoricoFormularioDTO>> asyncCompleteHistoricoFormularioDTO(FormularioDTO formularioDTO) {
        
        //List<HistoricoFormularioDTO> historicos = FormularioCache.obtemHistoricoAlteracoesEmCache(formularioDTO.getIdFormulario());
        List<HistoricoFormularioDTO> historicos = null;
        
        if (historicos == null) {
            Formulario formulario = recuperarFormularioPorIdFormulario(formularioDTO.getIdFormulario());
            if (formulario != null) {
                historicos = FormularioConverter.parseListaFormularioHistoricoParaListaHistoricoFormularioDTO(formulario.getFormulariosHistorico());
                
                //FormularioCache.adicionaHistoricoAlteracoesEmCache(formularioDTO.getIdFormulario(), historicos);
            }
        }
        
        if (historicos == null) 
            historicos = new ArrayList<HistoricoFormularioDTO>();
        
        return new AsyncResult<List<HistoricoFormularioDTO>>(historicos);
    }

    public FormularioDTO recuperarMetadadosFormulario(FormularioDTO formularioDTO) {
        List<SecaoDTO> secoes = new ArrayList<SecaoDTO>();
        MetadadosFormulario metadadosFormulario =
            metadadosFormularioDAO.procurarPorId(formularioDTO.getIdMetadadosFormulario());
        List<Secao> listaSecoesPrincipais = new ArrayList<Secao>();
        if (metadadosFormulario.getMetadadosSecoes() != null) {
            secoes =
                FormularioConverter.parseListaMetadadosSecaoParaListaSecaoDTO(metadadosFormulario.getMetadadosSecoes(),
                                                                              formularioDTO);
            formularioDTO.getListaSecoes().addAll(secoes);
        }
        return formularioDTO;
    }    
}
