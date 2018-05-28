package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "TIPO_FILA_PROCESSO")
public class TipoFilaProcesso extends BaseEntity<Long> {
    @Id
    @Column(name = "ID_TIPO_FILA_PROCESSO", nullable = false)
    private Long idTipoFilaProcesso;
    //private Long id;
        
    @Column(name = "DS_TIPO_FILA_PROCESSO")
    private String dsTipoFilaProcesso;
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_INCLUSAO", nullable = false)
    private Date dtInclusao;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tpSituacao;
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_ATUALIZACAO")
    private Date dtAtualizacao;

    public TipoFilaProcesso() {
    }
    
    public TipoFilaProcesso(Long idTipoFilaProcesso) {
        this.idTipoFilaProcesso = idTipoFilaProcesso;
    }

    public Long getId() {
        return idTipoFilaProcesso;
    }

    public void setId(Long idTipoFilaProcesso) {
        this.idTipoFilaProcesso = idTipoFilaProcesso;
    }

    public String getDsTipoFilaProcesso() {
        return dsTipoFilaProcesso;
    }

    public void setDsTipoFilaProcesso(String dsTipoFilaProcesso) {
        this.dsTipoFilaProcesso = dsTipoFilaProcesso;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDataInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getTpSituacao() {
        return tpSituacao;
    }

    public void setTpSituacao(String tpSituacao) {
        this.tpSituacao = tpSituacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDataAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }
}
