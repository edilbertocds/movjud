package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.util.Date;

public class TipoFilaProcessoDTO extends BaseDTO<Long>{
    @SuppressWarnings("compatibility:510167935208131200")
    private static final long serialVersionUID = 1L;
    
    private Long idTipoFilaProcesso;
    //private Long id;
    private Integer version;
    private String dsTipoFilaProcesso;
    private Date dtInclusao;
    private String tpSituacao;
    private Date dtAtualizacao;
    
    public TipoFilaProcessoDTO() {
        super();
    }


    public void setId(Long idTipoFilaProcesso) {
        this.idTipoFilaProcesso = idTipoFilaProcesso;
    }

    public Long getId() {
        return idTipoFilaProcesso;
    }

    public void setDsTipoFilaProcesso(String dsTipoFilaProcesso) {
        this.dsTipoFilaProcesso = dsTipoFilaProcesso;
    }

    public String getDsTipoFilaProcesso() {
        return dsTipoFilaProcesso;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setTpSituacao(String tpSituacao) {
        this.tpSituacao = tpSituacao;
    }

    public String getTpSituacao() {
        return tpSituacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }
}
