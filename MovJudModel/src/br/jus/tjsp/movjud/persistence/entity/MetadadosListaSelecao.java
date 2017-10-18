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
@NamedQueries({ @NamedQuery(name = "MetadadosListaSelecao.findAll", query = "select o from MetadadosListaSelecao o") })
@Table(name = "MD_LISTA_SELECAO")
public class MetadadosListaSelecao extends BaseEntity<Long> {
    private static final long serialVersionUID = -6796907352812075514L;
    @Column(name = "DS_SELECAO", length = 100)
    private String descricaoSelecao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @ManyToOne
    @JoinColumn(name = "FK_MD_CAMPO")
    private MetadadosCampo metadadosCampo;
    
    @Id
    @Column(name = "ID_MD_LISTA_SELECAO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_LISTA_SELECAO")     
    @SequenceGenerator(name = "SEQ_MD_LISTA_SELECAO", sequenceName = "SEQ_MD_LISTA_SELECAO", allocationSize = 1)   
    private Long idMetadadosListaSelecao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    public MetadadosListaSelecao() {
    }

    public MetadadosListaSelecao(String dsSelecao, Date dtAtualizacao, Date dtInclusao, MetadadosCampo metadadosCampo,
                                 Long idMdListaSelecao, String tpSituacao) {
        this.descricaoSelecao = dsSelecao;
        this.dataAtualizacao = dtAtualizacao;
        this.dataInclusao = dtInclusao;
        this.metadadosCampo = metadadosCampo;
        this.idMetadadosListaSelecao = idMdListaSelecao;
        this.flagTipoSituacao = tpSituacao;
    }


    public void setDescricaoSelecao(String descricaoSelecao) {
        this.descricaoSelecao = descricaoSelecao;
    }

    public String getDescricaoSelecao() {
        return descricaoSelecao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao==null){
            dataInclusao = new Date();
        }
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setMetadadosCampo(MetadadosCampo metadadosCampo) {
        this.metadadosCampo = metadadosCampo;
    }

    public MetadadosCampo getMetadadosCampo() {
        return metadadosCampo;
    }

    public void setIdMetadadosListaSelecao(Long idMetadadosListaSelecao) {
        this.idMetadadosListaSelecao = idMetadadosListaSelecao;
    }

    public Long getIdMetadadosListaSelecao() {
        return idMetadadosListaSelecao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosListaSelecao(id);
    }

    @Override
    public Long getId() {
        return getIdMetadadosListaSelecao();
    }
}
