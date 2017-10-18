package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;
import br.jus.tjsp.movjud.business.formula.type.FuncaoCalculoType;

import java.util.ArrayList;
import java.util.List;

public class FormulaDTO extends BaseDTO<String> {
    @SuppressWarnings("compatibility:4361716979630934946")
    private static final long serialVersionUID = 3255524035753232296L;
    private String codigoFormula;
    private String expressao;
    private String resultadoExpressao;
    private List<CampoDTO> listaCampos;
    private FuncaoCalculoType funcao;

    public final static String CAMPO_FORMULA_ABRE = "@{";
    public final static String CAMPO_FORMULA_FECHA = "}";
    public final static String PARAM_FORMULA_ABRE = "[";
    public final static String PARAM_FORMULA_FECHA = "]";

    public FormulaDTO() {
	listaCampos = new ArrayList<CampoDTO>();
	expressao = "";
    }

    public FormulaDTO(String formula) {
	this.codigoFormula = "";
	this.expressao = formula;
	this.funcao = null;
	this.listaCampos = null;
    }


    public void setExpressao(String expressao) {
	this.expressao = expressao;
    }

    public String getExpressao() {
	return expressao;
    }

    public void setListaCampos(List<CampoDTO> listaCampos) {
	this.listaCampos = listaCampos;
    }

    public List<CampoDTO> getListaCampos() {
	return listaCampos;
    }

    public void setCodigoFuncao(String codigoFucao) {
	this.funcao = FuncaoCalculoType.getFuncaoByCodigo(codigoFucao);
    }

    public String getCodigoFuncao() {
	String codigoFucao = "";
	if (funcao != null) {
	    codigoFucao = funcao.getCodigoFuncao();
	}
	return codigoFucao;
    }

    public void setCodigoFormula(String codigoFormula) {
	this.codigoFormula = codigoFormula;
    }

    public String getCodigoFormula() {
	return codigoFormula;
    }

    public void setResultadoExpressao(String resultadoExpressao) {
        this.resultadoExpressao = resultadoExpressao;
    }

    public String getResultadoExpressao() {
        return resultadoExpressao;
    }

    @Override
    public void setId(String id) {
	setCodigoFormula(id);
    }

    @Override
    public String getId() {
	return getCodigoFormula();
    }

    public FuncaoCalculoType getFuncao() {
	return funcao;
    }

    public void setFuncao(FuncaoCalculoType funcao) {
	this.funcao = funcao;
    }

    public void addCampoExpressao(CampoDTO campo) {
	if (campo != null && getFuncao() != null) {
	    expressao = expressao == null ? "" : expressao + " ";
	    expressao += CAMPO_FORMULA_ABRE + getFuncao().getNomeFuncao() + PARAM_FORMULA_ABRE + campo.getCodigoCampo();
	    if (getFuncao().getParametroAdicional() != null) {
		expressao += ", " + getFuncao().getParametroAdicional();
	    }
	    expressao += PARAM_FORMULA_FECHA + CAMPO_FORMULA_FECHA;
	}
    }

}
