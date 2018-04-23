package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.formulario.dto.ReuDTO;
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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_REU_PROVISORIO_HIST")
public class ReuProvisorioHistorico extends BaseEntity<Long> {
    
    @Id
    @Column(name = "ID_REU_PROVISORIO_HIST", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_REU_PROVISORIO_HIST")      
    @SequenceGenerator(name = "SEQ_CAD_REU_PROVISORIO_HIST", sequenceName = "SEQ_CAD_REU_PROVISORIO_HIST", allocationSize = 1)    
    private Long idReuProvisorioHistorico;
    
    @JoinColumn(name = "FK_TIPO_NATUREZA_PRISAO", nullable = false)
    private TipoNaturezaPrisao tipoNaturezaPrisao;
    
    @Column(name = "NR_MES", nullable = false)
    private Integer mes;
    
    @Column(name = "NR_ANO", nullable = false)
    private Integer ano;
    
    @Column(name = "NR_PROCESSO", length = 50)
    private String numeroProcesso;
    
    @Column(name = "NR_CONTROLE_ORDEM", length = 20)
    private String numeroControleOrdem;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ULT_MOV")
    private Date dataUltimaMovimentacao;
    
    @Column(name = "DS_CONTEUDO_ULT_MOV", length = 1000)
    private String descricaoConteudoUltimaMovimentacao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @JoinColumn(name = "ID_REU_PROVISORIO", nullable = false)
    private ReuProvisorio reuProvisorio;
    
    // 20.04.2018
    
    @Column(name = "DS_RELATORIO_CGJ", length = 100)
    private String descricaoRelatorioCgj;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_BAIXA")
    private Date dataBaixa;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA_BAIXA")
    private Date dtDataBaixa;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_LEVADO_MAGISTRADO")
    private Date dataLevadoMagistrado;
    
    // DT_LEVADO_MAGISTRADO ?
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_PRISAO")
    private Date dataPrisao;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_ESTAB_PRISIONAL")
    private EstabelecimentoPrisional estabelecimentoPrisional;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "FK_TIPO_MOTIVO_BAIXA")
    private TipoMotivoBaixa tipoMotivoBaixa;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE")
    private Unidade unidade;

    
    public ReuProvisorioHistorico() {
    }

    public ReuProvisorioHistorico(Long idReuProvisorioHistorico, TipoNaturezaPrisao tipoNaturezaPrisao, Integer mes,
                                  Integer ano, String numeroProcesso, String numeroControleOrdem,
                                  Date dataUltimaMovimentacao, String descricaoConteudoUltimaMovimentacao,
                                  String flagTipoSituacao,
                                  ReuProvisorio reuProvisorio, ReuDTO reuDTO) {
        super();
        this.idReuProvisorioHistorico = idReuProvisorioHistorico;
        this.tipoNaturezaPrisao = tipoNaturezaPrisao;
        this.mes = mes;
        this.ano = ano;
        this.numeroProcesso = numeroProcesso;
        this.numeroControleOrdem = numeroControleOrdem;
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
        this.descricaoConteudoUltimaMovimentacao = descricaoConteudoUltimaMovimentacao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.reuProvisorio = reuProvisorio;
        
        // 20.04.2018
        if (reuDTO.getIdMotivoBaixa() != null)
            setTipoMotivoBaixa(new TipoMotivoBaixa(reuDTO.getIdMotivoBaixa()));
        if (reuDTO.getIdMagistrado() != null)
            setUsuario(new Usuario(reuDTO.getIdMagistrado()));
        setDataBaixa(reuDTO.getDataBaixa());
        setDtDataBaixa(reuDTO.getDtDataBaixa());
        setDataLevadoMagistrado(reuDTO.getDataLevadoMagistrado());
        setDataPrisao(reuDTO.getDataPrisao());
        setDescricaoRelatorioCgj(reuDTO.getDescricaoRelatorioCgj());
        setEstabelecimentoPrisional(new EstabelecimentoPrisional(reuDTO.getIdEstabelecimentoPrisional()));
    }
    
    public ReuProvisorioHistorico(Integer mes,
                                  Integer ano, 
                                  ReuProvisorio reuProvisorio) {
        this.mes = mes;
        this.ano = ano;
        this.reuProvisorio = reuProvisorio;
    }
    public ReuProvisorioHistorico(ReuProvisorio reuProvisorio) {
        this.reuProvisorio = reuProvisorio;
    }
    
    public ReuProvisorioHistorico(Long idReuProvisorioHistorico) {
        this.idReuProvisorioHistorico = idReuProvisorioHistorico;
    }

    public void setIdReuProvisorioHistorico(Long idReuProvisorioHistorico) {
        this.idReuProvisorioHistorico = idReuProvisorioHistorico;
    }

    public Long getIdReuProvisorioHistorico() {
        return idReuProvisorioHistorico;
    }

    public void setTipoNaturezaPrisao(TipoNaturezaPrisao tipoNaturezaPrisao) {
        this.tipoNaturezaPrisao = tipoNaturezaPrisao;
    }

    public TipoNaturezaPrisao getTipoNaturezaPrisao() {
        return tipoNaturezaPrisao;
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

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroControleOrdem(String numeroControleOrdem) {
        this.numeroControleOrdem = numeroControleOrdem;
    }

    public String getNumeroControleOrdem() {
        return numeroControleOrdem;
    }

    public void setDataUltimaMovimentacao(Date dataUltimaMovimentacao) {
        this.dataUltimaMovimentacao = dataUltimaMovimentacao;
    }

    public Date getDataUltimaMovimentacao() {
        return dataUltimaMovimentacao;
    }

    public void setDescricaoConteudoUltimaMovimentacao(String descricaoConteudoUltimaMovimentacao) {
        this.descricaoConteudoUltimaMovimentacao = descricaoConteudoUltimaMovimentacao;
    }

    public String getDescricaoConteudoUltimaMovimentacao() {
        return descricaoConteudoUltimaMovimentacao;
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

    public void setReuProvisorio(ReuProvisorio reuProvisorio) {
        this.reuProvisorio = reuProvisorio;
    }

    public ReuProvisorio getReuProvisorio() {
        return reuProvisorio;
    }

    @Override
    public Long getId() {
        return getIdReuProvisorioHistorico();
    }

    @Override
    public void setId(Long id) {
        setIdReuProvisorioHistorico(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Reu Provisorio Historico = ");
        sb.append(idReuProvisorioHistorico);
        sb.append("\n");
        
        if (tipoNaturezaPrisao != null) {
            sb.append("Tipo Natureza Prisao = ");
            sb.append(tipoNaturezaPrisao.getDescricaoTipoNatureza());
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
        
        sb.append("Numero Controle Ordem = ");
        sb.append(numeroControleOrdem);
        sb.append("\n");
        
        if (dataUltimaMovimentacao != null) {
            sb.append("Data Ultima Movimentacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataUltimaMovimentacao));
            sb.append("\n");
        }
        
        sb.append("Descricao Conteudo Ultima Movimentacao = ");
        sb.append(descricaoConteudoUltimaMovimentacao);
        sb.append("\n");
        
        if(reuProvisorio!=null){
            sb.append("Reu Provisorio = ");
            sb.append(reuProvisorio.getNomeReuProvisorio());
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

    public void setDescricaoRelatorioCgj(String descricaoRelatorioCgj) {
        this.descricaoRelatorioCgj = descricaoRelatorioCgj;
    }

    public String getDescricaoRelatorioCgj() {
        return descricaoRelatorioCgj;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDtDataBaixa(Date dtDataBaixa) {
        this.dtDataBaixa = dtDataBaixa;
    }

    public Date getDtDataBaixa() {
        return dtDataBaixa;
    }

    public void setDataLevadoMagistrado(Date dataLevadoMagistrado) {
        this.dataLevadoMagistrado = dataLevadoMagistrado;
    }

    public Date getDataLevadoMagistrado() {
        return dataLevadoMagistrado;
    }

    public void setDataPrisao(Date dataPrisao) {
        this.dataPrisao = dataPrisao;
    }

    public Date getDataPrisao() {
        return dataPrisao;
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
}
