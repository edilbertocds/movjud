package br.jus.tjsp.movjud.business.formula.type;


public enum FuncaoCalculoType {

    VALOR_CAMPO("VC", "valor", "Valor do Campo", null),
    VALOR_CAMPO_MES_ANTERIOR("VM", "valorMesAnterior", "Valor do mês antetior", null),
    SOMA_SECAO("SS", "somaSecao", "Soma seção","secao");
  
   // ROTULO_CAMPO("RT", "rotulo", "Rótulo do campo", null),
   // SOMA_UNIDADES_ANEXAS("UA", "somaUnidadesAnexas", "Soma das unidades anexas","unidade");


    FuncaoCalculoType(String codigoFuncao, String nomeFuncao, String labelFuncao, String parametroAdicional) {
	this.codigoFuncao = codigoFuncao;
	this.nomeFuncao = nomeFuncao;
	this.labelFuncao = labelFuncao;
	this.parametroAdicional = parametroAdicional;
    }

    private String codigoFuncao;
    private String nomeFuncao;
    private String labelFuncao;
    private String parametroAdicional;


    public String getNomeFuncao() {
	return nomeFuncao;
    }

    public void setLabelFuncao(String labelFuncao) {
	this.labelFuncao = labelFuncao;
    }

    public String getLabelFuncao() {
	return labelFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
	this.nomeFuncao = nomeFuncao;
    }

    public void setCodigoFuncao(String codigoFuncao) {
	this.codigoFuncao = codigoFuncao;
    }

    public String getCodigoFuncao() {
	return codigoFuncao;
    }

    public static FuncaoCalculoType getFuncaoByCodigo(String codigo) {
	FuncaoCalculoType funcao = null;
	if (codigo != null) {
	    for (FuncaoCalculoType funcaoItem : values()) {
		if (funcaoItem.getCodigoFuncao().equals(codigo.toUpperCase().trim())) {
		    funcao = funcaoItem;
		    break;
		}
	    }
	}
	return funcao;
    }

    public void setParametroAdicional(String parametroAdicional) {
	this.parametroAdicional = parametroAdicional;
    }

    public String getParametroAdicional() {
	return parametroAdicional;
    }
}
