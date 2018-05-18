package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.math.BigDecimal;

import java.util.Date;

public class ProcessoConclusoDTO extends BaseDTO<Long>{
      
    private Long idEntidadeProcessoConcluso;
    
    private Long idUnidadeProcesso;

    @Override
    public boolean equals(Object obj) {
            if (this == obj)
                    return true;
            if (!super.equals(obj))
                    return false;
            if (getClass() != obj.getClass())
                    return false;
            ProcessoConclusoDTO other = (ProcessoConclusoDTO) obj;
            if (idEntidadeProcessoConcluso == null) {
                    if (other.idEntidadeProcessoConcluso != null)
                            return false;
            } else if (!idEntidadeProcessoConcluso.equals(other.idEntidadeProcessoConcluso))
                    return false;
            return true;
    }
    
    private Long idMagistradoProcesso;
    private String nomeMagistrado;
    
    private TipoConclusoDTO tipoConclusoDTO;
    
    private TipoFilaProcessoDTO tipoFilaProcessoDTO;
    
    private Integer mes;
    
    private Integer ano;
    
    private String srcFormulario;
    
    private BigDecimal numeroProcesso;
    
    private Integer anoProcesso;
    
    private Date dataBaixa;
    
    private Date dataConclusao;
    
    private String codigoProcessoSaj;
    
    private String idBaseOrigemSaj;
    
    private Boolean marcadoExclusao;
    
    // 03.05.2018
    
    private String dsAssunto;
             
    private String dsMovimentacao;
    
    private Integer fkCadUsuarioManutencao;
    
    private Date dtDesignacaoInicio;

    private Date dtDesignacaoFim;
    
    // FIM - 03.05.2018
    
    public ProcessoConclusoDTO() {
        super();
        this.marcadoExclusao = false;
    }
    
    public ProcessoConclusoDTO(Date dataConclusao, TipoConclusoDTO tipoConclusoDTO) {
        this.dataConclusao = dataConclusao;
        this.tipoConclusoDTO = tipoConclusoDTO;
        this.marcadoExclusao=false;
    }
    
    public ProcessoConclusoDTO(Date dataConclusao, TipoConclusoDTO tipoConclusoDTO, TipoFilaProcessoDTO tipoFilaProcessoDTO) {
        this.dataConclusao = dataConclusao;
        this.tipoConclusoDTO = tipoConclusoDTO;
        this.tipoFilaProcessoDTO = tipoFilaProcessoDTO;
        this.marcadoExclusao=false;
    }

    public ProcessoConclusoDTO(Integer ano, Integer mes, Long unidade, Long usuario, String srcFormulario) {
        this.mes = mes;
        this.ano = ano;
        this.idUnidadeProcesso = unidade;
        this.idMagistradoProcesso = usuario;
        this.srcFormulario = srcFormulario;
        this.marcadoExclusao=false;
    }
    
    public ProcessoConclusoDTO(Integer ano, Integer mes, Long unidade, Long usuario, BigDecimal numeroProcesso, String srcFormulario) {
        this.mes = mes;
        this.ano = ano;
        this.idUnidadeProcesso = unidade;
        this.idMagistradoProcesso = usuario;
        this.numeroProcesso = numeroProcesso;
        this.srcFormulario = srcFormulario;
        this.marcadoExclusao=false;
    }
    
    public void setIdEntidadeProcessoConcluso(Long idEntidadeProcessoConcluso) {
        this.idEntidadeProcessoConcluso = idEntidadeProcessoConcluso;
    }

    public Long getIdEntidadeProcessoConcluso() {
        return idEntidadeProcessoConcluso;
    }

    public void setSrcFormulario(String srcFormulario) {
        this.srcFormulario = srcFormulario;
    }

    public String getSrcFormulario() {
        return srcFormulario;
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

    public void setTipoConclusoDTO(TipoConclusoDTO tipoConclusoDTO) {
        this.tipoConclusoDTO = tipoConclusoDTO;
    }

    public TipoConclusoDTO getTipoConclusoDTO() {
        return tipoConclusoDTO;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }


    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
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

    public void setIdUnidadeProcesso(Long idUnidadeProcesso) {
        this.idUnidadeProcesso = idUnidadeProcesso;
    }

    public Long getIdUnidadeProcesso() {
        return idUnidadeProcesso;
    }

    public void setIdMagistradoProcesso(Long idMagistradoProcesso) {
        this.idMagistradoProcesso = idMagistradoProcesso;
    }

    public Long getIdMagistradoProcesso() {
        return idMagistradoProcesso;
    }

    @Override
    public void setId(Long id) {
        setIdEntidadeProcessoConcluso(id);
    }

    @Override
    public Long getId() {
        return getIdEntidadeProcessoConcluso();
    }

    public void setMarcadoExclusao(Boolean marcadoExclusao) {
        this.marcadoExclusao = marcadoExclusao;
    }

    public Boolean getMarcadoExclusao() {
        return marcadoExclusao;
    }

    public void setTipoFilaProcessoDTO(TipoFilaProcessoDTO tipoFilaProcessoDTO) {
        this.tipoFilaProcessoDTO = tipoFilaProcessoDTO;
    }

    public TipoFilaProcessoDTO getTipoFilaProcessoDTO() {
        return tipoFilaProcessoDTO;
    }

    public void setDsAssunto(String dsAssunto) {
        this.dsAssunto = dsAssunto;
    }

    public String getDsAssunto() {
        return dsAssunto;
    }

    public void setDsMovimentacao(String dsMovimentacao) {
        this.dsMovimentacao = dsMovimentacao;
    }

    public String getDsMovimentacao() {
        return dsMovimentacao;
    }

    public void setFkCadUsuarioManutencao(Integer fkCadUsuarioManutencao) {
        this.fkCadUsuarioManutencao = fkCadUsuarioManutencao;
    }

    public Integer getFkCadUsuarioManutencao() {
        return fkCadUsuarioManutencao;
    }

    public void setDtDesignacaoInicio(Date dtDesignacaoInicio) {
        this.dtDesignacaoInicio = dtDesignacaoInicio;
    }

    public Date getDtDesignacaoInicio() {
        return dtDesignacaoInicio;
    }

    public void setDtDesignacaoFim(Date dtDesignacaoFim) {
        this.dtDesignacaoFim = dtDesignacaoFim;
    }

    public Date getDtDesignacaoFim() {
        return dtDesignacaoFim;
    }

    public void setNomeMagistrado(String nomeMagistrado) {
        this.nomeMagistrado = nomeMagistrado;
    }

    public String getNomeMagistrado() {
        return nomeMagistrado;
    }
}
