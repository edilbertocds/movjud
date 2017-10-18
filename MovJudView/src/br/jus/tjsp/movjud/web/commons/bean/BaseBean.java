package br.jus.tjsp.movjud.web.commons.bean;


import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.types.MovJudErrorType;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.web.estruturajuridica.bean.UnidadeBean;
import br.jus.tjsp.movjud.web.login.bean.LoginBean;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.net.aso.e;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public abstract class BaseBean<T extends BaseObject> implements Serializable {

    final static Logger logger = Logger.getLogger(BaseBean.class);
    
    protected Paginacao paginacao;

    protected Date dataAtual = new Date();

    protected T entidadeOriginal;

    protected T entidadePersistencia;

    protected T entidadeFiltro;

    protected List<T> listaEntidade;

    protected List<? extends BaseObject> listaParametros;

    protected boolean visualizar = false;

    protected String sugestao;

    protected Paginacao paginacaoSeguestao;

    protected int mesCompetencia;

    protected int anoCompetencia;

    @SuppressWarnings("compatibility:1751902630796289846")
    private static final long serialVersionUID = 1L;

    private static transient Map<Class<?>, Method> beanMapped = new HashMap<Class<?>, Method>();

    @PostConstruct
    public void init() {
        initDefault();

        pesquisarEntidade();
    }

    public void initDefault() {
        entidadeFiltro = getInstanceEntidade();
        entidadeOriginal = getInstanceEntidade();
        entidadePersistencia = (T) entidadeOriginal.clonar();
        //    listaEntidade        = null;
        paginacao = new Paginacao();
        sugestao = "";

        paginacaoSeguestao = new Paginacao();
        paginacaoSeguestao.setQtdItensPorPagina(5);
        setMesAnoCompetencia();
    }

    /* get e set de paginacao e Entidade para consulta em tela */
    public void setPaginacao(Paginacao paginacao) {
        this.paginacao = paginacao;
    }

    public Paginacao getPaginacao() {
        return paginacao;
    }

    public void setEntidadeFiltro(T entidadeFiltro) {
        this.entidadeFiltro = entidadeFiltro;
    }

    public T getEntidadeFiltro() {
        return entidadeFiltro;
    }

    /* Metodos de controle da paginacao */
    public void irPrimeiraPaginacao(ActionEvent actionEvent) {
        paginacao.primeiro();
    }

    public void irPaginacaoAnterior(ActionEvent actionEvent) {
        paginacao.anterior();
    }

    public void irProximaPaginacao(ActionEvent actionEvent) {
        paginacao.proximo();
    }

    public void irUltimaPaginacao(ActionEvent actionEvent) {
        paginacao.ultimo();
    }

    public String alterarIndicePaginacao(int indice) {
        paginacao.atualizarIndice(indice);
        atualizarComponenteDeTela(Arrays.asList("tabelaResultados", "primeiraPagina", "paginaAnterior",
                                                "botaoPaginacaoAtual", "botaoPaginacao", "proximaPagina",
                                                "ultimaPagina", "panelGroupPaginacaoNumeral"));
        return null;
    }

    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null || object.toString().isEmpty() || object.toString().trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                          AppBundleProperties.getString("msg.validacao"), null));
        }
    }

    public String filtrarEntidade() {
        paginacao = new Paginacao();
        pesquisarEntidade();
        return null;
    }

    public void popUpConfirmacao(String idPopup){
        RichPopup.PopupHints hint = new RichPopup.PopupHints();
        RichPopup popupSalvoComSucesso = (RichPopup) findComponent(idPopup);
        popupSalvoComSucesso.show(hint);
    }
    
    public String limparPesquisa() {
        init();
        return null;
    }

    public static Object getValueBinding(String binding) {
        Object retorno = null;
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ValueExpression vle =
                fctx.getApplication().getExpressionFactory().createValueExpression(fctx.getELContext(), binding,
                                                                                   Object.class);

            retorno = vle.getValue(fctx.getELContext());
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e);
        }

        return retorno;
    }

    protected LoginBean getLoginBean() {
        return (LoginBean) getValueBinding("#{loginBean}");
    }

    protected UnidadeBean getUnidadeBean() {
        return (UnidadeBean) getValueBinding("#{pageFlowScope.unidadeBean}");
    }

    protected static InjectBean getInjectBean() {
        return (InjectBean) getValueBinding("#{injectBean}");
    }

    public static <E> E getBean(Class<E> classBean) {
        InjectBean injectBean = getInjectBean();
        if (!beanMapped.containsKey(classBean)) {
            //procura o metodo alvo para o serviço
            for (Method method : injectBean.getClass().getMethods()) {
                if (!method.getReturnType().getName().equals(Class.class.getName()) &&
                    method.getReturnType().equals(classBean)) {
                    beanMapped.put(classBean, method);
                    break;
                }
            }
        }
        E retorno = null;
        try {
            Method method = beanMapped.get(classBean);
            if (method != null) {
                retorno = (E) method.invoke(injectBean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }

    /* Metodo para selecionar linha de tabela */
    public void selecionarEntidadeEmTabela(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        entidadeOriginal = (T) richTable.getSelectedRowData();
        entidadePersistencia = (T) entidadeOriginal.clonar();

        richTable.getSelectedRowKeys().clear();
    }

    public abstract void persistirEntidade(DialogEvent dialogEvent);

    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        setVisualizar(false);

        entidadeOriginal = getInstanceEntidade();
        entidadePersistencia = (T) entidadeOriginal.clonar();

    }

    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        setVisualizar(false);
        entidadePersistencia = (T) entidadeOriginal.clonar();
    }

    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        setVisualizar(true);
        entidadePersistencia = (T) entidadeOriginal.clonar();
    }

    public void cancelarInsercao(PopupCanceledEvent popupCanceledEvent) {
        ResetUtils.reset(popupCanceledEvent.getComponent());
    }

    public abstract String pesquisarEntidade();

    public abstract String excluirEntidade();

    public void excluirEntidade(DialogEvent dialogEvent) {
    }

    public abstract Class<T> getClasseEntidade();

    //Adicionar uiComponent em parent
    public void addComponent(UIComponent parentUIComponent, UIComponent childUIComponent) {
        parentUIComponent.getChildren().add(childUIComponent);
        atualizarComponenteDeTela(parentUIComponent);
    }

    //Atualizar componente de tela por id
    public static void atualizarComponenteDeTela(UIComponent component) {
        AdfFacesContext.getCurrentInstance().addPartialTarget(component);
    }

    public static void atualizarComponenteDeTela(String id) {
        UIComponent component = findComponent(id);
        if (component != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(component);
        }
    }

    public static void atualizarComponenteDeTela(List<String> listaIds) {
        for (String id : listaIds) {
            atualizarComponenteDeTela(id);
        }
    }

    public static UIComponent findComponent(String id) {
        FacesContext.getCurrentInstance();
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            UIComponent root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }
        return component;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if (id.equals(base.getId()))
            return base;

        UIComponent children = null;
        UIComponent result = null;
        Iterator childrens = base.getFacetsAndChildren();
        while (childrens.hasNext() && (result == null)) {
            children = (UIComponent) childrens.next();
            if (id.equals(children.getId())) {
                result = children;
                break;
            }
            result = findComponent(children, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public List<? extends BaseObject> listarAutoCompletar(String parametro) {
        return null;
    }

    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        return null;
    }

    public <E extends BaseObject> E alterarValorSelecionadoAutoCompletar(Long id, Class<E> classe) {
        E objeto = null;
        for (E entidade : ((List<E>) listaParametros)) {
            if (entidade.getId().equals(id)) {
                objeto = entidade;
                break;
            }
        }
        return objeto;
    }

    public <E extends BaseObject> E alterarValorSelecionadoAutoCompletar(String id, Class<E> classe) {
        E objeto = null;
        for (E entidade : ((List<E>) listaParametros)) {
            if (entidade.getId().equals(id)) {
                objeto = entidade;
            }
        }
        return objeto;
    }

    public void atualizarComponenteTextoAutoCompletar(String id, String label) {
        RichInputText richInput = RichInputText.class.cast(findComponent(id));
        richInput.setValue(label);
        atualizarComponenteDeTela(id);
    }

    public void atualizarComponenteTextoAutoCompletar(UIComponent component, String label) {
        RichInputText richInput = (RichInputText) component;
        richInput.setValue(label);
        atualizarComponenteDeTela(component);
    }

    public List autoCompletar(String parametro) {
        listaParametros = listarAutoCompletar(parametro);
        return montarSelectItem(listaParametros);
    }

    public void setListaParametros(List<? extends BaseObject> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public List<? extends BaseObject> getListaParametros() {
        return listaParametros;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setEntidadePersistencia(T entidadePersistencia) {
        if (entidadePersistencia == null) {
            entidadePersistencia = getInstanceEntidade();
        }
        this.entidadePersistencia = entidadePersistencia;
    }


    public void setEntidadeOriginal(T entidadeOriginal) {
        this.entidadeOriginal = entidadeOriginal;
    }

    public T getEntidadeOriginal() {
        return entidadeOriginal;
    }

    public T getEntidadePersistencia() {
        return entidadePersistencia;
    }

    public void setListaEntidade(List<T> listaEntidade) {
        this.listaEntidade = listaEntidade;
    }

    public List<T> getListaEntidade() {
        return listaEntidade;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public boolean isVisualizar() {
        return visualizar;
    }
    
    private static void mensagem(FacesMessage.Severity severityMsg, String mensagem) {
	FacesContext context = FacesContext.getCurrentInstance(); 
	FacesMessage message = new FacesMessage("<html><body>"+ mensagem +"</body></html>");
	message.setSeverity(severityMsg);
	context.addMessage(null, message);
    }
    
    public static void mensagemErro(String msgErro) {
	mensagem(FacesMessage.SEVERITY_ERROR, msgErro);
    }
    
    public static void mensagemInfo(String msgErro) {
	mensagem(FacesMessage.SEVERITY_INFO, msgErro);
    }
    
    public static void mensagemAviso(String msgErro) {
	mensagem(FacesMessage.SEVERITY_WARN, msgErro);
    }

    public static void mensagemErroComponente(UIComponent component, String mensagem) {
        FacesMessage fm = new FacesMessage(mensagem);
        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(context), fm);
    }

    public T getInstanceEntidade() {
        T entidade = null;
        try {
            entidade = getClasseEntidade().newInstance();
        } catch (Exception e) {

        }
        return entidade;
    }


    public Date getDataAtual() {
        return dataAtual;
    }

    protected void atualizarPagina() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String refreshpage = fc.getViewRoot().getViewId();
        ViewHandler ViewH = fc.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fc, refreshpage);
        UIV.setViewId(refreshpage);
        fc.setViewRoot(UIV);
    }


    public void setMesAnoCompetencia() {
        Calendar c = Calendar.getInstance();
        int mes = c.get(Calendar.MONTH);
        int ano = c.get(Calendar.YEAR);
        if (mes == 0) {
            mes = 11;
            ano = ano - 1;        
        }       
        mesCompetencia=mes;
        anoCompetencia=ano;
        
    }


    public int getMesCompetencia() {
        return mesCompetencia;
    }

    public int getAnoCompetencia() {
        return anoCompetencia;
    }
}

