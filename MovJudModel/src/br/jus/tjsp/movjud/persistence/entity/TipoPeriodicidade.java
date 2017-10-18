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
@Table(name = "TIPO_PERIODICIDADE")
public class TipoPeriodicidade extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7871549877739615979L;
    
    @Id
    @Column(name = "ID_TIPO_PERIODICIDADE", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_PERIODICIDADE")   
    @SequenceGenerator(name = "SEQ_TIPO_PERIODICIDADE", sequenceName = "SEQ_TIPO_PERIODICIDADE", allocationSize = 1)    
    private Long idTipoPeriodicidade;
    
    @Column(name = "CD_PERIODICIDADE", length = 100)
    private String codigoPeriodicidade;
    
    @Column(name = "DS_PERIODICIDADE", length = 100)
    private String descricaoPeriodicidade;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public TipoPeriodicidade() {
    }

    public TipoPeriodicidade(Long idTipoPeriodicidade, String descricaoPeriodicidade, String tipoSituacao,
                             Date dataAtualizacao, Date dataInclusao) {
        super();
        this.idTipoPeriodicidade = idTipoPeriodicidade;
        this.descricaoPeriodicidade = descricaoPeriodicidade;
        this.tipoSituacao = tipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdTipoPeriodicidade(Long idTipoPeriodicidade) {
        this.idTipoPeriodicidade = idTipoPeriodicidade;
    }

    public Long getIdTipoPeriodicidade() {
        return idTipoPeriodicidade;
    }
    
    public void setCodigoPeriodicidade(String codigoPeriodicidade) {
        this.codigoPeriodicidade = codigoPeriodicidade;
    }

    public String getCodigoPeriodicidade() {
        return codigoPeriodicidade;
    }

    public void setDescricaoPeriodicidade(String descricaoPeriodicidade) {
        this.descricaoPeriodicidade = descricaoPeriodicidade;
    }

    public String getDescricaoPeriodicidade() {
        return descricaoPeriodicidade;
    }

    public void setTipoSituacao(String tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public String getTipoSituacao() {
        return tipoSituacao;
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

    @Override
    public Long getId() {
        return getIdTipoPeriodicidade();
    }

    @Override
    public void setId(Long id) {
        setIdTipoPeriodicidade(id);    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Tipo Periodicidade = ");
        sb.append(idTipoPeriodicidade);
        sb.append("\n");
        
        sb.append("Código Periodicidade = ");
        sb.append(codigoPeriodicidade);
        sb.append("\n");
        
        sb.append("Descricao Periodicidade = ");
        sb.append(descricaoPeriodicidade);
        sb.append("\n");
        
        sb.append("Tipo Situacao = ");
        sb.append(tipoSituacao);
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
