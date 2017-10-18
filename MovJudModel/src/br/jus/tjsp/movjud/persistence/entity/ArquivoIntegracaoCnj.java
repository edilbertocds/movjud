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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_ARQ_INTEGRACAO_CNJ")
public class ArquivoIntegracaoCnj extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -7893071615805799225L;
    
    @Id
    @Column(name = "ID_ARQ_INTEGRACAO_CNJ", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_ARQ_INTEGRACAO_CNJ")
    @SequenceGenerator(name = "SEQ_CAD_ARQ_INTEGRACAO_CNJ", sequenceName = "SEQ_CAD_ARQ_INTEGRACAO_CNJ", allocationSize = 1)
    private Long idArqIntegracaoCnj;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_INF_ARQUIVO")
    private TipoInformacaoArquivo tipoInformacaoArquivo;
    
    @Column(name = "NM_PATH")
    private String nomePath;
    
    @Column(name = "NR_ANO")
    private Integer ano;
    
    @Column(name = "NR_LOTE")
    private Long numeroLote;
    
    @Column(name = "NR_MES")
    private Integer mes;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_GERACAO")
    private Date dataGeracao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FINALIZACAO")
    private Date dataFinalizacao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @ManyToMany(mappedBy = "arquivosIntegracaoCnj")
    private List<Formulario> formularios;

    public ArquivoIntegracaoCnj() {
    }

    public ArquivoIntegracaoCnj(Long idArqIntegracaoCnj, TipoInformacaoArquivo tipoInformacaoArquivo, String nomePath,
                                Integer ano, Long numeroLote, Integer mes, Date dataGeracao, Date dataFinalizacao,
                                String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idArqIntegracaoCnj = idArqIntegracaoCnj;
        this.tipoInformacaoArquivo = tipoInformacaoArquivo;
        this.nomePath = nomePath;
        this.ano = ano;
        this.numeroLote = numeroLote;
        this.mes = mes;
        this.dataGeracao = dataGeracao;
        this.dataFinalizacao = dataFinalizacao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public Long getId() {
        return getIdArqIntegracaoCnj();
    }

    @Override
    public void setId(Long id) {
        setIdArqIntegracaoCnj(id);
    }

    public void setIdArqIntegracaoCnj(Long idArqIntegracaoCnj) {
        this.idArqIntegracaoCnj = idArqIntegracaoCnj;
    }

    public Long getIdArqIntegracaoCnj() {
        return idArqIntegracaoCnj;
    }

    public void setTipoInformacaoArquivo(TipoInformacaoArquivo tipoInformacaoArquivo) {
        this.tipoInformacaoArquivo = tipoInformacaoArquivo;
    }

    public TipoInformacaoArquivo getTipoInformacaoArquivo() {
        return tipoInformacaoArquivo;
    }

    public void setNomePath(String nomePath) {
        this.nomePath = nomePath;
    }

    public String getNomePath() {
        return nomePath;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getAno() {
        return ano;
    }

    public void setNumeroLote(Long numeroLote) {
        this.numeroLote = numeroLote;
    }

    public Long getNumeroLote() {
        return numeroLote;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getMes() {
        return mes;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
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

    public void setFormularios(List<Formulario> formularios) {
        this.formularios = formularios;
    }

    public List<Formulario> getFormularios() {
        return formularios;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Arquivo Integracao CNJ = ");
        sb.append(idArqIntegracaoCnj);
        sb.append("\n");
        
        if (tipoInformacaoArquivo != null) {
            sb.append("Tipo de Informacao de Arquivo = ");
            sb.append(tipoInformacaoArquivo.getDescricaoTipoInformacaoArquivo());
            sb.append("\n");
        }    
        
        sb.append("Nome Path = ");
        sb.append(nomePath);
        sb.append("\n");
        
        sb.append("Ano = ");
        sb.append(ano);
        sb.append("\n");
        
        sb.append("Numero do Lote = ");
        sb.append(numeroLote);
        sb.append("\n");
        
        sb.append("Mes = ");
        sb.append(mes);
        sb.append("\n");
        
        if (dataGeracao != null) {
            sb.append("Data Geracao = ");
            sb.append(ModelUtils.formatarDataToStr(dataGeracao));
            sb.append("\n");
        }

        if (dataFinalizacao != null) {        
            sb.append("Data Finalizado = ");
            sb.append(ModelUtils.formatarDataToStr(dataFinalizacao));
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
