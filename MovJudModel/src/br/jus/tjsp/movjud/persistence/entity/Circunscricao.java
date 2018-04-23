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
@Table(name = "CAD_CIRCUNSCRICAO")
public class Circunscricao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 230501824830976368L;
    
    @Id
    @Column(name = "ID_CAD_CIRCUNSCRICAO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_CIRCUNSCRICAO")    
    @SequenceGenerator(name = "SEQ_CAD_CIRCUNSCRICAO", sequenceName = "SEQ_CAD_CIRCUNSCRICAO", allocationSize = 1)
    private Long idCircunscricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAD_REGIAO", nullable = false)
    private Regiao regiao;
    
    @Column(name = "NM_CIRCUNSCRICAO", length = 100, nullable = false)
    private String nomeCircunscricao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;    
    
    @OneToMany(mappedBy = "circunscricao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Comarca> comarcas;

    public Circunscricao() {
    }

    public Circunscricao(Long idCircunscricao, Regiao regiao, String nomeCircunscricao, String flagTipoSituacao,
                         Date dataAtualizacao, Date dataInclusao) {
        super();
        this.idCircunscricao = idCircunscricao;
        this.regiao = regiao;
        this.nomeCircunscricao = nomeCircunscricao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdCircunscricao(Long idCircunscricao) {
        this.idCircunscricao = idCircunscricao;
    }

    public Long getIdCircunscricao() {
        return idCircunscricao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setNomeCircunscricao(String nomeCircunscricao) {
        this.nomeCircunscricao = nomeCircunscricao;
    }

    public String getNomeCircunscricao() {
        return nomeCircunscricao;
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

    public void setComarcas(List<Comarca> comarcas) {
        this.comarcas = comarcas;
    }

    public List<Comarca> getComarcas() {
        return comarcas;
    }

    public Comarca addComarca(Comarca comarca) {
        getComarcas().add(comarca);
        comarca.setCircunscricao(this);
        return comarca;
    }

    public Comarca removeComarca(Comarca comarca) {
        getComarcas().remove(comarca);
        comarca.setCircunscricao(null);
        return comarca;
    }

    @Override
    public Long getId() {
        return getIdCircunscricao();
    }

    @Override
    public void setId(Long id) {
        setIdCircunscricao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("ID Circunscricao = ");
        sb.append(idCircunscricao);
        sb.append("\n");
        
        sb.append("Nome Circunscricao = ");
        sb.append(nomeCircunscricao);
        sb.append("\n");
        
        if (regiao != null) {
            sb.append("Regiao = ");
            sb.append(regiao.getNomeRegiao());
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
