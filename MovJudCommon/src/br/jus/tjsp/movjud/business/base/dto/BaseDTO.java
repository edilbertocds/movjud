package br.jus.tjsp.movjud.business.base.dto;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;

import java.util.Date;


public abstract class BaseDTO<T> extends BaseObject<T> {
    @SuppressWarnings("compatibility:7929488303605044221")
    private static final long serialVersionUID = 1L;

    public BaseDTO() {
        super();
    }
    
    private Date dataInclusao;
    private String situacao;
    
    public void setDataInclusao(Date dataInclusao){
        this.dataInclusao = dataInclusao;
    }
    public Date getDataInclusao(){
        return this.dataInclusao;
    }
    
    public void setSituacao(String situacao){
        this.situacao = situacao;
    }
    
    public String getSituacao(){
        if(situacao == null || situacao.isEmpty()){
            situacao = ConstantesMovjud.FLAG_SITUACAO_ATIVA;
        }
        return this.situacao;
    }
}
