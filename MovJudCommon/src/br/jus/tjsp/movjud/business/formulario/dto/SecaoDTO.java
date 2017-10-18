package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecaoDTO extends BaseDTO<String> implements Comparable<SecaoDTO>{
    @SuppressWarnings("compatibility:8695884097885371680")
    private static final long serialVersionUID = 4542493249191257766L;
    //@SuppressWarnings("compatibility:-6491215303879054340")
    //private static final long serialVersionUID = 1L;

    private String codigoSecao;
    private Long idMetadadosSecao;
    private Long idSecao;
    private String codigoMagistrado;
    private String labelMagistrado;
    private String informativoMagistrado;
    private boolean conclusos;
    private boolean totalizadores;
    private String codigoCNJ;
    private boolean tabelaProcessos;
    private boolean reus;
    private String tituloReus;
    private String textoInformativo;
    private String labelReus;
    private boolean tipoInternacao;
    private boolean tipoPrisional;
    private String labelSecao;
    private Long ordemSecao;
    private List<GrupoDTO> listaGrupos;
    private List<TipoMateriaDTO> listaMaterias;
    private List<SubSecaoDTO> listaSubSecoes;
    private FormularioDTO formulario;

    public SecaoDTO() {
        listaGrupos = new ArrayList<GrupoDTO>();
        listaMaterias = new ArrayList<TipoMateriaDTO>();
        listaSubSecoes = new ArrayList<SubSecaoDTO>();
    }
    
    public SecaoDTO(SecaoType secao) {
        this();
        codigoSecao = secao.getCodigoSecao();
        labelSecao = secao.getLabelSecao();
        ordemSecao = secao.getOrdemSecao();
    }
    
    public SecaoDTO(SecaoDTO secao) {
        this();
        this.codigoSecao = secao.codigoSecao;
        this.idMetadadosSecao = secao.idMetadadosSecao;
        this.idSecao = secao.idSecao;
        this.codigoMagistrado = secao.codigoMagistrado;
        this.labelMagistrado = secao.labelMagistrado;
        this.informativoMagistrado = secao.informativoMagistrado;
        this.conclusos = secao.conclusos;
        this.totalizadores = secao.totalizadores;
        this.codigoCNJ = secao.codigoCNJ;
        this.tabelaProcessos = secao.tabelaProcessos;
        this.reus = secao.reus;
        this.tituloReus = secao.tituloReus;
        this.labelReus = secao.labelReus;
        this.tipoInternacao = secao.tipoInternacao;
        this.tipoPrisional = secao.tipoPrisional;
        this.labelSecao = secao.labelSecao;
        this.ordemSecao = secao.ordemSecao;
        this.listaGrupos.addAll(secao.listaGrupos);
        this.listaMaterias.addAll(secao.listaMaterias);
        this.listaSubSecoes.addAll(secao.listaSubSecoes);
        this.textoInformativo = secao.textoInformativo;
        setDataInclusao(secao.getDataInclusao());
        setSituacao(secao.getSituacao());
    }

    public void setValuesSecaoMagistrado(SecaoDTO secao) {
        this.codigoMagistrado = secao.codigoMagistrado;
        this.labelMagistrado = secao.labelMagistrado;
        this.informativoMagistrado = secao.informativoMagistrado;
        this.conclusos = secao.conclusos;
        this.totalizadores = secao.totalizadores;
        this.tabelaProcessos = secao.tabelaProcessos;
    }
    
    public void setValuesSecaoEstabelecimentoPrisional(SecaoDTO secao) {
        this.tipoInternacao = secao.tipoInternacao;
        this.tipoPrisional = secao.tipoPrisional;
    }

    public void setValues(SecaoDTO secao) {
        this.codigoSecao = secao.codigoSecao;
        this.idMetadadosSecao = secao.idMetadadosSecao;
        this.idSecao = secao.idSecao;
        this.codigoMagistrado = secao.codigoMagistrado;
        this.labelMagistrado = secao.labelMagistrado;
        this.informativoMagistrado = secao.informativoMagistrado;
        this.conclusos = secao.conclusos;
        this.totalizadores = secao.totalizadores;
        this.codigoCNJ = secao.codigoCNJ;
        this.tabelaProcessos = secao.tabelaProcessos;
        this.reus = secao.reus;
        this.tituloReus = secao.tituloReus;
        this.labelReus = secao.labelReus;
        this.tipoInternacao = secao.tipoInternacao;
        this.tipoPrisional = secao.tipoPrisional;
        this.labelSecao = secao.labelSecao;
        this.ordemSecao = secao.ordemSecao;
        this.listaGrupos.addAll(secao.listaGrupos);
        this.listaMaterias.addAll(secao.listaMaterias);
        this.listaSubSecoes.addAll(secao.listaSubSecoes);
        this.textoInformativo = secao.textoInformativo;
    }

    public void setCodigoSecao(String codigoSecao) {
        this.codigoSecao = codigoSecao;
    }

    public String getCodigoSecao() {
        return codigoSecao;
    }

    public void setFormulario(FormularioDTO formulario) {
        this.formulario = formulario;
    }

    public FormularioDTO getFormulario() {
        return formulario;
    }

    public void setTipoInternacao(boolean tipoInternacao){
        this.tipoInternacao = tipoInternacao;
    }

    public boolean isTipoInternacao() {
        return tipoInternacao;
    }

    public void setTipoPrisional(boolean tipoPrisional) {
        this.tipoPrisional = tipoPrisional;
    }

    public boolean isTipoPrisional() {
        return tipoPrisional;
    }


    public void setReus(boolean reus) {
        this.reus = reus;
    }

    public boolean isReus() {
        return reus;
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setTituloReus(String tituloReus) {
        this.tituloReus = tituloReus;
    }

    public String getTituloReus() {
        return tituloReus;
    }

    public void setLabelReus(String labelReus) {
        this.labelReus = labelReus;
    }

    public String getLabelReus() {
        return labelReus;
    }


    public void setLabelSecao(String labelSecao) {
        this.labelSecao = labelSecao;
    }

    public String getLabelSecao() {
        return labelSecao;
    }

    public void setOrdemSecao(Long ordemSecao) {
        this.ordemSecao = ordemSecao;
    }

    public Long getOrdemSecao() {
        return ordemSecao;
    }

    public void setListaGrupos(List<GrupoDTO> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<GrupoDTO> getListaGrupos() {
        if(listaGrupos==null){
            listaGrupos = new ArrayList<GrupoDTO>();
        }
        return listaGrupos;
    }

    @Override
    public void setId(String id) {
        this.codigoSecao = id;
    }

    @Override
    public String getId() {
        return codigoSecao;
    }

    public void setTabelaProcessos(boolean tabelaProcessos) {
        this.tabelaProcessos = tabelaProcessos;
    }

    public boolean isTabelaProcessos() {
        return tabelaProcessos;
    }
    
    public void setCodigoMagistrado(String codigoMagistrado) {
        this.codigoMagistrado = codigoMagistrado;
    }

    public String getCodigoMagistrado() {
        return codigoMagistrado;
    }

    public void setLabelMagistrado(String labelMagistrado) {
        this.labelMagistrado = labelMagistrado;
    }

    public String getLabelMagistrado() {
        return labelMagistrado;
    }

    public void setInformativoMagistrado(String informativoMagistrado) {
        this.informativoMagistrado = informativoMagistrado;
    }

    public String getInformativoMagistrado() {
        return informativoMagistrado;
    }

    public void setConclusos(boolean conclusos) {
        this.conclusos = conclusos;
    }

    public boolean isConclusos() {
        return conclusos;
    }

    public void setTotalizadores(boolean totalizadores) {
        this.totalizadores = totalizadores;
    }

    public boolean isTotalizadores() {
        return totalizadores;
    }

    public void setCodigoCNJ(String codigoCNJ) {
        this.codigoCNJ = codigoCNJ;
    }

    public String getCodigoCNJ() {
        return codigoCNJ;
    }

    public void setListaMaterias(List<TipoMateriaDTO> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<TipoMateriaDTO> getListaMaterias() {
        if(listaMaterias==null){
            listaMaterias = new ArrayList<TipoMateriaDTO>();
        }
        Collections.sort(listaMaterias);
        return listaMaterias;
    }

    public void setIdMetadadosSecao(Long idEntidade) {
        this.idMetadadosSecao = idEntidade;
    }

    public Long getIdMetadadosSecao() {
        return idMetadadosSecao;
    }

    public void setTextoInformativo(String textoInformativo) {
        this.textoInformativo = textoInformativo;
    }

    public String getTextoInformativo() {
        return textoInformativo;
    }

    @Override
    public int compareTo(SecaoDTO o) {
        return ordemSecao.compareTo(o.ordemSecao);
    }

    public void setListaSubSecoes(List<SubSecaoDTO> listaSubSecoes) {
        if(listaSubSecoes==null){
            listaSubSecoes = new ArrayList<SubSecaoDTO>();
        }
        this.listaSubSecoes = listaSubSecoes;
    }

    public List<SubSecaoDTO> getListaSubSecoes() {
        if(listaSubSecoes==null){
            listaSubSecoes = new ArrayList<SubSecaoDTO>();
        }
        return listaSubSecoes;
    }
    
    public SecaoType getTipoSecao() {
        return SecaoType.getSecaoByCodigo(getCodigoSecao());
    }

}
