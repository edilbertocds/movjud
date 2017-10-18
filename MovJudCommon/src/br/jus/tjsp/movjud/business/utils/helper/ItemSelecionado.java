package br.jus.tjsp.movjud.business.utils.helper;

import java.io.Serializable;

public class ItemSelecionado<T extends Serializable> {
    private boolean marcado;
    private T model;

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }


}
