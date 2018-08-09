package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONFIGURACAO)
@Table(name = "CAD_PROCESSO_GAB")
@SequenceGenerator(name = "SEQ_CAD_PROCESSO_GAB", sequenceName = "SEQ_CAD_PROCESSO_GAB", allocationSize = 1)
public class ProcessoGabinete extends BaseEntity<Long> implements Comparable<ProcessoGabinete>{
    private static final long serialVersionUID = -8455119219470144869L;
    @Column(name = "NR_ANO_PROCESSO_CPA")
    private Integer anoProcessoCpa;
    @Column(name = "NR_ANO_PROCESSO_GAB", nullable = false)
    private Integer anoProcessoGabinete;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ARQUIVAMENTO")
    private Date dataArquivamento;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    @Column(name = "FL_ARQUIVADO")
    private String flagArquivado;
    @Column(name = "FL_SITUACAO", nullable = false)
    private String flagSituacao;
    @Id
    @Column(name = "ID_CAD_PROCESSO_GAB", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAD_PROCESSO_GAB")
    private Long idProcessoGabinete;
    @Column(name = "NR_CAIXA", length = 10)
    private String numeroCaixa;
    @Column(name = "NR_PROCESSO_CPA")
    private Long numeroProcessoCpa;
    @Column(name = "NR_PROCESSO_GAB", nullable = false)
    private Long numeroProcessoGabinete;
    @Column(name = "TP_PROCESSO", length = 20)
    private String tipoProcesso;
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO_MAGISTRADO")
    private Usuario usuarioMagistrado;
    
    public ProcessoGabinete() {
        super();
        setFlagSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
    }

    public ProcessoGabinete(Integer anoProcessoCpa, Integer anoProcessoGabinete, Date dataArquivamento,
                            Date dataAtualizacao, Date dataInclusao, String flagArquivado, String flagSituacao,
                            Long idProcessoGabinete, String numeroCaixa, Long numeroProcessoCpa,
                            Long numeroProcessoGabinete, String tipoProcesso, Usuario usuarioMagistrado) {
        this.anoProcessoCpa = anoProcessoCpa;
        this.anoProcessoGabinete = anoProcessoGabinete;
        this.dataArquivamento = dataArquivamento;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.flagArquivado = flagArquivado;
        this.flagSituacao = flagSituacao;
        this.idProcessoGabinete = idProcessoGabinete;
        this.numeroCaixa = numeroCaixa;
        this.numeroProcessoCpa = numeroProcessoCpa;
        this.numeroProcessoGabinete = numeroProcessoGabinete;
        this.tipoProcesso = tipoProcesso;
        this.usuarioMagistrado = usuarioMagistrado;
    }

    public Integer getAnoProcessoCpa() {
        return anoProcessoCpa;
    }

    public void setAnoProcessoCpa(Integer anoProcessoCpa) {
        this.anoProcessoCpa = anoProcessoCpa;
    }

    public Integer getAnoProcessoGabinete() {
        return anoProcessoGabinete;
    }

    public void setAnoProcessoGabinete(Integer anoProcessoGabinete) {
        this.anoProcessoGabinete = anoProcessoGabinete;
    }

    public Date getDataArquivamento() {
        return dataArquivamento;
    }

    public void setDataArquivamento(Date dataArquivamento) {
        this.dataArquivamento = dataArquivamento;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getFlagArquivado() {
        return flagArquivado;
    }

    public void setFlagArquivado(String flagArquivado) {
        this.flagArquivado = flagArquivado;
    }

    public String getFlagSituacao() {
        return flagSituacao;
    }

    public void setFlagSituacao(String flagSituacao) {
        this.flagSituacao = flagSituacao;
    }

    public Long getIdProcessoGabinete() {
        return idProcessoGabinete;
    }

    public void setIdProcessoGabinete(Long idProcessoGabinete) {
        this.idProcessoGabinete = idProcessoGabinete;
    }

    public String getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(String numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public Long getNumeroProcessoCpa() {
        return numeroProcessoCpa;
    }

    public void setNumeroProcessoCpa(Long numeroProcessoCpa) {
        this.numeroProcessoCpa = numeroProcessoCpa;
    }

    public Long getNumeroProcessoGabinete() {
        return numeroProcessoGabinete;
    }

    public void setNumeroProcessoGabinete(Long numeroProcessoGabinete) {
        this.numeroProcessoGabinete = numeroProcessoGabinete;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public Usuario getUsuarioMagistrado() {
        return usuarioMagistrado;
    }

    public void setUsuarioMagistrado(Usuario usuarioMagistrado) {
        this.usuarioMagistrado = usuarioMagistrado;
    }

    @Override
    public void setId(Long id) {
        setIdProcessoGabinete(idProcessoGabinete);
    }

    @Override
    public Long getId() {
        return getIdProcessoGabinete();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID Processo Gabinete = ");
        sb.append(idProcessoGabinete);
        sb.append("\n");

        if (usuarioMagistrado != null) {
            sb.append("Nome = ");
            sb.append(usuarioMagistrado.getNome());
            sb.append("\n");
        }        
        
        sb.append("Numero Processo CPA = ");
        sb.append(numeroProcessoCpa);
        sb.append("\n");
        
        sb.append("Ano Processo CPA = ");
        sb.append(anoProcessoCpa);
        sb.append("\n");

        sb.append("Ano Processo Gabinete = ");
        sb.append(anoProcessoGabinete);
        sb.append("\n");
       
        sb.append("Flag Arquivado = ");
        sb.append(flagArquivado);
        sb.append("\n");

        sb.append("Flag Situacao = ");
        sb.append(flagSituacao);
        sb.append("\n");

        sb.append("Numero Caixa = ");
        sb.append(numeroCaixa);
        sb.append("\n");

        
        sb.append("Numero Processo Gabinete = ");
        sb.append(numeroProcessoGabinete);
        sb.append("\n");
        
        sb.append("Tipo Processo = ");
        sb.append(tipoProcesso);
        sb.append("\n");
        

        if (dataArquivamento != null) {
            sb.append("Data Arquivamento = ");
            sb.append(ModelUtils.formatarDataToStr(dataArquivamento));
            sb.append("\n");
        }
        if (dataAtualizacao != null) {
            sb.append("Data Atualizacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataAtualizacao));
            sb.append("\n");
        }

        if (dataInclusao != null) {
            sb.append("Data Inclusao = ");
            sb.append(ModelUtils.formatarDataToStr(dataInclusao));
            sb.append("\n");
        }

        return sb.toString();
    }

    @Override
    public int compareTo(ProcessoGabinete o) {
        return this.dataInclusao.compareTo(o.getDataInclusao());
    }
}
