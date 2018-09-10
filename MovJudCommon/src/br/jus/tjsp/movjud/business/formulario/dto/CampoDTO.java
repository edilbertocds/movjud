package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CampoDTO extends BaseDTO<String> implements Comparable<CampoDTO> {
    @SuppressWarnings("compatibility:3976547655814284846")
    private static final long serialVersionUID = 3382176891880375633L;
    //@SuppressWarnings("compatibility:4410060742051158738")
    //private static final long serialVersionUID = 1L;
    private String codigoCampo;
    private Long idCampo;
    private Long idCampoPai;
    private Long idGrupo;
    private Long idMetadadosCampo;
    private Long idMetadadosGrupoCampo;
    private Long idMetadadosCampoCampo;
    private String labelCampo;
    private String indiceCampo;
    private TipoCampoType tipoCampo;
    private FormulaDTO formula;
    private List<ItemSelecaoDTO> listaItensSelecaoDTO;
    private String codigoBI;
    private Long numeroMinimoCaracteres;
    private Long numeroMaximoCaracteres;
    private String numeroCNJ;
    private Long casasDecimais;
    private List<ValidacaoDTO> listaValidacoes;
    private Long ordemCampo;
    private boolean requerido;
    private boolean desabilitado;
    private String hint;
    private List<CampoDTO> listaCampos;
    private List<CampoDTO> sortedListaCampos;
    private CampoDTO campoPai;
    private GrupoDTO grupo;
    private TipoRegraDTO tipoRegraDTO;
    private String valorCampo;
    private String valorCampoMesAnterior;
    private String descricaoCampoFormula;
    private Integer nivelCampo;
    
    public static int QTDE_ESPACOS_CAMPOS = 5;


    public void setValues(CampoDTO campo) {
	this.listaCampos = new ArrayList<CampoDTO>();
        this.sortedListaCampos = new ArrayList<CampoDTO>();
	this.listaValidacoes = new ArrayList<ValidacaoDTO>();
	this.listaItensSelecaoDTO = new ArrayList<ItemSelecaoDTO>();
	this.codigoCampo = campo.codigoCampo;
	this.idCampo = campo.getIdCampo();
	this.idCampoPai = campo.idCampoPai;
	this.idGrupo = campo.idGrupo;
	this.idMetadadosCampo = campo.idMetadadosCampo;
	this.idMetadadosGrupoCampo = campo.idMetadadosGrupoCampo;
	this.labelCampo = campo.labelCampo;
	this.indiceCampo = campo.indiceCampo;
	this.tipoCampo = campo.tipoCampo;
	this.formula = (FormulaDTO) campo.formula.clonar();
	this.listaItensSelecaoDTO.addAll(campo.getListaItensSelecaoDTO());
	this.codigoBI = campo.codigoBI;
	this.numeroMinimoCaracteres = campo.numeroMinimoCaracteres;
	this.numeroMaximoCaracteres = campo.numeroMaximoCaracteres;
	this.numeroCNJ = campo.numeroCNJ;
	this.casasDecimais = campo.casasDecimais;
	this.listaValidacoes.addAll(campo.getListaValidacoes());
	this.ordemCampo = campo.ordemCampo;
	this.requerido = campo.requerido;
	this.desabilitado = campo.desabilitado;
	this.hint = campo.hint;
	this.listaCampos.addAll(campo.getListaCampos());
        this.sortedListaCampos = doSortListaCampos();
	this.idMetadadosCampoCampo = campo.idMetadadosCampoCampo;
	this.tipoRegraDTO = campo.tipoRegraDTO;
	this.valorCampo = campo.valorCampo;
        this.nivelCampo = nivelCampo;
    }

    public CampoDTO() {
	listaCampos = new ArrayList<CampoDTO>();
        sortedListaCampos = new ArrayList<CampoDTO>();
	listaValidacoes = new ArrayList<ValidacaoDTO>();
	listaItensSelecaoDTO = new ArrayList<ItemSelecaoDTO>();
	formula = new FormulaDTO();
    }
    
    public CampoDTO(String codigoBI) {
        this.codigoBI = codigoBI;
    }

    public CampoDTO(CampoDTO campo) {
	this();
	this.codigoCampo = campo.codigoCampo;
	this.idCampo = campo.getIdCampo();
	this.idCampoPai = campo.idCampoPai;
	this.idGrupo = campo.idGrupo;
	this.idMetadadosCampo = campo.idMetadadosCampo;
	this.idMetadadosGrupoCampo = campo.idMetadadosGrupoCampo;
	this.labelCampo = campo.labelCampo;
	this.indiceCampo = campo.indiceCampo;
	this.tipoCampo = campo.tipoCampo;
        this.formula = (FormulaDTO) campo.formula.clonar();
	this.listaItensSelecaoDTO.addAll(campo.getListaItensSelecaoDTO());
	this.codigoBI = campo.codigoBI;
	this.numeroMinimoCaracteres = campo.numeroMinimoCaracteres;
	this.numeroMaximoCaracteres = campo.numeroMaximoCaracteres;
	this.numeroCNJ = campo.numeroCNJ;
	this.casasDecimais = campo.casasDecimais;
	this.listaValidacoes.addAll(campo.getListaValidacoes());
	this.ordemCampo = campo.ordemCampo;
	this.requerido = campo.requerido;
	this.desabilitado = campo.desabilitado;
	this.hint = campo.hint;
	this.listaCampos.addAll(campo.getListaCampos());
        this.sortedListaCampos = doSortListaCampos();
	this.idMetadadosCampoCampo = campo.idMetadadosCampoCampo;
	this.tipoRegraDTO = campo.tipoRegraDTO;
	this.valorCampo = campo.valorCampo;
	this.valorCampoMesAnterior = campo.valorCampoMesAnterior;
    }

    public boolean isUltimoFilho(){
        boolean retorno = false;
        if(campoPai!=null && campoPai.getCampoPai()!=null && campoPai.getCampoPai().getCampoPai()!=null){
            retorno = true;
        }
        return retorno;
    }

    public void setIdMetadadosCampoCampo(Long idCampoCampoEntidade) {
	this.idMetadadosCampoCampo = idCampoCampoEntidade;
    }

    public Long getIdMetadadosCampoCampo() {
	return idMetadadosCampoCampo;
    }


    public void setFormula(FormulaDTO formula) {
	this.formula = formula;
    }

    public FormulaDTO getFormula() {
	return formula;
    }

    public void setGrupo(GrupoDTO grupo) {
        this.grupo = grupo;
    }

    public GrupoDTO getGrupo() {
        return grupo;
    }

    public GrupoDTO getGrupoRecursivo(){
        GrupoDTO grupoDTO = null;
        if(grupo == null){
            grupoDTO = getGrupoDeCampo(campoPai);
        }else{
            grupoDTO = grupo;
        }
        return grupoDTO;
    }
            
    private GrupoDTO getGrupoDeCampo(CampoDTO campoPai){
        GrupoDTO grupoDTO = null;
        if(campoPai.getGrupo()==null){
            grupoDTO = getGrupoDeCampo(campoPai.getCampoPai());
        }else{
            grupoDTO = campoPai.getGrupo();
        }
        return grupoDTO;
    }

    public void setNumeroMinimoCaracteres(Long numeroMinimoCaracteres) {
	this.numeroMinimoCaracteres = numeroMinimoCaracteres;
    }

    public void setIdCampo(Long idCampo) {
	this.idCampo = idCampo;
    }

    public Long getIdCampo() {
	return idCampo;
    }

    public void setCampoPai(CampoDTO campoPai) {
        this.campoPai = campoPai;
    }

    public CampoDTO getCampoPai() {
        return campoPai;
    }

    public Long getNumeroMinimoCaracteres() {
	return numeroMinimoCaracteres;
    }

    public void setNumeroMaximoCaracteres(Long numeroMaximoCaracteres) {
	this.numeroMaximoCaracteres = numeroMaximoCaracteres;
    }

    public Long getNumeroMaximoCaracteres() {
	return numeroMaximoCaracteres;
    }

    public void setIdCampoPai(Long idCampoPai) {
	this.idCampoPai = idCampoPai;
    }

    public Long getIdCampoPai() {
	return idCampoPai;
    }

    public void setIdGrupo(Long idGrupo) {
	this.idGrupo = idGrupo;
    }

    public Long getIdGrupo() {
	return idGrupo;
    }

    public void setNumeroCNJ(String numeroCNJ) {
	this.numeroCNJ = numeroCNJ;
    }

    public String getNumeroCNJ() {
	return numeroCNJ;
    }

    public void setCasasDecimais(Long casasDecimais) {
	this.casasDecimais = casasDecimais;
    }

    public Long getCasasDecimais() {
	return casasDecimais;
    }

    public void setListaValidacoes(List<ValidacaoDTO> listaValidacoes) {
	this.listaValidacoes = listaValidacoes;
    }

    public List<ValidacaoDTO> getListaValidacoes() {
	if (listaValidacoes == null) {
	    listaValidacoes = new ArrayList<ValidacaoDTO>();
	}
	return listaValidacoes;
    }

    public void setValorCampo(String valorCampo) {
	this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
	return valorCampo;
    }
    
    public String getValorCampoPdf() {
        if( TipoCampoType.LISTA.name().equals(this.tipoCampo.name())){
            for(ItemSelecaoDTO item : listaItensSelecaoDTO){
                if(item.getCodigoItemSelecao().equalsIgnoreCase(valorCampo))
                    return item.getLabelItemSelecao();
            }
        }
        return valorCampo;
    }
    
    public void setValorCampoMesAnterior(String valorCampoMesAnterior) {
	this.valorCampoMesAnterior = valorCampoMesAnterior;
    }

    public String getValorCampoMesAnterior() {
	return valorCampoMesAnterior;
    }

    public void setCodigoBI(String codigoBI) {
	this.codigoBI = codigoBI;
    }

    public String getCodigoBI() {
	return codigoBI;
    }

    public void setHint(String hint) {
	this.hint = hint;
    }

    public String getHint() {
	return hint;
    }

    public void setRequerido(boolean requerido) {
	this.requerido = requerido;
    }

    public boolean isRequerido() {
	return requerido;
    }

    public void setCodigoCampo(String codigoCampo) {
	this.codigoCampo = codigoCampo;
    }

    public String getCodigoCampo() {
	return codigoCampo;
    }

    public void setLabelCampo(String labelCampo) {
	this.labelCampo = labelCampo;
    }

    public String getLabelCampo() {
	return labelCampo;
    }

    public void setTipoCampo(TipoCampoType tipoCampo) {
	this.tipoCampo = tipoCampo;
    }

    public TipoCampoType getTipoCampo() {
	return tipoCampo;
    }

    public void setListaCampos(List<CampoDTO> listaCampos) {
	this.listaCampos = listaCampos;
        this.sortedListaCampos = doSortListaCampos();
    }

    public List<CampoDTO> getListaCampos() {
	if (listaCampos == null) {
	    listaCampos = new ArrayList<CampoDTO>();
	}
	return listaCampos;
    }

    public List<CampoDTO> getSortedListaCampos() {
        return sortedListaCampos;
    }
    
    public List<CampoDTO> doSortListaCampos() {
        ArrayList<CampoDTO> cloneList = new ArrayList<CampoDTO>(getListaCampos());
        Collections.sort(cloneList);
        return cloneList;
    }

    public void setOrdemCampo(Long ordemCampo) {
	this.ordemCampo = ordemCampo;
    }

    public Long getOrdemCampo() {
	return ordemCampo;
    }

    public void setDesabilitado(boolean desabilitado) {
	this.desabilitado = desabilitado;
    }

    public boolean isDesabilitado() {
	return desabilitado;
    }

    public void setIdMetadadosCampo(Long idCampoEntidade) {
	this.idMetadadosCampo = idCampoEntidade;
    }

    public Long getIdMetadadosCampo() {
	return idMetadadosCampo;
    }

    public void setIdMetadadosGrupoCampo(Long idGrupoCampoEntidade) {
	this.idMetadadosGrupoCampo = idGrupoCampoEntidade;
    }

    public Long getIdMetadadosGrupoCampo() {
	return idMetadadosGrupoCampo;
    }

    public void setListaItensSelecaoDTO(List<ItemSelecaoDTO> listaItens) {
	this.listaItensSelecaoDTO = listaItens;
    }

    public List<ItemSelecaoDTO> getListaItensSelecaoDTO() {
	if (listaItensSelecaoDTO == null)
	    listaItensSelecaoDTO = new ArrayList<ItemSelecaoDTO>();
	return listaItensSelecaoDTO;
    }

    @Override
    public void setId(String id) {
	this.codigoCampo = id;
    }

    @Override
    public String getId() {
	return codigoCampo;
    }

    public void setIndiceCampo(String indiceCampo) {
	this.indiceCampo = indiceCampo;
    }

    public String getIndiceCampo() {
	return indiceCampo;
    }

    @Override
    public int compareTo(CampoDTO o) {
	return ordemCampo.compareTo(o.ordemCampo);
    }

    public void setTipoRegraDTO(TipoRegraDTO tipoRegraDTO) {
	this.tipoRegraDTO = tipoRegraDTO;
    }

    public TipoRegraDTO getTipoRegraDTO() {
	return tipoRegraDTO;
    }

    public String getDescricaoCampoFormula() {
        return "[" + getCodigoCampo() + "] - " + getGrupoRecursivo().getSecao().getLabelSecao() + ", " + getGrupoRecursivo().getLabelGrupo() + ", " + getLabelCampo() ;
    }
    
    public String getLabelCampoFormatado(){
        return (indiceCampo == null ? "" : indiceCampo)+ " " + (labelCampo == null ? "" : labelCampo);         
    }

    public Integer getNivelCampo() {
        return nivelCampo;
    }
    
    public void setNivelCampo(Integer nivelCampo) {
        this.nivelCampo = nivelCampo;
    }

    public static void main(String[] args) {
        CampoDTO campo = new CampoDTO();
        campo.setIndiceCampo("1.");
        campo.setLabelCampo("Campo de Entrada");  
        campo.setNivelCampo(1);
        System.out.println(campo.getLabelCampoFormatado());
        campo.setNivelCampo(2);
        System.out.println(campo.getLabelCampoFormatado());
        campo.setNivelCampo(3);
        System.out.println(campo.getLabelCampoFormatado());
    }
}
