package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_TIPO_SITUACAO")
public class MetadadosTipoSituacao extends BaseEntity<Long> {

    private static final long serialVersionUID = 3945613970000543059L;

    @Id
    @Column(name = "ID_MD_TIPO_SITUACAO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_TIPO_SITUACAO")
    @SequenceGenerator(name = "SEQ_MD_TIPO_SITUACAO", sequenceName = "SEQ_MD_TIPO_SITUACAO", allocationSize = 1)
    private Long idMetadadosTipoSituacao;

    @Column(name = "DS_SITUACAO", length = 100)
    private String descricaoSituacao;
    
    @Column(name = "CD_TIPO_SITUACAO", length = 50)
    private String tipoSituacao;


    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "metadadosTipoSituacao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<MetadadosFormulario> metadadosFormularios;

    public MetadadosTipoSituacao() {
    }

    public MetadadosTipoSituacao(Long idMetadadosTipoSituacao, String descricaoSituacao, String tipoSituacao, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
	super();
	this.idMetadadosTipoSituacao = idMetadadosTipoSituacao;
	this.descricaoSituacao = descricaoSituacao;
	this.tipoSituacao = tipoSituacao;
	this.flagTipoSituacao = flagTipoSituacao;
	this.dataInclusao = dataInclusao;
	this.dataAtualizacao = dataAtualizacao;
	
    }

    public void setIdMetadadosTipoSituacao(Long idMetadadosTipoSituacao) {
	this.idMetadadosTipoSituacao = idMetadadosTipoSituacao;
    }

    public Long getIdMetadadosTipoSituacao() {
	return idMetadadosTipoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
	this.descricaoSituacao = descricaoSituacao;
    }

    public String getDescricaoSituacao() {
	return descricaoSituacao;
    }

    public void setTipoSituacao(String tipoSituacao) {
	this.tipoSituacao = tipoSituacao;
    }

    public String getTipoSituacao() {
	return tipoSituacao;
    }


    public void setFlagTipoSituacao(String flagTipoSituacao) {
	this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
	return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
	this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
	return dataInclusao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
	this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
	return dataAtualizacao;
    }

    public void setMetadadosFormularios(List<MetadadosFormulario> metadadosFormularios) {
	this.metadadosFormularios = metadadosFormularios;
    }

    public List<MetadadosFormulario> getMetadadosFormularios() {
	return metadadosFormularios;
    }

    public MetadadosFormulario addMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
	getMetadadosFormularios().add(metadadosFormulario);
	metadadosFormulario.setMetadadosTipoSituacao(this);
	return metadadosFormulario;
    }

    public MetadadosFormulario removeMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
	getMetadadosFormularios().remove(metadadosFormulario);
	metadadosFormulario.setMetadadosTipoSituacao(null);
	return metadadosFormulario;
    }

    @Override
    public Long getId() {
	return getIdMetadadosTipoSituacao();
    }

    @Override
    public void setId(Long id) {
	setIdMetadadosTipoSituacao(id);
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("ID Metadados Tipo Situacao = ");
	sb.append(idMetadadosTipoSituacao);
	sb.append("\n");

	sb.append("Descricao Situacao = ");
	sb.append(descricaoSituacao);
	sb.append("\n");

	sb.append("Flag Tipo Situacao = ");
	sb.append(flagTipoSituacao);
	sb.append("\n");

	if (dataAtualizacao != null) {
	    sb.append("Data Atualizacao = ");
	    sb.append(ModelUtils.formatarDataToStr(dataAtualizacao));
	    sb.append("\n");
	}

	if (dataInclusao != null) {
	    sb.append("Data Inclusao = ");
	    sb.append(ModelUtils.formatarDataToStr(dataInclusao));
	}

	return sb.toString();
    }
}
