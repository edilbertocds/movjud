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
import javax.persistence.FetchType;
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
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_COMARCA")
public class Comarca extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -4642455693859114441L;
    
    @Id
    @Column(name = "ID_CAD_COMARCA", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_COMARCA")  
    @SequenceGenerator(name = "SEQ_CAD_COMARCA", sequenceName = "SEQ_CAD_COMARCA", allocationSize = 1)
    private Long idComarca;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CAD_CIRCUNSCRICAO", nullable = false)
    private Circunscricao circunscricao;
    
    @Column(name = "NM_COMARCA", length = 100, nullable = false)
    private String nomeComarca;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CRIACAO", nullable = false)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataFim;
    
    @OneToMany(mappedBy = "comarca", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Foro> foros;
    
    public Comarca() {
    }

    public Comarca(Long idComarca, Circunscricao circunscricao, String nomeComarca, String flagTipoSituacao,
                   Date dataAtualizacao, Date dataInclusao, Date dataCriacao, Date dataFim) {
        super();
        this.idComarca = idComarca;
        this.circunscricao = circunscricao;
        this.nomeComarca = nomeComarca;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.dataCriacao = dataCriacao;
        this.dataFim = dataFim;
    }

    public void setIdComarca(Long idComarca) {
        this.idComarca = idComarca;
    }

    public Long getIdComarca() {
        return idComarca;
    }

    public void setCircunscricao(Circunscricao circunscricao) {
        this.circunscricao = circunscricao;
    }

    public Circunscricao getCircunscricao() {
        return circunscricao;
    }

    public void setNomeComarca(String nomeComarca) {
        this.nomeComarca = nomeComarca;
    }

    public String getNomeComarca() {
        return nomeComarca;
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

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setForos(List<Foro> foros) {
        this.foros = foros;
    }

    public List<Foro> getForos() {
        return foros;
    }

    @Override
    public Long getId() {
        return getIdComarca();
    }

    @Override
    public void setId(Long id) {
        setIdComarca(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Comarca = ");
        sb.append(idComarca);
        sb.append("\n");
        
        sb.append("Nome Comarca = ");
        sb.append(nomeComarca);
        sb.append("\n");
        
        if (circunscricao != null) {
            sb.append("Circunscricao = ");
            sb.append(circunscricao.getNomeCircunscricao());
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
            sb.append("\n");
        }
        
        if (dataCriacao != null) {
            sb.append("Data Criacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataCriacao));
            sb.append("\n");
        }
        
        if (dataFim != null) {
            sb.append("Data Fim = ");
            sb.append(ModelUtils.formatarDataToStr(dataFim));
        }
        
        return sb.toString();
    }
}
