package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.text.SimpleDateFormat;

import java.util.Date;

public class HistoricoFormularioDTO extends BaseDTO<Long> implements Comparable<HistoricoFormularioDTO> {
    
    private Long idFormularioHistorico;
    
    private Long idFormulario;
    
    private SituacaoFormularioDTO situacaoFormularioDTO;
    
    private Long idUsuario;
    
    private String nomeUsuario;
    
    private Date dataCriacao;
    
    private String descricaoComentario;
    
    public HistoricoFormularioDTO() {
        super();
    }


    public void setIdFormularioHistorico(Long idFormularioHistorico) {
        this.idFormularioHistorico = idFormularioHistorico;
    }

    public Long getIdFormularioHistorico() {
        return idFormularioHistorico;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setSituacaoFormularioDTO(SituacaoFormularioDTO situacaoFormularioDTO) {
        this.situacaoFormularioDTO = situacaoFormularioDTO;
    }

    public SituacaoFormularioDTO getSituacaoFormularioDTO() {
        return situacaoFormularioDTO;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }
    
    public String getDataCriacaoFormatada(){
        return new SimpleDateFormat("dd/MM/yyy HH:mm").format(dataCriacao);
    }

    public void setDescricaoComentario(String descricaoComentario) {
        this.descricaoComentario = descricaoComentario;
    }

    public String getDescricaoComentario() {
        return descricaoComentario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    @Override
    public void setId(Long id) {
        setIdFormularioHistorico(id);
    }

    @Override
    public Long getId() {
        return getIdFormularioHistorico();
    }

    @Override
    public int compareTo(HistoricoFormularioDTO o) {
        return dataCriacao.compareTo(o.getDataCriacao());
    }
}
