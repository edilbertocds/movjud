package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

import java.io.Serializable;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@NamedQueries({ @NamedQuery(name = "FormularioTipoRegra.findAll", query = "select o from FormularioTipoRegra o") })
@Table(name = "CAD_FORM_TIPO_REGRA")
public class FormularioTipoRegra implements Serializable {
    private static final long serialVersionUID = -4373671151086213007L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dtAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", nullable = false)
    private Date dtInclusao;
    @Id
    @Column(name = "ID_CAD_FORM_TIPO_REGRA", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORM_TIPO_REGRA")  
    @SequenceGenerator(name = "SEQ_CAD_FORM_TIPO_REGRA", sequenceName = "SEQ_CAD_FORM_TIPO_REGRA", allocationSize = 1)
    private Long idCadFormTipoRegra;
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tpSituacao;
    @ManyToOne
    @JoinColumn(name = "FK_CAD_FORMULARIO")
    private Formulario formulario4;
    @ManyToOne
    @JoinColumn(name = "FK_MD_TIPO_REGRA")
    private MetadadosTipoRegra mdTipoRegra2;

    public FormularioTipoRegra() {
    }

    public FormularioTipoRegra(Date dtAtualizacao, Date dtInclusao, Formulario formulario4,
                               MetadadosTipoRegra mdTipoRegra2, Long idCadFormTipoRegra, String tpSituacao) {
        this.dtAtualizacao = dtAtualizacao;
        this.dtInclusao = dtInclusao;
        this.formulario4 = formulario4;
        this.mdTipoRegra2 = mdTipoRegra2;
        this.idCadFormTipoRegra = idCadFormTipoRegra;
        this.tpSituacao = tpSituacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }


    public Long getIdCadFormTipoRegra() {
        return idCadFormTipoRegra;
    }

    public void setIdCadFormTipoRegra(Long idCadFormTipoRegra) {
        this.idCadFormTipoRegra = idCadFormTipoRegra;
    }

    public String getTpSituacao() {
        return tpSituacao;
    }

    public void setTpSituacao(String tpSituacao) {
        this.tpSituacao = tpSituacao;
    }

    public Formulario getFormulario4() {
        return formulario4;
    }

    public void setFormulario4(Formulario formulario4) {
        this.formulario4 = formulario4;
    }

    public MetadadosTipoRegra getMdTipoRegra2() {
        return mdTipoRegra2;
    }

    public void setMdTipoRegra2(MetadadosTipoRegra mdTipoRegra2) {
        this.mdTipoRegra2 = mdTipoRegra2;
    }
}
