package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

import java.util.Date;

public class EstabelecimentoEntidadeDTO extends BaseDTO<Long> {
    
    private Long idEstabelecimentoEntidade;
    private String nomeEstabelecimentoEntidade;
    private Long idVinculoEstabelecimentoEntidade;
    private Long idInspecaoEstabelecimento;
    private Long idMagistrado;
    private String nomeMagistrado;
    private Long idUnidade;
    private Date dataInspecao;
    private boolean inspecaoNaoRealizada;
    private String motivoInspecaoNaoRealizada;
    private boolean tipoInternacao;
    private boolean tipoPrisional;
    
    public EstabelecimentoEntidadeDTO() {
        super();
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdEstabelecimentoEntidade(Long idEstabelecimentoEntidade) {
        this.idEstabelecimentoEntidade = idEstabelecimentoEntidade;
    }

    public Long getIdEstabelecimentoEntidade() {
        return idEstabelecimentoEntidade;
    }

    public void setNomeEstabelecimentoEntidade(String nomeEstabelecimentoEntidade) {
        this.nomeEstabelecimentoEntidade = nomeEstabelecimentoEntidade;
    }

    public String getNomeEstabelecimentoEntidade() {
        return nomeEstabelecimentoEntidade;
    }

    public void setIdVinculoEstabelecimentoEntidade(Long idVinculoEstabelecimentoEntidade) {
        this.idVinculoEstabelecimentoEntidade = idVinculoEstabelecimentoEntidade;
    }

    public Long getIdVinculoEstabelecimentoEntidade() {
        return idVinculoEstabelecimentoEntidade;
    }

    public void setIdMagistrado(Long idMagistrado) {
        this.idMagistrado = idMagistrado;
    }

    public Long getIdMagistrado() {
        return idMagistrado;
    }

    public void setTipoInternacao(boolean tipoInternacao) {
        this.tipoInternacao = tipoInternacao;
    }

    public boolean isTipoInternacao() {
        return tipoInternacao;
    }

    public void setTipoPrisional(boolean tipoPrisional) {
        this.tipoPrisional = tipoPrisional;
    }

    public boolean isTipoPrisional() {
        return tipoPrisional;
    }

    public void setNomeMagistrado(String nomeMagistrado) {
        this.nomeMagistrado = nomeMagistrado;
    }

    public String getNomeMagistrado() {
        return nomeMagistrado;
    }

    public void setDataInspecao(Date dataInspecao) {
        this.dataInspecao = dataInspecao;
    }

    public Date getDataInspecao() {
        return dataInspecao;
    }

    public void setInspecaoNaoRealizada(boolean flagInspecaoNaoRealizada) {
        this.inspecaoNaoRealizada = flagInspecaoNaoRealizada;
    }

    public boolean isInspecaoNaoRealizada() {
        return inspecaoNaoRealizada;
    }

    public void setMotivoInspecaoNaoRealizada(String motivoInspecaoNaoRealizada) {
        this.motivoInspecaoNaoRealizada = motivoInspecaoNaoRealizada;
    }

    public String getMotivoInspecaoNaoRealizada() {
        return motivoInspecaoNaoRealizada;
    }


    public void setIdInspecaoEstabelecimento(Long idInspecaoEstabelecimento) {
        this.idInspecaoEstabelecimento = idInspecaoEstabelecimento;
    }

    public Long getIdInspecaoEstabelecimento() {
        return idInspecaoEstabelecimento;
    }

    @Override
    public void setId(Long id) {
        setIdEstabelecimentoEntidade(id);
    }

    @Override
    public Long getId() {
        return getIdEstabelecimentoEntidade();
    }
}
