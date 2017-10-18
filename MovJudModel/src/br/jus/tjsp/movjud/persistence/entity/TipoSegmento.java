package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@NamedQueries({ @NamedQuery(name = "TipoSegmento.findAll", query = "select o from TipoSegmento o") })
@Table(name = "TIPO_SEGMENTO")
public class TipoSegmento extends BaseEntity<Long> {
    private static final long serialVersionUID = 7675677184991832600L;
    @Column(name = "DS_SEGMENTO", length = 100)
    private String descricaoSegmento;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    @Id
    @Column(name = "ID_TIPO_SEGMENTO", nullable = false)
    private Long idTipoSegmento;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    public TipoSegmento() {
    }

    public TipoSegmento(String dsSegmento, Date dtAtualizacao, Date dtInclusao, Long idTipoSegmento,
                        String tpSituacao) {
        this.descricaoSegmento = dsSegmento;
        this.dataAtualizacao = dtAtualizacao;
        this.dataInclusao = dtInclusao;
        this.idTipoSegmento = idTipoSegmento;
        this.flagTipoSituacao = tpSituacao;
    }

    public String getDescricaoSegmento() {
        return descricaoSegmento;
    }

    public void setDescricaoSegmento(String descricaoSegmento) {
        this.descricaoSegmento = descricaoSegmento;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dtAtualizacao) {
        this.dataAtualizacao = dtAtualizacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dtInclusao) {
        this.dataInclusao = dtInclusao;
    }

    public Long getIdTipoSegmento() {
        return idTipoSegmento;
    }

    public void setIdTipoSegmento(Long idTipoSegmento) {
        this.idTipoSegmento = idTipoSegmento;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    @Override
    public void setId(Long id) {
        setIdTipoSegmento(id);
    }

    @Override
    public Long getId() {
        return getIdTipoSegmento();
    }
}
