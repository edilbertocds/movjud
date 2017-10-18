package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_PERFIL")
@SequenceGenerator(name = "SEQ_CAD_PERFIL", sequenceName = "SEQ_CAD_PERFIL", allocationSize = 1)
public class Perfil extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 7647397672435613178L;
    
    @Id
    @Column(name = "ID_CAD_PERFIL", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAD_PERFIL")
    private Long idPerfil;
    
    @Column(name = "DS_SRC_PERFIL", nullable = false, length = 10)
    private String codigoPerfil;
    
    @Column(name = "NM_PERFIL", nullable = false, length = 100)
    private String nomePerfil;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;    

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "CAD_PERFIL_ACAO",
               joinColumns = { @JoinColumn(name = "FK_CAD_PERFIL",
                                           referencedColumnName =
                                           "ID_CAD_PERFIL") },
               inverseJoinColumns =
               { @JoinColumn(name = "FK_TIPO_ACAO", referencedColumnName =
                             "ID_TIPO_ACAO") })
    private List<Acao> acoes;

    public Perfil() {
    }

    public Perfil(Long idPerfil, String codigoPerfil, String nomePerfil, String flagTipoSituacao, Date dataAtualizacao, Date dataInclusao) {
        this.idPerfil = idPerfil;
        this.codigoPerfil = codigoPerfil;
        this.nomePerfil = nomePerfil;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }
    
    @Override
    public Long getId() {
        return getIdPerfil();
    }

    @Override
    public void setId(Long id) {
        setIdPerfil(id);
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setCodigoPerfil(String codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public String getNomePerfil() {
        return nomePerfil;
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

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Perfil = ");
        sb.append(idPerfil);
        sb.append("\n");
        
        sb.append("Codigo Perfil = ");
        sb.append(codigoPerfil);
        sb.append("\n");
        
        sb.append("Nome Perfil = ");
        sb.append(nomePerfil);
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
