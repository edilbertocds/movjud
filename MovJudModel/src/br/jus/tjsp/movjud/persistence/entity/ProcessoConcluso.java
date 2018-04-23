package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.math.BigDecimal;

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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_PROCESSO_CONCLUSO")
public class ProcessoConcluso extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -2744410378106088134L;
    
    @Id
    @Column(name = "ID_CAD_PROCESSO_CONCLUSO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_PROCESSO_CONCLUSO")        
    @SequenceGenerator(name = "SEQ_CAD_PROCESSO_CONCLUSO", sequenceName = "SEQ_CAD_PROCESSO_CONCLUSO", allocationSize = 1)    
    private Long idProcessoConcluso;
    
   // @ManyToOne
    //@JoinColumn(name = "FK_MD_FORMULARIO")
   // private MetadadosFormulario metadadosFormulario;
    
   // @ManyToOne
   // @JoinColumn(name = "FK_CAD_SECAO")
   // private Secao secao;
    
    @ManyToOne //(cascade = { CascadeType.ALL})
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE")
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_CONCLUSO")
    private TipoConcluso tipoConcluso;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_FILA_PROCESSO")
    private TipoFilaProcesso tipoFilaProcesso;
    
    @Column(name = "DS_SRC_FORMULARIO")
    private String sourceFormulario;
    
    @Column(name = "NR_MES", nullable = false)
    private Integer mes;
    
    @Column(name = "NR_ANO", nullable = false)
    private Integer ano;
    
    @Column(name = "NR_PROCESSO", nullable = false)
    private BigDecimal numeroProcesso;
    
    @Column(name = "NR_ANO_PROCESSO", nullable = false)
    private Integer anoProcesso;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_BAIXA")
    private Date dataBaixa;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA_BAIXA")
    private Date dtDataBaixa;
    
    @Column(name = "SAJPG_CDPROCESSO", length = 20)
    private String codigoProcessoSaj;
    
    @Column(name = "SAJPG_BASE_ORIGEM_ID", length = 10)
    private String idBaseOrigemSaj;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CONCLUSAO")
    private Date dataConclusao;
    
    public ProcessoConcluso() {
    }
    
    public ProcessoConcluso( Unidade unidade,Usuario usuario, Integer ano, Integer mes, BigDecimal numeroProcesso, String sourceFormulario) {
        this.usuario = usuario;
        this.unidade = unidade;
        this.mes = mes;
        this.ano = ano;
        this.numeroProcesso = numeroProcesso;
        this.sourceFormulario = sourceFormulario;
    }

    public ProcessoConcluso(Long idProcessoConcluso, Usuario usuario, String sourceFormulario,
                            Unidade unidade, TipoConcluso tipoConcluso, Integer mes, Integer ano,
                            BigDecimal numeroProcesso, Integer anoProcesso, Date dataBaixa, String codigoProcessoSaj,
                            String idBaseOrigemSaj, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idProcessoConcluso = idProcessoConcluso;
       // this.metadadosFormulario = metadadosFormulario;
        this.usuario = usuario;
        this.unidade = unidade;
        this.tipoConcluso = tipoConcluso;
        this.mes = mes;
        this.ano = ano;
        this.numeroProcesso = numeroProcesso;
        this.anoProcesso = anoProcesso;
        this.dataBaixa = dataBaixa;
        this.codigoProcessoSaj = codigoProcessoSaj;
        this.idBaseOrigemSaj = idBaseOrigemSaj;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.sourceFormulario = sourceFormulario;
    }

    public void setIdProcessoConcluso(Long idProcessoConcluso) {
        this.idProcessoConcluso = idProcessoConcluso;
    }

    public Long getIdProcessoConcluso() {
        return idProcessoConcluso;
    }

  //  public void setMetadadosFormulario(MetadadosFormulario metadadosFormulario) {
  //      this.metadadosFormulario = metadadosFormulario;
  //  }

  //  public MetadadosFormulario getMetadadosFormulario() {
  //      return metadadosFormulario;
  //  }

    public void setSourceFormulario(String sourceFormulario) {
        this.sourceFormulario = sourceFormulario;
    }

    public String getSourceFormulario() {
        return sourceFormulario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setTipoConcluso(TipoConcluso tipoConcluso) {
        this.tipoConcluso = tipoConcluso;
    }

    public TipoConcluso getTipoConcluso() {
        return tipoConcluso;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getMes() {
        return mes;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setNumeroProcesso(BigDecimal numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public BigDecimal getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setAnoProcesso(Integer anoProcesso) {
        this.anoProcesso = anoProcesso;
    }

    public Integer getAnoProcesso() {
        return anoProcesso;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setCodigoProcessoSaj(String codigoProcessoSaj) {
        this.codigoProcessoSaj = codigoProcessoSaj;
    }

    public String getCodigoProcessoSaj() {
        return codigoProcessoSaj;
    }

    public void setIdBaseOrigemSaj(String idBaseOrigemSaj) {
        this.idBaseOrigemSaj = idBaseOrigemSaj;
    }

    public String getIdBaseOrigemSaj() {
        return idBaseOrigemSaj;
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


    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    @Override
    public Long getId() {
        return getIdProcessoConcluso();
    }

    @Override
    public void setId(Long id) {
        setIdProcessoConcluso(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Processo Concluso = ");
        sb.append(idProcessoConcluso);
        sb.append("\n");

   //     if (metadadosFormulario != null) {
   //         sb.append("Formulario = ");
    //        sb.append(metadadosFormulario.getDescricaoNome());
   //         sb.append("\n");
    //    }
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        if (tipoConcluso != null) {
            sb.append("Tipo Concluso = ");
            sb.append(tipoConcluso.getDescricaoTipoConcluso());
            sb.append("\n");
        }
        
        sb.append("Mes = ");
        sb.append(mes);
        sb.append("\n");
        
        sb.append("Ano = ");
        sb.append(ano);
        sb.append("\n");
        
        sb.append("Numero Processo = ");
        sb.append(numeroProcesso);
        sb.append("\n");

        sb.append("Ano Processo = ");
        sb.append(anoProcesso);
        sb.append("\n");
        
        if (dataBaixa != null) {
            sb.append("Data Baixa = ");
            sb.append(ModelUtils.formatarDataToStr(dataBaixa));
            sb.append("\n");
        }
        
        sb.append("Codigo Processo SAJ = ");
        sb.append(codigoProcessoSaj);
        sb.append("\n");
        
        sb.append("ID Base Origem SAJ = ");
        sb.append(idBaseOrigemSaj);
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

    public void setTipoFilaProcesso(TipoFilaProcesso tipoFilaProcesso) {
        this.tipoFilaProcesso = tipoFilaProcesso;
    }

    public TipoFilaProcesso getTipoFilaProcesso() {
        return tipoFilaProcesso;
    }

    public void setDtDataBaixa(Date dtDataBaixa) {
        this.dtDataBaixa = dtDataBaixa;
    }

    public Date getDtDataBaixa() {
        return dtDataBaixa;
    }
}
