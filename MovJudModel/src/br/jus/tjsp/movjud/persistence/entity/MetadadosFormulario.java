package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_FORMULARIO")
public class MetadadosFormulario extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 6571515686554988839L;
    
    @Id
    @Column(name = "ID_MD_FORMULARIO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_FORMULARIO")        
    @SequenceGenerator(name = "SEQ_MD_FORMULARIO", sequenceName = "SEQ_MD_FORMULARIO", allocationSize = 1)    
    private Long idMetadadosFormulario;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_TIPO_SITUACAO")
    private MetadadosTipoSituacao metadadosTipoSituacao;
    
    @Column(name = "DS_NOME", nullable = false, length = 200)
    private String descricaoNome;
    
    @Column(name = "DS_TXT_INFORMATIVO", nullable = false, length = 4000)
    private String descricaoTextoInformativo;
    
    @Column(name = "DS_SRC_FORMULARIO", nullable = false, length = 25)
    private String descricaoSourceFormulario;
    
    @Column(name = "NR_VERSAO", nullable = false)
    private Long numeroVersao;
    
    @Column(name = "NR_INSTANCIA", nullable = false)
    private Long numeroInstancia;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Transient
    private transient Date dataFiltroCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
            
    @OneToMany(mappedBy = "metadadosFormulario", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<MetadadosSecao> metadadosSecoes;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "MD_FORMULARIO_COMPETENCIA",
               joinColumns = { @JoinColumn(name = "FK_MD_FORMULARIO",
                                           referencedColumnName =
                                           "ID_MD_FORMULARIO") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_TIPO_COMPETENCIA", referencedColumnName =
                             "ID_TIPO_COMPETENCIA")})
    private List<TipoCompetencia> tiposCompetencia;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_AREA")
    private TipoArea tipoArea;

    @ManyToOne
    @JoinColumn(name = "FK_TIPO_SEGMENTO")
    private TipoSegmento tipoSegmento;
    
    @OneToMany(mappedBy = "metadadosFormulario", cascade = { CascadeType.ALL })
    private List<Formulario> formularios;
    
    @OneToMany(mappedBy = "metadadosFormulario", cascade = { CascadeType.ALL })
    private List<FormularioVinculacao> formulariosVinculacao;

    public MetadadosFormulario() {
    }
    
    public MetadadosFormulario(String descricaoSourceFormulario) {
        this.descricaoSourceFormulario = descricaoSourceFormulario;
    }

    public MetadadosFormulario(Long idMetadadosFormulario, MetadadosTipoSituacao metadadosTipoSituacao,
                               String descricaoNome,String descricaoSourceFormulario, Long numeroVersao,
                               Long numeroInstancia, String flagTipoSituacao, Date dataInclusao,
                               Date dataAtualizacao, String descricaoTextoInformativo) {
        this.idMetadadosFormulario = idMetadadosFormulario;
        this.metadadosTipoSituacao = metadadosTipoSituacao;
        this.descricaoNome = descricaoNome;
        this.descricaoSourceFormulario = descricaoSourceFormulario;
        this.numeroVersao = numeroVersao;
        this.numeroInstancia = numeroInstancia;
        this.flagTipoSituacao = flagTipoSituacao;
        this.descricaoTextoInformativo = descricaoTextoInformativo;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setTipoArea(TipoArea tipoArea) {
        this.tipoArea = tipoArea;
    }

    public TipoArea getTipoArea() {
        return tipoArea;
    }

    public void setTipoSegmento(TipoSegmento tipoSegmento) {
        this.tipoSegmento = tipoSegmento;
    }

    public TipoSegmento getTipoSegmento() {
        return tipoSegmento;
    }

    public void setIdMetadadosFormulario(Long idMetadadosFormulario) {
        this.idMetadadosFormulario = idMetadadosFormulario;
    }

    public Long getIdMetadadosFormulario() {
        return idMetadadosFormulario;
    }

    public void setMetadadosTipoSituacao(MetadadosTipoSituacao metadadosTipoSituacao) {
        this.metadadosTipoSituacao = metadadosTipoSituacao;
    }

    public MetadadosTipoSituacao getMetadadosTipoSituacao() {
        return metadadosTipoSituacao;
    }

    public void setDescricaoNome(String descricaoNome) {
        this.descricaoNome = descricaoNome;
    }

    public String getDescricaoNome() {
        return descricaoNome;
    }

    public void setDescricaoSourceFormulario(String descricaoSourceFormulario) {
        this.descricaoSourceFormulario = descricaoSourceFormulario;
    }

    public String getDescricaoSourceFormulario() {
        return descricaoSourceFormulario;
    }

    public void setNumeroVersao(Long numeroVersao) {
        this.numeroVersao = numeroVersao;
    }

    public Long getNumeroVersao() {
        return numeroVersao;
    }

    public void setNumeroInstancia(Long numeroInstancia) {
        this.numeroInstancia = numeroInstancia;
    }

    public Long getNumeroInstancia() {
        return numeroInstancia;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao == null){
            dataInclusao = new Date();
        }
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

    public void setMetadadosSecoes(List<MetadadosSecao> metadadosSecoes) {
        if(metadadosSecoes==null){
            metadadosSecoes = new ArrayList<MetadadosSecao>();
        }
        this.metadadosSecoes = metadadosSecoes;
    }

    public List<MetadadosSecao> getMetadadosSecoes() {
        if(metadadosSecoes==null){
            metadadosSecoes = new ArrayList<MetadadosSecao>();
        }
        return metadadosSecoes;
    }

    public MetadadosSecao addMetadadosSecao(MetadadosSecao metadadosSecao) {
        getMetadadosSecoes().add(metadadosSecao);
        metadadosSecao.setMetadadosFormulario(this);
        return metadadosSecao;
    }

    public MetadadosSecao removeMetadadosSecao(MetadadosSecao metadadosSecao) {
        getMetadadosSecoes().remove(metadadosSecao);
        metadadosSecao.setMetadadosFormulario(null);
        return metadadosSecao;
    }


    public void setFormularios(List<Formulario> formularios) {
	this.formularios = formularios;
    }

    public List<Formulario> getFormularios() {
	if(formularios == null){
	    formularios = new ArrayList<Formulario>();
	}	
	return formularios;
    }


    public void setFormulariosVinculacao(List<FormularioVinculacao> formulariosVinculacao) {
	this.formulariosVinculacao = formulariosVinculacao;
    }

    public List<FormularioVinculacao> getFormulariosVinculacao() {
	if(formulariosVinculacao == null){
	    formulariosVinculacao = new ArrayList<FormularioVinculacao>();
	}
	return formulariosVinculacao;
    }

    @Override
    public Long getId() {
        return getIdMetadadosFormulario();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosFormulario(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Metadados Formulario = ");
        sb.append(idMetadadosFormulario);
        sb.append("\n");
        
        if (metadadosTipoSituacao != null) {
            sb.append("Tipo Situacao = ");
            sb.append(metadadosTipoSituacao.getDescricaoSituacao());
            sb.append("\n");
        }
        
        sb.append("Descricao Nome = ");
        sb.append(descricaoNome);
        sb.append("\n");
        
        sb.append("Descricao Texto Informativo = ");
        sb.append(descricaoTextoInformativo);
        sb.append("\n");
        
        sb.append("Descricao Source Formulario = ");
        sb.append(descricaoSourceFormulario);
        sb.append("\n");
        
        sb.append("Numero Versao = ");
        sb.append(numeroVersao);
        sb.append("\n");
        
        sb.append("Numero Instancia = ");
        sb.append(numeroInstancia);
        sb.append("\n");
        
        if (tipoSegmento != null){
            sb.append("Tipo Segmento = ");
            sb.append(tipoSegmento.getDescricaoSegmento());
            sb.append("\n");
        }
        
        if (tipoArea != null){
            sb.append("Tipo Area = ");
            sb.append(tipoArea.getDescricaoArea());
            sb.append("\n");
        }
        
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

    public void setTiposCompetencia(List<TipoCompetencia> tiposCompetencia) {
        this.tiposCompetencia = tiposCompetencia;
    }

    public List<TipoCompetencia> getTiposCompetencia() {
        return tiposCompetencia;
    }

    public void setDescricaoTextoInformativo(String descricaoTextoInformativo) {
        this.descricaoTextoInformativo = descricaoTextoInformativo;
    }

    public String getDescricaoTextoInformativo() {
        return descricaoTextoInformativo;
    }

    public void setDataFiltroCriacao(Date dataFiltroCriacao) {
        this.dataFiltroCriacao = dataFiltroCriacao;
    }

    public Date getDataFiltroCriacao() {
        return dataFiltroCriacao;
    }
}
