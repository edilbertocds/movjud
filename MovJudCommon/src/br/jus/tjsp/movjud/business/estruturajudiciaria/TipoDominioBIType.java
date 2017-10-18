package br.jus.tjsp.movjud.business.estruturajudiciaria.types;

import java.util.ArrayList;

public enum TipoDominioBIType {
    GRUPO("G", "Grupo"),
    CAMPO("C", "Campo");
    @SuppressWarnings("compatibility:5757907282298014206")

    private String codigo;
    private String descricao;
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    private TipoDominioBIType(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }
        
    public static String getTipoDominioStr(String tipoDominio){
        if((tipoDominio != null) && !tipoDominio.isEmpty()){
            for(TipoDominioBIType td : values()){
                if(tipoDominio.equalsIgnoreCase(td.codigo)){
                    return td.descricao;
                }
            }
        }
        return null;
    }
}
