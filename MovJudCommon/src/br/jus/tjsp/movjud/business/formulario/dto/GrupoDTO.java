package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoDTO extends BaseDTO<String> implements Comparable<GrupoDTO>{
    @SuppressWarnings("compatibility:7228266890802262797")
    private static final long serialVersionUID = -5930018385757672760L;
    //@SuppressWarnings("compatibility:1078604863151382237")
    //private static final long serialVersionUID = 1L;
    private String codigoGrupo;
    private String labelGrupo;
    private Long idMetadadosGrupo;
    private Long idGrupo;
    private Long ordemGrupo;
    private String dominioBI;
    private SubSecaoDTO subSecao;
    private SecaoDTO secao;
    private String textoInformativo;
    private List<CampoDTO> listaCampos;
    private List<CampoDTO> sortedListaCampos;
    private TipoRegraDTO tipoRegraDTO;
    
    public GrupoDTO(GrupoDTO grupoDTO) {
        this.codigoGrupo =grupoDTO.codigoGrupo;
        this.labelGrupo=grupoDTO.labelGrupo;
        this.idMetadadosGrupo=grupoDTO.idMetadadosGrupo;
        this.idGrupo=grupoDTO.idGrupo;
        this.ordemGrupo=grupoDTO.ordemGrupo;
        this.dominioBI=grupoDTO.dominioBI;
        this.tipoRegraDTO=grupoDTO.tipoRegraDTO;
    }
    
    public GrupoDTO() {
        listaCampos = new ArrayList<CampoDTO>();
        sortedListaCampos = new ArrayList<CampoDTO>();
    }
    
    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setLabelGrupo(String labelGrupo) {
        this.labelGrupo = labelGrupo;
    }

    public String getLabelGrupo() {
        return labelGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setSecao(SecaoDTO secao) {
        this.secao = secao;
    }

    public SecaoDTO getSecao() {
        return secao;
    }

    public void setSubSecao(SubSecaoDTO subSecao) {
        this.subSecao = subSecao;
    }

    public SubSecaoDTO getSubSecao() {
        return subSecao;
    }

    public void setListaCampos(List<CampoDTO> listaCampos) {
        this.listaCampos = listaCampos;
        this.sortedListaCampos = doSortListaCampos();
    }

    public List<CampoDTO> getListaCampos() {
        if(listaCampos == null){
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

    public void setOrdemGrupo(Long ordemGrupo) {
        this.ordemGrupo = ordemGrupo;
    }

    public Long getOrdemGrupo() {
        return ordemGrupo;
    }
    

    public void setDominioBI(String dominioBI) {
        this.dominioBI = dominioBI;
    }

    public String getDominioBI() {
        return dominioBI;
    }

    public void setTextoInformativo(String textoInformativo) {
        this.textoInformativo = textoInformativo;
    }

    public String getTextoInformativo() {
        return textoInformativo;
    }


    @Override
    public void setId(String id) {
        this.codigoGrupo = id;
    }

    @Override
    public String getId() {
        return codigoGrupo;
    }

    public void setIdMetadadosGrupo(Long idEntidade) {
        this.idMetadadosGrupo = idEntidade;
    }

    public Long getIdMetadadosGrupo() {
        return idMetadadosGrupo;
    }

    public void setTipoRegraDTO(TipoRegraDTO tipoRegraDTO) {
        this.tipoRegraDTO = tipoRegraDTO;
    }

    public TipoRegraDTO getTipoRegraDTO() {
        return tipoRegraDTO;
    }

    @Override
    public int compareTo(GrupoDTO o) {
        return ordemGrupo.compareTo(o.ordemGrupo);
    }
}
