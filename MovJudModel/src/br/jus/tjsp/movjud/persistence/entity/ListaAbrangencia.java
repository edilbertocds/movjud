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
@Table(name = "CAD_LISTA_ABRANGENCIA")
public class ListaAbrangencia extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 20160729110807L;
    
    @Id
    @Column(name = "ID_CAD_LISTA_ABRANGENCIA", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_LISTA_ABRANGENCIA")    
    @SequenceGenerator(name = "SEQ_CAD_LISTA_ABRANGENCIA", sequenceName = "SEQ_CAD_LISTA_ABRANGENCIA", allocationSize = 1)
    private Long idListaAbrangencia;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAD_CONFIG_AVISO")
    private ConfiguracaoAviso configuracaoAviso;
    
    @Column(name = "VL_ABRANGENCIA", length = 100, nullable = false)
    private String valorAbrangencia;

    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public ListaAbrangencia() {
        super();
    }

    public ListaAbrangencia(Long idListaAbrangencia, ConfiguracaoAviso configuracaoAviso, String valorAbrangencia,
                            String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idListaAbrangencia = idListaAbrangencia;
        this.configuracaoAviso = configuracaoAviso;
        this.valorAbrangencia = valorAbrangencia;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdListaAbrangencia(Long idListaAbrangencia) {
        this.idListaAbrangencia = idListaAbrangencia;
    }

    public Long getIdListaAbrangencia() {
        return idListaAbrangencia;
    }

    public void setConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso) {
        this.configuracaoAviso = configuracaoAviso;
    }

    public ConfiguracaoAviso getConfiguracaoAviso() {
        return configuracaoAviso;
    }

    public void setValorAbrangencia(String valorAbrangencia) {
        this.valorAbrangencia = valorAbrangencia;
    }

    public String getValorAbrangencia() {
        return valorAbrangencia;
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
        return getIdListaAbrangencia();
    }

    @Override
    public void setId(Long id) {
        setIdListaAbrangencia(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Lista Abrangencia = ");
        sb.append(idListaAbrangencia);
        sb.append("\n");
        
        if (configuracaoAviso != null) {
            sb.append("Configuracao Aviso = ");
            sb.append(configuracaoAviso.getNomeTitulo());
            sb.append("\n");
        }
        
        sb.append("Valor Abrangencia = ");
        sb.append(valorAbrangencia);
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
