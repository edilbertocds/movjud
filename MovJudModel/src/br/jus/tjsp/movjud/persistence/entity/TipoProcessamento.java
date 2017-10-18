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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "TIPO_PROCESSAMENTO")
@Audit(dominio = DominioType.CONFIGURACAO)
public class TipoProcessamento extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -6513596194617892715L;
    
    @Id
    @Column(name = "ID_TIPO_PROCESSAMENTO", nullable = false)
    @GeneratedValue(generator = "SEQ_TIPO_PROCESSAMENTO")   
    @SequenceGenerator(name = "SEQ_TIPO_PROCESSAMENTO", sequenceName = "SEQ_TIPO_PROCESSAMENTO", allocationSize = 1)
    private Long idTipoProcessamento;
    
    @Column(name = "DS_TIPO_PROCESSAMENTO", length = 100)
    private String descricaoTipoProcessamento;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_CONFIG_AVISO")
    private ConfiguracaoAviso configuracaoAviso;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    public TipoProcessamento() {
    }

    public TipoProcessamento(Long idTipoProcessamento, String descricaoTipoProcessamento,
                             ConfiguracaoAviso configuracaoAviso, String flagTipoSituacao, Date dataAtualizacao,
                             Date dataInclusao) {
        super();
        this.idTipoProcessamento = idTipoProcessamento;
        this.descricaoTipoProcessamento = descricaoTipoProcessamento;
        this.configuracaoAviso = configuracaoAviso;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
    }

    public void setIdTipoProcessamento(Long idTipoProcessamento) {
        this.idTipoProcessamento = idTipoProcessamento;
    }

    public Long getIdTipoProcessamento() {
        return idTipoProcessamento;
    }

    public void setDescricaoTipoProcessamento(String descricaoTipoProcessamento) {
        this.descricaoTipoProcessamento = descricaoTipoProcessamento;
    }

    public String getDescricaoTipoProcessamento() {
        return descricaoTipoProcessamento;
    }

    public void setConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        this.configuracaoAviso = configuracaoAviso;
    }

    public ConfiguracaoAviso getConfiguracaoAviso() {
        return configuracaoAviso;
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

    @Override
    public Long getId() {
        return getIdTipoProcessamento();
    }

    @Override
    public void setId(Long id) {
        setIdTipoProcessamento(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Tipo Processamento = ");
        sb.append(idTipoProcessamento);
        sb.append("\n");
        
        sb.append("Descricao Tipo Processamento = ");
        sb.append(descricaoTipoProcessamento);
        sb.append("\n");
        
        if (configuracaoAviso != null) {
            sb.append("Configuracao Aviso = ");
            sb.append(configuracaoAviso.getNomeTitulo());
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
