package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.persistence.entity.TipoLocal;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.UnidadeEstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.util.ResetUtils;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public class UnidadeBean extends BaseBean<Unidade> {

    final static Logger logger = Logger.getLogger(UnidadeBean.class);

    @SuppressWarnings("compatibility:7551897779871504055")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciaria;

    private String sugestaoForo;
    private String sugestaoForoRecursal;
    private String sugestaoUsuario;
    private String sugestaoUnidade;
    private String sugestaoEstabelecimentoPrisional;

    private Unidade unidadeAnexa;

    private Foro foroRecursal;

    private UnidadeEstabelecimentoPrisional unidadeEstabelecimentoPrisional;

    private EstabelecimentoPrisional estabelecimentoPrisional;

    private List<TipoLocal> listaTipoLocal;
    private RichPopup popUpAlterarUnidade;
    private RichPopup popUpInserirUnidade;
    private RichInputDate idCriacao;
    private RichInputDate idFim;
    private RichInputText inputTextUnidadeAnexaSugestao;
    private RichInputText inputTextUnidadeEstabelecimento;
    private RichInputText inputTextForoRecursal;

    public UnidadeBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);

    }

    @PostConstruct
    public void init() {
        super.init();

        entidadeFiltro.setForo(new Foro());
        entidadeFiltro.getForo().setComarca(new Comarca());
        entidadeFiltro.getForo().getComarca().setCircunscricao(new Circunscricao());
        entidadeFiltro.getForo().getComarca().getCircunscricao().setRegiao(new Regiao());
        listaTipoLocal = estruturaJudiciaria.listarTipoLocal();
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarUnidadesComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    public void pesquisarEntidade(ActionEvent actionEvent) {
        pesquisarEntidade();
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirUnidade(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<Unidade> getClasseEntidade() {
        return Unidade.class;
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {

        super.initDialogInserir(popupFetchEvent);
        entidadePersistencia.setForo(new Foro());
        entidadePersistencia.setUsuario(new Usuario());
        entidadePersistencia.setTipoLocal(new TipoLocal());
        entidadePersistencia.setUnidadesAnexa(new ArrayList<Unidade>());
        entidadePersistencia.setForosRecursais(new ArrayList<Foro>());
        entidadePersistencia.setEstabelecimentosPrisionais(new ArrayList<UnidadeEstabelecimentoPrisional>());
        unidadeAnexa = new Unidade();
        foroRecursal = new Foro();
        estabelecimentoPrisional = new EstabelecimentoPrisional();
        unidadeEstabelecimentoPrisional = new UnidadeEstabelecimentoPrisional();
        sugestaoForo = "";
        sugestaoUsuario = "";
        sugestaoUnidade = "";
        sugestaoForoRecursal = "";
        sugestaoEstabelecimentoPrisional = "";
        sugestao = "";
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        ResetUtils.reset(popUpAlterarUnidade);
        ResetUtils.reset(popUpInserirUnidade);
        sugestao = entidadePersistencia.getForo().getNomeForo();
        sugestaoUsuario = entidadePersistencia.getUsuario().getNome();
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        super.initDialogVisualizar(popupFetchEvent);
        sugestao = entidadePersistencia.getForo().getNomeForo();
        sugestaoUsuario = entidadePersistencia.getUsuario().getNome();
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            if (entidadePersistencia.getNomeUnidade() != null && entidadePersistencia.getUsuario().getNome() != null &&
                entidadePersistencia.getTipoLocal().getNomeLocal() != null) {
                apagarListasAposMudancaTiposLocal();
                estruturaJudiciaria.salvarUnidade(entidadePersistencia);
                popUpConfirmacao("popupInfoAlterado");
                pesquisarEntidade();

            } else {
                FacesContext fc = FacesContext.getCurrentInstance();
                FacesMessage facesMessage =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha os campos obrigatórios.", null);
                fc.addMessage(null, facesMessage);
            }
        } else {
            pesquisarEntidade();
            ResetUtils.reset(popUpAlterarUnidade);
            ResetUtils.reset(popUpInserirUnidade);
        }

        /* if (entidadePersistencia.getNomeUnidade() != null) {
            estruturaJudiciaria.salvarUnidade(entidadePersistencia);
        } else {
            ControllerContext cc = ControllerContext.getInstance();

            Exception ex = cc.getCurrentViewPort().getExceptionData();
            String message = ex.getMessage();


            FacesContext fc = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "UTF: " + message, null);
            fc.addMessage(null, facesMessage);

            cc.getCurrentRootViewPort().clearException();
            fc.renderResponse();
        } */
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) {
        super.validate(facesContext, uIComponent, object);
        RichPanelBox panel = (RichPanelBox) findComponent("boxlabelDadosUnidade");
        if (entidadePersistencia.getNomeUnidade() != null && panel.isDisclosed() == false) {
            panel.setDisclosed(true);
            //atualizarComponenteDeTela(panel);
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                          AppBundleProperties.getString("msg.validacao"), null));
        }

    }


    /** Sugestao de unidadeAnexa **/
    public List autoCompletarUnidade(String parametro) {
        Unidade entidadeUnidade = new Unidade();
        entidadeUnidade.setNomeUnidade(parametro);
        if (entidadePersistencia.getForo().getId() != null)
            entidadeUnidade.setForo(entidadePersistencia.getForo());
        listaParametros = estruturaJudiciaria.listarUnidadesComFiltroPaginacao(entidadeUnidade, paginacaoSeguestao);
        return montarSelectItemEtidades(listaParametros, Unidade.class);
    }

    public void alterarNomeUnidadeAnexaSelecionada(ValueChangeEvent valueChangeEvent) {
        //  if (unidadeAnexa == null || unidadeAnexa.getId() == null || entidadePersistencia.getUnidadesAnexa().contains(unidadeAnexa)) {
        try {
            unidadeAnexa =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     Unidade.class);
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), unidadeAnexa.getNomeUnidade());
        } catch (Exception e) {
            unidadeAnexa = new Unidade();
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
        //  }
    }

    public String incluirUnidadeAnexa() {
        if (unidadeAnexa != null && unidadeAnexa.getId() != null &&
            !entidadePersistencia.getUnidadesAnexa().contains(unidadeAnexa)) {
            entidadePersistencia.getUnidadesAnexa().add(unidadeAnexa);
        }
        ResetUtils.reset((RichInputText) findComponent("inputTextUnidadeAnexaSugestao")); // inputTextUnidadeAnexaSugestao
        AdfFacesContext.getCurrentInstance().addPartialTarget((RichInputText) findComponent("inputTextUnidadeAnexaSugestao"));
        sugestaoUnidade = "";
        unidadeAnexa = null;

        atualizarComponenteDeTela("boxAnexoSubUnidades");
        atualizarComponenteDeTela("inputTextUnidadeAnexaSugestao");
        return null;
    }

    public void selecionarUnidadeAnexa(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        unidadeAnexa = (Unidade) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerUnidadeAnexa() {
        List<Unidade> listaUnidadeAnexa = entidadePersistencia.getUnidadesAnexa();
        for (int i = 0; i <= listaUnidadeAnexa.size(); i++) {
            if (unidadeAnexa.equals(listaUnidadeAnexa.get(i))) {
                entidadePersistencia.getUnidadesAnexa().remove(i);
                break;
            }
        }
        unidadeAnexa = new Unidade();
        return null;
    }

    /*  private boolean contemUnidade(List<Unidade> listaUnidadeAnexa, Unidade unidadeAxexa) {
        boolean existente = false;
        for (Unidade unidadeAnexaItem : listaUnidadeAnexa) {
            if (unidadeAnexaItem.equals(unidadeAxexa)) {
                existente = true;
            }
        }
        return existente;
    }*/


    /** Sugestao de foro e foroRecursal**/

    public List autoCompletarForo(String parametro) {
        Foro entidadeForo = new Foro();
        entidadeForo.setNomeForo(parametro);
        listaParametros = estruturaJudiciaria.listarForoComFiltroPaginacao(entidadeForo, paginacaoSeguestao);
        return montarSelectItemEtidades(listaParametros, Foro.class);
    }

    public void alterarNomeForoSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setForo(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                              Foro.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  entidadePersistencia.getForo().getNomeForo());
        } catch (Exception e) {
            entidadePersistencia.setForo(new Foro());
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public void alterarNomeForoRecursalSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            foroRecursal = new Foro();
            foroRecursal =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     Foro.class);
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), foroRecursal.getNomeForo());
        } catch (Exception e) {
            foroRecursal = new Foro();
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public String incluirForoRecursal() {
        if (foroRecursal != null && foroRecursal.getIdForo() != null &&
            !entidadePersistencia.getForosRecursais().contains(foroRecursal)) {
            entidadePersistencia.getForosRecursais().add(foroRecursal);
        }
        ResetUtils.reset((RichInputText) findComponent("inputTextForoRecursal")); // inputTextForoRecursal
        AdfFacesContext.getCurrentInstance().addPartialTarget((RichInputText) findComponent("inputTextForoRecursal"));
        sugestaoForoRecursal = "";

        atualizarComponenteDeTela("boxforosRecursais");
        atualizarComponenteDeTela("inputTextForoRecursal");
        return null;
    }

    public void selecionarForoRecursal(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        foroRecursal = (Foro) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerForoRecursal() {
        entidadePersistencia.getForosRecursais().remove(foroRecursal);
        foroRecursal = new Foro();
        return null;
    }

    /** Sugestao de usuarioResponsavelUnidade **/

    public List autoCompletarUsuario(String parametro) {
        Usuario entidadeUsuario = new Usuario();
        Perfil perfilRespCart = new Perfil();
        perfilRespCart.setCodigoPerfil(ConstantesMovjud.PERFIL_COD_RESPONSAVEL);
        entidadeUsuario.setPerfil(perfilRespCart);
        entidadeUsuario.setNome(parametro);
        entidadeUsuario.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        listaParametros =
            estruturaJudiciaria.listarUsuarioOrdenadoPorNomeComPaginacao(entidadeUsuario, paginacaoSeguestao);
        return montarSelectItemEtidades(listaParametros, Usuario.class);
    }


    public void alterarNomeUsuarioSelecionado(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setUsuario(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                                 Usuario.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  entidadePersistencia.getUsuario().getNome());
        } catch (Exception e) {
            entidadePersistencia.setUsuario(new Usuario());
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    /** Metodo comum **/

    public <E extends BaseEntity> List<SelectItem> montarSelectItemEtidades(List listaParametros, Class<E> classe) {
        List<SelectItem> listaSugestoes = null;
        E entidade = null;
        try {
            entidade = classe.newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        }
        if (listaParametros != null) {
            listaSugestoes = new ArrayList<SelectItem>();
            if (entidade instanceof Foro) {
                for (Foro item : ((List<Foro>) listaParametros)) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getNomeForo());
                    selectItem.setValue(item.getIdForo());
                    listaSugestoes.add(selectItem);
                }
            } else if (entidade instanceof Usuario) {
                for (Usuario item : ((List<Usuario>) listaParametros)) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getNome());
                    selectItem.setValue(item.getIdUsuario());
                    listaSugestoes.add(selectItem);
                }
            } else if (entidade instanceof Unidade) {
                for (Unidade item : ((List<Unidade>) listaParametros)) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getNomeUnidade());
                    selectItem.setValue(item.getIdUnidade());
                    listaSugestoes.add(selectItem);
                }
            } else if (entidade instanceof EstabelecimentoPrisional) {
                for (EstabelecimentoPrisional item : ((List<EstabelecimentoPrisional>) listaParametros)) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getNomeEstabelecimentoPrisional());
                    selectItem.setValue(item.getIdEstabelecimentoPrisional());
                    listaSugestoes.add(selectItem);
                }
            }
        }
        return listaSugestoes;
    }


    public void alterarNomeEstabelecimentoPrisional(ValueChangeEvent valueChangeEvent) {
        try {
            estabelecimentoPrisional = new EstabelecimentoPrisional();
            estabelecimentoPrisional =
                alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                     EstabelecimentoPrisional.class);
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  estabelecimentoPrisional.getNomeEstabelecimentoPrisional());
        } catch (Exception e) {
            estabelecimentoPrisional = new EstabelecimentoPrisional();
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public List autoCompletarEstabelecimentoPrisional(String parametro) {
        EstabelecimentoPrisional entidadeEstabelecimentoPrisional = new EstabelecimentoPrisional();
        entidadeEstabelecimentoPrisional.setNomeEstabelecimentoPrisional(parametro);
        listaParametros =
            estruturaJudiciaria.listarEstabelecimentosPrisionaisOrdenadoPorNome(entidadeEstabelecimentoPrisional,
                                                                                paginacaoSeguestao);
        return montarSelectItemEtidades(listaParametros, EstabelecimentoPrisional.class);
    }

    public String incluirRegistroEstabelecimentoPrisional() {
        if (estabelecimentoPrisional != null && estabelecimentoPrisional.getIdEstabelecimentoPrisional() != null &&
            !contemEstabelecimentoPrisional(entidadePersistencia.getUnidadeEstabelecimentosPrisionais(),
                                            estabelecimentoPrisional)) {
            unidadeEstabelecimentoPrisional = new UnidadeEstabelecimentoPrisional();
            unidadeEstabelecimentoPrisional.setEstabelecimentoPrisional(estabelecimentoPrisional);
            entidadePersistencia.addEstabelecimentoPrisional(unidadeEstabelecimentoPrisional);

        }
        ResetUtils.reset((RichInputText) findComponent("inputTextUnidadeEstabelecimento")); //inputTextUnidadeEstabelecimento
        AdfFacesContext.getCurrentInstance().addPartialTarget((RichInputText) findComponent("inputTextUnidadeEstabelecimento"));
        sugestaoEstabelecimentoPrisional = "";
        estabelecimentoPrisional = null;
        unidadeEstabelecimentoPrisional = null;
        return null;
    }

    private boolean contemEstabelecimentoPrisional(List<UnidadeEstabelecimentoPrisional> unidadesEstabelecimentoPriosionais,
                                                   EstabelecimentoPrisional estabelecimentoPrisional) {
        boolean existente = false;
        if (unidadesEstabelecimentoPriosionais != null) {
            for (UnidadeEstabelecimentoPrisional unidadeAtual : unidadesEstabelecimentoPriosionais) {
                if (unidadeAtual.getEstabelecimentoPrisional().equals(estabelecimentoPrisional)) {
                    existente = true;
                }
            }
        }
        return existente;
    }

    public void selecionarEstabelecimentoPrisional(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        unidadeEstabelecimentoPrisional = (UnidadeEstabelecimentoPrisional) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerRegistroEstabelecimentoPrisional() {
        entidadePersistencia.getUnidadeEstabelecimentosPrisionais().remove(unidadeEstabelecimentoPrisional);
        unidadeEstabelecimentoPrisional = new UnidadeEstabelecimentoPrisional();
        return null;
    }


    public void validarPopupAlterar() {
        if (validarCamposObrigatorios()) {
            if (entidadePersistencia.getFlagTipoSituacao().equals(ConstantesMovjud.FLAG_SITUACAO_INATIVA)) {
                if (validarInativarUnidade()) {
                    if (entidadePersistencia.getDataCriacao() != null && entidadePersistencia.getDataFim() != null) {
                        salvarEntidade();
                        getPopUpAlterarUnidade().hide();
                        popUpConfirmacao("popupInfoAlterado");
                        pesquisarEntidade();
                    }

                } else {
                    mensagemErro(AppBundleProperties.getString("msg.unidade.inativarUnidade"));
                }
            } else {
                salvarEntidade();
                getPopUpAlterarUnidade().hide();
                popUpConfirmacao("popupInfoAlterado");
                pesquisarEntidade();
            }
        } else {
            mensagemErro(AppBundleProperties.getString("msg.unidade.camposObrigatorios"));
        }
        //return null;
    }

    public void salvarEntidade() {
        apagarListasAposMudancaTiposLocal();
        estruturaJudiciaria.salvarUnidade(entidadePersistencia);
    }

    public void apagarListasAposMudancaTiposLocal() {
        // LIMPA AS LISTAS QUE PASSARAM A NAO EXISTIR COM MUDANCA DE TIPO LOCAIS
        if (entidadePersistencia.getTipoLocal().getFlagAnexo().equals(ConstantesMovjud.FLAG_SITUACAO_NAO) &&
            entidadePersistencia.getUnidadesAnexa() != null) {
            for (int i = entidadePersistencia.getUnidadesAnexa().size() - 1; i >= 0; i--) {
                entidadePersistencia.getUnidadesAnexa().remove(i);
            }

        }
        if (entidadePersistencia.getTipoLocal().getFlagUnidadePrisional().equals(ConstantesMovjud.FLAG_SITUACAO_NAO) &&
            entidadePersistencia.getUnidadeEstabelecimentosPrisionais() != null) {
            for (int i = (entidadePersistencia.getUnidadeEstabelecimentosPrisionais().size() - 1); i >= 0; i--) {
                UnidadeEstabelecimentoPrisional unidadeEstabelecimentoPrisional =
                    entidadePersistencia.getUnidadeEstabelecimentosPrisionais().get(i);
                entidadePersistencia.removeEstabelecimentoPrisional(unidadeEstabelecimentoPrisional);
            }

        }
        if (entidadePersistencia.getTipoLocal().getFlagColegioRecursal().equals(ConstantesMovjud.FLAG_SITUACAO_NAO) &&
            entidadePersistencia.getForosRecursais() != null) {
            for (int i = (entidadePersistencia.getForosRecursais().size() - 1); i >= 0; i--) {
                entidadePersistencia.getForosRecursais().remove(i);
            }
        }
    }

    public boolean validarCamposObrigatorios() {
        boolean valido = true;
        if (entidadePersistencia.getNomeUnidade() != null && entidadePersistencia.getUsuario().getNome() != null &&
            entidadePersistencia.getTipoLocal().getNomeLocal() != null) {
            valido = true;
        } else {
            valido = false;
        }
        return valido;
    }

    public boolean validarInativarUnidade() {
        boolean valido = true;
        int quantidadeFormulariosVinculados = 0;
        List<FormularioVinculacao> listaFormularioVinculacao = entidadePersistencia.getFormulariosVinculacao();
        for (FormularioVinculacao item : listaFormularioVinculacao) {
            if (item.getFlagTipoSituacao().equals(ConstantesMovjud.FLAG_SITUACAO_ATIVA)) {
                quantidadeFormulariosVinculados++;
            }
        }
        if (quantidadeFormulariosVinculados > 0) {
            valido = false;
        } else {
            valido = true;
        }

        return valido;
    }

    public String validarPopupInserir() {
        boolean valido = true;
        String nomeEstabelecimentoPrisional = "";
        List<UnidadeEstabelecimentoPrisional> lista = entidadePersistencia.getUnidadeEstabelecimentosPrisionais();
        for (UnidadeEstabelecimentoPrisional item : lista) {
            if (item.getDataInicio() == null) {
                valido = false;
                nomeEstabelecimentoPrisional =
                    nomeEstabelecimentoPrisional + "<br>" +
                    item.getEstabelecimentoPrisional().getNomeEstabelecimentoPrisional();
            }
        }
        if (valido) {
            if (validarCamposObrigatorios()) {
                if (validarInativarUnidade()) {
                    salvarEntidade();
                    getPopUpInserirUnidade().hide();
                    popUpConfirmacao("popupInfoInclusao");
                    logger.info(AppBundleProperties.getString("msg.unidade.log") + entidadePersistencia.getNomeUnidade());
                    pesquisarEntidade();
                } else {
                    mensagemErro(AppBundleProperties.getString("msg.unidade.inativarUnidade"));
                }
            } else {
                mensagemErro(AppBundleProperties.getString("msg.unidade.camposObrigatorios"));
            }
        } else {
            mensagemErro(AppBundleProperties.getString("msg.unidade.estabelecimentoPricionalDataNula") + "<br>" +
                         nomeEstabelecimentoPrisional);
        }
        return null;
    }

    public void validarDataFim(ValueChangeEvent event) {
        idFim.setMinValue((Date) idCriacao.getValue());
        AdfFacesContext.getCurrentInstance().addPartialTarget(idCriacao);
        AdfFacesContext.getCurrentInstance().addPartialTarget(idFim);
    }

    private boolean verificarFormularioVinculado(List<FormularioVinculacao> listaFormularioVinculacao) {
        int quantidadeFormulariosVinculados = 0;
        for (FormularioVinculacao item : listaFormularioVinculacao) {
            if (item.getFlagTipoSituacao().equals(ConstantesMovjud.FLAG_SITUACAO_ATIVA)) {
                quantidadeFormulariosVinculados++;
            }
        }
        return false;
    }

    public void setSugestaoForo(String sugestaoForo) {
        this.sugestaoForo = sugestaoForo;
    }

    public String getSugestaoForo() {
        return sugestaoForo;
    }

    public void setSugestaoUsuario(String sugestaoUsuario) {
        this.sugestaoUsuario = sugestaoUsuario;
    }

    public String getSugestaoUsuario() {
        return sugestaoUsuario;
    }

    public void setListaTipoLocal(List<TipoLocal> listaTipoLocal) {
        this.listaTipoLocal = listaTipoLocal;
    }

    public List<TipoLocal> getListaTipoLocal() {
        return listaTipoLocal;
    }

    public void setSugestaoUnidade(String sugestaoUnidade) {
        this.sugestaoUnidade = sugestaoUnidade;
    }

    public String getSugestaoUnidade() {
        return sugestaoUnidade;
    }

    public void setUnidadeAnexa(Unidade unidadeAnexa) {
        this.unidadeAnexa = unidadeAnexa;
    }

    public Unidade getUnidadeAnexa() {
        return unidadeAnexa;
    }

    public void setSugestaoForoRecursal(String sugestaoForoRecursal) {
        this.sugestaoForoRecursal = sugestaoForoRecursal;
    }

    public String getSugestaoForoRecursal() {
        return sugestaoForoRecursal;
    }

    public void setForoRecursal(Foro foroRecursal) {
        this.foroRecursal = foroRecursal;
    }

    public Foro getForoRecursal() {
        return foroRecursal;
    }

    public void setEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
        this.estabelecimentoPrisional = estabelecimentoPrisional;
    }

    public EstabelecimentoPrisional getEstabelecimentoPrisional() {
        return estabelecimentoPrisional;
    }

    public void setSugestaoEstabelecimentoPrisional(String sugestaoEstabelecimentoPrisional) {
        this.sugestaoEstabelecimentoPrisional = sugestaoEstabelecimentoPrisional;
    }

    public String getSugestaoEstabelecimentoPrisional() {
        return sugestaoEstabelecimentoPrisional;
    }

    public void setPopUpAlterarUnidade(RichPopup popUpAlterarUnidade) {
        this.popUpAlterarUnidade = popUpAlterarUnidade;
    }

    public RichPopup getPopUpAlterarUnidade() {
        return popUpAlterarUnidade;
    }

    public void setPopUpInserirUnidade(RichPopup popUpInserirUnidade) {
        this.popUpInserirUnidade = popUpInserirUnidade;
    }

    public RichPopup getPopUpInserirUnidade() {
        return popUpInserirUnidade;
    }

    public void setIdCriacao(RichInputDate idCriacao) {
        this.idCriacao = idCriacao;
    }

    public RichInputDate getIdCriacao() {
        return idCriacao;
    }

    public void setIdFim(RichInputDate idFim) {
        this.idFim = idFim;
    }

    public RichInputDate getIdFim() {
        return idFim;
    }

    public void setInputTextUnidadeAnexaSugestao(RichInputText inputTextUnidadeAnexaSugestao) {
        this.inputTextUnidadeAnexaSugestao = inputTextUnidadeAnexaSugestao;
    }

    public RichInputText getInputTextUnidadeAnexaSugestao() {
        return inputTextUnidadeAnexaSugestao;
    }

    public void setInputTextUnidadeEstabelecimento(RichInputText inputTextUnidadeEstabelecimento) {
        this.inputTextUnidadeEstabelecimento = inputTextUnidadeEstabelecimento;
    }

    public RichInputText getInputTextUnidadeEstabelecimento() {
        return inputTextUnidadeEstabelecimento;
    }

    public void setInputTextForoRecursal(RichInputText inputTextForoRecursal) {
        this.inputTextForoRecursal = inputTextForoRecursal;
    }

    public RichInputText getInputTextForoRecursal() {
        return inputTextForoRecursal;
    }

    public String cancelarPopupInserir() {
        RichPopup popup = (RichPopup) findComponent("popUpInserirUnidade");
        popup.hide();
        pesquisarEntidade();
        ResetUtils.reset(popup);
        return null;
    }

    public String cancelarPopup() {
        RichPopup popup = (RichPopup) findComponent("popUpAlterarUnidade");
        popup.hide();
        pesquisarEntidade();
        ResetUtils.reset(popup);
        return null;
    }

    public String salvarPopupUnidade() {

        boolean valido = true;
        String nomeEstabelecimentoPrisional = "";
        List<UnidadeEstabelecimentoPrisional> lista = entidadePersistencia.getUnidadeEstabelecimentosPrisionais();
        for (UnidadeEstabelecimentoPrisional item : lista) {
            if (item.getDataInicio() == null) {
                valido = false;
                nomeEstabelecimentoPrisional =
                    nomeEstabelecimentoPrisional + "<br>" +
                    item.getEstabelecimentoPrisional().getNomeEstabelecimentoPrisional();
            }
        }
        if (valido) {
            validarPopupAlterar();
        } else {
            mensagemErro(AppBundleProperties.getString("msg.unidade.estabelecimentoPricionalDataNula") + "<br>" +
                         nomeEstabelecimentoPrisional);
        }

        return null;
    }
}
