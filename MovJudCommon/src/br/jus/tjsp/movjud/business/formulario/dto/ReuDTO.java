package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.time.temporal.Temporal;

import java.util.Date;

public class ReuDTO extends BaseDTO<Long>{
    
    private Long idReuProvisorio;
    
    private String numeroProcesso;
    
    private String numeroControle;

    private String nomeReuProvisorio;
    
    private String nomeMaeReuProvisorio;
    
    private Date dataPrisao;
    
    private Date dataUltimoMovimento;
    
    private String conteudoUltimoMovimento;
    
    private Long idMagistrado;
    
    private Long idUnidade;
    
    private String nomeMagistrado;
    
    private String sexo;
    
    private Date dataBaixa;
    
    private String descricaoRelatorioCgj;
    
    private Date dataLevadoMagistrado;
    
    private Long codigoPessoaSaj;
    
    private String idBaseOrigemSaj;
    
    private Long idEstabelecimentoPrisional;
    
    private String nomeEstabelecimentoPrisional;
    
    private Long idMotivoBaixa;
    
    private String descricaoMotivoBaixa;
    
    private Long idNaturezaPrisao;
    
    private String descricaoNaturezaPrisao;
    
    private Long idReuHistorico;
    
    private Integer ano;
    
    private Integer mes;
    
    public void setReuDTO(ReuDTO reuDTO){
        this.idMotivoBaixa = reuDTO.idMotivoBaixa;
        this.idReuHistorico = reuDTO.idReuHistorico;
        this.descricaoMotivoBaixa = reuDTO.descricaoMotivoBaixa;
        this.idNaturezaPrisao = reuDTO.idNaturezaPrisao;
        this.descricaoNaturezaPrisao = reuDTO.descricaoNaturezaPrisao;
        this.idReuProvisorio = reuDTO.idReuProvisorio;
        this.nomeReuProvisorio = reuDTO.nomeReuProvisorio;
        this.nomeMaeReuProvisorio = reuDTO.nomeMaeReuProvisorio;
        this.dataPrisao = reuDTO.dataPrisao;
        this.sexo = reuDTO.sexo;
        this.dataBaixa = reuDTO.dataBaixa;
        this.descricaoRelatorioCgj = reuDTO.descricaoRelatorioCgj;
        this.dataLevadoMagistrado = reuDTO.dataLevadoMagistrado;
        this.codigoPessoaSaj = reuDTO.codigoPessoaSaj;
        this.idBaseOrigemSaj = reuDTO.idBaseOrigemSaj;
        this.numeroProcesso = reuDTO.numeroProcesso;
        this.numeroControle = reuDTO.numeroControle;
        this.dataUltimoMovimento = reuDTO.dataUltimoMovimento;
        this.conteudoUltimoMovimento = reuDTO.conteudoUltimoMovimento;
        this.idMagistrado = reuDTO.idMagistrado;
        this.nomeMagistrado = reuDTO.nomeMagistrado;
        this.idEstabelecimentoPrisional = reuDTO.idEstabelecimentoPrisional;
        this.nomeEstabelecimentoPrisional = reuDTO.nomeEstabelecimentoPrisional;
        this.ano = reuDTO.ano;
        this.mes = reuDTO.mes;
    }
    
    public ReuDTO() {
        super();
    }

    @Override
    public void setId(Long id) {
        setIdReuProvisorio(id);
    }

    @Override
    public Long getId() {
        return getIdReuProvisorio();
    }


    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdMotivoBaixa(Long idMotivoBaixa) {
        this.idMotivoBaixa = idMotivoBaixa;
    }

    public Long getIdMotivoBaixa() {
        return idMotivoBaixa;
    }

    public void setIdReuHistorico(Long idReuHistorico) {
        this.idReuHistorico = idReuHistorico;
    }

    public Long getIdReuHistorico() {
        return idReuHistorico;
    }

    public void setDescricaoMotivoBaixa(String descricaoMotivoBaixa) {
        this.descricaoMotivoBaixa = descricaoMotivoBaixa;
    }

    public String getDescricaoMotivoBaixa() {
        return descricaoMotivoBaixa;
    }

    public void setIdNaturezaPrisao(Long idNaturezaPrisao) {
        this.idNaturezaPrisao = idNaturezaPrisao;
    }

    public Long getIdNaturezaPrisao() {
        return idNaturezaPrisao;
    }

    public void setDescricaoNaturezaPrisao(String descricaoNaturezaPrisao) {
        this.descricaoNaturezaPrisao = descricaoNaturezaPrisao;
    }

    public String getDescricaoNaturezaPrisao() {
        return descricaoNaturezaPrisao;
    }

    public void setIdReuProvisorio(Long idReuProvisorio) {
        this.idReuProvisorio = idReuProvisorio;
    }

    public Long getIdReuProvisorio() {
        return idReuProvisorio;
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


    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }

    public String getNumeroControle() {
        return numeroControle;
    }

    public void setDataUltimoMovimento(Date dataUltimoMovimento) {
        this.dataUltimoMovimento = dataUltimoMovimento;
    }

    public Date getDataUltimoMovimento() {
        return dataUltimoMovimento;
    }

    public void setConteudoUltimoMovimento(String conteudoUltimoMovimento) {
        this.conteudoUltimoMovimento = conteudoUltimoMovimento;
    }

    public String getConteudoUltimoMovimento() {
        return conteudoUltimoMovimento;
    }

    public void setIdMagistrado(Long idMagistrado) {
        this.idMagistrado = idMagistrado;
    }

    public Long getIdMagistrado() {
        return idMagistrado;
    }

    public void setNomeMagistrado(String nomeMagistrado) {
        this.nomeMagistrado = nomeMagistrado;
    }

    public String getNomeMagistrado() {
        return nomeMagistrado;
    }

    public void setIdEstabelecimentoPrisional(Long idEstabelecimentoPrisional) {
        this.idEstabelecimentoPrisional = idEstabelecimentoPrisional;
    }

    public Long getIdEstabelecimentoPrisional() {
        return idEstabelecimentoPrisional;
    }

    public void setNomeEstabelecimentoPrisional(String nomeEstabelecimentoPrisional) {
        this.nomeEstabelecimentoPrisional = nomeEstabelecimentoPrisional;
    }

    public String getNomeEstabelecimentoPrisional() {
        return nomeEstabelecimentoPrisional;
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
}
