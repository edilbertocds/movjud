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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_SECAO")
public class Secao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -850323764782147916L;
    
    @Id
    @Column(name = "ID_CAD_SECAO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_SECAO")    
    @SequenceGenerator(name = "SEQ_CAD_SECAO", sequenceName = "SEQ_CAD_SECAO", allocationSize = 1)    
    private Long idSecao;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_SECAO")
    private MetadadosSecao metadadosSecao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_SECAO_PAI")
    private Secao secaoPai;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_MATERIA")
    private TipoMateria tipoMateria;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario magistrado;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_FORMULARIO")
    private Formulario formulario;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Column(name = "TP_JUIZ", length = 30)
    private String tipoJuiz;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @OneToMany(mappedBy = "secao", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<FormularioForoOrigem> formularioForosOrigem;
    
    @OneToMany(mappedBy = "secao", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<Grupo> grupos;
    
    @OneToMany(mappedBy = "secaoPai", cascade = { CascadeType.ALL },orphanRemoval = true)
    @OrderBy("idSecao ASC")
    private List<Secao> subSecoes;

   // @OneToMany(mappedBy = "secao", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    //private List<ProcessoConcluso> processosConclusos;

    @OneToMany(mappedBy = "secao", cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) 
    private List<InspecaoEstabelecimentoPrisional> inspecoesEstabelecimentoPrisional;
    
    //@OneToMany(mappedBy = "secao", cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) 
    //private List<ReuProvisorio> reusProvisorios;

    public Secao() {
    }
    
    public Secao(Long id) {
        this.idSecao = id;
    }

    public Secao(Long idSecao, MetadadosSecao metadadosSecao, Formulario formulario, String flagTipoSituacao,
                 Date dataInclusao, Date dataAtualizacao, TipoMateria tipoMateria, Usuario magistrado) {
        super();
        this.idSecao = idSecao;
        this.metadadosSecao = metadadosSecao;
        this.formulario = formulario;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.magistrado = magistrado;
        this.tipoMateria = tipoMateria;
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setMetadadosSecao(MetadadosSecao metadadosSecao) {
        this.metadadosSecao = metadadosSecao;
    }

    public MetadadosSecao getMetadadosSecao() {
        return metadadosSecao;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Formulario getFormulario() {
        return formulario;
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

    public void setFormularioForosOrigem(List<FormularioForoOrigem> formularioForosOrigem) {
        this.formularioForosOrigem = formularioForosOrigem;
    }

    public List<FormularioForoOrigem> getFormularioForosOrigem() {
        if(formularioForosOrigem == null){
            formularioForosOrigem = new ArrayList<FormularioForoOrigem>();
        }
        return formularioForosOrigem;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }


    public void setSecaoPai(Secao secaoPai) {
        this.secaoPai = secaoPai;
    }

    public Secao getSecaoPai() {
        return secaoPai;
    }

    public void setTipoJuiz(String tipoJuiz) {
        this.tipoJuiz = tipoJuiz;
    }

    public String getTipoJuiz() {
        return tipoJuiz;
    }

    public void setInspecoesEstabelecimentoPrisional(List<InspecaoEstabelecimentoPrisional> inspecoesEstabelecimentoPrisional) {
        this.inspecoesEstabelecimentoPrisional = inspecoesEstabelecimentoPrisional;
    }

    public List<InspecaoEstabelecimentoPrisional> getInspecoesEstabelecimentoPrisional() {
        if(inspecoesEstabelecimentoPrisional==null){
            inspecoesEstabelecimentoPrisional = new ArrayList<InspecaoEstabelecimentoPrisional>();
        }
        return inspecoesEstabelecimentoPrisional;
    }

    public void setSubSecoes(List<Secao> subSecoes) {
        this.subSecoes = subSecoes;
    }

    public List<Secao> getSubSecoes() {
        if(subSecoes==null){
            subSecoes = new ArrayList<Secao>();
        }
        return subSecoes;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    

    public List<Grupo> getGrupos() {
        if(grupos==null){
            grupos = new ArrayList<Grupo>();
        }
        return grupos;
    }


    public Grupo addGrupo(Grupo grupo) {
        getGrupos().add(grupo);
        grupo.setSecao(this);
        return grupo;
    }

    public Grupo removeGrupo(Grupo grupo) {
        getGrupos().remove(grupo);
        grupo.setSecao(null);
        return grupo;
    }

    @Override
    public Long getId() {
        return getIdSecao();
    }

    @Override
    public void setId(Long id) {
        setIdSecao(id);
    }

    public void setTipoMateria(TipoMateria tipoMateria) {
        this.tipoMateria = tipoMateria;
    }

    public TipoMateria getTipoMateria() {
        return tipoMateria;
    }

    public void setMagistrado(Usuario magistrado) {
        this.magistrado = magistrado;
    }

    public Usuario getMagistrado() {
        return magistrado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Secao = ");
        sb.append(idSecao);
        sb.append("\n");
        
        if (metadadosSecao != null) {
            sb.append("Nome Secao = ");
            sb.append(metadadosSecao.getDescricaoNome());
            sb.append("\n");
        }
        
        if (formulario != null && formulario.getMetadadosFormulario() != null) {
            sb.append("Nome Formulario = ");
            sb.append(formulario.getMetadadosFormulario().getDescricaoNome());
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
        
        if (magistrado != null) {
            sb.append("Magistrado = ");
            sb.append(magistrado.getNome());
        }
        
        return sb.toString();
    }
}
