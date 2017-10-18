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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_GRUPO")
public class Grupo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 4547856709913947459L;
    
    @Id
    @Column(name = "ID_CAD_GRUPO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_GRUPO")    
    @SequenceGenerator(name = "SEQ_CAD_GRUPO", sequenceName = "SEQ_CAD_GRUPO", allocationSize = 1)
    private Long idGrupo;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_GRUPO")
    private MetadadosGrupo metadadosGrupo;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_SECAO")
    private Secao secao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_SECAO")
    private MetadadosSecao metadadosSecao;
   
    @OneToMany(mappedBy = "grupo", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<Campo> campos;
    

    public Grupo() {
    }

    public Grupo(Long idGrupo, MetadadosGrupo metadadosGrupo, Secao secao, String flagTipoSituacao,
                 Date dataAtualizacao, Date dataInclusao, MetadadosSecao metadadosSecao) {
        super();
        this.idGrupo = idGrupo;
        this.metadadosGrupo = metadadosGrupo;
        this.secao = secao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.metadadosSecao = metadadosSecao;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdGrupo() {
        return idGrupo;
    }

    public void setMetadadosGrupo(MetadadosGrupo metadadosGrupo) {
        this.metadadosGrupo = metadadosGrupo;
    }

    public MetadadosGrupo getMetadadosGrupo() {
        return metadadosGrupo;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Secao getSecao() {
        return secao;
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

    public void setMetadadosSecao(MetadadosSecao metadadosSecao) {
        this.metadadosSecao = metadadosSecao;
    }

    public MetadadosSecao getMetadadosSecao() {
        return metadadosSecao;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public List<Campo> getCampos() {
        if(campos==null){
            campos = new ArrayList<Campo>();
        }
        return campos;
    }

    public Campo addCampo(Campo campo) {
        getCampos().add(campo);
        campo.setGrupo(this);
        return campo;
    }

    public Campo removeCampo(Campo campo) {
        getCampos().remove(campo);
        campo.setGrupo(null);
        return campo;
    }

    @Override
    public Long getId() {
        return getIdGrupo();
    }

    @Override
    public void setId(Long id) {
        setIdGrupo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Grupo = ");
        sb.append(idGrupo);
        sb.append("\n");
        
        if (metadadosGrupo != null) {
            sb.append("Nome Grupo = ");
            sb.append(metadadosGrupo.getDescricaoNome());
            sb.append("\n");
        }
        
        if (secao != null && secao.getMetadadosSecao() != null) {
            sb.append("Secao = ");
            sb.append(secao.getMetadadosSecao().getDescricaoNome());
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
}
