package br.jus.tjsp.movjud.web.login.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.ModelException;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
// <epr> 0.7.0 implementação de bloqueio da aplicação
import br.jus.tjsp.movjud.persistence.entity.Parametro;
// </epr> 0.7.0 implementação de bloqueio da aplicação
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.io.IOException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import java.util.Optional;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BaseBean<Usuario> {

    private static final long serialVersionUID = 1L;

    private static final String URL_LOGIN_SUCCESS = "/adfAuthentication?success_url=/faces/principal";
    private static final String URL_LOGOUT_SESSION = "/adfAuthentication?logout=true";
    private static final String anoCorrente = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

    final static Logger logger = Logger.getLogger(LoginBean.class);

    private Map<String, Boolean> permissoesUsuario;
    private Usuario usuarioSessao;
    private EstruturaJudiciariaService estruturaJudiciraria;
    
    // <epr> 0.7.0 implementação de bloqueio da aplicação
    private Boolean bloqueado = false;
    // </epr> 0.7.0 implementação de bloqueio da aplicação

    public LoginBean() {
        try {
            estruturaJudiciraria = getBean(EstruturaJudiciariaService.class);
            // <epr> 0.7.0 implementação de bloqueio da aplicação
            List<Parametro> parametros = estruturaJudiciraria.listarParametro();
            Optional<Parametro> flagSomenteAdm = parametros.stream().filter(p -> p.getNomeParametro().equalsIgnoreCase("FLAG_SOMENTE_ADM")).findFirst();
            bloqueado = 
                flagSomenteAdm.isPresent() && 
                (flagSomenteAdm.get().getValorParametro() != null) && 
                (flagSomenteAdm.get().getValorParametro().compareToIgnoreCase("S") == 0);
            // </epr> 0.7.0 implementação de bloqueio da aplicação
        } catch(Exception ex) {
            FacesMessage message = new FacesMessage("Erro inicializando a aplicação: " + ex.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_FATAL);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, message);
            fc.getExceptionHandler().handle();
        }
    }

    @PostConstruct
    public void init() {
        permissoesUsuario = new HashMap<String, Boolean>();
        usuarioSessao = new Usuario();
    }

    public String logar() {
        try{
            logout();
            if (preencheuUsuarioSenha()) {
                if (autenticarUsuario()) {
                    if (autorizarUsuario()) {
                        if (isAtivoUsuario()) {
                            logger.debug(
                                    AppBundleProperties.getString("msg.login.loginEfetuado") + 
                                        " [" + usuarioSessao.getCodigoUsuario() + "] - " + usuarioSessao.getNome());
                            sendForward(URL_LOGIN_SUCCESS);
                        } else {
                            mensagemErro(AppBundleProperties.getString("msg.login.erroUsuarioInativo"));
                        }
                    } else {
                        if(bloqueado) {
                            mensagemErro(AppBundleProperties.getString("msg.login.loginBloqueado"));
                        } else {
                            mensagemErro(AppBundleProperties.getString("msg.login.erroAutorizacao"));
                        }
                    }
                } else {
                    mensagemErro(AppBundleProperties.getString("msg.login.erroAutenticacao"));
                }
            } else {
                mensagemErro(AppBundleProperties.getString("msg.login.erroUsuarioSenhaPreenchimento"));
            }
        }catch(Exception e){
            logger.error(AppBundleProperties.getString("msg.exception.aplicacaoindisponivel") + ": "+MovJudErrorMessage.getLogMessage(e), MovJudErrorMessage.getException(e));
            mensagemErro(AppBundleProperties.getString("msg.exception.aplicacaoindisponivel"));
        }

        return "";
    }

    private boolean preencheuUsuarioSenha() {
        boolean status = false;
        if (usuarioSessao.getCodigoUsuario() != null && !usuarioSessao.getCodigoUsuario().trim().isEmpty() && usuarioSessao.getSenha() != null && !usuarioSessao.getSenha().trim().isEmpty())
            status = true;

        return status;
    }

    private boolean autorizarUsuario() throws ModelException{
        boolean autorizou = false;

        Usuario usuarioAutorizacao = estruturaJudiciraria.procurarUsuarioPorCodigoUsuario(usuarioSessao);
        // <epr> 0.7.0  0.7.0 implementação de bloqueio da aplicação
        if(usuarioAutorizacao == null) {
            return autorizou;
        }
        Perfil perfil = usuarioAutorizacao.getPerfil();

        if (perfil != null) {
            if(bloqueado && !perfil.getCodigoPerfil().equalsIgnoreCase(ConstantesMovjud.PERFIL_COD_ADMIN)) {
                return autorizou;
            }
            // </epr> 0.7.0  0.7.0 implementação de bloqueio da aplicação
            autorizou = true;
            setUsuarioSessao(usuarioAutorizacao);

            if (isAtivoUsuario()) {
                for (Acao i : estruturaJudiciraria.listarAcaoPermissoesUsuario(getUsuarioSessao())) {
                    permissoesUsuario.put(i.getCodigoAcao(), true);
                }
            }
        }
        return autorizou;
    }

    private boolean isAtivoUsuario() {
        return ConstantesMovjud.FLAG_SITUACAO_ATIVA.equals(getUsuarioSessao().getFlagTipoSituacao());
    }

    private boolean autenticarUsuario() {
        boolean autenticou = false;

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(usuarioSessao.getCodigoUsuario(), usuarioSessao.getSenha());
            autenticou = true;

        } catch (ServletException e) {
            e.getMessage();
        }
        return autenticou;
    }

    public void logout() {
        logger.debug(
                AppBundleProperties.getString("msg.login.logoutEfetuado") + 
                    " [" + usuarioSessao.getCodigoUsuario() + "] - " + usuarioSessao.getNome());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
        }
    }

    public String irLogout() {
        logout();

        sendForward(URL_LOGOUT_SESSION);
        return URL_LOGOUT_SESSION;
    }

    protected void sendForward(String forwardUrl) {

        RequestDispatcher dispatcher = getRequest().getRequestDispatcher(forwardUrl);

        try {
            dispatcher.forward(getRequest(), getResponse());
        } catch (ServletException se) {
            throw new RuntimeException("ServletException", se);
        } catch (IOException ie) {
            throw new RuntimeException("IOException", ie);
        }

        getContext().responseComplete();
    }

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    protected HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    protected HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    @Override
    public String pesquisarEntidade() {

        return null;
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class getClasseEntidade() {
        return LoginBean.class;
    }

    public String getAnoCorrente() {
        return anoCorrente;
    }

    public void setPermissoesUsuario(Map<String, Boolean> permissoesUsuario) {
        this.permissoesUsuario = permissoesUsuario;
    }

    public Map<String, Boolean> getPermissoesUsuario() {
        return permissoesUsuario;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        // TODO Implement this method
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        // TODO Implement this method
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        // TODO Implement this method
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }
}
