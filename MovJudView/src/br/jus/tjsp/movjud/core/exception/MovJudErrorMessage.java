package br.jus.tjsp.movjud.core.exception;


import br.jus.tjsp.movjud.core.types.MovJudErrorType;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.commons.bean.ExceptionBean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import oracle.adf.model.RegionBinding;
import oracle.adf.model.binding.DCErrorMessage;

import oracle.adf.view.rich.component.rich.RichPopup;

import org.apache.log4j.Logger;

public class MovJudErrorMessage {

    public MovJudErrorMessage() {
        super();
    }

    public static MovJudErrorType getErrorType(Throwable e) {
        MovJudErrorType exception = null;
        if (e.getCause() == null) {
            exception = MovJudErrorType.getErroPorMensagem(e.getMessage());
        } else {
            exception = getErrorType(e.getCause());
        }
        return exception;
    }

    public static void printStackTrace(Throwable e) {
        if (e.getCause() == null) {
            ModelException me = new ModelException(e.getMessage(), e);
            me.printStackTrace();
        } else {
            printStackTrace(e.getCause());
        }
    }

    public static ModelException getException(Throwable e) {
        ModelException me = null;
        if (e.getCause() == null) {
            me = new ModelException(e.getMessage(), e);
        } else {
            me = getException(e.getCause());
        }
        return me;
    }

    public static String getLogMessage(Throwable e) {
        String exception = "";
        if (e.getCause() == null) {
            exception = e.getMessage();
        } else {
            exception = getLogMessage(e.getCause());
        }
        return exception;
    }

    public static void gerarErroMovjud(Logger logger, Throwable e, UIComponent popUp) {
        ((RichPopup) popUp).hide();
        MovJudErrorType errorType = getErrorType(e);
        BaseBean.mensagemErro(errorType.getMensagenErro());
        logger.error(errorType.getMensagenErro(), getException(e));
    }

    public static void gerarErroMovjud(Logger logger, Throwable e) {
        MovJudErrorType errorType = getErrorType(e);
        BaseBean.mensagemErro(errorType.getMensagenErro());
        logger.error(errorType.getMensagenErro(), getException(e));
    }

    public static void gerarAvisoMovjud(Logger logger, Throwable e) {
        MovJudErrorType errorType = getErrorType(e);
        BaseBean.mensagemAviso(errorType.getMensagenErro());
        logger.warn(errorType.getMensagenErro(), getException(e));
    }

    public static void gerarInfoMonjud(Logger logger, Throwable e) {
        MovJudErrorType errorType = getErrorType(e);
        BaseBean.mensagemInfo(errorType.getMensagenErro());
        logger.info(errorType.getMensagenErro(), getException(e));
    }
}
