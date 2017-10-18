package br.jus.tjsp.movjud.business.formula.utils;


public class FormulaInvalidaException extends Exception{
    @SuppressWarnings("compatibility:-5923643283620547841")
    private static final long serialVersionUID = 1L;
    
    public static final String FORMULA_ERRO_SCRIPT_INVALIDO = "Erro cálculo. Script está inválido";
    public static final String FORMULA_ERRO_CALCULO = "Erro cálculo. Não foi possível executar o cálculo da fórmula";
    public static final String FORMULA_ERRO_VALIDACAO = "Erro na validação. Resultado não é do tipo booleano";

    public FormulaInvalidaException(String msg){
        super(msg);
    }
}
