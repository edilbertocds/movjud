package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_LISTA_PERIODICIDADE")
public class ListaPeriodicidade extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 20160729113007L;
    
    @Id
    @Column(name = "ID_CAD_LISTA_PERIODICIDADE", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_LISTA_PERIODICIDADE")    
    @SequenceGenerator(name = "SEQ_CAD_LISTA_PERIODICIDADE", sequenceName = "SEQ_CAD_LISTA_PERIODICIDADE", allocationSize = 1)
    private Long idListaPeriodicidade;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAD_CONFIG_AVISO")
    private ConfiguracaoAviso configuracaoAviso;
    
    @Column(name = "VL_PERIODICIDADE", length = 100, nullable = false)
    private String valorPeriodicidade;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public ListaPeriodicidade() {
        super();
    }

    public ListaPeriodicidade(Long idListaPeriodicidade, ConfiguracaoAviso configuracaoAviso, String valorPeriodicidade,
                              String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idListaPeriodicidade = idListaPeriodicidade;
        this.configuracaoAviso = configuracaoAviso;
        this.valorPeriodicidade = valorPeriodicidade;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdListaPeriodicidade(Long idListaPeriodicidade) {
        this.idListaPeriodicidade = idListaPeriodicidade;
    }

    public Long getIdListaPeriodicidade() {
        return idListaPeriodicidade;
    }

    public void setConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        this.configuracaoAviso = configuracaoAviso;
    }

    public ConfiguracaoAviso getConfiguracaoAviso() {
        return configuracaoAviso;
    }

    public void setValorPeriodicidade(String valorPeriodicidade) {
        this.valorPeriodicidade = valorPeriodicidade;
    }

    public String getValorPeriodicidade() {
        return valorPeriodicidade;
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
        return getIdListaPeriodicidade();
    }

    @Override
    public void setId(Long id) {
        setIdListaPeriodicidade(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Lista Periodicidade = ");
        sb.append(idListaPeriodicidade);
        sb.append("\n");
        
        if (configuracaoAviso != null) {
            sb.append("Configuracao Aviso = ");
            sb.append(configuracaoAviso.getNomeTitulo());
            sb.append("\n");
        }
        
        sb.append("Valor Periodicidade = ");
        sb.append(valorPeriodicidade);
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
