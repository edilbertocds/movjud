package br.jus.tjsp.movjud.business.base.dto;

import java.io.Serializable;

public abstract class BaseObject<T> implements Serializable, Cloneable{
    @SuppressWarnings("compatibility:-3340489329050655908")
    private static final long serialVersionUID = 1L;
    private transient boolean marcado;

    public abstract void setId(T id);
    public abstract T getId();
    
    @Override
    public boolean equals(Object objComparacao) {
        boolean statusEquals = false;
        
        if(objComparacao != null) {
            statusEquals = super.equals(objComparacao); 
            
             if (!statusEquals && objComparacao instanceof BaseObject && this.getClass().equals(objComparacao.getClass()) ) {
                
                T idAtual =  this.getId();
                T idComparacao = ((BaseObject<T>) objComparacao).getId();

                if (idAtual != null && idComparacao != null && idAtual.equals(idComparacao)) {
                    statusEquals = true;
                }
            }
        }
        
        return statusEquals;
    }

    public void setMarcado(boolean marcado) {
	this.marcado = marcado;
    }

    public boolean isMarcado() {
	return marcado;
    }
    
    public BaseObject clonar() {
	BaseObject objClone = null;
	try {
	    objClone = (BaseObject) this.clone();
	} catch (CloneNotSupportedException e) {
	
	}
	return objClone;
    }
}
