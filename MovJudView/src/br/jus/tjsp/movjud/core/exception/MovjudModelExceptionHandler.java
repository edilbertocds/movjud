package br.jus.tjsp.movjud.core.exception;

import oracle.adf.model.BindingContext;
import oracle.adf.model.RegionBinding;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCErrorHandlerImpl;
import oracle.adf.model.binding.DCErrorMessage;

public class MovjudModelExceptionHandler extends DCErrorHandlerImpl {
    public MovjudModelExceptionHandler(boolean b) {
        super(b);
    }

    public MovjudModelExceptionHandler() {
        super();
    }
    
    @Override
    public void reportException(DCBindingContainer dCBindingContainer, Exception exception) {
        super.reportException(dCBindingContainer, exception);
    }

    @Override
    public DCErrorMessage getDetailedDisplayMessage(BindingContext bindingContext, RegionBinding regionBinding, Exception exception) {
        return super.getDetailedDisplayMessage(bindingContext, regionBinding, exception);
    }
    
    @Override
    public String getDisplayMessage(BindingContext ctx, Exception exception) {
        return super.getDisplayMessage(ctx, exception);
    }
    
    @Override
    protected boolean skipException(Exception exception) {
        return super.skipException(exception);
    }    
}
