package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@NamedQueries({
              @NamedQuery(name = "PeriodoProcessoConcluso.findAll",
                          query = "select o from PeriodoProcessoConcluso o") })
@Table(name = "CAD_PERIODO_PROCESSO_CONCLUSO")
public class PeriodoProcessoConcluso extends BaseEntity<Long> {
    private static final long serialVersionUID = -2534621936970525084L;
    
    public static final Integer NUMERO_DIAS_PERIODO = 60;
    
    @Id
    @Column(name = "NR_ANO", nullable = false)
    private Integer ano;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM", nullable = false)
    private Date dataFim;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INICIO", nullable = false)
    private Date dataInicio;
    @Transient
    private transient Date dataPeriodo;
    @Id
    @Column(name = "NR_MES", nullable = false)
    private Integer mes;

    public PeriodoProcessoConcluso() {
    }

    public PeriodoProcessoConcluso(Integer ano, Date dataFim, Date dataInicio, Integer mes, Date dataPeriodo) {
        this.ano = ano;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.mes = mes;
        this.dataPeriodo = dataPeriodo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setDataPeriodo(Date dataPeriodo) {
        this.dataPeriodo = dataPeriodo;
    }

    public Date getDataPeriodo() {
        return dataPeriodo;
    }

    @Override
    public void setDataAtualizacao(Date dataAtualizacao) {
        // TODO Implement this method
    }

    @Override
    public void setDataInclusao(Date dataInclusao) {
        // TODO Implement this method
    }

    @Override
    public void setId(Long id) {
        // TODO Implement this method
    }

    @Override
    public Long getId() {
        // TODO Implement this method
        return null;
    }
}
