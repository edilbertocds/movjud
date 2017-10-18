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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "TIPO_SITUACAO")
@Audit(dominio = DominioType.FORMULARIOS)
public class TipoSituacao extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 7629463579773857930L;
    
    @Id
    @Column(name = "ID_TIPO_SITUACAO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_SITUACAO")        
    @SequenceGenerator(name = "SEQ_TIPO_SITUACAO", sequenceName = "SEQ_TIPO_SITUACAO", allocationSize = 1)
    private Long idTipoSituacao;
    
    @Column(name = "CD_SITUACAO", length = 30)
    private String codigoSituacao;
    
    @Column(name = "DS_SITUACAO", length = 100)
    private String descricaoSituacao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String tipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public TipoSituacao() {
    }
    
    public TipoSituacao(Long id) {
        this.idTipoSituacao = id;
    }


    public TipoSituacao(Long idTipoSituacao, String descricaoSituacao, String tipoSituacao, Date dataInclusao,
                        Date dataAtualizacao) {
        super();
        this.idTipoSituacao = idTipoSituacao;
        this.descricaoSituacao = descricaoSituacao;
        this.tipoSituacao = tipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoSituacao(Long idTipoSituacao) {
        this.idTipoSituacao = idTipoSituacao;
    }

    public Long getIdTipoSituacao() {
        return idTipoSituacao;
    }
    
    public void setCodigoSituacao(String codigoSituacao) {
        this.codigoSituacao = codigoSituacao;
    }

    public String getCodigoSituacao() {
        return codigoSituacao;
    }

    public void setDescricaoSituacao(String descricaoSituacao) {
        this.descricaoSituacao = descricaoSituacao;
    }

    public String getDescricaoSituacao() {
        return descricaoSituacao;
    }

    public void setTipoSituacao(String tipoSituacao) {
        this.tipoSituacao = tipoSituacao;
    }

    public String getTipoSituacao() {
        return tipoSituacao;
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
        return getIdTipoSituacao();
    }

    @Override
    public void setId(Long id) {
        setIdTipoSituacao(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Situacao = ");
        sb.append(idTipoSituacao);
        sb.append("\n");
        
        sb.append("Codigo Situacao = ");
        sb.append(codigoSituacao);
        sb.append("\n");
        
        sb.append("Descricao Situacao = ");
        sb.append(descricaoSituacao);
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
