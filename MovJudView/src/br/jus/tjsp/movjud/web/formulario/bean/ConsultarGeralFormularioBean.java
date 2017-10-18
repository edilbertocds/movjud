package br.jus.tjsp.movjud.web.formulario.bean;

import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

public class ConsultarGeralFormularioBean extends BaseBean<FormularioDTO> {
    final static Logger logger = Logger.getLogger(ConsultarGeralFormularioBean.class);
    @SuppressWarnings("compatibility:7388098888405449657")
    private static final long serialVersionUID = 1L;


    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    @Override
    public String pesquisarEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<FormularioDTO> getClasseEntidade() {
        // TODO Implement this method
        return null;
    }
}
