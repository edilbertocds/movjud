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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_REU_PROVISORIO")
public class ReuProvisorio extends BaseEntity<Long> {
    
    private static final long serialVersionUID = -864669879865367056L;
    
    @Id
    @Column(name = "ID_CAD_REU_PROVISORIO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_REU_PROVISORIO")   
    @SequenceGenerator(name = "SEQ_CAD_REU_PROVISORIO", sequenceName = "SEQ_CAD_REU_PROVISORIO", allocationSize = 1)    
    private Long idReuProvisorio;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_MOTIVO_BAIXA")
    private TipoMotivoBaixa tipoMotivoBaixa;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE")
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_ESTAB_PRISIONAL")
    private EstabelecimentoPrisional estabelecimentoPrisional;
    
    //@ManyToOne
    //@JoinColumn(name = "FK_CAD_SECAO")
   // private Secao secao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @Column(name = "NM_REU_PROV", length = 100)
    private String nomeReuProvisorio;
    
    @Column(name = "NM_MAE_REU_PROV", length = 100)
    private String nomeMaeReuProvisorio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_PRISAO")
    private Date dataPrisao;
    
    @Column(name = "TP_SEXO")
    private String sexo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_BAIXA")
    private Date dataBaixa;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA_BAIXA")
    private Date dtDataBaixa;
    
    @Column(name = "DS_RELATORIO_CGJ", length = 100)
    private String descricaoRelatorioCgj;
            
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_LEVADO_MAGISTRADO")
    private Date dataLevadoMagistrado;
    
    @Column(name = "SAJPG_CD_PESSOA_ID")
    private Long codigoPessoaSaj;
    
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
    
    @OneToMany(mappedBy = "reuProvisorio", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<ReuProvisorioHistorico> historicosReuProvisorio;

    public ReuProvisorio() {
    }
    
    public ReuProvisorio(Long id) {
        this.idReuProvisorio = id;
    }
    
    public ReuProvisorio(Unidade unidade) {
        this.unidade = unidade;
    }
    
    public ReuProvisorio(Unidade unidade, String nomeReuProvisorio) {
        this.unidade = unidade;
        this.nomeReuProvisorio = nomeReuProvisorio;
    }

    public ReuProvisorio(Long idReuProvisorio, TipoMotivoBaixa tipoMotivoBaixa, Unidade unidade, EstabelecimentoPrisional estabelecimentoPrisional,
                         Usuario usuario, String nomeReuProvisorio, String nomeMaeReuProvisorio, Date dataPrisao,
                         String sexo, Date dataBaixa, String descricaoRelatorioCgj, Date dataLevadoMagistrado,
                         Long codigoPessoaSaj, String idBaseOrigemSaj, String flagTipoSituacao, Date dataInclusao,
                         Date dataAtualizacao) {
        super();
        this.idReuProvisorio = idReuProvisorio;
        this.tipoMotivoBaixa = tipoMotivoBaixa;
        this.unidade = unidade;
        this.estabelecimentoPrisional = estabelecimentoPrisional;
        this.usuario = usuario;
        this.nomeReuProvisorio = nomeReuProvisorio;
        this.nomeMaeReuProvisorio = nomeMaeReuProvisorio;
        this.dataPrisao = dataPrisao;
        this.sexo = sexo;
        this.dataBaixa = dataBaixa;
        this.descricaoRelatorioCgj = descricaoRelatorioCgj;
        this.dataLevadoMagistrado = dataLevadoMagistrado;
        this.codigoPessoaSaj = codigoPessoaSaj;
        this.idBaseOrigemSaj = idBaseOrigemSaj;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public void setIdReuProvisorio(Long idReuProvisorio) {
        this.idReuProvisorio = idReuProvisorio;
    }

    public Long getIdReuProvisorio() {
        return idReuProvisorio;
    }

    public void setTipoMotivoBaixa(TipoMotivoBaixa tipoMotivoBaixa) {
        this.tipoMotivoBaixa = tipoMotivoBaixa;
    }

    public TipoMotivoBaixa getTipoMotivoBaixa() {
        return tipoMotivoBaixa;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }


    public void setEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
        this.estabelecimentoPrisional = estabelecimentoPrisional;
    }

    public EstabelecimentoPrisional getEstabelecimentoPrisional() {
        return estabelecimentoPrisional;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setNomeReuProvisorio(String nomeReuProvisorio) {
        this.nomeReuProvisorio = nomeReuProvisorio;
    }

    public String getNomeReuProvisorio() {
        return nomeReuProvisorio;
    }

    public void setNomeMaeReuProvisorio(String nomeMaeReuProvisorio) {
        this.nomeMaeReuProvisorio = nomeMaeReuProvisorio;
    }

    public String getNomeMaeReuProvisorio() {
        return nomeMaeReuProvisorio;
    }

    public void setDataPrisao(Date dataPrisao) {
        this.dataPrisao = dataPrisao;
    }

    public Date getDataPrisao() {
        return dataPrisao;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDescricaoRelatorioCgj(String descricaoRelatorioCgj) {
        this.descricaoRelatorioCgj = descricaoRelatorioCgj;
    }

    public String getDescricaoRelatorioCgj() {
        return descricaoRelatorioCgj;
    }

    public void setDataLevadoMagistrado(Date dataLevadoMagistrado) {
        this.dataLevadoMagistrado = dataLevadoMagistrado;
    }

    public Date getDataLevadoMagistrado() {
        return dataLevadoMagistrado;
    }

    public void setCodigoPessoaSaj(Long codigoPessoaSaj) {
        this.codigoPessoaSaj = codigoPessoaSaj;
    }

    public Long getCodigoPessoaSaj() {
        return codigoPessoaSaj;
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

    public void setHistoricosReuProvisorio(List<ReuProvisorioHistorico> historicosReuProvisorio) {
        this.historicosReuProvisorio = historicosReuProvisorio;
    }

    public List<ReuProvisorioHistorico> getHistoricosReuProvisorio() {
        if(historicosReuProvisorio==null){
            historicosReuProvisorio = new ArrayList<ReuProvisorioHistorico>();
        }
        return historicosReuProvisorio;
    }

    public ReuProvisorioHistorico addReuProvisorioHistorico(ReuProvisorioHistorico reuProvisorioHistorico) {
        getHistoricosReuProvisorio().add(reuProvisorioHistorico);
        reuProvisorioHistorico.setReuProvisorio(this);
        return reuProvisorioHistorico;
    }

    public ReuProvisorioHistorico removeReuProvisorioHistorico(ReuProvisorioHistorico reuProvisorioHistorico) {
        getHistoricosReuProvisorio().remove(reuProvisorioHistorico);
        reuProvisorioHistorico.setReuProvisorio(null);
        return reuProvisorioHistorico;
    }

    @Override
    public Long getId() {
        return getIdReuProvisorio();
    }

    @Override
    public void setId(Long id) {
        setIdReuProvisorio(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        sb.append("ID Reu Provisorio = ");
        sb.append(idReuProvisorio);
        sb.append("\n");
        
        if (tipoMotivoBaixa != null) {
            sb.append("Tipo Motivo Baixa = ");
            sb.append(tipoMotivoBaixa.getDescricaoTipoMotivoBaixa());
            sb.append("\n");
        }
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        sb.append("Estabelecimento Prisional = ");
        sb.append(estabelecimentoPrisional);
        sb.append("\n");
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        sb.append("Nome Reu Provisorio = ");
        sb.append(nomeReuProvisorio);
        sb.append("\n");
        
        sb.append("Nome Mae Reu Provisorio = ");
        sb.append(nomeMaeReuProvisorio);
        sb.append("\n");
        
        if (dataPrisao != null) {
            sb.append("Data Prisao = ");
            sb.append(ModelUtils.formatarDataToStr(dataPrisao));
            sb.append("\n");
        }
        
        sb.append("Sexo = ");
        sb.append(sexo);
        sb.append("\n");
        
        if (dataBaixa != null) {
            sb.append("Data Baixa = ");
            sb.append(ModelUtils.formatarDataToStr(dataBaixa));
            sb.append("\n");
        }
        
        sb.append("Descricao Relatorio CGJ = ");
        sb.append(descricaoRelatorioCgj);
        sb.append("\n");
        
        if (dataLevadoMagistrado != null) {
            sb.append("Data Levado Magistrado = ");
            sb.append(ModelUtils.formatarDataToStr(dataLevadoMagistrado));
            sb.append("\n");
        }
        
        sb.append("Codigo Pessoa SAJ = ");
        sb.append(codigoPessoaSaj);
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

    public void setDtDataBaixa(Date dtDataBaixa) {
        this.dtDataBaixa = dtDataBaixa;
    }

    public Date getDtDataBaixa() {
        return dtDataBaixa;
    }
}
