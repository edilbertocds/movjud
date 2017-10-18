package br.jus.tjsp.movjud.web.commons.bean;

import com.sun.faces.component.visit.FullVisitContext;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;
import oracle.adf.view.rich.component.rich.RichPopup;

@ManagedBean(name = "exceptionBean")
public class ExceptionBean {
    private String classeErro;
    private String mensagemErro;
    private String stack;
    private RichPopup errorPopup;

    public ExceptionBean() {
    }

    @PostConstruct
    public void init() {
        if(errorPopup!=null){
            errorPopup.show(new RichPopup.PopupHints());    
        }
        Exception e = ControllerContext.getInstance().getCurrentRootViewPort().getExceptionData();
        
        if(e!=null){
            classeErro = e.getClass().toGenericString();
            
            mensagemErro = e.getMessage();
            
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for(StackTraceElement element : e.getStackTrace()){
                sb.append("     ");
                sb.append(element.toString());
                sb.append("\n");
            }
            
            stack = sb.toString();   
        }
    }

    public static UIComponent findComponent(final String id) {

        FacesContext context = FacesContext.getCurrentInstance(); 
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {     
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if(component.getId().equals(id)){
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;              
            }
        });

        return found[0];

    }

    public String showPopupError(){
        return "";
    }

    public void setClasseErro(String classeErro) {
        this.classeErro = classeErro;
    }

    public String getClasseErro() {
        return classeErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getStack() {
        return stack;
    }

    public void setErrorPopup(RichPopup errorPopup) {
        this.errorPopup = errorPopup;
    }

    public RichPopup getErrorPopup() {
        return errorPopup;
    }
}
