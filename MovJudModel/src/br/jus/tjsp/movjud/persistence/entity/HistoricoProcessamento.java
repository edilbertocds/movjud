package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_HISTORICO_PROCESSAMENTO")
public class HistoricoProcessamento extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 8398953587348127881L;
    
    @Id
    @Column(name = "ID_CAD_HISTORICO_PROCESSAMENTO", nullable = false)
    private Long idHistoricoProcessamento;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_PROCESSAMENTO")
    private TipoProcessamento tipoProcessamento;
    
    @Column(name = "NR_ANO")
    private Integer ano;
    
    @Column(name = "NR_MES")
    private Integer mes;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INICIO")
    private Date dataInicio;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataFim;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    public HistoricoProcessamento() {
    }

    public HistoricoProcessamento(Long idHistoricoProcessamento, TipoProcessamento tipoProcessamento, Integer ano,
                                  Integer mes, Date dataInicio, Date dataFim, String flagTipoSituacao,
                                  Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idHistoricoProcessamento = idHistoricoProcessamento;
        this.tipoProcessamento = tipoProcessamento;
        this.ano = ano;
        this.mes = mes;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdHistoricoProcessamento(Long idHistoricoProcessamento) {
        this.idHistoricoProcessamento = idHistoricoProcessamento;
    }

    public Long getIdHistoricoProcessamento() {
        return idHistoricoProcessamento;
    }

    public void setTipoProcessamento(TipoProcessamento tipoProcessamento) {
        this.tipoProcessamento = tipoProcessamento;
    }

    public TipoProcessamento getTipoProcessamento() {
        return tipoProcessamento;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getMes() {
        return mes;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFim() {
        return dataFim;
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
        return getIdHistoricoProcessamento();
    }

    @Override
    public void setId(Long id) {
        setIdHistoricoProcessamento(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Historico Processamento = ");
        sb.append(idHistoricoProcessamento);
        sb.append("\n");
        
        if (tipoProcessamento != null) {
            sb.append("Tipo Processamento = ");
            sb.append(tipoProcessamento.getDescricaoTipoProcessamento());
            sb.append("\n");
        }
        
        sb.append("Ano = ");
        sb.append(ano);
        sb.append("\n");
        
        sb.append("Mes = ");
        sb.append(mes);
        sb.append("\n");
        
        if (dataInicio != null) {
            sb.append("Data Inicio = ");
            sb.append(ModelUtils.formatarDataToStr(dataInicio));
            sb.append("\n");
        }
        
        if (dataFim != null) {
            sb.append("Data Fim = ");
            sb.append(ModelUtils.formatarDataToStr(dataFim));
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
