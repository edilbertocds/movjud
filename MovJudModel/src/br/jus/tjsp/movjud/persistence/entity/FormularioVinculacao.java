package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_FORM_VINCULACAO")
public class FormularioVinculacao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -8737941538459816823L;
    
    @Id
    @Column(name = "ID_CAD_FORM_VINCULACAO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORM_VINCULACAO")  
    @SequenceGenerator(name = "SEQ_CAD_FORM_VINCULACAO", sequenceName = "SEQ_CAD_FORM_VINCULACAO", allocationSize = 1)    
    private Long idFormularioVinculacao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE")
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_FORMULARIO")
    private MetadadosFormulario metadadosFormulario;
    
    @Column(name = "DS_REGRAS", length = 2000)
    private String descricaoRegras;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @ManyToMany
    @JoinTable(name = "CAD_FORM_VINC_TIPO_REGRA",
               joinColumns = { @JoinColumn(name = "FK_CAD_FORM_VINCULACAO",
                                           referencedColumnName =
                                           "ID_CAD_FORM_VINCULACAO") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_MD_TIPO_REGRA", referencedColumnName =
                             "ID_MD_TIPO_REGRA") })
    private List<MetadadosTipoRegra> listaMetadadosTipoRegra;

    public FormularioVinculacao() {
	super();    
	
	listaMetadadosTipoRegra = new ArrayList<MetadadosTipoRegra>();
    }

    public FormularioVinculacao(Long idFormularioVinculacao, Unidade unidade, MetadadosFormulario metadadosFormulario,
                                String descricaoRegras, String flagTipoSituacao, Date dataAtualizacao,
                                Date dataInclusao) {
	this();
	
        this.idFormularioVinculacao = idFormularioVinculacao;
        this.unidade = unidade;
        this.metadadosFormulario = metadadosFormulario;
        this.descricaoRegras = descricaoRegras;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdFormularioVinculacao(Long idFormularioVinculacao) {
        this.idFormularioVinculacao = idFormularioVinculacao;
    }

    public Long getIdFormularioVinculacao() {
        return idFormularioVinculacao;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
        this.metadadosFormulario = metadadosFormulario;
    }

    public MetadadosFormulario getMetadadosFormulario() {
        return metadadosFormulario;
    }

    public void setDescricaoRegras(String descricaoRegras) {
        this.descricaoRegras = descricaoRegras;
    }

    public String getDescricaoRegras() {
        return descricaoRegras;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setListaMetadadosTipoRegra(List<MetadadosTipoRegra> listaMetadadosTipoRegra) {
        this.listaMetadadosTipoRegra = listaMetadadosTipoRegra;
    }

    public List<MetadadosTipoRegra> getListaMetadadosTipoRegra() {
        if(listaMetadadosTipoRegra==null){
            listaMetadadosTipoRegra = new ArrayList<MetadadosTipoRegra>();
        }
        return listaMetadadosTipoRegra;
    }

    @Override
    public Long getId() {
        return getIdFormularioVinculacao();
    }

    @Override
    public void setId(Long id) {
        setIdFormularioVinculacao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Formulario Vinculacao = ");
        sb.append(idFormularioVinculacao);
        sb.append("\n");
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        if (metadadosFormulario != null) {
            sb.append("Formulario = ");
            sb.append(metadadosFormulario.getDescricaoNome());
            sb.append("\n");
        }
        
        sb.append("Descricao Regras = ");
        sb.append(descricaoRegras);
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
