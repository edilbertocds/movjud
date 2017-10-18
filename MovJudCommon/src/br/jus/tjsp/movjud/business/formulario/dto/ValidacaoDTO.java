package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;
import br.jus.tjsp.movjud.business.formulario.types.TipoValidacaoType;

import java.util.ArrayList;

public class ValidacaoDTO extends BaseDTO<Long> {
    private Long codigoValidacao;
    private String mensagem;
    private TipoValidacaoType tipoValidacao;
    private boolean statusValidacao;
    private boolean validacaoAceita;
    
    private FormulaDTO formula;


    public ValidacaoDTO() {
	statusValidacao = true;
	this.setFormula(new FormulaDTO());
	this.getFormula().setListaCampos(new ArrayList<CampoDTO>());
    }

    public ValidacaoDTO(ValidacaoDTO validacaoDTO) {
	this.codigoValidacao = validacaoDTO.codigoValidacao;
	this.mensagem = validacaoDTO.mensagem;
	this.tipoValidacao = validacaoDTO.tipoValidacao;
	this.formula = validacaoDTO.formula;
	this.statusValidacao = validacaoDTO.statusValidacao;
    }

    public void setCodigoValidacao(Long codigoValidacao) {
	this.codigoValidacao = codigoValidacao;
    }

    public Long getCodigoValidacao() {
	return codigoValidacao;
    }

    public void setMensagem(String mensagem) {
	this.mensagem = mensagem;
    }

    public String getMensagem() {
	return mensagem;
    }

    public void setTipoValidacao(TipoValidacaoType tipo) {
	this.tipoValidacao = tipo;
    }

    public TipoValidacaoType getTipoValidacao() {
	return tipoValidacao;
    }
    
    public void setCodigoTipoValidacao(String codigoTipoValidacao) {
        this.tipoValidacao = TipoValidacaoType.getTipoValidacaoByCodigo(codigoTipoValidacao);
    }

    public String getCodigoTipoValidacao() {
        String codigoTipoValidacao = "";
        if (tipoValidacao != null) {
            codigoTipoValidacao = tipoValidacao.getCodigoValidacao();
        }
        return codigoTipoValidacao;
    }

    public void setFormula(FormulaDTO formula) {
	this.formula = formula;
    }

    public FormulaDTO getFormula() {
	return formula;
    }


    public void setStatusValidacao(boolean statusValidacao) {
        this.statusValidacao = statusValidacao;
    }

    public boolean isStatusValidacao() {
        return statusValidacao;
    }

    @Override
    public void setId(Long id) {
	setCodigoValidacao(id);
    }

    @Override
    public Long getId() {
	return getCodigoValidacao();
    }

    public void setValidacaoAceita(boolean validacaoAceita) {
        this.validacaoAceita = validacaoAceita;
    }

    public boolean isValidacaoAceita() {
        return validacaoAceita;
    }
}
