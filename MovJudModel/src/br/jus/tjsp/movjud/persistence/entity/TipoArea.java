package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

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
@NamedQueries({ @NamedQuery(name = "TipoArea.findAll", query = "select o from TipoArea o") })
@Table(name = "TIPO_AREA")
public class TipoArea extends BaseEntity<Long> {
    private static final long serialVersionUID = -7263053178773376429L;
    @Column(name = "DS_AREA", length = 100)
    private String descricaoArea;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    @Id
    @Column(name = "ID_TIPO_AREA", nullable = false)
    private Long idTipoArea;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    public TipoArea() {
    }

    public TipoArea(String dsArea, Date dtAtualizacao, Date dtInclusao, Long idTipoArea, String tpSituacao) {
	this.descricaoArea = dsArea;
	this.dataAtualizacao = dtAtualizacao;
	this.dataInclusao = dtInclusao;
	this.idTipoArea = idTipoArea;
	this.flagTipoSituacao = tpSituacao;
    }

    public String getDescricaoArea() {
	return descricaoArea;
    }

    public void setDescricaoArea(String descricaoArea) {
	this.descricaoArea = descricaoArea;
    }

    public Date getDataAtualizacao() {
	return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
	this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataInclusao() {
	return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
	this.dataInclusao = dataInclusao;
    }

    public Long getIdTipoArea() {
	return idTipoArea;
    }

    public void setIdTipoArea(Long idTipoArea) {
	this.idTipoArea = idTipoArea;
    }

    public String getFlagTipoSituacao() {
	return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
	this.flagTipoSituacao = flagTipoSituacao;
    }

    @Override
    public void setId(Long id) {
	setIdTipoArea(id);
    }

    @Override
    public Long getId() {
	return getIdTipoArea();
    }
}
