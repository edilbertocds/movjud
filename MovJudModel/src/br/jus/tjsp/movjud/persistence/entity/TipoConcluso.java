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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "TIPO_CONCLUSO")
public class TipoConcluso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -6819121247843907454L;
    
    @Id
    @Column(name = "ID_TIPO_CONCLUSO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_CONCLUSO")        
    @SequenceGenerator(name = "SEQ_TIPO_CONCLUSO", sequenceName = "SEQ_TIPO_CONCLUSO", allocationSize = 1)    
    private Long idTipoConcluso;
    
    @Column(name = "DS_TIPO_CONCLUSO", length = 100)
    private String descricaoTipoConcluso;
    
    
    @Column(name = "CD_TIPO_CONCLUSO", length = 50)
    private String codigoTipoConcluso;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public TipoConcluso() {
    }
    
    public TipoConcluso(Long id) {
        this.idTipoConcluso = id;
    }

    public TipoConcluso(Long idTipoConcluso, String descricaoTipoConcluso, String flagTipoSituacao, Date dataInclusao,
                        Date dataAtualizacao) {
        super();
        this.idTipoConcluso = idTipoConcluso;
        this.descricaoTipoConcluso = descricaoTipoConcluso;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdTipoConcluso(Long idTipoConcluso) {
        this.idTipoConcluso = idTipoConcluso;
    }

    public Long getIdTipoConcluso() {
        return idTipoConcluso;
    }

    public void setDescricaoTipoConcluso(String descricaoTipoConcluso) {
        this.descricaoTipoConcluso = descricaoTipoConcluso;
    }

    public String getDescricaoTipoConcluso() {
        return descricaoTipoConcluso;
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

    public void setCodigoTipoConcluso(String codigoTipoConcluso) {
        this.codigoTipoConcluso = codigoTipoConcluso;
    }

    public String getCodigoTipoConcluso() {
        return codigoTipoConcluso;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    @Override
    public Long getId() {
        return getIdTipoConcluso();
    }

    @Override
    public void setId(Long id) {
        setIdTipoConcluso(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Tipo Concluso = ");
        sb.append(idTipoConcluso);
        sb.append("\n");
        
        sb.append("Descricao Tipo Concluso = ");
        sb.append(descricaoTipoConcluso);
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
