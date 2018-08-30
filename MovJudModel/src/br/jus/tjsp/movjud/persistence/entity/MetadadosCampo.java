package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_CAMPO")
public class MetadadosCampo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -8089559393699031467L;
    
    @Id
    @Column(name = "ID_MD_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_CAMPO")     
    @SequenceGenerator(name = "SEQ_MD_CAMPO", sequenceName = "SEQ_MD_CAMPO", allocationSize = 1)    
    private Long idMetadadosCampo;
    
    @Column(name = "CD_SIGLA", nullable = false, length = 25)
    private String codigoSigla;

    @Column(name = "DS_NOME", nullable = false, length = 500)
    private String nomeCampo;
    
    @Column(name = "DS_TXT_INFORMATIVO", nullable = false, length = 4000)
    private String descricaoTextoInformativo;
    
    @Column(name = "FL_OBRIGATORIO")
    private String flagObrigatorio;
    
    @Column(name = "FL_INVERTER_MD_TIPO_REGRA")
    private String flagInverterTipoRegra;
    
    @Column(name = "NR_MIN_CARACTERES")
    private Long numeroMinimoCaracteres;
    
    @Column(name = "NR_MAX_CARACTERES")
    private Long numeroMaximoCaracteres;
    
    @Column(name = "NR_CASAS_DECIMAIS")
    private Long numeroCasasDecimais;

    @Column(name = "TP_SITUACAO")
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;   
    
    @Column(name = "TIPO_CAMPO", nullable = false, length = 30)
    private String tipoCampo;
    
    @Column(name = "CD_DOMINIO_BI", nullable = false, length = 50)
    private String codigoDominioBI;
    
    @OneToMany(mappedBy = "metadadosCampo", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @OrderBy("idMetadadosListaSelecao")
    private List<MetadadosListaSelecao> metadadosListaSelecao;
    
    @OneToMany(mappedBy = "metadadosCampo", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<MetadadosValidacaoCampo> metadadosValidacaoCampo;

    @OneToMany(mappedBy = "metadadosCampoFilho", cascade = { CascadeType.ALL } , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MetadadosCampoCampo> metadadosCamposFilho;
    
    @OneToMany(mappedBy = "metadadosCampoPai", cascade = { CascadeType.ALL } , fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MetadadosCampoCampo> metadadosCamposPai;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_TIPO_REGRA")
    private MetadadosTipoRegra metadadosTipoRegra;

    public MetadadosCampo() {
        metadadosListaSelecao = new ArrayList<MetadadosListaSelecao>();
        metadadosValidacaoCampo = new ArrayList<MetadadosValidacaoCampo>();
	metadadosCamposPai = new ArrayList<MetadadosCampoCampo>();
    }
    
    public MetadadosCampo(Long idMetadadosCampo) {
        this.idMetadadosCampo = idMetadadosCampo;
    }
    
    public MetadadosCampo(String codigoDominioBI) {
        this.codigoDominioBI = codigoDominioBI;
    }

    public MetadadosCampo(Long idMetadadosCampo, String nomeCampo, 
                          String flagTipoSituacao, String codigoDominioBI,
                          Date dataInclusao, Date dataAtualizacao, String tipoCampo, String flagObrigatorio, String flagInverterTipoRegra,
                          Long numeroMinimoCaracteres, Long numeroMaximoCaracteres, 
                          List<MetadadosListaSelecao> metadadosListaSelecao, MetadadosTipoRegra metadadosTipoRegra,
                          Long numeroCasasDecimais, List<MetadadosCampoCampo> metadadosCamposFilho, 
                          List<MetadadosCampoCampo> metadadosCamposPai, String codigoSigla, 
                          String descricaoTextoInformativo, List<MetadadosValidacaoCampo> metadadosValidacaoCampo) {
        super();
        this.idMetadadosCampo = idMetadadosCampo;
        this.nomeCampo = nomeCampo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.tipoCampo = tipoCampo;
        this.flagObrigatorio = flagObrigatorio;
        this.numeroMinimoCaracteres = numeroMinimoCaracteres;
        this.numeroMaximoCaracteres = numeroMaximoCaracteres;
        this.numeroCasasDecimais = numeroCasasDecimais;
        this.metadadosListaSelecao = metadadosListaSelecao;
        this.metadadosCamposFilho = metadadosCamposFilho;
        this.metadadosCamposPai = metadadosCamposPai;
        this.codigoDominioBI = codigoDominioBI;
        this.codigoSigla = codigoSigla;
        this.descricaoTextoInformativo = descricaoTextoInformativo;
        this.metadadosValidacaoCampo = metadadosValidacaoCampo;
        this.metadadosTipoRegra = metadadosTipoRegra;
        this.flagInverterTipoRegra = flagInverterTipoRegra;
    }

    public void setCodigoDominioBI(String codigoDominioBI) {
        this.codigoDominioBI = codigoDominioBI;
    }

    public String getCodigoDominioBI() {
        return codigoDominioBI;
    }

    public void setIdMetadadosCampo(Long idMetadadosCampo) {
        this.idMetadadosCampo = idMetadadosCampo;
    }

    public Long getIdMetadadosCampo() {
        return idMetadadosCampo;
    }

    public void setNomeCampo(String nomeCampo) {
        this.nomeCampo = nomeCampo;
    }

    public String getNomeCampo() {
        return nomeCampo;
    }

    public void setFlagInverterTipoRegra(String flagInverterTipoRegra) {
        this.flagInverterTipoRegra = flagInverterTipoRegra;
    }

    public String getFlagInverterTipoRegra() {
        return flagInverterTipoRegra;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao == null){
            dataInclusao = new Date();
        }
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
    
    public void setCodigoSigla(String codigoSigla) {
        this.codigoSigla = codigoSigla;
    }

    public String getCodigoSigla() {
        return codigoSigla;
    }

    public void setDescricaoTextoInformativo(String descricaoTextoInformativo) {
        this.descricaoTextoInformativo = descricaoTextoInformativo;
    }

    public String getDescricaoTextoInformativo() {
        return descricaoTextoInformativo;
    }

    @Override
    public Long getId() {
        return getIdMetadadosCampo();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosCampo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Metadados Campo = ");
        sb.append(idMetadadosCampo);
        sb.append("\n");
        
        sb.append("Nome Campo = ");
        sb.append(nomeCampo);
        sb.append("\n");
        
        sb.append("Tipo Campo = ");
        sb.append(tipoCampo);
        sb.append("\n");
        
        sb.append("Flag Tipo Situacao = ");
        sb.append(flagTipoSituacao);
        sb.append("\n");
        
        sb.append("Flag Obrigatorio = ");
        sb.append(flagObrigatorio);
        sb.append("\n");
        
        sb.append("Minimo de Caracteres = ");
        sb.append(numeroMinimoCaracteres);
        sb.append("\n");
        
        sb.append("Maximo de Caracteres = ");
        sb.append(numeroMaximoCaracteres);
        sb.append("\n");
        
        sb.append("Número Casas Decimais = ");
        sb.append(numeroCasasDecimais);
        sb.append("\n");
        
        sb.append("Código Domínio BI = ");
        sb.append(codigoDominioBI);
        sb.append("\n");
        
        sb.append("Código Sigla = ");
        sb.append(codigoSigla);
        sb.append("\n");
        
        sb.append("Descrição Texto Informativo = ");
        sb.append(descricaoTextoInformativo);
        sb.append("\n");
        
        if (metadadosTipoRegra != null) {
            sb.append("Tipo Regra = ");
            sb.append(metadadosTipoRegra.getDescricaoTipoRegra());
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
        }
        
        return sb.toString();
    }

    public void setTipoCampo(String tipoCampo) {
            this.tipoCampo = tipoCampo;
    }

    public String getTipoCampo() {
        return tipoCampo;
    }

    public void setFlagObrigatorio(String flagObrigatorio) {
        this.flagObrigatorio = flagObrigatorio;
    }

    public String getFlagObrigatorio() {
        return flagObrigatorio;
    }

    public void setNumeroMinimoCaracteres(Long numeroMinimoCaracteres) {
        this.numeroMinimoCaracteres = numeroMinimoCaracteres;
    }

    public Long getNumeroMinimoCaracteres() {
        return numeroMinimoCaracteres;
    }

    public void setNumeroMaximoCaracteres(Long numeroMaximoCaracteres) {
        this.numeroMaximoCaracteres = numeroMaximoCaracteres;
    }

    public Long getNumeroMaximoCaracteres() {
        return numeroMaximoCaracteres;
    }

    public void setMetadadosListaSelecao(List<MetadadosListaSelecao> metadadosListaSelecao) {
        this.metadadosListaSelecao = metadadosListaSelecao;
    }

    public List<MetadadosListaSelecao> getMetadadosListaSelecao() {
        if(metadadosListaSelecao==null){
            metadadosListaSelecao = new ArrayList<MetadadosListaSelecao>();
        }
        return metadadosListaSelecao;
    }
    

    public void setNumeroCasasDecimais(Long numeroCasasDecimais) {
        this.numeroCasasDecimais = numeroCasasDecimais;
    }

    public Long getNumeroCasasDecimais() {
        return numeroCasasDecimais;
    }

    public void setMetadadosCamposFilho(List<MetadadosCampoCampo> metadadosCamposFilho) {
        this.metadadosCamposFilho = metadadosCamposFilho;
    }

    public List<MetadadosCampoCampo> getMetadadosCamposFilho() {
        return metadadosCamposFilho;
    }

    public void setMetadadosCamposPai(List<MetadadosCampoCampo> metadadosCamposPai) {
        this.metadadosCamposPai = metadadosCamposPai;
    }

    public List<MetadadosCampoCampo> getMetadadosCamposPai() {
        return metadadosCamposPai;
    }

    public void setMetadadosValidacaoCampo(List<MetadadosValidacaoCampo> metadadosValidacaoCampo) {
        this.metadadosValidacaoCampo = metadadosValidacaoCampo;
    }

    public List<MetadadosValidacaoCampo> getMetadadosValidacaoCampo() {
        if(metadadosValidacaoCampo==null){
            metadadosValidacaoCampo = new ArrayList<MetadadosValidacaoCampo>();
        }
        return metadadosValidacaoCampo;
    }

    public void setMetadadosTipoRegra(MetadadosTipoRegra metadadosTipoRegra) {
        this.metadadosTipoRegra = metadadosTipoRegra;
    }

    public MetadadosTipoRegra getMetadadosTipoRegra() {
        return metadadosTipoRegra;
    }
}
