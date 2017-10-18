package br.jus.tjsp.movjud.web.formulario.helper;

import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.formulario.helper.FormularioUtils;
import br.jus.tjsp.movjud.business.formulario.types.SecaoType;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.core.util.AppResourceProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.MethodExpressionValueChangeListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTreeTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.event.ClientListenerSet;
import oracle.adf.view.rich.model.ChildPropertyTreeModel;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ComponentesHelper {

    public static final String LABEL_BOTAO_ADDSECAO = AppBundleProperties.getString("label.formulario.addSecao");
    public static final String LABEL_BOTAO_ADDGRUPO = AppBundleProperties.getString("label.formulario.addGrupo");
    public static final String LABEL_BOTAO_ADDCAMPO = AppBundleProperties.getString("label.formulario.addCampo");
    public static final String LABEL_BOTAO_ADDSUBCAMPO = AppBundleProperties.getString("label.formulario.addSubcampo");
    public static final String LABEL_BOTAO_SETAPARACIMA = AppBundleProperties.getString("label.formulario.setaParaCima");
    public static final String LABEL_BOTAO_SETAPARABAIXO = AppBundleProperties.getString("label.formulario.setaParaBaixo");
    public static final String LABEL_BOTAO_REMOVER_SECAO = AppBundleProperties.getString("label.formulario.removerSecao");
    public static final String LABEL_BOTAO_REMOVER_GRUPO = AppBundleProperties.getString("label.formulario.removerGrupo");
    public static final String LABEL_BOTAO_REMOVER_CAMPO = AppBundleProperties.getString("label.formulario.removerCampo");
    public static final String LABEL_BOTAO_REMOVER_SUBCAMPO = AppBundleProperties.getString("label.formulario.removerSubcampo");
    public static final String LABEL_BOTAO_CONFIG_SECAO = AppBundleProperties.getString("label.formulario.configSecao");
    public static final String LABEL_BOTAO_CONFIG_GRUPO = AppBundleProperties.getString("label.formulario.configGrupo");
    public static final String LABEL_BOTAO_CONFIG_CAMPO = AppBundleProperties.getString("label.formulario.configCampo");
    public static final String LABEL_BOTAO_CONFIG_SUBCAMPO = AppBundleProperties.getString("label.formulario.configSubcampo");
    public static final String LABEL_BOTAO_ALTERAR_CAMPO = AppBundleProperties.getString("label.formulario.alterarCampo");


    public static final String LABEL_TITULO_GRUPO = AppBundleProperties.getString("label.formulario.titulo");
    public static final String LABEL_CAMPOS = AppBundleProperties.getString("label.formulario.campos");
    public static final String LABEL_LABEL_CAMPO = AppBundleProperties.getString("label.formulario.labelcampo");
    public static final String LABEL_CODIGO_CAMPO = AppBundleProperties.getString("label.formulario.codigocampo");
    public static final String LABEL_TIPO_CAMPO = AppBundleProperties.getString("label.formulario.tipo");
    public static final String LABEL_ACOES = AppBundleProperties.getString("label.formulario.acoes");
    public static final String LABEL_SELECIONE = AppBundleProperties.getString("label.combo.selecione");

    public static final String ACTION_ADICIONAR_SECAO = "#{pageFlowScope.configuracaoFormularioBean.adicionarSecao}";
    public static final String ACTION_ADICIONAR_GRUPO = "#{pageFlowScope.configuracaoFormularioBean.adicionarGrupo}";
    public static final String ACTION_ADICIONAR_CAMPO = "#{pageFlowScope.configuracaoFormularioBean.adicionarCampo}";
    public static final String ACTION_ADICIONAR_SUBCAMPO = "#{pageFlowScope.configuracaoFormularioBean.adicionarSubCampo}";
    public static final String ACTION_REMOVER_SECAO = "#{pageFlowScope.configuracaoFormularioBean.confirmarRemoverSecao}";
    public static final String ACTION_ORDENAR_GRUPO_ACIMA = "#{pageFlowScope.configuracaoFormularioBean.ordenarGrupoAcima}";
    public static final String ACTION_ORDENAR_GRUPO_ABAIXO = "#{pageFlowScope.configuracaoFormularioBean.ordenarGrupoAbaixo}";
    public static final String ACTION_REMOVER_GRUPO = "#{pageFlowScope.configuracaoFormularioBean.confirmarRemoverGrupo}";
    public static final String ACTION_ALTERAR_TITULO_GRUPO = "#{pageFlowScope.configuracaoFormularioBean.alterarTituloGrupo}";
    public static final String ACTION_SELECIONAR_LINHA_TREE = "#{pageFlowScope.configuracaoFormularioBean.selecionarLinhaEmTabelaArvore}";
    public static final String ACTION_ALTERAR_TITULO_CAMPO = "#{pageFlowScope.configuracaoFormularioBean.alterarTituloCampo}";
    public static final String ACTION_ALTERAR_TIPO_CAMPO = "#{pageFlowScope.configuracaoFormularioBean.alterarTipoCampo}";
    public static final String ACTION_ORDENAR_CAMPO_ACIMA = "#{pageFlowScope.configuracaoFormularioBean.ordenarCampoAcima}";
    public static final String ACTION_ORDENAR_CAMPO_ABAIXO = "#{pageFlowScope.configuracaoFormularioBean.ordenarCampoAbaixo}";
    public static final String ACTION_REMOVER_CAMPO = "#{pageFlowScope.configuracaoFormularioBean.confirmarRemoverCampo}";
    public static final String ACTION_INIT_CONFIGURAR_CAMPO = "#{pageFlowScope.configuracaoFormularioBean.initDialogConfiguracaoCampo}";
    public static final String ACTION_INIT_CONFIGURAR_GRUPO = "#{pageFlowScope.configuracaoFormularioBean.initDialogConfiguracaoGrupo}";

    public static final String VAR = "campo";
    public static final String OBJETO_NAVEGACAO_TREE = "listaCampos";
    public static final String PAINEL_PRINCIPAL = "painelPrincipal";

    public static final String HALIGN_RIGTH = "right";
    public static final String HALIGN_CENTER = "center";
    public static final String HALIGN_START = "start";
    public static final String HALIGN_END = "end";
    public static final String LAYOUT_HORIZONTAL = "horizontal";
    public static final String STYLE_BOX_SECAO = "boxSecao";
    public static final String STYLE_BOX_GRUPO = "boxGrupo";

    public static final String ICONE_ADICIONAR_ON = AppResourceProperties.getString("img.adicionarOn");
    public static final String ICONE_ADICIONAR_OFF = AppResourceProperties.getString("img.adicionarOff");
    public static final String ICONE_ADICIONAR_CAMPO_ON = AppResourceProperties.getString("img.adicionarCampoOn");
    public static final String ICONE_ADICIONAR_CAMPO_OFF = AppResourceProperties.getString("img.adicionarCampoOff");
    public static final String ICONE_ADICIONAR_GRUPO_ON = AppResourceProperties.getString("img.adicionarGrupoOn");
    public static final String ICONE_ADICIONAR_GRUPO_OFF = AppResourceProperties.getString("img.adicionarGrupoOff");
    public static final String ICONE_ADICIONAR_SUBCAMPO_ON = AppResourceProperties.getString("img.adicionarSubCampoOn");
    public static final String ICONE_ADICIONAR_SUBCAMPO_OFF = AppResourceProperties.getString("img.adicionarSubCampoOff");
    public static final String ICONE_ADICIONAR_LISTA_ON = AppResourceProperties.getString("img.adicionarListaOn");
    public static final String ICONE_ADICIONAR_LISTA_OFF = AppResourceProperties.getString("img.adicionarListaOff");
    public static final String ICONE_LIXEIRA = AppResourceProperties.getString("img.lixeiraOn");
    public static final String ICONE_LIXEIRA_OFF = AppResourceProperties.getString("img.lixeiraOff");
    public static final String ICONE_CONFIGURACAO = AppResourceProperties.getString("img.configuracaoOn");
    public static final String ICONE_CONFIGURACAO_OFF = AppResourceProperties.getString("img.configuracaoOff");
    public static final String ICONE_PARA_CIMA_ON = AppResourceProperties.getString("img.paraCimaOn");
    public static final String ICONE_PARA_CIMA_OFF = AppResourceProperties.getString("img.paraCimaOff");
    public static final String ICONE_PARA_BAIXO_ON = AppResourceProperties.getString("img.paraBaixoOn");
    public static final String ICONE_PARA_BAIXO_OFF = AppResourceProperties.getString("img.paraBaixoOff");
    public static final String ICONE_EDITAR_ON = AppResourceProperties.getString("img.editarOn");
    public static final String ICONE_EDITAR_OFF = AppResourceProperties.getString("img.editarOff");

    public static final String FORMATACAO_CODIGO_CAMPO = "color:#9E9E9E; font-style:italic;";
    public static final String SPACER_WIDTH = "3px";

    public static final String ENABLED = "enabled";
    public static final String ROW_SELECTION_SINGLE = "single";
    public static final String PROP_SHORT_DESC = "shortDesc";
    public static final String PROP_SOURCE = "source";
    public static final String PROP_VALUE = "value";
    public static final String PROP_DISABLED = "disabled";
    public static final String PROP_RENDERED = "rendered";
    public static final String PROP_CLICK = "click";
    public static final String POPUP_CONFIG_CAMPO = ":::dialogConfigCampo";
    public static final String POPUP_CONFIG_GRUPO = "::dialogConfigGrupo";

    public static final String PREFIXO_TREE = "tt";

    public ComponentesHelper() {
    }

    public static String getIdTreeTable(String idGrupo) {
        return PREFIXO_TREE + idGrupo;
    }

    //Rich Components
    public static RichPanelBox criarPainelSecaoPorTipo(SecaoDTO secao, FormularioDTO formulario, boolean visualizar) {
        List<RichPanelBox> listaPaineisGrupo = new ArrayList<RichPanelBox>();
        RichPanelBox painelSecao = null;
        if (secao.getCodigoSecao().equals(SecaoType.DADOS_UNIDADES.getCodigoSecao())) {
            painelSecao = criarPainelSecao(SecaoType.DADOS_UNIDADES, new RichPanelBox(), secao, visualizar);
        } else if (secao.getCodigoSecao().equals(SecaoType.MAGISTRADO.getCodigoSecao())) {
            painelSecao = criarPainelSecao(SecaoType.MAGISTRADO, new RichPanelBox(), secao, visualizar);
        } else if (secao.getCodigoSecao().equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS.getCodigoSecao())) {
            painelSecao = criarPainelSecao(SecaoType.ESTABELECIMENTOS_PRISIONAIS, new RichPanelBox(), secao, visualizar);
        } else if (secao.getCodigoSecao().equals(SecaoType.MATERIA.getCodigoSecao())) {
            painelSecao = criarPainelSecao(SecaoType.MATERIA, new RichPanelBox(), secao, visualizar);
        } else if (secao.getCodigoSecao().equals(SecaoType.REUS.getCodigoSecao())) {
            painelSecao = criarPainelSecao(SecaoType.REUS, new RichPanelBox(), secao, visualizar);
        }
        listaPaineisGrupo.addAll(montarListaPaineisGrupo(secao, formulario, visualizar));
        painelSecao.getChildren().addAll(listaPaineisGrupo);
        return painelSecao;
    }

    public static List<RichPanelBox> montarListaPaineisGrupo(SecaoDTO secao, FormularioDTO formulario, boolean visualizar) {
        List<RichPanelBox> listaPaineisGrupo = new ArrayList<RichPanelBox>();
        List<GrupoDTO> listaGrupos = null;
        int i = 0;
        if (secao.getListaGrupos() != null) {
            listaGrupos = new ArrayList<GrupoDTO>();
            listaGrupos.addAll(secao.getListaGrupos());
            for (GrupoDTO grupoDTO : listaGrupos) {
                listaPaineisGrupo.add(criarGrupoComCampos(grupoDTO, secao, formulario, visualizar));
            }
        }
        return listaPaineisGrupo;
    }

    public static RichPanelBox criarGrupoComCampos(GrupoDTO grupoDTO, SecaoDTO secaoDTO, FormularioDTO formulario, boolean visualizar) {
        boolean novoGrupo = false;
        if (grupoDTO == null)
            novoGrupo = true;
        if (novoGrupo)
            grupoDTO = FormularioUtils.criarGrupo(secaoDTO);
        RichPanelBox boxGrupo = ComponentesHelper.criarPainelGrupo(grupoDTO, visualizar);
        RichTreeTable tree = ComponentesHelper.criarTreeTable(new ChildPropertyTreeModel(grupoDTO.getListaCampos(), OBJETO_NAVEGACAO_TREE), visualizar);
        tree.setId(ComponentesHelper.getIdTreeTable(boxGrupo.getId()));
        boxGrupo.getChildren().add(tree);
        if (novoGrupo) {
            //grupoDTO = FormularioUtils.gerarIndices(grupoDTO);
            grupoDTO = FormularioUtils.gerarCodigoCampos(grupoDTO);
            for (SecaoDTO secao : formulario.getListaSecoes()) {
                if (secaoDTO.getCodigoSecao().equals(secao.getCodigoSecao())) {
                    secao.getListaGrupos().add(grupoDTO);
                    break;
                }
            }
        }
        return boxGrupo;
    }

    public static UIComponent gerarIdsComponentesGrupos(UIComponent secao, SecaoDTO secaoDTO) {
        int i = 0;
        for (UIComponent ui : secao.getChildren()) {
            RichPanelBox grupoUI = (RichPanelBox) ui;
            grupoUI.setId(secaoDTO.getListaGrupos().get(i).getCodigoGrupo());
            RichPanelGroupLayout grupoToolbar = (RichPanelGroupLayout) grupoUI.getToolbar();
            ((RichOutputText) grupoToolbar.getChildren().get(0)).setValue(grupoUI.getId());
            for(UIComponent tree : grupoUI.getChildren()){
                if(tree instanceof RichTreeTable){
                    tree.setId(ComponentesHelper.getIdTreeTable(grupoUI.getId()));
                }
            }
            i++;
        }
        return secao;
    }

    public static RichPanelBox criarPainelSecao(SecaoType secaoType, RichPanelBox panelBoxSecao, SecaoDTO secaoDTO, boolean visualizar) {
        //panelBoxSecao.setDisclosed(false);
        panelBoxSecao.setText((secaoDTO == null) ? secaoType.getLabelSecao() : secaoDTO.getLabelSecao());
        panelBoxSecao.setId(secaoType.getCodigoSecao());
        RichPanelGroupLayout layoutBotoesSecao = ComponentesHelper.criarPaineldeLayout(HALIGN_RIGTH, LAYOUT_HORIZONTAL);
        RichLink addGrupo = ComponentesHelper.criarLink(null, ACTION_ADICIONAR_GRUPO, visualizar, ICONE_ADICIONAR_GRUPO_ON, ICONE_ADICIONAR_GRUPO_OFF, LABEL_BOTAO_ADDGRUPO);
        RichLink configuracao = ComponentesHelper.criarLink(null, null, false, ICONE_CONFIGURACAO, ICONE_CONFIGURACAO_OFF, LABEL_BOTAO_CONFIG_SECAO);
        RichLink removerSecao = ComponentesHelper.criarLink(null, ACTION_REMOVER_SECAO, visualizar, ICONE_LIXEIRA, ICONE_LIXEIRA_OFF, LABEL_BOTAO_REMOVER_SECAO);

        layoutBotoesSecao.getChildren().add(removerSecao);
        if (secaoType.equals(SecaoType.DADOS_UNIDADES)) {
            removerSecao.setDisabled(true);
            configuracao.setDisabled(true);
        } else if (secaoType.equals(SecaoType.ESTABELECIMENTOS_PRISIONAIS)) {
            configuracao = adicionarShowPopUpEmLink(configuracao, "dialogConfigSecaoEstabelecimentoPrisional");
        } else if (secaoType.equals(SecaoType.MAGISTRADO)) {
            configuracao = adicionarShowPopUpEmLink(configuracao, "dialogConfigSecaoMagistrado");
        } else if (secaoType.equals(SecaoType.MATERIA)) {
            configuracao = adicionarShowPopUpEmLink(configuracao, "dialogConfigSecaoMaterias");
        } else if (secaoType.equals(SecaoType.REUS)) {
            configuracao.setDisabled(true);
        }
        layoutBotoesSecao.getChildren().add(addGrupo);
        layoutBotoesSecao.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoesSecao.getChildren().add(configuracao);
        layoutBotoesSecao.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoesSecao.getChildren().add(removerSecao);
        panelBoxSecao.setToolbar(layoutBotoesSecao);
        panelBoxSecao.setStyleClass(ComponentesHelper.STYLE_BOX_SECAO);
        return panelBoxSecao;
    }

    public static RichPanelGroupLayout criarGrupoDeBotoesAcaoGrupo(boolean visualizar, GrupoDTO grupoDTO) {
        RichPanelGroupLayout layoutBotoes = criarPaineldeLayout(HALIGN_RIGTH, LAYOUT_HORIZONTAL);

        RichOutputText codigoGrupo = new RichOutputText();
        codigoGrupo.setInlineStyle(FORMATACAO_CODIGO_CAMPO);
        codigoGrupo.setValue(grupoDTO.getCodigoGrupo());
        layoutBotoes.getChildren().add(codigoGrupo);

        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));

        RichLink botaoOrdenarAcima = criarLink(null, ACTION_ORDENAR_GRUPO_ACIMA, visualizar, ICONE_PARA_CIMA_ON, ICONE_PARA_CIMA_OFF, LABEL_BOTAO_SETAPARACIMA);
        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoes.getChildren().add(botaoOrdenarAcima);

        RichLink botaoOrdenarAbaixo = criarLink(null, ACTION_ORDENAR_GRUPO_ABAIXO, visualizar, ICONE_PARA_BAIXO_ON, ICONE_PARA_BAIXO_OFF, LABEL_BOTAO_SETAPARABAIXO);
        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoes.getChildren().add(botaoOrdenarAbaixo);

        RichLink botaoAddCampo = criarLink(null, ACTION_ADICIONAR_CAMPO, visualizar, ICONE_ADICIONAR_CAMPO_ON, ICONE_ADICIONAR_CAMPO_OFF, LABEL_BOTAO_ADDCAMPO);
        RichLink botaoAddCampoFilho = criarLink(null, ACTION_ADICIONAR_SUBCAMPO, visualizar, ICONE_ADICIONAR_SUBCAMPO_ON, ICONE_ADICIONAR_SUBCAMPO_OFF, LABEL_BOTAO_ADDSUBCAMPO);

        layoutBotoes.getChildren().add(botaoAddCampo);
        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoes.getChildren().add(botaoAddCampoFilho);

        RichLink botaoConfigGrupo = criarLink(null, ACTION_INIT_CONFIGURAR_GRUPO, false, ICONE_CONFIGURACAO, ICONE_CONFIGURACAO_OFF, LABEL_BOTAO_CONFIG_GRUPO);
        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoes.getChildren().add(botaoConfigGrupo);

        RichLink botaoExcluirGrupo = criarLink(null, ACTION_REMOVER_GRUPO, visualizar, ICONE_LIXEIRA, ICONE_LIXEIRA_OFF, LABEL_BOTAO_REMOVER_GRUPO);
        layoutBotoes.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutBotoes.getChildren().add(botaoExcluirGrupo);

        return layoutBotoes;
    }

    public static RichSpacer criarSpacer(String width, String height) {
        RichSpacer spacer = new RichSpacer();
        if (width != null)
            spacer.setWidth(width);
        if (height != null)
            spacer.setHeight(height);
        return spacer;
    }

    public static RichPanelBox criarPainelGrupo(GrupoDTO grupoDTO, boolean visualizar) {
        RichPanelGroupLayout toolbar = criarGrupoDeBotoesAcaoGrupo(visualizar, grupoDTO);
        RichPanelBox boxGrupo = new RichPanelBox();
        boxGrupo.setText(grupoDTO.getLabelGrupo());
        boxGrupo.setId(grupoDTO.getCodigoGrupo());
        boxGrupo.setStyleClass(STYLE_BOX_GRUPO);

        boxGrupo.getChildren().add(new RichSeparator());

        boxGrupo.setToolbar(toolbar);

        return boxGrupo;
    }

    public static RichPanelGroupLayout criarPaineldeLayout(String alinhamento, String layout) {
        RichPanelGroupLayout layoutRet = new RichPanelGroupLayout();
        layoutRet.setHalign(alinhamento);
        layoutRet.setLayout(layout);
        return layoutRet;
    }

    public static RichTreeTable criarTreeTable(ChildPropertyTreeModel charatcerVal, boolean visualizar) {
        RichTreeTable tree = new RichTreeTable();
        tree.setSelectionListener(criarEventoSelecionarLinhaEmTabela(tree.getClass(), ACTION_SELECIONAR_LINHA_TREE));
        tree.setVar(VAR);
        tree.setColumnResizing(ENABLED);
        tree.setDisableColumnReordering(false);
        tree.setRowSelection(ROW_SELECTION_SINGLE);

        RichOutputText labelStamp = new RichOutputText();
        labelStamp.setValueExpression(PROP_VALUE, recuperarValorPorEL("#{" + tree.getVar() + ".indiceCampo}" + " " + "#{" + tree.getVar() + ".labelCampo}"));
        labelStamp.setValueExpression(PROP_SHORT_DESC, recuperarValorPorEL("#{" + tree.getVar() + ".hint}"));

        RichColumn nodeStamp = criarColuna(LABEL_CAMPOS, false, labelStamp);
        nodeStamp.setWidth("350px");
        tree.setNodeStamp(nodeStamp);

        RichOutputText labelCodigo = new RichOutputText();
        labelCodigo.setValueExpression(PROP_VALUE, recuperarValorPorEL(" [#{" + tree.getVar() + ".codigoCampo}]"));
        labelCodigo.setValueExpression(PROP_SHORT_DESC, recuperarValorPorEL("#{" + tree.getVar() + ".hint}"));
        labelCodigo.setInlineStyle(FORMATACAO_CODIGO_CAMPO);

        RichColumn labelCodigoCol = criarColuna(LABEL_CODIGO_CAMPO, false, labelCodigo);
        labelCodigoCol.setWidth("170px");
        tree.getChildren().add(labelCodigoCol);

        /* RichInputText tituloCampo = new RichInputText();
        tituloCampo.setPlaceholder(LABEL_LABEL_CAMPO);
        tituloCampo.addValueChangeListener(criarValueChangeListener(tituloCampo.getClass(), ACTION_ALTERAR_TITULO_CAMPO));
        tituloCampo.setAutoSubmit(true);
        tituloCampo.setValueExpression(PROP_VALUE, recuperarValorPorEL("#{" + tree.getVar() + ".labelCampo}"));
        tituloCampo.setValueExpression(PROP_DISABLED, recuperarValorPorEL("#{pageFlowScope.configuracaoFormularioBean.visualizar ? true : " + tree.getVar() + ".desabilitado}"));

        RichColumn labelCampo = criarColuna(LABEL_LABEL_CAMPO, false, tituloCampo);
        tree.getChildren().add(labelCampo);
        
        RichSelectOneChoice soc = criarChoiceTipoCampo();
        soc.setValueExpression(PROP_DISABLED, recuperarValorPorEL("#{pageFlowScope.configuracaoFormularioBean.visualizar ? true : " + tree.getVar() + ".desabilitado}"));
        soc.setValueExpression(PROP_VALUE, recuperarValorPorEL("#{" + tree.getVar() + ".tipoCampo}"));
        soc.setAutoSubmit(true);*/
        //soc.addValueChangeListener(criarValueChangeListener(soc.getClass(), ACTION_ALTERAR_TIPO_CAMPO));

        RichPanelGroupLayout layoutTipoCampo = criarPaineldeLayout(HALIGN_CENTER, LAYOUT_HORIZONTAL);
        RichImage imagemTipoCampo = new RichImage();
        imagemTipoCampo.setValueExpression(PROP_SOURCE, recuperarValorPorEL("#{pageFlowScope.configuracaoFormularioBean.visualizar ? res["+ tree.getVar() + ".tipoCampo.imagemOff] : " + tree.getVar() + ".desabilitado ? res[" + tree.getVar() + ".tipoCampo.imagemOff] : res[" + tree.getVar() + ".tipoCampo.imagemOn]}"));
        imagemTipoCampo.setValueExpression(PROP_SHORT_DESC, recuperarValorPorEL("#{" + tree.getVar() + ".tipoCampo.labelTipoCampo} - #{" + tree.getVar() + ".tipoCampo.hintTipoCampo}"));
        layoutTipoCampo.getChildren().add(imagemTipoCampo);

        RichColumn tipoCampo = criarColuna(LABEL_TIPO_CAMPO, false, layoutTipoCampo);
        tipoCampo.setWidth("50px");
        tipoCampo.setAlign(HALIGN_CENTER);
        tree.getChildren().add(tipoCampo); 

        RichColumn acoes = criarColuna(LABEL_ACOES, false, null);
        acoes.setAlign(HALIGN_CENTER);
        acoes.setWidth("125px");
        RichPanelGroupLayout layoutAcoesCampos = new RichPanelGroupLayout();
        layoutAcoesCampos.setLayout(LAYOUT_HORIZONTAL);
        layoutAcoesCampos.setHalign(HALIGN_CENTER);
        
        RichLink botaoOrdenarAcima = criarLink(null, ACTION_ORDENAR_CAMPO_ACIMA, visualizar, ICONE_PARA_CIMA_ON, ICONE_PARA_CIMA_OFF, LABEL_BOTAO_SETAPARACIMA);
        layoutAcoesCampos.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutAcoesCampos.getChildren().add(botaoOrdenarAcima);

        RichLink botaoOrdenarAbaixo = criarLink(null, ACTION_ORDENAR_CAMPO_ABAIXO, visualizar, ICONE_PARA_BAIXO_ON, ICONE_PARA_BAIXO_OFF, LABEL_BOTAO_SETAPARABAIXO);
        layoutAcoesCampos.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutAcoesCampos.getChildren().add(botaoOrdenarAbaixo);

        /* RichLink botaoEditar = criarLink(null, ACTION_INIT_CONFIGURAR_CAMPO, false, ICONE_EDITAR_ON, ICONE_EDITAR_OFF, LABEL_BOTAO_ALTERAR_CAMPO);
        botaoEditar.setValueExpression(PROP_DISABLED, recuperarValorPorEL("#{pageFlowScope.configuracaoFormularioBean.visualizar ? true : " + tree.getVar() + ".desabilitado ? false : true}"));
        botaoEditar = adicionarShowPopUpEmLink(botaoEditar, ":::dialogEditarCampo");
        layoutAcoesCampos.getChildren().add(botaoEditar); */

        RichLink botaoConfig = criarLink(null, ACTION_INIT_CONFIGURAR_CAMPO, false, ICONE_CONFIGURACAO, ICONE_CONFIGURACAO_OFF, LABEL_BOTAO_CONFIG_CAMPO);
        //botaoConfig.setPartialTriggers(new String[] { soc.getId() });
        layoutAcoesCampos.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutAcoesCampos.getChildren().add(botaoConfig);

        RichLink excluirCampo = criarLink(null, ACTION_REMOVER_CAMPO, visualizar, ICONE_LIXEIRA, ICONE_LIXEIRA_OFF, LABEL_BOTAO_REMOVER_CAMPO);
        layoutAcoesCampos.getChildren().add(criarSpacer(SPACER_WIDTH, null));
        layoutAcoesCampos.getChildren().add(excluirCampo);
        //layoutAcoesCampos.setPartialTriggers(new String[] { soc.getId() });
        acoes.getChildren().add(layoutAcoesCampos);
        tree.getChildren().add(acoes);

        tree.setAutoHeightRows(10);
        tree.setWidth("100%");
        tree.setColumnStretching("column:" + nodeStamp.getId());
        tree.setValue(charatcerVal);
        tree.setInitiallyExpanded(true);
        return tree;
    }

    public static RichLink adicionarShowPopUpEmLink(RichLink link, String popup) {
        ClientListenerSet set = link.getClientListeners();
        if (set == null) {
            set = new ClientListenerSet();
            link.setClientListeners(set);
        }
        set.addBehavior("new AdfShowPopupBehavior('" + popup + "',null,null,null)");
        return link;
    }

    public static RichButton adicionarShowPopUpEmBotao(RichButton botao, String popup) {
        ClientListenerSet set = botao.getClientListeners();
        if (set == null) {
            set = new ClientListenerSet();
            botao.setClientListeners(set);
        }
        set.addBehavior("new AdfShowPopupBehavior('" + popup + "',null,null,null)");
        return botao;
    }

    public static RichColumn criarColuna(String cabecalho, boolean ordenavel, UIComponent componente) {
        RichColumn coluna = new RichColumn();
        if (cabecalho != null && !cabecalho.isEmpty())
            coluna.setHeaderText(cabecalho);
        if (ordenavel != false)
            coluna.setSortable(ordenavel);
        if (componente != null)
            coluna.getChildren().add(componente);
        return coluna;
    }

    public static RichButton criarBotao(String label, String elAction, boolean visualizar, String imagemOn, String imagemOff) {
        RichButton botao = new RichButton();
        if (label != null)
            botao.setText(label);
        if (elAction != null)
            botao.addActionListener(criarActionListener(botao.getClass(), elAction));
        if (imagemOn != null)
            botao.setIcon(imagemOn);
        if (imagemOff != null)
            botao.setDisabledIcon(imagemOff);
        botao.setDisabled(visualizar);
        return botao;
    }

    public static RichLink criarLink(String label, String elAction, boolean visualizar, String imagemOn, String imagemOff, String shortDesc) {
        RichLink link = new RichLink();

        if (label != null)
            link.setText(label);
        if (elAction != null)
            link.addActionListener(criarActionListener(link.getClass(), elAction));
        if (imagemOn != null)
            link.setIcon(imagemOn);
        if (imagemOff != null)
            link.setDisabledIcon(imagemOff);
        if (shortDesc != null)
            link.setShortDesc(shortDesc);

        link.setPartialSubmit(true);
        if (visualizar){
            link.setDisabled(visualizar);
        }
        return link;
    }

    public static RichSelectOneChoice criarChoiceTipoCampo() {
        RichSelectOneChoice soc = new RichSelectOneChoice();
        List<TipoCampoType> listaTipoCampos = Arrays.asList(TipoCampoType.values());
        UISelectItems selectItems = new UISelectItems();
        List<SelectItem> listaItens = new ArrayList<SelectItem>();
        for (TipoCampoType tipoCampoType : listaTipoCampos) {
            SelectItem item = criarSelectItem(tipoCampoType.getLabelTipoCampo(), tipoCampoType);
            listaItens.add(item);
        }
        selectItems.setValue(listaItens);
        soc.getChildren().add(selectItems);
        soc.setLabel(LABEL_TIPO_CAMPO);
        return soc;
    }

    public static SelectItem criarSelectItem(String label, TipoCampoType value) {
        SelectItem item = new SelectItem();
        item.setLabel(label);
        item.setValue(value);
        return item;
    }

    public static RichSelectOneChoice criarChoiceTipoSecao() {
        RichSelectOneChoice soc = new RichSelectOneChoice();
        UISelectItems selectItems = new UISelectItems();
        List<SelectItem> listaItens = new ArrayList<SelectItem>();
        SelectItem choice = new SelectItem();
        choice.setLabel(LABEL_SELECIONE);
        choice.setValue("");
        listaItens.add(choice);
        for (SecaoType tipoSecao : Arrays.asList(SecaoType.values())) {
            SelectItem secao = new SelectItem();
            secao.setLabel(tipoSecao.getLabelSecao());
            secao.setValue(tipoSecao);
            listaItens.add(secao);
        }
        selectItems.setValue(listaItens);
        soc.getChildren().add(selectItems);
        return soc;
    }

    //Metodos de EL
    public static MethodExpression criarEventoSelecionarLinhaEmTabela(Class classe, String elAction) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        MethodExpression methodExp = elFactory.createMethodExpression(elContext, elAction, classe, new Class[] { SelectionEvent.class });
        return methodExp;
    }

    public static ValueChangeListener criarValueChangeListener(Class classe, String elAction) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesCtx.getELContext();
        MethodExpression methodExp = elFactory.createMethodExpression(elContext, elAction, classe, new Class[] { ValueChangeEvent.class });
        return new MethodExpressionValueChangeListener(methodExp);
    }

    public static MethodExpressionActionListener criarActionListener(Class classe, String elAction) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        Application application = fctx.getApplication();
        ExpressionFactory elFactory = application.getExpressionFactory();
        ELContext elContext = fctx.getELContext();
        MethodExpression methodExpression = elFactory.createMethodExpression(elContext, elAction, classe, new Class[] { ActionEvent.class });
        return new MethodExpressionActionListener(methodExpression);
    }

    public static ValueExpression recuperarValorPorEL(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
        return valueExp;
    }

}
