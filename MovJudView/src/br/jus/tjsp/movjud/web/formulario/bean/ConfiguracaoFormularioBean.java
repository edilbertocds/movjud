package br.jus.tjsp.movjud.web.formulario.bean;

import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.formula.type.FuncaoCalculoType;
import br.jus.tjsp.movjud.business.formula.utils.FormulaCalculo;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.CompetenciaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormulaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ItemSelecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.MetadadoSituacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SegmentoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoMateriaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.TipoRegraDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioUtils;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.types.MetadadosTipoSituacaoType;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.formulario.helper.ComponentesHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.model.ChildPropertyTreeModel;
import oracle.adf.view.rich.util.ResetUtils;

import org.apache.log4j.Logger;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySetTreeImpl;

public class ConfiguracaoFormularioBean extends BaseBean<FormularioDTO> {

    private static final String MENSAGEM_AREA_SEGMENTO_VAZIO =
        AppBundleProperties.getString("msg.formulario.areaesegmentovazio");

    final static Logger logger = Logger.getLogger(ConfiguracaoFormularioBean.class);

    private FormularioService formularioService;

    private RichPanelHeader painelPricipal;
    private RichSelectOneChoice tipoSecao;

    private CampoDTO campoSelecionado;
    private CampoDTO campoSelecionadoClone;
    private boolean inverterRegraCampo;
    private GrupoDTO grupoSelecionado;
    private GrupoDTO grupoSelecionadoClone;
    private boolean inverterRegraGrupo;
    private FormularioDTO formulario;
    private FormularioDTO formularioHistorico;
    private SecaoDTO secaoMagistrado;
    private SecaoDTO secaoEstabelecimentoPrisional;
    private SecaoDTO secaoDadosUnidade;

    private CampoDTO campoSugerido;
    private ValidacaoDTO validacao;
    private ValidacaoDTO validacaoSelecionada;
    private ValidacaoDTO validacaoSelecionadaEdicao;
    private List<ValidacaoDTO> listaValidacoesCampo;
    private List<CompetenciaDTO> listaCompetencias;
    private List<CompetenciaDTO> listaCompetenciasCopia;
    private List<CompetenciaDTO> listaCompetenciasFormulario;
    private List<CompetenciaDTO> listaCompetenciasFormularioSemEdicao;
    private List<MateriaDTO> listaAreas;
    private List<SecaoType> listaTiposSecao;
    private List<SegmentoDTO> listaSegmentos;
    private CompetenciaDTO competenciaFormulario;
    private CompetenciaDTO competenciaSelecionada;
    private String sugestaoCampo;
    private String item;
    private ItemSelecaoDTO itemRemover;
    private List<FuncaoCalculoType> listaFuncoes;
    private List<CampoDTO> listaCamposFormulario;
    private List<CampoDTO> listaCamposFormularioFull;
    private CampoDTO campoValidacaoEdicao;
    private CampoDTO campoValidacao;
    private CampoDTO campoFormula;
    private List<TipoMateriaDTO> listaMaterias;
    private List<TipoMateriaDTO> listaMateriasSecao;
    private List<SelectItem> listaTipoCampo;
    private TipoMateriaDTO materiaAdicionar;
    private TipoMateriaDTO materiaVincular;
    private TipoMateriaDTO materiaSelecionada;
    private List<MetadadoSituacaoFormularioDTO> listaMetadadoSituacoesFormulario;
    private List<TipoRegraDTO> listaTipoRegra;
    private TipoRegraDTO tipoRegraDTO;
    private RichSelectOneChoice socFuncaoValidacao;
    private RichSelectOneChoice socFuncaoFormula;
    private RichSelectOneChoice socCamposValidacao;
    private RichInputText itExpressaoFormula;
    private RichInputText itExpressaoValidacao;
    private RichInputText itMensagem;
    private RichTable t3;
    private RichSelectOneChoice socTipoValidacao;
    private RichSelectOneChoice socCampoValidacao;
    private String codigoValidacao;
    private String codigoFuncao;
    private String expressao;
    private String mensagem;
    private RichSelectOneChoice socCampoFormula;
    private RichPopup dialogConfigCampo;
    private UIComponent secaoExcluir;
    private GrupoDTO grupoRemoverCampo;
    private List<UIComponent> listaComponentes;
    private UIComponent parentExcluir;
    private UIComponent grupoExcluir;
    private RichPopup popupConfirmacaoExclusaoCampo;


    //Metodos em uso
    public ConfiguracaoFormularioBean() {
        formularioService = getBean(FormularioService.class);

    }

    @PostConstruct
    public void init() {
        super.init();
        listaTiposSecao = new ArrayList<SecaoType>();
        listaTiposSecao.addAll(Arrays.asList(SecaoType.values()));
        listaTiposSecao.remove(SecaoType.DADOS_UNIDADES);
        listaFuncoes = Arrays.asList(FuncaoCalculoType.values());
        listaCompetencias = formularioService.listarTipoCompetencia();
        listaAreas = formularioService.listarTipoAreas();
        listaSegmentos = formularioService.listarTipoSegmentos();
        listaMetadadoSituacoesFormulario = formularioService.listarMetadadosTipoSituacao();
        listaTipoCampo = carregarListaTipoCampo();
    }

    //Metodos criacao formulario
    public void initInserirMetadadosFormulario(ActionEvent actionEvent) {
        visualizar = false;
        painelPricipal = new RichPanelHeader();
        painelPricipal.setId(ComponentesHelper.PAINEL_PRINCIPAL);
        formulario = new FormularioDTO();
        formulario.setVersao(1L);
        secaoMagistrado = new SecaoDTO();
        secaoEstabelecimentoPrisional = new SecaoDTO();
        validacao = new ValidacaoDTO();
        formulario.getListaSecoes().add(FormularioUtils.criarSecaoPorTipo(SecaoType.DADOS_UNIDADES, formulario));
        addComponent(painelPricipal,
                     ComponentesHelper.criarPainelSecao(SecaoType.DADOS_UNIDADES, new RichPanelBox(), null,
                                                        visualizar));
    }

    public void liberarVersaoFormulario(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.formulario.logVersaoLiberada") + entidadePersistencia.getNomeFormulario() +" / "+ AppBundleProperties.getString("msg.formulario.logNumVersaoLiberada") + entidadePersistencia.getVersao());
        formulario = entidadeOriginal;
        formulario.setMetadadoSituacaoFormularioDTO(MetadadosTipoSituacaoType.recuperaSituacaoFormularioDTOPorCodigo(listaMetadadoSituacoesFormulario,
                                                                                                                     MetadadosTipoSituacaoType.EM_USO));
        formularioService.liberarVersaoMetadadosFormulario(formulario);
        filtrarEntidade();
    }

    public void initVisualizarMetadadosFormulario(ActionEvent actionEvent) {
        visualizar = true;
        renderizarFormularioBaseadoEmDTO();
    }

    public void initBaixarMetadadosFormulario(ActionEvent actionEvent) {
        visualizar = true;
        renderizarFormularioBaseadoEmDTO();
    }

    public void initAlterarMetadadosFormulario(ActionEvent actionEvent) {
        visualizar = false;
        renderizarFormularioBaseadoEmDTO();
    }

    public void renderizarFormularioBaseadoEmDTO() {
        painelPricipal = new RichPanelHeader();
        painelPricipal.setId(ComponentesHelper.PAINEL_PRINCIPAL);
        tipoSecao = new RichSelectOneChoice();
        formulario = entidadePersistencia;
        formularioHistorico = formularioService.criarCopiaFomularioDTO(entidadePersistencia);
        //formulario = formularioService.recuperarMetadadosFormulario(formulario);
        formularioHistorico = formularioService.recuperarMetadadosFormulario(formularioHistorico);
        listaCamposFormularioFull = FormularioUtils.recuperarMetadadosCampoFormulario(formulario);
        List<SecaoDTO> listaSecoes = new ArrayList<SecaoDTO>();
        listaSecoes.addAll(formulario.getListaSecoes());
        for (SecaoDTO secao : listaSecoes) {
            formulario = desabilitarEdicaoCamposReaproveitados(formulario);
            RichPanelBox painelSecao = ComponentesHelper.criarPainelSecaoPorTipo(secao, formulario, visualizar);
            painelPricipal.getChildren().add(painelSecao);
        }
    }

    public FormularioDTO desabilitarEdicaoCamposReaproveitados(FormularioDTO formulario) {
        for (SecaoDTO secaoDTO : formulario.getListaSecoes()) {
            for (GrupoDTO grupoDTO : secaoDTO.getListaGrupos()) {
                for (CampoDTO campoDTO : grupoDTO.getListaCampos()) {
                    Long emUso = formularioService.countUtilizacaoCampo(campoDTO);
                    campoDTO.setDesabilitado(isCampoDesabilitado(emUso, campoDTO));
                    campoDTO = desabilitarEdicaoCamposReaproveitados(campoDTO);
                }
            }
        }
        return formulario;
    }

    public boolean isCampoDesabilitado(Long totalIncidencias, CampoDTO campoDTO) {
        //Metodo precisa ser removido
        boolean emUso = false;
        if (totalIncidencias == null) {
            emUso = false;
        } else if (totalIncidencias > 1) {
            //emUso = true;
            emUso = false;
        } else if (totalIncidencias == 1 && listaCamposFormularioFull.contains(campoDTO)) {
            emUso = false;
        } else {
            //emUso = true;
            emUso = false;
        }
        return emUso;
    }

    public CampoDTO desabilitarEdicaoCamposReaproveitados(CampoDTO campoDTO) {
        if (campoDTO != null && campoDTO.getListaCampos() != null) {
            for (CampoDTO subCampoDTO : campoDTO.getListaCampos()) {
                Long emUso = formularioService.countUtilizacaoCampo(subCampoDTO);
                subCampoDTO.setDesabilitado(isCampoDesabilitado(emUso, campoDTO));
                subCampoDTO = desabilitarEdicaoCamposReaproveitados(subCampoDTO);
            }
        }
        return campoDTO;
    }
    //Metodos da secao
    public String adicionarSecao(ActionEvent actionEvent) {
        if (tipoSecao != null && tipoSecao.getValue() != null && !tipoSecao.getValue().toString().equals("")) {
            boolean secaoExistente = false;
            for (SecaoDTO secaoDTO : formulario.getListaSecoes()) {
                if (secaoDTO.getCodigoSecao().equals(((SecaoType) tipoSecao.getValue()).getCodigoSecao())) {
                    secaoExistente = true;
                    break;
                }
            }
            if (!secaoExistente) {
                RichPanelBox painelSecao = null;
                SecaoDTO secaoDTO = FormularioUtils.criarSecaoPorTipo((SecaoType) tipoSecao.getValue(), formulario);
                formulario.getListaSecoes().add(secaoDTO);
                painelSecao = ComponentesHelper.criarPainelSecaoPorTipo(secaoDTO, formulario, visualizar);
                addComponent(painelPricipal, painelSecao);
            }
        }
        return null;
    }

    public String getNomeSecao() {
        return FormularioUtils.encontrarSecaoPorCodigo(formulario, secaoExcluir.getId()).getLabelSecao();
    }

    public String confirmarRemoverSecao(ActionEvent actionEvent) {
        secaoExcluir = actionEvent.getComponent().getParent().getParent();
        RichPopup popupConfirmacaoExclusao = (RichPopup) findComponent("popupExcluirSecao");
        popupConfirmacaoExclusao.show(new RichPopup.PopupHints());
        return null;
    }

    public String RemoverSecao(DialogEvent dialogEvent) {
        formulario.getListaSecoes().remove(FormularioUtils.encontrarSecaoPorCodigo(formulario, secaoExcluir.getId()));
        painelPricipal.getChildren().remove(secaoExcluir);
        atualizarComponenteDeTela(painelPricipal);
        return null;
    }


    //Dialog Secao Area
    public void initDialogConfigurarSecaoMaterias(PopupFetchEvent popupFetchEvent) {
        SecaoDTO secaoDTO = FormularioUtils.encontrarSecaoPorCodigo(formulario, SecaoType.MATERIA.getCodigoSecao());
        if (secaoDTO != null) {
            listaMateriasSecao = new ArrayList<TipoMateriaDTO>();
            listaMateriasSecao.addAll(secaoDTO.getListaMaterias());
        }
        consultarMaterias();
        materiaSelecionada = new TipoMateriaDTO();
        materiaAdicionar = new TipoMateriaDTO();
        //TO_DO Remover da lista os itens ja selecionados
    }

    public void consultarMaterias() {
        listaMaterias = formularioService.listarTipoMaterias();
    }

    public void adicionarNovaMateria(DialogEvent dialogEvent) {
        formularioService.salvarTipoMateriaDTO(materiaAdicionar);
        consultarMaterias();
        materiaAdicionar = new TipoMateriaDTO();
    }

    public void vincularMateriaFormulario(ActionEvent actionEvent) {
        listaMateriasSecao.add(materiaVincular);
        listaMaterias.remove(materiaVincular);
    }

    public void selecionarLinhaTabelaMaterias(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        materiaSelecionada = (TipoMateriaDTO) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public void desvincularMateriaFormulario(ActionEvent actionEvent) {
        listaMaterias.add(materiaSelecionada);
        listaMateriasSecao.remove(materiaSelecionada);
    }

    public void adicionarConfiguracaoSecaoMateria(DialogEvent dialogEvent) {
        FormularioUtils.encontrarSecaoPorCodigo(formulario,
                                                SecaoType.MATERIA.getCodigoSecao()).setListaMaterias(listaMateriasSecao);
    }
    //Fim Dialog Secao Materia

    //inicio Dialog Secao Magistrado
    public void initDialogSecaoMagistrado(PopupFetchEvent popupFetchEvent) {
        secaoMagistrado =
            new SecaoDTO(FormularioUtils.encontrarSecaoPorCodigo(formulario, SecaoType.MAGISTRADO.getCodigoSecao()));
    }

    public void adicionarConfiguracaoSecaoMagistrado(DialogEvent dialogEvent) {
        FormularioUtils.encontrarSecaoPorCodigo(formulario,
                                                SecaoType.MAGISTRADO.getCodigoSecao()).setValuesSecaoMagistrado(secaoMagistrado);
    }
    //Fim Dialog Secao Magistrado

    //inicio Dialog Secao Dados Unidade
    public void initDialogSecaoDadosUnidade(PopupFetchEvent popupFetchEvent) {
        secaoDadosUnidade = FormularioUtils.encontrarSecaoPorCodigo(formulario, SecaoType.DADOS_UNIDADES.getCodigoSecao());
    }
    public void adicionarConfiguracaoSecaoDadosUnidade(DialogEvent dialogEvent) {
        FormularioUtils.encontrarSecaoPorCodigo(formulario,
                                                SecaoType.DADOS_UNIDADES.getCodigoSecao()).setValuesSecaoDadosUnidade(secaoDadosUnidade);
    }
    //fim Dialog Secao Dados Unidade

    //inicio Dialog Secao Estabelecimentos Prisionais
    public void initDialogSecaoEstabelecimentoPrisional(PopupFetchEvent popupFetchEvent) {
        secaoEstabelecimentoPrisional =
            new SecaoDTO(FormularioUtils.encontrarSecaoPorCodigo(formulario,
                                                                 SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao()));
    }

    public void adicionarConfiguracaoSecaoEstabelecimentoPrisional(DialogEvent dialogEvent) {
        FormularioUtils.encontrarSecaoPorCodigo(formulario,
                                                SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao()).setValuesSecaoEstabelecimentoPrisional(secaoEstabelecimentoPrisional);
    }
    //Fim Dialog Secao Estabelecimentos Prisionais
    //Fim Metodos da secao

    //Metodos Grupo
    public String adicionarGrupo(ActionEvent actionEvent) {
        RichPanelBox boxSecao = ((RichPanelBox) actionEvent.getComponent().getParent().getParent());
        SecaoDTO secaoDTO = FormularioUtils.encontrarSecaoPorCodigo(formulario, boxSecao.getId());
        RichPanelBox boxGrupo = ComponentesHelper.criarGrupoComCampos(null, secaoDTO, formulario, visualizar);
        FormularioUtils.gerarCodigoGrupos(secaoDTO);
        ComponentesHelper.gerarIdsComponentesGrupos(boxSecao, secaoDTO);
        addComponent(boxSecao, boxGrupo);
        return null;
    }

    public String confirmarRemoverGrupo(ActionEvent actionEvent) {
        parentExcluir = actionEvent.getComponent().getParent().getParent().getParent();
        grupoExcluir = actionEvent.getComponent().getParent().getParent();
        RichPopup popupConfirmacaoExclusao = (RichPopup) findComponent("popupExcluirGrupo");
        popupConfirmacaoExclusao.show(new RichPopup.PopupHints());
        return null;
    }

    public String getNomeGrupo() {
        return FormularioUtils.encontrarGrupoPorCodigo(formulario, grupoExcluir.getId()).getLabelGrupo();
    }

    public String removerGrupo(DialogEvent dialogEvent) {
        SecaoDTO secaoDTO = FormularioUtils.encontrarSecaoPorCodigo(formulario, parentExcluir.getId());
        secaoDTO.getListaGrupos().remove(FormularioUtils.encontrarIndiceGrupo(formulario, parentExcluir.getId(),
                                                                              grupoExcluir.getId()));
        parentExcluir.getChildren().remove(grupoExcluir);
        FormularioUtils.gerarCodigoGrupos(secaoDTO);
        ComponentesHelper.gerarIdsComponentesGrupos(parentExcluir, secaoDTO);
        atualizarComponenteDeTela(parentExcluir);
        return null;
    }

    public String ordenarGrupoAbaixo(ActionEvent actionEvent) {
        UIComponent parent = actionEvent.getComponent().getParent().getParent().getParent();
        UIComponent grupo = actionEvent.getComponent().getParent().getParent();
        SecaoDTO secaoDTO = FormularioUtils.encontrarSecaoPorCodigo(formulario, parent.getId());
        GrupoDTO grupoDTO = FormularioUtils.encontrarGrupoPorCodigo(formulario, grupo.getId());
        if (parent.getChildren().contains(grupo)) {
            int indice = parent.getChildren().indexOf(grupo);
            if (indice >= 0 && indice + 1 < parent.getChildren().size()) {
                parent.getChildren().remove(grupo);
                parent.getChildren().add(indice + 1, grupo);
                secaoDTO.getListaGrupos().remove(grupoDTO);
                secaoDTO.getListaGrupos().add(indice + 1, grupoDTO);
            }
        }
        FormularioUtils.gerarCodigoGrupos(secaoDTO);
        ComponentesHelper.gerarIdsComponentesGrupos(parent, secaoDTO);
        atualizarComponenteDeTela(parent);
        return null;
    }

    public String ordenarGrupoAcima(ActionEvent actionEvent) {
        UIComponent parent = actionEvent.getComponent().getParent().getParent().getParent();
        UIComponent grupo = actionEvent.getComponent().getParent().getParent();
        SecaoDTO secaoDTO = FormularioUtils.encontrarSecaoPorCodigo(formulario, parent.getId());
        GrupoDTO grupoDTO = FormularioUtils.encontrarGrupoPorCodigo(formulario, grupo.getId());
        if (parent.getChildren().contains(grupo)) {
            int indice = parent.getChildren().indexOf(grupo);
            if (indice > 0) {
                parent.getChildren().remove(grupo);
                parent.getChildren().add(indice - 1, grupo);
                secaoDTO.getListaGrupos().remove(grupoDTO);
                secaoDTO.getListaGrupos().add(indice - 1, grupoDTO);
            }
        }
        FormularioUtils.gerarCodigoGrupos(secaoDTO);
        ComponentesHelper.gerarIdsComponentesGrupos(parent, secaoDTO);
        atualizarComponenteDeTela(parent);
        return null;
    }

    //Configuracao Grupo
    public void initDialogConfiguracaoGrupo(ActionEvent actionEvent) {
        tipoRegraDTO = new TipoRegraDTO();
        consultarTipoRegras();
        UIComponent grupo = actionEvent.getComponent().getParent().getParent();
        abrirPopupConfiguracao(grupo, "dialogConfigGrupo");
    }
    //Configuracao Grupo

    public void abrirPopupConfiguracao(UIComponent grupo, String popup) {
        RichPopup.PopupHints hint = new RichPopup.PopupHints();
        RichPopup popupConfigurar = (RichPopup) findComponent(popup);
        grupoSelecionado = FormularioUtils.encontrarGrupoPorCodigo(formulario, grupo.getId());
        grupoSelecionadoClone = (GrupoDTO) grupoSelecionado.clonar();
        if (grupoSelecionado.getTipoRegraDTO() == null) {
            inverterRegraGrupo = false;
        } else {
            inverterRegraGrupo = grupoSelecionado.getTipoRegraDTO().isInverterRegra();
        }
        popupConfigurar.show(hint);
    }
    //Fim Metodos Grupo

    //Metodos Campo
    public void selecionarLinhaEmTabelaArvore(SelectionEvent selectionEvent) {
        RichTreeTable treeTable = (RichTreeTable) selectionEvent.getSource();
        for (Object key : treeTable.getSelectedRowKeys()) {
            treeTable.setRowKey(key);
            campoSelecionado = ((CampoDTO) treeTable.getRowData());
            campoSelecionadoClone = (CampoDTO) campoSelecionado.clonar();
            break;
        }
        treeTable.getSelectedRowKeys().clear();
    }

    public String adicionarCampo(ActionEvent actionEvent) {
        GrupoDTO grupoAddCampo =
            FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                    actionEvent.getComponent().getParent().getParent().getId());

        grupoAddCampo.getListaCampos().add(FormularioUtils.criarCampo(grupoAddCampo.getId(), null,
                                                                      Long.valueOf(grupoAddCampo.getListaCampos().size()),
                                                                      null, false, grupoAddCampo.getListaCampos(),
                                                                      grupoAddCampo, null));
        grupoAddCampo = FormularioUtils.gerarCodigoCampos(grupoAddCampo);

        atualizarValoresDaTabelaArvore(actionEvent.getComponent().getParent().getParent().getChildren(), grupoAddCampo);
        return null;
    }

    public String adicionarSubCampo(ActionEvent actionEvent) {
        if(campoSelecionado!=null){
            GrupoDTO grupoAddCampo =
                FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                        actionEvent.getComponent().getParent().getParent().getId());
            CampoDTO campoPai = FormularioUtils.encontrarCampo(formulario, campoSelecionado.getCodigoCampo());
            if (!campoPai.isUltimoFilho()) {
                if (campoSelecionado.getListaCampos() != null) {
                    campoPai.getListaCampos().add(FormularioUtils.criarCampo(campoSelecionado.getCodigoCampo(),
                                                                             campoSelecionado.getIndiceCampo(),
                                                                             Long.valueOf(campoPai.getListaCampos().size()),
                                                                             null, false, campoPai.getListaCampos(), null,
                                                                             campoSelecionado));
                } else {
                    campoPai.getListaCampos().add(FormularioUtils.criarCampo(campoSelecionado.getCodigoCampo(),
                                                                             campoSelecionado.getIndiceCampo(),
                                                                             Long.valueOf(campoPai.getListaCampos().size() +
                                                                                          1), null, false,
                                                                             campoPai.getListaCampos(), null,
                                                                             campoSelecionado));
                }
                grupoAddCampo = FormularioUtils.gerarCodigoCampos(grupoAddCampo);
                atualizarValoresDaTabelaArvore(actionEvent.getComponent().getParent().getParent().getChildren(),
                                               grupoAddCampo);
            }
        }
        return null;
    }

    public String getNomeCampo() {
        return FormularioUtils.encontrarCampo(formulario, campoSelecionado.getId()).getLabelCampo();
    }

    public String confirmarRemoverCampo(ActionEvent actionEvent) {
        grupoRemoverCampo =
            FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                    actionEvent.getComponent().getParent().getParent().getParent().getParent().getId());
        listaComponentes = actionEvent.getComponent().getParent().getParent().getParent().getParent().getChildren();

        popupConfirmacaoExclusaoCampo = (RichPopup) findComponent("popupExcluirCampo");
        popupConfirmacaoExclusaoCampo.show(new RichPopup.PopupHints());
        return null;
    }

    public String removerCampo(DialogEvent dialogEvent) {
        if (grupoRemoverCampo.getListaCampos().contains(campoSelecionado) &&
            grupoRemoverCampo.getListaCampos().size() > 1) {
            grupoRemoverCampo.getListaCampos().remove(campoSelecionado);
        } else if (grupoRemoverCampo.getListaCampos().contains(campoSelecionado) &&
                   grupoRemoverCampo.getListaCampos().size() == 1) {
            popupConfirmacaoExclusaoCampo.hide();
            mensagemErro(AppBundleProperties.getString("msg.formulario.erroExclusaoCampoUnico"));
        } else {
            FormularioUtils.encontrarCampoPai(campoSelecionado.getCodigoCampo(),
                                              grupoRemoverCampo).getListaCampos().remove(campoSelecionado);
        }
        //grupoRemoverCampo = FormularioUtils.gerarIndices(grupoRemoverCampo);
        grupoRemoverCampo = FormularioUtils.gerarCodigoCampos(grupoRemoverCampo);
        campoSelecionado = null;
        atualizarValoresDaTabelaArvore(listaComponentes, grupoRemoverCampo);
        return null;
    }

    public String ordenarCampoAcima(ActionEvent actionEvent) {
        GrupoDTO grupoOrdenarCampo =
            FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                    actionEvent.getComponent().getParent().getParent().getParent().getParent().getId());
        if (grupoOrdenarCampo.getListaCampos().contains(campoSelecionado)) {
            int indice = grupoOrdenarCampo.getListaCampos().indexOf(campoSelecionado);
            if (indice > 0) {
                grupoOrdenarCampo.getListaCampos().remove(campoSelecionado);
                grupoOrdenarCampo.getListaCampos().add(indice - 1, campoSelecionado);
            }

        } else {
            CampoDTO campoOrdenar =
                FormularioUtils.encontrarCampoPai(campoSelecionado.getCodigoCampo(), grupoOrdenarCampo);
            int indice = campoOrdenar.getListaCampos().indexOf(campoSelecionado);
            if (indice > 0) {
                campoOrdenar.getListaCampos().remove(campoSelecionado);
                campoOrdenar.getListaCampos().add(indice - 1, campoSelecionado);
            }
        }
        //grupoOrdenarCampo = FormularioUtils.gerarIndices(grupoOrdenarCampo);
        grupoOrdenarCampo = FormularioUtils.gerarCodigoCampos(grupoOrdenarCampo);
        atualizarValoresDaTabelaArvore(actionEvent.getComponent().getParent().getParent().getParent().getParent().getChildren(),
                                       grupoOrdenarCampo);
        return null;
    }

    public String ordenarCampoAbaixo(ActionEvent actionEvent) {
        GrupoDTO grupoOrdenarCampo =
            FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                    actionEvent.getComponent().getParent().getParent().getParent().getParent().getId());
        if (grupoOrdenarCampo.getListaCampos().contains(campoSelecionado)) {
            int indice = grupoOrdenarCampo.getListaCampos().indexOf(campoSelecionado);
            if (indice >= 0 && indice + 1 < grupoOrdenarCampo.getListaCampos().size()) {
                grupoOrdenarCampo.getListaCampos().remove(campoSelecionado);
                grupoOrdenarCampo.getListaCampos().add(indice + 1, campoSelecionado);
            }
        } else {
            CampoDTO campoOrdenar =
                FormularioUtils.encontrarCampoPai(campoSelecionado.getCodigoCampo(), grupoOrdenarCampo);
            int indice = campoOrdenar.getListaCampos().indexOf(campoSelecionado);
            if (indice >= 0 && indice + 1 < campoOrdenar.getListaCampos().size()) {
                campoOrdenar.getListaCampos().remove(campoSelecionado);
                campoOrdenar.getListaCampos().add(indice + 1, campoSelecionado);
            }
        }
        //grupoOrdenarCampo = FormularioUtils.gerarIndices(grupoOrdenarCampo);
        grupoOrdenarCampo = FormularioUtils.gerarCodigoCampos(grupoOrdenarCampo);
        atualizarValoresDaTabelaArvore(actionEvent.getComponent().getParent().getParent().getParent().getParent().getChildren(),
                                       grupoOrdenarCampo);
        return null;
    }

    public void alterarTipoCampo(ValueChangeEvent valueChangeEvent) {
        FormularioUtils.encontrarCampo(formulario,
                                       campoSelecionado.getCodigoCampo()).setTipoCampo(((TipoCampoType) valueChangeEvent.getNewValue()));
        campoSelecionadoClone = new CampoDTO(campoSelecionado);
    }

    public void editarCampoReaproveitado(DialogEvent dialogEvent) {
        campoSelecionadoClone.setDesabilitado(false);
        listaCamposFormulario = FormularioUtils.recuperarMetadadosCampoFormularioAplicaveisFormula(formulario);
        listaCamposFormularioFull = FormularioUtils.recuperarMetadadosCampoFormulario(formulario);
    }

    public void alterarTituloCampo(ValueChangeEvent valueChangeEvent) {
        FormularioUtils.encontrarCampo(formulario,
                                       campoSelecionado.getCodigoCampo()).setLabelCampo(valueChangeEvent.getNewValue().toString());
        GrupoDTO grupoCampo =
            FormularioUtils.encontrarGrupoPorCodigo(formulario,
                                                    valueChangeEvent.getComponent().getParent().getParent().getParent().getId());
        atualizarValoresDaTabelaArvore(valueChangeEvent.getComponent().getParent().getParent().getParent().getChildren(),
                                       grupoCampo);
    }

    private void atualizarValoresDaTabelaArvore(List<UIComponent> listaComponentesDoGrupo, GrupoDTO grupoDTO) {
        for (UIComponent component : listaComponentesDoGrupo) {
            if (component.getId().equals(ComponentesHelper.getIdTreeTable(component.getParent().getId()))) {
                RichTreeTable tree = (RichTreeTable) component;
                FormularioUtils.gerarCodigoCampos(grupoDTO);
                ChildPropertyTreeModel charatcerVal =
                    new ChildPropertyTreeModel(grupoDTO.getListaCampos(), ComponentesHelper.OBJETO_NAVEGACAO_TREE);
                tree.setValue(charatcerVal);
                tree.setDisclosedRowKeys(new RowKeySetTreeImpl(true));
                atualizarComponenteDeTela(tree);
            }
        }
    }

    //Fim Metodos Campo

    public String persistirEntidade() {
        try {
            // <epr 20180831: remover obrigatoriedade de area e segmento (item 165)>
            // if (formulario.getArea() != null && formulario.getSegmento() != null) {
            // </epr 20180831: remover obrigatoriedade de area e segmento (item 165)>
                if (formulario.getMetadadoSituacaoFormularioDTO() != null &&
                    formulario.getMetadadoSituacaoFormularioDTO().getIdentificadorSituacaoFormulario().equals(MetadadosTipoSituacaoType.EM_USO.getCodigo())) {
                    formularioService.salvarMetadadosFormulario(formulario, formularioHistorico);
                } else {
                    formularioService.salvarMetadadosFormulario(formulario);
                }
                return FormularioUtils.REDIRECT_GERENCIAR_FORMULARIO;
            /*
            <epr 20180831: remover obrigatoriedade de area e segmento (item 165)>
            } else {
                mensagemErro(MENSAGEM_AREA_SEGMENTO_VAZIO);
            }
            </epr 20180831: remover obrigatoriedade de area e segmento (item 165)>
            */
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e);
        }
        return null;
    }
    //Fim metodos em uso

    //Metodos tela de consulta
    @Override
    /*public String pesquisarEntidade() {
        listaEntidade = formularioService.listarMetadadosFormulariosComFiltro(entidadeFiltro, paginacao);
        return null;
    }*/
    public String pesquisarEntidade() {
        listaEntidade = formularioService.listarMetadadosFormulariosComFiltro(entidadeFiltro, paginacao);
        if(listaEntidade != null && !listaEntidade.isEmpty()){
        for (FormularioDTO formularioDTO : listaEntidade) {
            formularioService.recuperarMetadadosFormulario(formularioDTO);
        }}
        return null;
    }
    //Fim tela de consulta

    //Metodos dialog configurar Formulario
    public void initDialogConfiguracaoFormulario(PopupFetchEvent popupFetchEvent) {
        competenciaFormulario = null;
        if (listaCompetenciasFormulario == null) {
            listaCompetenciasCopia = new ArrayList<CompetenciaDTO>();
            listaCompetenciasFormulario = new ArrayList<CompetenciaDTO>();
            listaCompetenciasCopia.addAll(listaCompetencias);
            listaCompetenciasFormulario.addAll(formulario.getListaCompetencias());
            listaCompetenciasCopia.removeAll(listaCompetenciasFormulario);
        }
        listaCompetenciasFormularioSemEdicao = new ArrayList<CompetenciaDTO>();
        listaCompetenciasFormularioSemEdicao.addAll(listaCompetenciasFormulario);
    }

    public String adicionarCompetenciaAoFormulario() {
        if (competenciaFormulario != null) {
            if (listaCompetenciasFormulario == null)
                listaCompetenciasFormulario = new ArrayList<CompetenciaDTO>();
            if (listaCompetenciasFormulario.contains(competenciaFormulario)) {
                mensagemErro(AppBundleProperties.getString("msg.formulario.erroCompetencia"));
            } else {
                listaCompetenciasFormulario.add(competenciaFormulario);
            }
        }
        return null;
    }

    public void selecionarLinhaCompetenciaFormulario(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        competenciaSelecionada = (CompetenciaDTO) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerCompetenciaFormulario() {
        if (competenciaSelecionada != null) {
            listaCompetenciasFormulario.remove(competenciaSelecionada);
            //listaCompetencias.add(competenciaSelecionada);
            Collections.sort(listaCompetencias);
        }
        return null;
    }

    public String salvarPopupConfigurarFormulario() {
        if (listaCompetenciasFormulario != null) {

            formulario.setListaCompetencias(listaCompetenciasFormulario);
        }
        fecharPopup("dialogConfigForm");
        return null;
    }

    public String cancelarPopupConfigurarFormulario() {
        if (!visualizar) {
            if (listaCompetenciasFormularioSemEdicao != null) {
                listaCompetenciasFormulario = new ArrayList<CompetenciaDTO>();
                listaCompetenciasFormulario.addAll(listaCompetenciasFormularioSemEdicao);
                formulario.setListaCompetencias(listaCompetenciasFormularioSemEdicao);
            }
        }
        fecharPopup("dialogConfigForm");
        return null;
    }

    public void fecharPopup(String idComponent) {
        RichPopup popup = (RichPopup) findComponent(idComponent);
        popup.hide();
    }
    //Fim dialog Configurar Formulario

    //Metodos do dialog de configurar campo
    public void initDialogConfiguracaoCampo(ActionEvent actionEvent) {
        campoSelecionadoClone = (CampoDTO) campoSelecionado.clonar();
        if (campoSelecionado.getFormula() != null) {
            campoSelecionadoClone.setFormula((FormulaDTO) campoSelecionado.getFormula().clonar());
            campoSelecionadoClone.getFormula().setCodigoFuncao(null);
        }
        campoFormula = null;
        campoValidacao = null;
        item = "";
        validacao = new ValidacaoDTO();
        tipoRegraDTO = new TipoRegraDTO();
        listaValidacoesCampo = new ArrayList<ValidacaoDTO>();
        if (campoSelecionadoClone.getListaValidacoes() != null &&
            !campoSelecionadoClone.getListaValidacoes().isEmpty()) {
            listaValidacoesCampo.addAll(campoSelecionadoClone.getListaValidacoes());
        }
        if (campoSelecionado.getListaItensSelecaoDTO() != null &&
            !campoSelecionado.getListaItensSelecaoDTO().isEmpty()) {
            List<ItemSelecaoDTO> listaItens = new ArrayList<ItemSelecaoDTO>();
            listaItens.addAll(campoSelecionado.getListaItensSelecaoDTO());
            campoSelecionadoClone.setListaItensSelecaoDTO(listaItens);
        } else {
            campoSelecionadoClone.setListaItensSelecaoDTO(new ArrayList<ItemSelecaoDTO>());
        }
        if (campoSelecionadoClone.getTipoRegraDTO() == null) {
            inverterRegraCampo = false;
        } else {
            inverterRegraCampo = campoSelecionadoClone.getTipoRegraDTO().isInverterRegra();
        }
        listaCamposFormulario = FormularioUtils.recuperarMetadadosCampoFormularioAplicaveisFormula(formulario);
        listaCamposFormularioFull = FormularioUtils.recuperarMetadadosCampoFormulario(formulario);
        consultarTipoRegras();
        UIComponent grupo = actionEvent.getComponent().getParent().getParent().getParent().getParent();
        abrirPopupConfiguracao(grupo, "dialogConfigCampo");
    }

    public String consultarTipoRegras() {
        listaTipoRegra = formularioService.listarMetadadosTipoRegra();
        return null;
    }

    public void cancelarConfiguracaoGrupo(PopupCanceledEvent popupCanceledEvent) {
        grupoSelecionado.setDominioBI(grupoSelecionadoClone.getDominioBI());
        grupoSelecionado.setLabelGrupo(grupoSelecionadoClone.getLabelGrupo());
        grupoSelecionado.setTipoRegraDTO(grupoSelecionadoClone.getTipoRegraDTO());
        ResetUtils.reset(popupCanceledEvent.getComponent());
    }

    public void calcelarPopupConfigCampo(PopupCanceledEvent popupCanceledEvent) {
        campoSugerido = null;
        sugestaoCampo = "";
        socFuncaoFormula.setValue("");
        socFuncaoValidacao.setValue("");
        campoFormula = null;
        campoValidacao = null;
        itExpressaoValidacao.setValue("");
        itMensagem.setValue("");
        dialogConfigCampo.hide();
        esconderBotoesEdicaoValidacao();
    }
  // Metodo acima duplicado apenas sem a passagem de parametro. Precisa testar para saber se o correto é usar sem ou com parametro. 30/08/2017 - Paula Covo.  
    public void calcelarPopupConfigCampo() {
        campoSugerido = null;
        sugestaoCampo = "";
        socFuncaoFormula.setValue("");
        socFuncaoValidacao.setValue("");
        campoFormula = null;
        campoValidacao = null;
        itExpressaoValidacao.setValue("");
        itMensagem.setValue("");
        dialogConfigCampo.hide();
        esconderBotoesEdicaoValidacao();
    }
    
    public List autoCompletarCampo(String labelCampo) {
        CampoDTO campoDTO = new CampoDTO();
        campoDTO.setLabelCampo(labelCampo);
        listaParametros =
            formularioService.listarMetadadosCamposReaproveitamentoComPaginacao(campoDTO, paginacaoSeguestao,
                                                                                listaCamposFormularioFull);
        return montarSelectItemEtidades(listaParametros, CampoDTO.class);
    }

    public void alterarNomeCampoSelecionado(ValueChangeEvent valueChangeEvent) {
        try {
            for (CampoDTO campoDTO : ((List<CampoDTO>) listaParametros)) {
                if (campoDTO.getIdMetadadosCampo().equals(new Long(String.valueOf(valueChangeEvent.getNewValue())))) {
                    campoSugerido = new CampoDTO(campoDTO);
                    break;
                }
            }
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), campoSugerido.getLabelCampo());
        } catch (Exception e) {
            campoSugerido = null;
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    public String reaproveitarCampo() {
        if (campoSugerido != null) {
            campoSelecionadoClone = new CampoDTO(campoSugerido);

            if (campoSelecionado.getGrupo() != null)
                campoSelecionadoClone.setGrupo(campoSelecionado.getGrupo());
            else
                campoSelecionadoClone.setCampoPai(campoSelecionado.getCampoPai());

            listaValidacoesCampo = campoSelecionadoClone.getListaValidacoes();
            Long emUso = formularioService.countUtilizacaoCampo(campoSelecionadoClone);
            campoSelecionadoClone.setDesabilitado(isCampoDesabilitado(emUso, campoSelecionadoClone));
        }
        return null;
    }

    public <E extends BaseObject> List<SelectItem> montarSelectItemEtidades(List listaParametros, Class<E> classe) {
        List<SelectItem> listaSugestoes = new ArrayList<SelectItem>();
        E entidade = null;
        if (listaParametros != null) {
            try {
                entidade = classe.newInstance();
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
            if (entidade instanceof CampoDTO) {
                for (CampoDTO item : ((List<CampoDTO>) listaParametros)) {
                    SelectItem selectItem = new SelectItem();
                    selectItem.setLabel(item.getLabelCampo());
                    selectItem.setValue(item.getIdMetadadosCampo());
                    listaSugestoes.add(selectItem);
                }
            }
        }
        return listaSugestoes;
    }

    public String criarCopiaCampoModelo() {
        reaproveitarCampo();
        campoSelecionadoClone.setDesabilitado(false);

        campoSelecionadoClone.setIdMetadadosCampo(null);
        if (campoSelecionadoClone.getListaItensSelecaoDTO() != null) {
            for (ItemSelecaoDTO itemSelecaoDTO : campoSelecionadoClone.getListaItensSelecaoDTO()) {
                itemSelecaoDTO.setIdEntidadeCampo(null);
                itemSelecaoDTO.setCodigoItemSelecao(null);
            }
        }
        if (campoSelecionadoClone.getListaValidacoes() != null) {
            for (ValidacaoDTO validacaoDTO : campoSelecionadoClone.getListaValidacoes()) {
                validacaoDTO.setCodigoValidacao(null);
            }
        }
        campoSugerido = null;
        sugestaoCampo = "";
        return null;
    }

    public String atualizarGrupo() {
        RichPopup configGrupoPopup = (RichPopup) findComponent("dialogConfigGrupo");
        RichInputText inputDominioBI = (RichInputText) findComponent("dominioBIGrupo");
        if (!validarDominioBIGrupo(inputDominioBI, grupoSelecionado)) {
            if (grupoSelecionado.getTipoRegraDTO() != null)
                grupoSelecionado.getTipoRegraDTO().setInverterRegra(inverterRegraGrupo);
            RichPanelBox boxGrupo = (RichPanelBox) findComponent(grupoSelecionado.getCodigoGrupo());
            boxGrupo.setText(grupoSelecionado.getLabelGrupo());
            configGrupoPopup.hide();
            atualizarComponenteDeTela(boxGrupo);
        }
        return null;
    }

    public String atualizarCampo() {
        if (validaAtualizarCampo()) {
            sugestaoCampo = "";
            campoSelecionadoClone.setListaValidacoes(listaValidacoesCampo);
            if (campoSelecionadoClone.getTipoRegraDTO() != null)
                campoSelecionadoClone.getTipoRegraDTO().setInverterRegra(inverterRegraCampo);
            campoSelecionado.setValues(campoSelecionadoClone);
            RichPanelBox boxGrupo = (RichPanelBox) findComponent(painelPricipal, grupoSelecionado.getCodigoGrupo());
            atualizarValoresDaTabelaArvore(boxGrupo.getChildren(), grupoSelecionado);
            dialogConfigCampo.hide();
            esconderBotoesEdicaoValidacao();
        }
        return null;
    }

    public void persistirTipoRegra(DialogEvent dialogEvent) {
        formularioService.salvarMetadadosTipoRegra(tipoRegraDTO);
        tipoRegraDTO = new TipoRegraDTO();
        consultarTipoRegras();
    }

    //metodos do objeto validacao

    public void selecionarLinhaCampo(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        if ("tableValidacao".equals(richTable.getId()))
            campoValidacao = (CampoDTO) richTable.getSelectedRowData();
        else if ("tableFormula".equals(richTable.getId()))
            campoFormula = (CampoDTO) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String adicionarExpressaoValidacao() {
        if (!socTipoValidacao.getValue().toString().isEmpty()) {
            if (!socFuncaoValidacao.getValue().toString().isEmpty()) {
                if (socCampoValidacao.getValue() != null) {
                    validacao.getFormula().addCampoExpressao(campoValidacao);
                    campoValidacao = null;
                } else {
                    mensagemErroComponente(socCampoValidacao,
                                           AppBundleProperties.getString("msg.configuracao.selecaoObrigatoria"));
                }
            } else {
                mensagemErroComponente(socFuncaoValidacao,
                                       AppBundleProperties.getString("msg.configuracao.selecaoObrigatoria"));
            }
        } else {
            mensagemErroComponente(socTipoValidacao,
                                   AppBundleProperties.getString("msg.configuracao.selecaoObrigatoria"));

        }
        return null;
    }

    public String adicionarExpressaoFormula() {
        if (!socFuncaoFormula.getValue().toString().isEmpty()) {
            if (socCampoFormula.getValue() != null) {
                campoSelecionadoClone.getFormula().addCampoExpressao(campoFormula);
                campoFormula = null;
            } else {
                mensagemErroComponente(socCampoFormula,
                                       AppBundleProperties.getString("msg.configuracao.selecaoObrigatoria"));
            }
        } else {
            mensagemErroComponente(socFuncaoFormula,
                                   AppBundleProperties.getString("msg.configuracao.selecaoObrigatoria"));
        }
        return null;
    }

    public void selecionarValidacaoExistente(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        validacaoSelecionada = (ValidacaoDTO) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerValidacaoCampo() {
        listaValidacoesCampo.remove(validacaoSelecionada);
        return null;
    }

    public String alterarValidacaoCampo() {
        codigoValidacao = validacaoSelecionada.getCodigoTipoValidacao();
        codigoFuncao = validacaoSelecionada.getFormula().getCodigoFuncao();
        expressao = validacaoSelecionada.getFormula().getExpressao();
        mensagem = validacaoSelecionada.getMensagem();

        mostrarBotoesEdicaoValidacao();
        validacao = validacaoSelecionada;
        return null;
    }

    public String salvarEdicao() {
        listaValidacoesCampo.set(listaValidacoesCampo.indexOf(validacao), validacao);
        socCampoValidacao.setValue("");
        esconderBotoesEdicaoValidacao();
        atualizarComponentesPopup();
        validacao = new ValidacaoDTO();
        return null;
    }

    public String cancelarEdicao() {
        validacaoSelecionadaEdicao = new ValidacaoDTO();
        validacaoSelecionadaEdicao.setCodigoTipoValidacao(codigoValidacao);
        validacaoSelecionadaEdicao.getFormula().setCodigoFuncao(codigoFuncao);
        validacaoSelecionadaEdicao.getFormula().setExpressao(expressao);
        validacaoSelecionadaEdicao.setMensagem(mensagem);
        listaValidacoesCampo.set(listaValidacoesCampo.indexOf(validacao), validacaoSelecionadaEdicao);
        socCampoValidacao.setValue("");
        esconderBotoesEdicaoValidacao();
        atualizarComponentesPopup();
        validacao = new ValidacaoDTO();
        return null;
    }

    public void mostrarBotoesEdicaoValidacao() {
        RichButton b1, bCancelarEdicao, bSalvarEdicao;
        b1 = (RichButton) findComponent("b1");
        bSalvarEdicao = (RichButton) findComponent("bSalvarEdicao");
        bCancelarEdicao = (RichButton) findComponent("bCancelarEdicao");
        b1.setVisible(false);
        bSalvarEdicao.setVisible(true);
        bCancelarEdicao.setVisible(true);
        atualizarComponenteDeTela(b1);
        atualizarComponenteDeTela(bCancelarEdicao);
        atualizarComponenteDeTela(bSalvarEdicao);
    }

    public void atualizarComponentesPopup() {
        atualizarComponenteDeTela(t3);
        atualizarComponenteDeTela(socTipoValidacao);
        atualizarComponenteDeTela(socFuncaoValidacao);
        atualizarComponenteDeTela(socCampoValidacao);
        atualizarComponenteDeTela(itExpressaoValidacao);
        atualizarComponenteDeTela(itMensagem);
    }

    public void esconderBotoesEdicaoValidacao() {
        RichButton b1, bCancelarEdicao, bSalvarEdicao;
        b1 = (RichButton) findComponent("b1");
        bSalvarEdicao = (RichButton) findComponent("bSalvarEdicao");
        bCancelarEdicao = (RichButton) findComponent("bCancelarEdicao");
        b1.setVisible(true);
        bSalvarEdicao.setVisible(false);
        bCancelarEdicao.setVisible(false);
        atualizarComponenteDeTela(b1);
        atualizarComponenteDeTela(bCancelarEdicao);
        atualizarComponenteDeTela(bSalvarEdicao);
    }

    public String limparConfiguracaoValidacao() {
        campoValidacao = null;
        validacao = new ValidacaoDTO();
        return null;
    }

    //Fim metodos do objeto validacao

    //Metodo lista de selecao
    public String adicionarItemEmListaSelecao() {
        if (!item.equals("")) {
            ItemSelecaoDTO itemSelecao = new ItemSelecaoDTO();
            itemSelecao.setLabelItemSelecao(item);
            campoSelecionadoClone.getListaItensSelecaoDTO().add(itemSelecao);
            item = "";
        }
        return null;
    }

    public void selecionarLinhaListaSelecao(SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        itemRemover = (ItemSelecaoDTO) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String removerItemEmListaSelecao() {
        if (itemRemover != null) {
            campoSelecionadoClone.getListaItensSelecaoDTO().remove(itemRemover);
        }
        return null;
    }
    //Metodo Formula


    public void testarFormula(ActionEvent actionEvent) {
        validarExpressao(FormulaCalculo.TIPO_FORMULA, getCampoSelecionadoClone().getFormula().getExpressao(), false);
    }

    public void testarValidacao(ActionEvent actionEvent) {
        validarExpressao(FormulaCalculo.TIPO_VALIDACAO, getValidacao().getFormula().getExpressao(), false);
    }


    public void adicionarValidacaoCampo(ActionEvent actionEvent) {
        boolean status =
            validarExpressao(FormulaCalculo.TIPO_VALIDACAO, getValidacao().getFormula().getExpressao(), true);

        if (status) {
            if (!listaValidacoesCampo.contains(validacao)) {
                listaValidacoesCampo.add(validacao);
            } else {
                listaValidacoesCampo.set(listaValidacoesCampo.indexOf(validacao), validacao);
            }
            atualizarComponenteDeTela(t3);
            socCampoValidacao.setValue("");
            atualizarComponenteDeTela(socCampoValidacao);
            validacao = new ValidacaoDTO();
        }
    }

    private boolean validarExpressaoFormulaSalvar(String tipoExpressao, String strExpressao, boolean somenteErro) {
        boolean valido = false;
        if (strExpressao != null && !strExpressao.trim().isEmpty()) {
            if (FormulaCalculo.TIPO_FORMULA.equals(tipoExpressao))
                valido = FormulaCalculo.testarFormula(strExpressao, campoSelecionadoClone.getCodigoCampo());
            else
                valido = FormulaCalculo.testarValidacao(strExpressao);

            if (!valido)
                mensagemErro("A expressão da " + FormulaCalculo.TIPO_FORMULA +
                             " está <b><span style='color:red'>Inválida</span></b>.<br><br><b><p>Expressão:</p><p style='color:red'>" +
                             strExpressao + "</p></b>");
        } else {
            mensagemErro("A expressão da " + FormulaCalculo.TIPO_FORMULA +
                         " está <b><span style='color:red'>Inválida</span></b>");
        }

        return valido;
    }

    private boolean validarExpressao(String tipoExpressao, String strExpressao, boolean somenteErro) {
        boolean status = false;

        if (strExpressao != null && !strExpressao.trim().isEmpty()) {
            if (FormulaCalculo.TIPO_FORMULA.equals(tipoExpressao))
                status = FormulaCalculo.testarFormula(strExpressao, campoSelecionadoClone.getCodigoCampo());
            else
                status = FormulaCalculo.testarValidacao(strExpressao);

            if (!status)
                mensagemErro("A expressão de " + FormulaCalculo.TIPO_FORMULA +
                             " está <b><span style='color:red'>Inválida</span></b>.<br><br><b><p>Expressão:</p><p style='color:red'>" +
                             strExpressao + "</p></b>");
            else if (!somenteErro)
                mensagemInfo("A expressão de " + FormulaCalculo.TIPO_FORMULA +
                             " está <b><span style='color:green'>Válida</span></b>.<br><br><b><p>Expressão:</p><p style='color:green'>" +
                             strExpressao + "</p></b>");

        } else {
            mensagemErro("A expressão de " + FormulaCalculo.TIPO_FORMULA +
                         " está <b><span style='color:red'>Vazia</span></b>");
        }

        return status;
    }
    /*
     * edição das validações
     *
     * */

    private List<SelectItem> carregarListaTipoCampo() {
        List<SelectItem> listaTipoCampo = new ArrayList<SelectItem>();
        for (TipoCampoType tipoCampoType : TipoCampoType.values()) {
            SelectItem tipoCampo = new SelectItem();
            tipoCampo.setLabel(tipoCampoType.getLabelTipoCampo());
            tipoCampo.setValue(tipoCampoType);
            listaTipoCampo.add(tipoCampo);
        }
        return listaTipoCampo;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
    }

    @Override
    public String excluirEntidade() {
        return null;
    }

    @Override
    public Class<FormularioDTO> getClasseEntidade() {
        return FormularioDTO.class;
    }

    //Gets e Sets
    public void setPainelPricipal(RichPanelHeader painelPricipal) {
        this.painelPricipal = painelPricipal;
    }

    public RichPanelHeader getPainelPricipal() {
        return painelPricipal;
    }

    public void setCampoSelecionado(CampoDTO campoSelecionado) {
        this.campoSelecionado = campoSelecionado;
    }

    public CampoDTO getCampoSelecionado() {
        return campoSelecionado;
    }

    public void setFormulario(FormularioDTO formulario) {
        this.formulario = formulario;
    }

    public FormularioDTO getFormulario() {
        return formulario;
    }

    public void setSugestaoCampo(String sugestaoCampo) {
        this.sugestaoCampo = sugestaoCampo;
    }

    public String getSugestaoCampo() {
        return sugestaoCampo;
    }

    public void setCampoSugerido(CampoDTO campoSugerido) {
        this.campoSugerido = campoSugerido;
    }

    public CampoDTO getCampoSugerido() {
        return campoSugerido;
    }

    public void setCampoSelecionadoClone(CampoDTO campoSelecionadoClone) {
        this.campoSelecionadoClone = campoSelecionadoClone;
    }

    public CampoDTO getCampoSelecionadoClone() {
        return campoSelecionadoClone;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setListaFuncoes(List<FuncaoCalculoType> listaFuncoes) {
        this.listaFuncoes = listaFuncoes;
    }

    public List<FuncaoCalculoType> getListaFuncoes() {
        return listaFuncoes;
    }

    public void setValidacao(ValidacaoDTO validacao) {
        this.validacao = validacao;
    }

    public ValidacaoDTO getValidacao() {
        return validacao;
    }

    public void setListaCamposFormulario(List<CampoDTO> listaCamposFormulario) {
        this.listaCamposFormulario = listaCamposFormulario;
    }

    public List<CampoDTO> getListaCamposFormulario() {
        return listaCamposFormulario;
    }

    public void setCampoValidacao(CampoDTO campoValidacao) {
        this.campoValidacao = campoValidacao;
    }

    public CampoDTO getCampoValidacao() {
        return campoValidacao;
    }

    public void setCampoFormula(CampoDTO campoFormula) {
        this.campoFormula = campoFormula;
    }

    public CampoDTO getCampoFormula() {
        return campoFormula;
    }

    public void setListaCompetencias(List<CompetenciaDTO> listaCompetencias) {
        this.listaCompetencias = listaCompetencias;
    }

    public List<CompetenciaDTO> getListaCompetencias() {
        return listaCompetencias;
    }

    public void setListaCompetenciasFormulario(List<CompetenciaDTO> listaCompetenciasFormulario) {
        this.listaCompetenciasFormulario = listaCompetenciasFormulario;
    }

    public List<CompetenciaDTO> getListaCompetenciasFormulario() {
        return listaCompetenciasFormulario;
    }

    public void setCompetenciaFormulario(CompetenciaDTO competenciaFormulario) {
        this.competenciaFormulario = competenciaFormulario;
    }

    public CompetenciaDTO getCompetenciaFormulario() {
        return competenciaFormulario;
    }

    public void setCompetenciaSelecionada(CompetenciaDTO competenciaSelecionada) {
        this.competenciaSelecionada = competenciaSelecionada;
    }

    public CompetenciaDTO getCompetenciaSelecionada() {
        return competenciaSelecionada;
    }

    public void setListaAreas(List<MateriaDTO> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public List<MateriaDTO> getListaAreas() {
        return listaAreas;
    }

    public void setListaSegmentos(List<SegmentoDTO> listaSegmentos) {
        this.listaSegmentos = listaSegmentos;
    }

    public List<SegmentoDTO> getListaSegmentos() {
        return listaSegmentos;
    }

    public void setListaTiposSecao(List<SecaoType> listaTiposSecao) {
        this.listaTiposSecao = listaTiposSecao;
    }

    public List<SecaoType> getListaTiposSecao() {
        return listaTiposSecao;
    }

    public void setTipoSecao(RichSelectOneChoice tipoSecao) {
        this.tipoSecao = tipoSecao;
    }

    public RichSelectOneChoice getTipoSecao() {
        return tipoSecao;
    }

    public void setSecaoMagistrado(SecaoDTO secaoMagistrado) {
        this.secaoMagistrado = secaoMagistrado;
    }

    public SecaoDTO getSecaoMagistrado() {
        return secaoMagistrado;
    }

    public void setSecaoEstabelecimentoPrisional(SecaoDTO secaoEstabelecimentoPrisional) {
        this.secaoEstabelecimentoPrisional = secaoEstabelecimentoPrisional;
    }

    public SecaoDTO getSecaoEstabelecimentoPrisional() {
        return secaoEstabelecimentoPrisional;
    }

    public void setFormularioService(FormularioService formularioService) {
        this.formularioService = formularioService;
    }

    public FormularioService getFormularioService() {
        return formularioService;
    }

    public void setValidacaoSelecionada(ValidacaoDTO validacaoSelecionada) {
        this.validacaoSelecionada = validacaoSelecionada;
    }

    public ValidacaoDTO getValidacaoSelecionada() {
        return validacaoSelecionada;
    }

    public void setListaCompetenciasCopia(List<CompetenciaDTO> listaCompetenciasCopia) {
        this.listaCompetenciasCopia = listaCompetenciasCopia;
    }

    public List<CompetenciaDTO> getListaCompetenciasCopia() {
        return listaCompetenciasCopia;
    }

    public void setItemRemover(ItemSelecaoDTO itemRemover) {
        this.itemRemover = itemRemover;
    }

    public ItemSelecaoDTO getItemRemover() {
        return itemRemover;
    }

    public void setListaMaterias(List<TipoMateriaDTO> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public List<TipoMateriaDTO> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMateriasSecao(List<TipoMateriaDTO> listaMateriasSecao) {
        this.listaMateriasSecao = listaMateriasSecao;
    }

    public List<TipoMateriaDTO> getListaMateriasSecao() {
        return listaMateriasSecao;
    }

    public void setMateriaAdicionar(TipoMateriaDTO materiaAdicionar) {
        this.materiaAdicionar = materiaAdicionar;
    }

    public TipoMateriaDTO getMateriaAdicionar() {
        return materiaAdicionar;
    }

    public void setMateriaVincular(TipoMateriaDTO materiaVincular) {
        this.materiaVincular = materiaVincular;
    }

    public TipoMateriaDTO getMateriaVincular() {
        return materiaVincular;
    }

    public void setMateriaSelecionada(TipoMateriaDTO materiaSelecionada) {
        this.materiaSelecionada = materiaSelecionada;
    }

    public TipoMateriaDTO getMateriaSelecionada() {
        return materiaSelecionada;
    }

    public void setListaSituacoesFormulario(List<MetadadoSituacaoFormularioDTO> listaSituacoesFormulario) {
        this.listaMetadadoSituacoesFormulario = listaSituacoesFormulario;
    }

    public List<MetadadoSituacaoFormularioDTO> getListaSituacoesFormulario() {
        return listaMetadadoSituacoesFormulario;
    }

    public void setListaValidacoesCampo(List<ValidacaoDTO> listaValidacoesCampo) {
        this.listaValidacoesCampo = listaValidacoesCampo;
    }

    public List<ValidacaoDTO> getListaValidacoesCampo() {
        return listaValidacoesCampo;
    }

    public void setListaTipoRegra(List<TipoRegraDTO> listaTipoRegra) {
        this.listaTipoRegra = listaTipoRegra;
    }

    public List<TipoRegraDTO> getListaTipoRegra() {
        return listaTipoRegra;
    }

    public void setTipoRegraDTO(TipoRegraDTO tipoRegraDTO) {
        this.tipoRegraDTO = tipoRegraDTO;
    }

    public TipoRegraDTO getTipoRegraDTO() {
        return tipoRegraDTO;
    }

    public void setGrupoSelecionado(GrupoDTO grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public GrupoDTO getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setSocFuncaoValidacao(RichSelectOneChoice socFuncaoValidacao) {
        this.socFuncaoValidacao = socFuncaoValidacao;
    }

    public RichSelectOneChoice getSocFuncaoValidacao() {
        return socFuncaoValidacao;
    }

    public void setSocFuncaoFormula(RichSelectOneChoice socFuncaoFormula) {
        this.socFuncaoFormula = socFuncaoFormula;
    }

    public RichSelectOneChoice getSocFuncaoFormula() {
        return socFuncaoFormula;
    }

    public void setItExpressaoFormula(RichInputText itExpressaoFormula) {
        this.itExpressaoFormula = itExpressaoFormula;
    }

    public RichInputText getItExpressaoFormula() {
        return itExpressaoFormula;
    }

    public void setItExpressaoValidacao(RichInputText itExpressaoValidacao) {
        this.itExpressaoValidacao = itExpressaoValidacao;
    }

    public RichInputText getItExpressaoValidacao() {
        return itExpressaoValidacao;
    }

    public void setItMensagem(RichInputText itMensagem) {
        this.itMensagem = itMensagem;
    }

    public RichInputText getItMensagem() {
        return itMensagem;
    }

    public void setSocCamposValidacao(RichSelectOneChoice socCamposValidacao) {
        this.socCamposValidacao = socCamposValidacao;
    }

    public RichSelectOneChoice getSocCamposValidacao() {
        return socCamposValidacao;
    }

    public void setT3(RichTable t3) {
        this.t3 = t3;
    }

    public RichTable getT3() {
        return t3;
    }

    public String limparConfiguracaoFormula() {
        socCampoFormula.setValue("");
        socFuncaoFormula.setValue("");
        itExpressaoFormula.setValue("");
        return null;
    }


    public void setValidacaoSelecionadaEdicao(ValidacaoDTO validacaoSelecionadaEdicao) {
        this.validacaoSelecionadaEdicao = validacaoSelecionadaEdicao;
    }

    public ValidacaoDTO getValidacaoSelecionadaEdicao() {
        return validacaoSelecionadaEdicao;
    }

    public void setCampoValidacaoEdicao(CampoDTO campoValidacaoEdicao) {
        this.campoValidacaoEdicao = campoValidacaoEdicao;
    }

    public CampoDTO getCampoValidacaoEdicao() {
        return campoValidacaoEdicao;
    }

    public void setSocTipoValidacao(RichSelectOneChoice socTipoValidacao) {
        this.socTipoValidacao = socTipoValidacao;
    }

    public RichSelectOneChoice getSocTipoValidacao() {
        return socTipoValidacao;
    }

    public void setSocCampoValidacao(RichSelectOneChoice socCampoValidacao) {
        this.socCampoValidacao = socCampoValidacao;
    }

    public RichSelectOneChoice getSocCampoValidacao() {
        return socCampoValidacao;
    }

    public void setSocCampoFormula(RichSelectOneChoice socCampoFormula) {
        this.socCampoFormula = socCampoFormula;
    }

    public RichSelectOneChoice getSocCampoFormula() {
        return socCampoFormula;
    }

    public void setListaTipoCampo(List<SelectItem> listaTipoCampo) {
        this.listaTipoCampo = listaTipoCampo;
    }

    public List<SelectItem> getListaTipoCampo() {
        return listaTipoCampo;
    }

    public void setInverterRegraCampo(boolean inverterRegraCampo) {
        this.inverterRegraCampo = inverterRegraCampo;
    }

    public boolean isInverterRegraCampo() {
        return inverterRegraCampo;
    }

    public void setInverterRegraGrupo(boolean inverterRegraGrupo) {
        this.inverterRegraGrupo = inverterRegraGrupo;
    }

    public boolean isInverterRegraGrupo() {
        return inverterRegraGrupo;
    }

    public void setDialogConfigCampo(RichPopup dialogConfigCampo) {
        this.dialogConfigCampo = dialogConfigCampo;
    }

    public RichPopup getDialogConfigCampo() {
        return dialogConfigCampo;
    }

    public void validarDominiBICampo(ValueChangeEvent valueChangeEvent) {
        campoSelecionadoClone.setCodigoBI(valueChangeEvent.getNewValue().toString());
        validarDominioBICampo(valueChangeEvent.getComponent(), campoSelecionadoClone);
    }

    public boolean validarDominioBICampo(UIComponent component, CampoDTO dominioBI) {
        boolean emUso = false;
        if (component != null) {
            emUso = formularioService.dominioBIExistente(dominioBI);
            if (!emUso) {
                emUso = FormularioUtils.dominioBIExistenteNoFormulario(formulario, dominioBI);
            }
            if (emUso)
                mensagemErroComponente(component, AppBundleProperties.getString("msg.formulario.dominiobiemuso"));
            atualizarComponenteDeTela(component);
        }
        return emUso;
    }

    private boolean validaAtualizarCampo() {
        return (!TipoCampoType.FORMULA.equals(getCampoSelecionadoClone().getTipoCampo()) ||
                (TipoCampoType.FORMULA.equals(getCampoSelecionadoClone().getTipoCampo()) &&
                 validarExpressaoFormulaSalvar(FormulaCalculo.TIPO_FORMULA,
                                               getCampoSelecionadoClone().getFormula().getExpressao(), false))) &&
               !validarDominioBICampo(findComponent("dominioBICampo"), campoSelecionadoClone);
    }

    public void atualizarListaCamposFormulario(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null &&
            (((TipoCampoType) valueChangeEvent.getNewValue()).equals(TipoCampoType.FORMULA) ||
             ((TipoCampoType) valueChangeEvent.getNewValue()).equals(TipoCampoType.NUMERO))) {
            if (!listaCamposFormulario.contains(campoSelecionadoClone))
                listaCamposFormulario.add(campoSelecionadoClone);
        } else {
            if (listaCamposFormulario.contains(campoSelecionadoClone))
                listaCamposFormulario.remove(campoSelecionadoClone);
        }
    }

    public boolean validarDominioBIGrupo(UIComponent component, GrupoDTO dominioBI) {
        boolean emUso = false;
        if (component != null) {
            emUso = formularioService.dominioBIExistente(dominioBI);
            if (!emUso) {
                emUso = FormularioUtils.dominioBIExistenteNoFormulario(entidadePersistencia, dominioBI);
            }
            if (emUso)
                mensagemErroComponente(component, AppBundleProperties.getString("msg.formulario.dominiobiemuso"));
            atualizarComponenteDeTela(component);
        }
        return emUso;
    }

    public void validarDominioBIGrupo(ValueChangeEvent valueChangeEvent) {
        grupoSelecionado.setDominioBI(valueChangeEvent.getNewValue().toString());
        validarDominioBIGrupo(valueChangeEvent.getComponent(), grupoSelecionado);
    }

    public void setSecaoDadosUnidade(SecaoDTO secaoDadosUnidade) {
        this.secaoDadosUnidade = secaoDadosUnidade;
    }

    public SecaoDTO getSecaoDadosUnidade() {
        return secaoDadosUnidade;
    }
}
