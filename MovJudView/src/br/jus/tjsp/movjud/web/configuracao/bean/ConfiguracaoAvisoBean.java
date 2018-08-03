package br.jus.tjsp.movjud.web.configuracao.bean;

import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.configuracao.service.ConfiguracaoService;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.types.AbrangenciaType;
import br.jus.tjsp.movjud.persistence.base.types.DiaSemanaType;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;
import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.TipoAbrangenciaAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoPeriodicidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.login.bean.LoginBean;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;
import oracle.adf.view.rich.util.ResetUtils;

import org.apache.log4j.Logger;

@ManagedBean(name = "configuracaoAvisoBean")
@ViewScoped
public class ConfiguracaoAvisoBean extends BaseBean<ConfiguracaoAviso> {

    final static Logger logger = Logger.getLogger(ConfiguracaoAvisoBean.class);

    private static final long serialVersionUID = 20160826144207L;

    private ConfiguracaoService configuracaoService;

    private EstruturaJudiciariaService estruturaJudiciaria;

    private List<AvisoEstrutura> listaModelosAviso;

    private List<TipoAviso> listaTiposAviso;

    private List<TipoPeriodicidade> listaTiposPeriodicidade;

    private List<TipoAbrangenciaAviso> listaTiposAbrangenciaAviso;

    private List<SelectItem> listaQtdesDiasAntes;

    private List<SelectItem> listaDiasDaSemana;

    private LoginBean loginBean;

    private List<Perfil> listaPerfis;

    private Perfil perfil;

    private TipoAviso tipoAviso;

    private Usuario usuarioEspecifico;
    private RichSelectOneChoice inputFiltroPeriodicidade;
    private RichSelectOneChoice socDiaDaSemana;
    private RichSelectOneChoice socValor;
    private RichSelectOneChoice inputFiltroAbrangencia;
    private RichInputText usuarioSelecionado;
    private RichSelectOneChoice socPerfil;
    private RichInputDate socDataEsp;
    private RichPopup popUpAlterarConfiguracaoAviso;
    
    public ConfiguracaoAvisoBean() {
        configuracaoService = getBean(ConfiguracaoService.class);
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
        loginBean = getBean(LoginBean.class);
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
    }

    @PostConstruct
    public void init() {
        super.initDefault();
        tipoAviso = null;
        listaModelosAviso = configuracaoService.listarAvisoEstruturaOrdenadoPorNome();
        listaTiposAviso = configuracaoService.listarTiposAviso();
        if (listaTiposAviso == null) {
            listaTiposAviso = new ArrayList<TipoAviso>();
        }
        listaTiposPeriodicidade = configuracaoService.listarTiposPeriodicidade();
        if (listaTiposPeriodicidade == null) {
            listaTiposPeriodicidade = new ArrayList<TipoPeriodicidade>();
        }
        listaTiposAbrangenciaAviso = configuracaoService.listarTiposAbrangenciaAviso();
        if (listaTiposAbrangenciaAviso == null) {
            listaTiposAbrangenciaAviso = new ArrayList<TipoAbrangenciaAviso>();
        }
        listaPerfis = estruturaJudiciaria.listarPerfilOrdenadoPorNome();
        if (listaPerfis == null) {
            listaPerfis = new ArrayList<Perfil>();
        }
        listaQtdesDiasAntes = carregarListaQtdesDiasAntes();
        listaDiasDaSemana = carregarListaDiasDaSemana();
        pesquisarEntidade();
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.configuracaoAviso.log") + entidadePersistencia.getNomeTitulo());
        
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok) && validarCamposPopup()) {

            configuracaoService.salvarConfiguracaoAviso(entidadePersistencia);

            if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirConfiguracaoAviso")) {
                popUpConfirmacao("popupInfoInclusao");
            }
            if (dialogEvent.getComponent().getClientId().toString().contains("alterarConfiguracaoAvisoDialog")) {
                popUpConfirmacao("popupInfoAlterado");
            }

            // atualizarPagina();

        }
        pesquisarEntidade();
    }

    public void cancelarAtualiza(PopupCanceledEvent popupCanceledEvent) {
        ResetUtils.reset(popupCanceledEvent.getComponent());
        atualizarPagina();
    }
    
    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if(facesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.PROCESS_VALIDATIONS) {
            super.validate(facesContext, uIComponent, object);
        }
    }

    public boolean validarCamposPopup() {
        UIComponent panelGroupIncluirConfiguracaoAviso = findComponent("panelGroupIncluirConfiguracaoAviso");
        RichInputText inputTextTituloAviso = (RichInputText)findComponent(panelGroupIncluirConfiguracaoAviso, "inputTextTituloAviso");
        if((inputTextTituloAviso != null) && ((inputTextTituloAviso.getValue() == null) || inputTextTituloAviso.getValue().toString().trim().isEmpty())) {
            mensagemErroComponente(inputTextTituloAviso, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }
        
        RichInputText resumoAviso = (RichInputText)findComponent(panelGroupIncluirConfiguracaoAviso, "resumoAviso");
        if((resumoAviso != null) && ((resumoAviso.getValue() == null) || resumoAviso.getValue().toString().trim().isEmpty())) {
            mensagemErroComponente(resumoAviso, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }

        RichSelectOneChoice inputFiltroPeriodicidade = (RichSelectOneChoice)findComponent(panelGroupIncluirConfiguracaoAviso, "inputFiltroPeriodicidade");
        if((inputFiltroPeriodicidade != null) && (inputFiltroPeriodicidade.getValue() == null)) {
            mensagemErroComponente(inputFiltroPeriodicidade, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }
                
        if (entidadePersistencia.getTipoPeriodicidade().getCodigoPeriodicidade().equals("SEMANAL")) {
            if (socDiaDaSemana.getValue() == null || socDiaDaSemana.getValue().toString().isEmpty()) {
                mensagemErroComponente(socDiaDaSemana, AppBundleProperties.getString("msg.validacao"));
                return false;
            }
        } else if (entidadePersistencia.getTipoPeriodicidade().getCodigoPeriodicidade().equals("DIAS_ANTES_ACAO")) {
            if (socValor.getValue() == null || socValor.getValue().toString().isEmpty()) {
                mensagemErroComponente(socValor, AppBundleProperties.getString("msg.validacao"));
                return false;
            }
        }

        else if (entidadePersistencia.getTipoPeriodicidade().getCodigoPeriodicidade().equals("DATA_ESPECIFICA")) {
            if (socDataEsp.getValue() == null || socDataEsp.getValue().toString().isEmpty()) {
                mensagemErroComponente(socDataEsp, AppBundleProperties.getString("msg.validacao"));
                return false;
            }
        }

        RichSelectOneChoice inputFiltroAbrangencia = (RichSelectOneChoice)findComponent(panelGroupIncluirConfiguracaoAviso, "inputFiltroAbrangencia");
        if((inputFiltroAbrangencia != null) && (inputFiltroAbrangencia.getValue() == null)) {
            mensagemErroComponente(inputFiltroAbrangencia, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }
        
        if (entidadePersistencia.getTipoAbrangenciaAviso().getCodigoAbrangenciaAviso().equals("USUARIO_ESPECIFICO")) {
            if (entidadePersistencia.getValorAbrangenciaEnvio() == null ||
                entidadePersistencia.getValorAbrangenciaEnvio().isEmpty()) {
                RichInputText itUsuarioSelecionado=(RichInputText)findComponent("usuarioSelecionado");
                mensagemErroComponente(itUsuarioSelecionado, AppBundleProperties.getString("msg.validacao"));
                return false;
            }
        } else if (entidadePersistencia.getTipoAbrangenciaAviso().getCodigoAbrangenciaAviso().equals("PERFIL")) {
            if (entidadePersistencia.getValorAbrangenciaEnvio() == null ||
                entidadePersistencia.getValorAbrangenciaEnvio().isEmpty()) {
                RichSelectOneChoice socPerfil1 = (RichSelectOneChoice)findComponent("socPerfil");
                mensagemErroComponente(socPerfil1, AppBundleProperties.getString("msg.validacao"));
                return false;
            }
        }
        
        RichSelectOneChoice modeloConfiguracaoAvisoChoice = (RichSelectOneChoice)findComponent(panelGroupIncluirConfiguracaoAviso, "modeloConfiguracaoAvisoChoice");
        if((modeloConfiguracaoAvisoChoice != null) && (modeloConfiguracaoAvisoChoice.getValue() == null)) {
            mensagemErroComponente(modeloConfiguracaoAvisoChoice, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }
        
        RichSelectOneChoice listaSituacaoConfiguracaoAviso = (RichSelectOneChoice)findComponent(panelGroupIncluirConfiguracaoAviso, "listaSituacaoConfiguracaoAviso");
        if((listaSituacaoConfiguracaoAviso != null) && (listaSituacaoConfiguracaoAviso.getValue() == null)) {
            mensagemErroComponente(listaSituacaoConfiguracaoAviso, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }
        
        RichSelectOneRadio sor2 = (RichSelectOneRadio)findComponent(panelGroupIncluirConfiguracaoAviso,"sor2");
        if((sor2 != null) && (sor2.getValue() == null)) {
            mensagemErroComponente(sor2, AppBundleProperties.getString("msg.validacao"));
            return false;            
        }        
        
        return true;
    }

    @Override
    public String pesquisarEntidade() {
        if (tipoAviso != null) {
            if (entidadeFiltro.getAvisoEstrutura() == null) {
                entidadeFiltro.setAvisoEstrutura(new AvisoEstrutura());
            }
            entidadeFiltro.getAvisoEstrutura().setTipoAviso(tipoAviso);
        }
        listaEntidade = configuracaoService.listarConfiguracaoAvisoComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            configuracaoService.excluirConfiguracaoAviso(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public Class<ConfiguracaoAviso> getClasseEntidade() {
        return ConfiguracaoAviso.class;
    }

    /*   private List<SelectItem> carregarListaModelosAviso() {
        List<AvisoEstrutura> listaModelosAvisoObj = configuracaoService.listarAvisoEstruturaOrdenadoPorNome();
        listaModelosAviso = new ArrayList<SelectItem>();
        for (AvisoEstrutura modeloAvisoObj : listaModelosAvisoObj) {
            SelectItem modeloAviso = new SelectItem();
            modeloAviso.setValue(modeloAvisoObj);
            modeloAviso.setLabel(modeloAvisoObj.getNomeAvisoEstrutura());
            listaModelosAviso.add(modeloAviso);
        }
        return listaModelosAviso;
    } */

    private List<SelectItem> carregarListaQtdesDiasAntes() {
        List<SelectItem> listaQtdesDiasAntes = new ArrayList<SelectItem>();
        for (Integer i = 1; i <= 10; i++) {
            SelectItem selectItem = new SelectItem();
            if (i == 1) {
                selectItem.setLabel(i + " dia");
            } else {
                selectItem.setLabel(i + " dias");
            }
            selectItem.setValue(i.toString());
            listaQtdesDiasAntes.add(selectItem);
        }
        return listaQtdesDiasAntes;
    }

    private List<SelectItem> carregarListaDiasDaSemana() {
        List<SelectItem> listaDiasDaSemana = new ArrayList<SelectItem>();
        for (DiaSemanaType diaSemana : DiaSemanaType.values()) {
            SelectItem selectItem = new SelectItem();
            selectItem.setLabel(diaSemana.getNome());
            selectItem.setValue(diaSemana.getCodigo());
            listaDiasDaSemana.add(selectItem);
        }
        return listaDiasDaSemana;
    }

    public void adicionarValorAbrangencia(ActionEvent actionEvent) {
        // <epr 20180801 adiciona mensagem de compoente - solicitação de mensagem Eliane, email "Teste Corregedoria - Movjud-homologação -aviso", 30/07/2018>
        boolean isAbrangenciaPerfil = this.entidadePersistencia.getTipoAbrangenciaAviso().getCodigoAbrangenciaAviso().equals(AbrangenciaType.PERFIL.getCodigo());
        boolean isAbrangenciaUsuarioEspecifico = this.entidadePersistencia.getTipoAbrangenciaAviso().getCodigoAbrangenciaAviso().equals(AbrangenciaType.USUARIO_ESPECIFICO.getCodigo());
        if(isAbrangenciaUsuarioEspecifico && (this.usuarioEspecifico == null)) {
            UIComponent usuarioAbrangencia = findComponent("usuarioSelecionado");
            mensagemErroComponente(usuarioAbrangencia, "O usuário específico deve ser informado");
            return;
        }
        if(isAbrangenciaPerfil && (this.perfil == null)) {
            UIComponent socPerfil = findComponent("socPerfil");
            mensagemErroComponente(socPerfil, "O perfil deve ser informado");
            return;            
        }
        // </epr 20180801 - solicitação de mensagem Eliane, email "Teste Corregedoria - Movjud-homologação -aviso", 30/07/2018>
        // ADICIONA UMA ABRANGENCIA
        Long idAbrangenciaAdicionada = null;
        if (isAbrangenciaPerfil && (perfil != null)) {
            if (this.entidadePersistencia.getPerfisAbrangencia() == null) {
                this.entidadePersistencia.setPerfisAbrangencia(new LinkedHashSet<Perfil>());
            }
            this.entidadePersistencia.getPerfisAbrangencia().add(perfil);
            idAbrangenciaAdicionada = perfil.getIdPerfil();

        } else if (isAbrangenciaUsuarioEspecifico && (usuarioEspecifico != null)) {
            if (this.entidadePersistencia.getUsuariosAbrangencia() == null) {
                this.entidadePersistencia.setUsuariosAbrangencia(new LinkedHashSet<Usuario>());
            }
            this.entidadePersistencia.getUsuariosAbrangencia().add(usuarioEspecifico);
            idAbrangenciaAdicionada = usuarioEspecifico.getIdUsuario();
        }

        // ATUALIZA VARIAVEIS
        if (idAbrangenciaAdicionada != null) {
            this.perfil = null;
            this.usuarioEspecifico = null;
            sugestao = "";
         }
    }

    private boolean isAbrangenciaAdicionada(String valorAbrangeciaAdicionar) {
        String valorAbrangenciaEnvio = this.entidadePersistencia.getValorAbrangenciaEnvio();
        if (valorAbrangenciaEnvio.equals(valorAbrangeciaAdicionar) ||
            valorAbrangenciaEnvio.startsWith(valorAbrangeciaAdicionar + ",") ||
            valorAbrangenciaEnvio.endsWith(", " + valorAbrangeciaAdicionar) ||
            valorAbrangenciaEnvio.contains(", " + valorAbrangeciaAdicionar + ",")) {
            return true;
        } else {
            return false;
        }
    }

    public void limparValorAbrangencia(ValueChangeEvent valueChangeEvent) {
        if (this.entidadePersistencia.getUsuariosAbrangencia() != null)
            this.entidadePersistencia.getUsuariosAbrangencia().clear();
        if (this.entidadePersistencia.getPerfisAbrangencia() != null)
            this.entidadePersistencia.getPerfisAbrangencia().clear();
    }

    public void limparValorAbrangencia(ActionEvent actionEvent) {
        if (this.entidadePersistencia.getUsuariosAbrangencia() != null)
            this.entidadePersistencia.getUsuariosAbrangencia().clear();
        if (this.entidadePersistencia.getPerfisAbrangencia() != null)
            this.entidadePersistencia.getPerfisAbrangencia().clear();
    }

    @Override
    public List listarAutoCompletar(String parametro) {
        Usuario usuario = new Usuario();
        usuario.setNome(parametro);
        return estruturaJudiciaria.listarUsuariosComFiltroPaginacao(usuario, paginacaoSeguestao);
    }

    public void alterarNomeUsuarioSelecionado(ValueChangeEvent valueChangeEvent) {
        try {
            setUsuarioEspecifico(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                      Usuario.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), usuarioEspecifico.getNome());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        List<SelectItem> listaSugestoesUsuarios = new ArrayList<SelectItem>();

        if (listaParametros != null) {
            for (Usuario item : ((List<Usuario>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNome());
                selectItem.setValue(item.getIdUsuario());
                listaSugestoesUsuarios.add(selectItem);
            }
        }
        return listaSugestoesUsuarios;
    }

    /*     public void setListaModelosAviso(List<SelectItem> listaModelosAviso) {
        this.listaModelosAviso = listaModelosAviso;
    }

    public List<SelectItem> getListaModelosAviso() {
        return listaModelosAviso;
    } */

    public void setListaModelosAviso(List<AvisoEstrutura> listaModelosAviso) {
        this.listaModelosAviso = listaModelosAviso;
    }

    public List<AvisoEstrutura> getListaModelosAviso() {
        return listaModelosAviso;
    }

    public void setListaTiposAviso(List<TipoAviso> listaTiposAviso) {
        this.listaTiposAviso = listaTiposAviso;
    }

    public List<TipoAviso> getListaTiposAviso() {
        return listaTiposAviso;
    }

    public void setListaTiposPeriodicidade(List<TipoPeriodicidade> listaTiposPeriodicidade) {
        this.listaTiposPeriodicidade = listaTiposPeriodicidade;
    }

    public List<TipoPeriodicidade> getListaTiposPeriodicidade() {
        return listaTiposPeriodicidade;
    }

    public void setTipoAviso(TipoAviso tipoAviso) {
        this.tipoAviso = tipoAviso;
    }

    public TipoAviso getTipoAviso() {
        return tipoAviso;
    }

    public void setListaTiposAbrangenciaAviso(List<TipoAbrangenciaAviso> listaTiposAbrangenciaAviso) {
        this.listaTiposAbrangenciaAviso = listaTiposAbrangenciaAviso;
    }

    public List<TipoAbrangenciaAviso> getListaTiposAbrangenciaAviso() {
        return listaTiposAbrangenciaAviso;
    }

    public void setUsuarioEspecifico(Usuario usuarioEspecifico) {
        this.usuarioEspecifico = usuarioEspecifico;
    }

    public Usuario getUsuarioEspecifico() {
        return usuarioEspecifico;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setListaPerfis(List<Perfil> listaPerfis) {
        this.listaPerfis = listaPerfis;
    }

    public List<Perfil> getListaPerfis() {
        return listaPerfis;
    }

    public void setListaQtdesDiasAntes(List<SelectItem> listaQtdesDiasAntes) {
        this.listaQtdesDiasAntes = listaQtdesDiasAntes;
    }

    public List<SelectItem> getListaQtdesDiasAntes() {
        return listaQtdesDiasAntes;
    }

    public void setListaDiasDaSemana(List<SelectItem> listaDiasDaSemana) {
        this.listaDiasDaSemana = listaDiasDaSemana;
    }

    public List<SelectItem> getListaDiasDaSemana() {
        return listaDiasDaSemana;
    }

    @Override
    public String excluirEntidade() {
        return null;
    }

    public void setInputFiltroPeriodicidade(RichSelectOneChoice inputFiltroPeriodicidade) {
        this.inputFiltroPeriodicidade = inputFiltroPeriodicidade;
    }

    public RichSelectOneChoice getInputFiltroPeriodicidade() {
        return inputFiltroPeriodicidade;
    }

    public void setSocDiaDaSemana(RichSelectOneChoice socDiaDaSemana) {
        this.socDiaDaSemana = socDiaDaSemana;
    }

    public RichSelectOneChoice getSocDiaDaSemana() {
        return socDiaDaSemana;
    }

    public void setSocValor(RichSelectOneChoice socValor) {
        this.socValor = socValor;
    }

    public RichSelectOneChoice getSocValor() {
        return socValor;
    }

    public void setInputFiltroAbrangencia(RichSelectOneChoice inputFiltroAbrangencia) {
        this.inputFiltroAbrangencia = inputFiltroAbrangencia;
    }

    public RichSelectOneChoice getInputFiltroAbrangencia() {
        return inputFiltroAbrangencia;
    }

    public void setUsuarioSelecionado(RichInputText usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public RichInputText getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setSocPerfil(RichSelectOneChoice socPerfil) {
        this.socPerfil = socPerfil;
    }

    public RichSelectOneChoice getSocPerfil() {
        return socPerfil;
    }

    public void setSocDataEsp(RichInputDate socDataEsp) {
        this.socDataEsp = socDataEsp;
    }

    public RichInputDate getSocDataEsp() {
        return socDataEsp;
    }

    public String limparCampos() {
        
        if (entidadePersistencia.getTipoAbrangenciaAviso().getCodigoAbrangenciaAviso().equals("USUARIO_ESPECIFICO")) {
            RichInputText usuarioAbrangencia = (RichInputText) findComponent("usuarioSelecionado");
            ResetUtils.reset(usuarioAbrangencia);
            atualizarComponenteDeTela(usuarioAbrangencia);
        } else {
            RichSelectOneChoice perfilAbrangencia = (RichSelectOneChoice) findComponent("socPerfil");
            ResetUtils.reset(perfilAbrangencia);
            atualizarComponenteDeTela(perfilAbrangencia);
        }
        
        return null;
    }
}
