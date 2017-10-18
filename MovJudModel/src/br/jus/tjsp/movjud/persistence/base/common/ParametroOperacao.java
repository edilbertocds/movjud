package br.jus.tjsp.movjud.persistence.base.common;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;

import java.util.Date;

public class ParametroOperacao {
    private String campo;
    private Object valor;
    private Object valor2;
    private SQLOperatorType operacao;
    private boolean truncData;
    
    
    public ParametroOperacao(String campo, Object valor, SQLOperatorType operacao) {
        this.campo = campo;
        this.operacao = operacao;
        this.valor = valor;
    }
    
    public ParametroOperacao(String campo, Object valor, SQLOperatorType operacao, boolean truncData) {
        this.campo = campo;
        this.valor = valor;
        this.operacao = operacao;
        this.truncData = truncData;
    }
    
    public ParametroOperacao(String campo, Object valor,Object valor2, SQLOperatorType operacao) {
        this(campo, valor, operacao);
        this.valor2 = valor2;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setOperacao(SQLOperatorType operacao) {
        this.operacao = operacao;
    }

    public SQLOperatorType getOperacao() {
        return operacao;
    }
    
    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getValor() { 
        return tratarValor(valor);
    }
    
    public Object getValor2() {  
        return tratarValor(valor2);
    }
    
    private Object tratarValor(Object objValor) {

        if(objValor != null && objValor instanceof String) {
            String strValor = ((String) objValor).trim();
            
            switch(operacao) {
                case LikeFull:  
                case LikeFullNoCase: 
                    strValor = "%" + strValor + "%";
                    break;
                case Like:  
                case LikeNoCase: 
                    strValor= strValor + "%";
                    break;
            }
            objValor = strValor;
        }   
        
        return objValor;    
    }
    
    public String getParametro(int posicaoParametro) {
        String strParametro = null;
            switch (operacao) {
            case Equal:
                strParametro = truncData ? "FUNC('TRUNC', entidade." + campo + ") = FUNC('TRUNC', ?" + posicaoParametro +")" : "entidade." + campo + " = ?" + posicaoParametro;
                break;
            case EqualNoCase:
                strParametro = "UPPER(entidade." + campo + ") = UPPER(?" + posicaoParametro + ")";
                break;
            case Like:
            case LikeFull:
                strParametro = "UPPER(entidade." + campo + ") like ?" +posicaoParametro;
                break;
            case LikeNoCase:
            case LikeFullNoCase:
                strParametro = "UPPER(entidade." + campo + ") like UPPER(?" +  posicaoParametro + ")";
                break;
            case Between:
                strParametro = "entidade." + campo + " between ?" +  posicaoParametro + " and ?" + (posicaoParametro + 1);
                break;
            case Greater:
                strParametro = truncData ? "FUNC('TRUNC', entidade." + campo + ") > FUNC('TRUNC', ?" + posicaoParametro +")" : "entidade." + campo + " > ?" + posicaoParametro;
                break;
            case GreaterOrEqual:
                strParametro = truncData ? "FUNC('TRUNC', entidade." + campo + ") >= FUNC('TRUNC', ?" + posicaoParametro +")" : "entidade." + campo + " >= ?" + posicaoParametro;
                break;
            case Less:
                strParametro = truncData ? "FUNC('TRUNC', entidade." + campo + ") < FUNC('TRUNC', ?" + posicaoParametro +")" : "entidade." + campo + " < ?" + posicaoParametro;
                break;
            case LessOrEqual:
                strParametro = truncData ? "FUNC('TRUNC', entidade." + campo + ") <= FUNC('TRUNC', ?" + posicaoParametro +")" : "entidade." + campo + " <= ?" + posicaoParametro;
                break;
            case IsNull:
                strParametro = "entidade." + campo + " is null";
                break;
            case IsNotNull:
                strParametro = "entidade." + campo + " is not null";
                break;
            }
        return strParametro;
    }
    
    public boolean isEmpty() {      
        return (valor == null || (valor instanceof String && ((String)valor).trim().isEmpty()) && !(operacao == SQLOperatorType.IsNull || operacao == SQLOperatorType.IsNotNull));
    }
}

