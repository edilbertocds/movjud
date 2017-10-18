package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@NamedQueries({ @NamedQuery(name = "TipoCompetencia.findAll", query = "select o from TipoCompetencia o") })
@Table(name = "TIPO_COMPETENCIA")
public class TipoCompetencia extends BaseEntity<Long> {
    private static final long serialVersionUID = -4179563895103651684L;
    @Column(name = "CD_COMPETENCIA_SAJ", nullable = false)
    private Long codigoCompetenciaSaj;
    @Column(name = "DS_COMPETENCIA_SAJ", nullable = false, length = 100)
    private String descricaoCompetenciaSaj;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    @Id
    @Column(name = "ID_TIPO_COMPETENCIA", nullable = false)
    private Long idTipoCompetencia;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    @ManyToMany(mappedBy = "tiposCompetencia")
    private List<MetadadosFormulario> formularios;

    public void setFormularios(List<MetadadosFormulario> formularios) {
        this.formularios = formularios;
    }

    public List<MetadadosFormulario> getFormularios() {
        return formularios;
    }

    public TipoCompetencia() {
    }

    public TipoCompetencia(Long cdCompetenciaSaj, String dsCompetenciaSaj, Date dtAtualizacao, Date dtInclusao,
                           Long idTipoCompetencia, String tpSituacao) {
        this.codigoCompetenciaSaj = cdCompetenciaSaj;
        this.descricaoCompetenciaSaj = dsCompetenciaSaj;
        this.dataAtualizacao = dtAtualizacao;
        this.dataInclusao = dtInclusao;
        this.idTipoCompetencia = idTipoCompetencia;
        this.flagTipoSituacao = tpSituacao;
    }

    public Long getCodigoCompetenciaSaj() {
        return codigoCompetenciaSaj;
    }

    public void setCodigoCompetenciaSaj(Long codigoCompetenciaSaj) {
        this.codigoCompetenciaSaj = codigoCompetenciaSaj;
    }

    public String getDescricaoCompetenciaSaj() {
        return descricaoCompetenciaSaj;
    }

    public void setDescricaoCompetenciaSaj(String descricaoCompetenciaSaj) {
        this.descricaoCompetenciaSaj = descricaoCompetenciaSaj;
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

    public Long getIdTipoCompetencia() {
        return idTipoCompetencia;
    }

    public void setIdTipoCompetencia(Long idTipoCompetencia) {
        this.idTipoCompetencia = idTipoCompetencia;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String tpSituacao) {
        this.flagTipoSituacao = tpSituacao;
    }

    @Override
    public void setId(Long id) {
        setIdTipoCompetencia(id);
    }

    @Override
    public Long getId() {
        return getIdTipoCompetencia();
    }
}
