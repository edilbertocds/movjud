package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "TIPO_ENTRANCIA")
@SequenceGenerator(name = "SEQ_TIPO_ENTRANCIA", sequenceName = "SEQ_TIPO_ENTRANCIA", allocationSize = 1)
public class TipoEntrancia extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 20160729101107L;
    
    @Id
    @Column(name = "ID_TIPO_ENTRANCIA", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_ENTRANCIA")    
    private Long idTipoEntrancia;  
    
    @Column(name = "NM_ENTRANCIA", nullable = false, length = 40)
    private String nomeEntrancia;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    public TipoEntrancia() {
    }

    public TipoEntrancia(Long idTipoEntrancia, String nomeEntrancia, String flagTipoSituacao, Date dataInclusao,
                         Date dataAtualizacao) {
        super();
        this.idTipoEntrancia = idTipoEntrancia;
        this.nomeEntrancia = nomeEntrancia;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoEntrancia(Long idTipoEntrancia) {
        this.idTipoEntrancia = idTipoEntrancia;
    }

    public Long getIdTipoEntrancia() {
        return idTipoEntrancia;
    }

    public void setNomeEntrancia(String nomeEntrancia) {
        this.nomeEntrancia = nomeEntrancia;
    }

    public String getNomeEntrancia() {
        return nomeEntrancia;
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

    @Override
    public Long getId() {
        return getIdTipoEntrancia();
    }

    @Override
    public void setId(Long id) {
        setIdTipoEntrancia(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Entrancia = ");
        sb.append(idTipoEntrancia);
        sb.append("\n");
 
        sb.append("Nome Entrancia = ");
        sb.append(nomeEntrancia);
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
