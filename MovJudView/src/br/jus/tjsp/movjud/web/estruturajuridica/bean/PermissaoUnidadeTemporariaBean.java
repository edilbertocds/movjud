package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.controller.ControllerContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "permissaoUnidadeTemporariaBean")
@ViewScoped
public class PermissaoUnidadeTemporariaBean extends BaseBean<PermissaoUnidadeTemporaria> {
    final static Logger logger = Logger.getLogger(PermissaoUnidadeTemporariaBean.class);
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciariaService;

    private List<Unidade> listaUnidade;

    Usuario usuarioLogado;

    private String sugestaoUsuario;

    private RichPopup popup;

    private String mensagem;

    private Date dataMinimaInicial;

    public PermissaoUnidadeTemporariaBean() {
        estruturaJudiciariaService = getBean(EstruturaJudiciariaService.class);
        usuarioLogado = getLoginBean().getUsuarioSessao();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        dataMinimaInicial = calendar.getTime();
    }

    @PostConstruct
    public void init() {
        super.initDefault();
        entidadeFiltro = new PermissaoUnidadeTemporaria();
        entidadeFiltro.setUsuario(new Usuario());
        entidadeFiltro.setUnidade(new Unidade());
        listaUnidade = popularListaUnidade();
        listaEntidade = popularListaPermissaoTemporaria();
        pesquisarEntidade();
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        if (validarEntidade(false)) {
            entidadePersistencia.setDataInclusao(new Date());
            entidadePersistencia.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            entidadePersistencia.setUnidade(estruturaJudiciariaService.procurarUnidadePorCodigoUnidade(entidadePersistencia.getUnidade()));
            estruturaJudiciariaService.salvarPermissaoUnidadeTemporaria(entidadePersistencia);
        }
        pesquisarEntidade();
    }

    /** Overrides BaseBean**/
    @Override
    public Class<PermissaoUnidadeTemporaria> getClasseEntidade() {
        return PermissaoUnidadeTemporaria.class;
    }


    public void excluirEntidade(DialogEvent event) {
        estruturaJudiciariaService.excluirPermissaoTemporaria(entidadePersistencia);
        pesquisarEntidade();
    }

    @Override
    public String excluirEntidade() {
        return null;
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        popup = (RichPopup) findComponent("inserirPermissaoTemporariaPopUp");
        entidadePersistencia = getInstanceEntidade();
        sugestaoUsuario = "";
        setVisualizar(false);
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        popup = (RichPopup) findComponent("popUpAlterarPermissaoTemporaria");
        sugestaoUsuario = entidadePersistencia.getUsuario().getNome();
        setVisualizar(false);
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        popup = (RichPopup) findComponent("popUpAlterarPermissaoTemporaria");
        sugestaoUsuario = entidadePersistencia.getUsuario().getNome();
        setVisualizar(true);
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = popularListaPermissaoTemporaria();
        return null;
    }

    /** Montagem das listas e validações**/
    private List<Unidade> popularListaUnidade() {
        if (listaUnidade != null && listaUnidade.size() > 0) {
            return listaUnidade;
        }
        if (Boolean.TRUE.equals(verificarPerfil())) {
            return estruturaJudiciariaService.listarUnidadesCodigoDescricao();
        } else if (Boolean.FALSE.equals(verificarPerfil())) {
            return estruturaJudiciariaService.listarUnidadesCodigoDescricaoPorUsuario(usuarioLogado);
        }
        return new ArrayList<Unidade>();
    }

    private List<PermissaoUnidadeTemporaria> popularListaPermissaoTemporaria() {
        if (Boolean.TRUE.equals(verificarPerfil()) || Boolean.FALSE.equals(verificarPerfil())) {
            if (Boolean.FALSE.equals(verificarPerfil())) {
                if (getEntidadeFiltro().getUnidade() == null) {
                    getEntidadeFiltro().setUnidade(new Unidade());
                }
                getEntidadeFiltro().getUnidade().setUsuario(usuarioLogado);
            }
            return estruturaJudiciariaService.listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(getEntidadeFiltro(),
                                                                                                          getPaginacao());
        }
        return new ArrayList<PermissaoUnidadeTemporaria>();
    }

    private Boolean verificarPerfil() {
        if (ConstantesMovjud.PERFIL_COD_ADMIN.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
            return true;
        } else if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(usuarioLogado.getPerfil().getCodigoPerfil())) {
            return false;
        }
        return null;
    }

    private boolean validarEntidade(boolean novaPermissao) {
        //Validação se data de validade da vigência é menor que a data de início de vigência
        if (entidadePersistencia.getDataInicio().compareTo(entidadePersistencia.getDataValidade()) >= 1) {
            mensagem = (AppBundleProperties.getString("msg.permissaoTemporaria.dataFimMaiorQueInicio"));

            return false;
        }
        PermissaoUnidadeTemporaria filtro = new PermissaoUnidadeTemporaria();
        filtro.setUsuario(entidadePersistencia.getUsuario());
        filtro.setUnidade(entidadePersistencia.getUnidade());
        List<PermissaoUnidadeTemporaria> permissoesAtuais =
            estruturaJudiciariaService.listarPermissaoUnidadeTemporariaPorFiltro(filtro);

        if (permissoesAtuais != null && permissoesAtuais.size() > 0 && novaPermissao) {
            mensagem = (AppBundleProperties.getString("msg.permissaoTemporaria.permissaoTemporariaJaExiste"));

            return false;
        }
        return true;
    }

    /** Sugestao de usuário**/
    public List autoCompletarUsuario(String parametro) {
        Usuario entidadeUsuario = new Usuario();
        entidadeUsuario.setNome(parametro);
        entidadeUsuario.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        if (verificarPerfil() != null) {
            listaParametros =
                estruturaJudiciariaService.listarUsuariosPorPerfilPermissaoTemporaria(entidadeUsuario,
                                                                                      usuarioLogado.getPerfil(),
                                                                                      paginacaoSeguestao);
        } else {
            listaParametros = new ArrayList<Usuario>();
        }

        return montarSelectItemEntidades(listaParametros, Usuario.class);
    }

    public <E extends BaseEntity> List<SelectItem> montarSelectItemEntidades(List listaParametros, Class<E> classe) {
        List<SelectItem> listaSugestoes = null;
        E entidade = null;
        try {
            entidade = classe.newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }
        if (listaParametros != null) {
            listaSugestoes = new ArrayList<SelectItem>();

            for (Usuario item : ((List<Usuario>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNome());
                selectItem.setValue(item.getNome());
                listaSugestoes.add(selectItem);
            }
        }
        return listaSugestoes;
    }

    public void alterarNomeUsuarioSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setUsuario(alterarValorSelecionadoAutoCompletarPermissao(String.valueOf(valueChangeEvent.getNewValue())));
            atualizarComponenteTextoAutoCompletar("inputTextUsuarioSugestao",
                                                  entidadePersistencia.getUsuario().getNome());
        } catch (Exception e) {
            entidadePersistencia.setUsuario(new Usuario());
            atualizarComponenteTextoAutoCompletar("inputTextUsuarioSugestao", "");
        }
    }

    public Usuario alterarValorSelecionadoAutoCompletarPermissao(String id) {
        Usuario usuario = new Usuario();
        for (Usuario entidade : (List<Usuario>) listaParametros) {
            if (id.equals(entidade.getNome())) {
                usuario = entidade;
            }
        }
        return usuario;
    }

    public String alterarPopup(DialogEvent dialogEvent) {
        if (validarEntidade(false)) {
            entidadePersistencia.setDataInclusao(new Date());
            entidadePersistencia.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            entidadePersistencia.setUnidade(estruturaJudiciariaService.procurarUnidadePorCodigoUnidade(entidadePersistencia.getUnidade()));
            estruturaJudiciariaService.salvarPermissaoUnidadeTemporaria(entidadePersistencia);
            popup = (RichPopup) findComponent("popUpAlterarPermissaoTemporaria");
            popup.hide();
            popUpConfirmacao("popupInfoAlterado");
            pesquisarEntidade();
        } else {
            ControllerContext cc = ControllerContext.getInstance();

            Exception ex = cc.getCurrentViewPort().getExceptionData();

            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
            fc.addMessage(null, facesMessage);

            cc.getCurrentRootViewPort().clearException();
            fc.renderResponse();
        }
        return null;
    }

    public String salvarPopup(DialogEvent dialogEvent) {
        if (validarEntidade(true)) {
            entidadePersistencia.setDataInclusao(new Date());
            entidadePersistencia.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            entidadePersistencia.setUnidade(estruturaJudiciariaService.procurarUnidadePorCodigoUnidade(entidadePersistencia.getUnidade()));
            estruturaJudiciariaService.salvarPermissaoUnidadeTemporaria(entidadePersistencia);
            popup = (RichPopup) findComponent("popUpAlterarPermissaoTemporaria");
            popup.hide();
            popUpConfirmacao("popupInfoInclusao");
            pesquisarEntidade();
        } else {
            ControllerContext cc = ControllerContext.getInstance();

            Exception ex = cc.getCurrentViewPort().getExceptionData();

            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null);
            fc.addMessage(null, facesMessage);

            cc.getCurrentRootViewPort().clearException();
            fc.renderResponse();
        }
        return null;
    }

    public String cancelarPopup() {
        popup.hide();
        pesquisarEntidade();
        return null;
    }

    /** Getters and Setters**/
    public void setListaUnidade(List<Unidade> listaUnidade) {
        this.listaUnidade = listaUnidade;
    }

    public List<Unidade> getListaUnidade() {
        return listaUnidade;
    }

    public void setSugestaoUsuario(String sugestaoUsuario) {
        entidadePersistencia.getUsuario().setNome(sugestaoUsuario);
        this.sugestaoUsuario = sugestaoUsuario;
    }

    public String getSugestaoUsuario() {
        return sugestaoUsuario;
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }

    public void setDataMinimaInicial(Date dataMinimaInicial) {
        this.dataMinimaInicial = dataMinimaInicial;
    }

    public Date getDataMinimaInicial() {
        return dataMinimaInicial;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Date getDataMinimaFinal() {
        Date dataMinimaFinal = null;
        if (entidadePersistencia.getDataInicio() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(entidadePersistencia.getDataInicio());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dataMinimaFinal = calendar.getTime();
        }
        return dataMinimaFinal;
    }
}
