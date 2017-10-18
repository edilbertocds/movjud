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
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_REGIAO")
public class Regiao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 1342633098796684395L;
    
    @Id
    @Column(name = "ID_CAD_REGIAO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_REGIAO")   
    @SequenceGenerator(name = "SEQ_CAD_REGIAO", sequenceName = "SEQ_CAD_REGIAO", allocationSize = 1)    
    private Long idRegiao;
    
    @Column(name = "NM_REGIAO", length = 70, nullable = false)
    private String nomeRegiao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @OneToMany(mappedBy = "regiao", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Circunscricao> circunscricoes;

    public Regiao() {
    }

    public Regiao(Long idRegiao, String nomeRegiao, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idRegiao = idRegiao;
        this.nomeRegiao = nomeRegiao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdRegiao(Long idRegiao) {
        this.idRegiao = idRegiao;
    }

    public Long getIdRegiao() {
        return idRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
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

    public void setCircunscricoes(List<Circunscricao> circunscricoes) {
        this.circunscricoes = circunscricoes;
    }

    public List<Circunscricao> getCircunscricoes() {
        return circunscricoes;
    }

    public Circunscricao addCircunscricao(Circunscricao circunscricao) {
        getCircunscricoes().add(circunscricao);
        circunscricao.setRegiao(this);
        return circunscricao;
    }

    public Circunscricao removeCircunscricao(Circunscricao circunscricao) {
        getCircunscricoes().remove(circunscricao);
        circunscricao.setRegiao(null);
        return circunscricao;
    }

    @Override
    public Long getId() {
        return getIdRegiao();
    }

    @Override
    public void setId(Long id) {
        setIdRegiao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Regiao = ");
        sb.append(idRegiao);
        sb.append("\n");
        
        sb.append("Nome Regiao = ");
        sb.append(nomeRegiao);
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
