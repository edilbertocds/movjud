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
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_VARIAVEL")
public class Variavel extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7611641222367544107L;
    
    @Id
    @Column(name = "ID_CAD_VARIAVEL", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_VARIAVEL") 
    @SequenceGenerator(name = "SEQ_CAD_VARIAVEL", sequenceName = "SEQ_CAD_VARIAVEL", allocationSize = 1)    
    private Long idVariavel;
    
    @Column(name = "NM_VARIAVEL", nullable = false, length = 50)
    private String nomeVariavel;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public Variavel() {
    }

    public Variavel(Long idVariavel, String nomeVariavel, String flagTipoSituacao, Date dataInclusao,
                    Date dataAtualizacao) {
        super();
        this.idVariavel = idVariavel;
        this.nomeVariavel = nomeVariavel;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdVariavel(Long idVariavel) {
        this.idVariavel = idVariavel;
    }

    public Long getIdVariavel() {
        return idVariavel;
    }

    public void setNomeVariavel(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
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
        return getIdVariavel();
    }

    @Override
    public void setId(Long id) {
        setIdVariavel(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Variavel = ");
        sb.append(idVariavel);
        sb.append("\n");
        
        sb.append("Nome Variavel = ");
        sb.append(nomeVariavel);
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
