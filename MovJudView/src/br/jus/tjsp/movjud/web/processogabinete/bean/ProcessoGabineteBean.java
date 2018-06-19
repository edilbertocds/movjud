package br.jus.tjsp.movjud.web.processogabinete.bean;


import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.processogabinete.service.ProcessoGabineteService;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.ProcessoGabinete;
import br.jus.tjsp.movjud.persistence.entity.UsuarioProcessoGabinete;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;


@ManagedBean(name = "processoGabineteBean")
@ViewScoped
public class ProcessoGabineteBean extends BaseBean<UsuarioProcessoGabinete> {
    private ProcessoGabineteService processoGabineteService;
    private ProcessoGabinete processoGabinete;
    private RichPopup atualizarProcessoGabinetePopUp;
    private RichSelectOneRadio flagArquivado;
    private RichTable tabelaResultados;
    private List<SelectItem> listaAno;
    private boolean temProcessoNaoArquivado;


    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) {

        if (getFlagArquivado().getValue().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) && object == null || getFlagArquivado().getValue().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) && object.toString().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                          AppBundleProperties.getString("msg.validacao"), null));
        }
    }

    public ProcessoGabineteBean() {
        processoGabineteService = getBean(ProcessoGabineteService.class);

    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        List<ProcessoGabinete> listaProcesso =
            processoGabineteService.listarProcessosGabineteDoMagistrado(getEntidadePersistencia().getUsuario());
        if(listaProcesso == null) {
            listaProcesso = new ArrayList<ProcessoGabinete>();
        }
        temProcessoNaoArquivado = listaProcesso.stream().anyMatch(p->p.getFlagArquivado().equals(ConstantesMovjud.FLAG_SITUACAO_NAO));
        entidadePersistencia.getUsuario().setProcessosGabinete(listaProcesso);
        popularListaAno();
    }

    private void popularListaAno() {
        listaAno = new ArrayList<SelectItem>();
        for (int i = Calendar.getInstance().get(Calendar.YEAR); 1900 <= i; i--) {
            listaAno.add(new SelectItem(i, String.valueOf(i)));
            //listaAno.add(new SelectItem(new Long(i), String.valueOf(i)));
        }
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {

        if (validarArquivado()) {
            processoGabineteService.salvarUsuario(entidadePersistencia.getUsuario());
            pesquisarEntidade();
        } else {
            mensagemErro(AppBundleProperties.getString("msg.processoGabinete.erroSalvar"));
        }


    }


    @Override
    public String pesquisarEntidade() {
        listaEntidade =
            processoGabineteService.listarMagistradosProcessoGabinetePaginado(getEntidadeFiltro(), paginacao);

        return null;
    }


    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<UsuarioProcessoGabinete> getClasseEntidade() {
        // TODO Implement this method
        return UsuarioProcessoGabinete.class;
    }

    public boolean validarArquivado() {
        int quantProcGabArquivado = 0;
        boolean valido = true;
        List<ProcessoGabinete> listaProcGab = entidadePersistencia.getUsuario().getProcessosGabinete();
        for (ProcessoGabinete item : listaProcGab) {
            if (item.getFlagArquivado().equals(ConstantesMovjud.FLAG_SITUACAO_NAO)) {
                quantProcGabArquivado++;
            }
        }
        if (quantProcGabArquivado > 1) {
            valido = false;
        } else {
            for (ProcessoGabinete item : listaProcGab) {
                if (item.getFlagArquivado().equals(ConstantesMovjud.FLAG_SITUACAO_NAO)) {
                    item.setDataArquivamento(null);
                    item.setNumeroCaixa(null);
                }
            }
            valido = true;
        }
        return valido;
    }

    public String incluirProcessoGabinete() {
        ProcessoGabinete novoProcessoGabinete = new ProcessoGabinete();
        /*novoProcessoGabinete.setTipoProcesso("GAB");
        novoProcessoGabinete.setNumeroProcessoGabinete(new Long(0));
        novoProcessoGabinete.setAnoProcessoGabinete(new Integer((new SimpleDateFormat("yyyy")).format(new Date())));
        novoProcessoGabinete.setFlagArquivado("N");*/
        entidadePersistencia.addProcessoGabinete(novoProcessoGabinete);
        processoGabinete = null;
        return null;
    }
    
    public void excluirProcessoGabinete(ActionEvent actionEvent) {
        entidadePersistencia.removeProcessoGabinete(processoGabinete);
        processoGabinete = null;
    }

    public void selecionarProcessoGabinete(org.apache.myfaces.trinidad.event.SelectionEvent selectionEvent) {
        RichTable richTable = (RichTable) selectionEvent.getSource();
        processoGabinete = (ProcessoGabinete) richTable.getSelectedRowData();
        richTable.getSelectedRowKeys().clear();
    }

    public String salvarPopup() {
        if (validarArquivado()) {
            if (validarAno()) {
                processoGabineteService.salvarUsuario(entidadePersistencia.getUsuario());
                atualizarProcessoGabinetePopUp.hide();
                pesquisarEntidade();
                AdfFacesContext.getCurrentInstance().addPartialTarget(tabelaResultados);

            } else {
                mensagemErro(AppBundleProperties.getString("msg.processoGabinete.erroAno"));
            }

        } else {
            mensagemErro(AppBundleProperties.getString("msg.processoGabinete.erroSalvar"));
        }
        return null;
    }

    public boolean validarAno() {
        int quantAnosInvalidos = 0;
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        boolean valido = true;
        List<ProcessoGabinete> listaProcessoGabinete = entidadePersistencia.getUsuario().getProcessosGabinete();
        for (ProcessoGabinete item : listaProcessoGabinete) {
            if (item.getAnoProcessoCpa() != null) {
                if (anoAtual < item.getAnoProcessoCpa() || anoAtual < item.getAnoProcessoGabinete()) {
                    quantAnosInvalidos++;
                }
            } else {
                if (anoAtual < item.getAnoProcessoGabinete()) {
                    quantAnosInvalidos++;
                }
            }

            if (quantAnosInvalidos > 0) {
                valido = false;
            } else {
                valido = true;
            }
        }
        return valido;
    }

    public void setAtualizarProcessoGabinetePopUp(RichPopup atualizarProcessoGabinetePopUp) {
        this.atualizarProcessoGabinetePopUp = atualizarProcessoGabinetePopUp;
    }

    public RichPopup getAtualizarProcessoGabinetePopUp() {
        return atualizarProcessoGabinetePopUp;
    }

    public void setFlagArquivado(RichSelectOneRadio flagArquivado) {
        this.flagArquivado = flagArquivado;
    }

    public RichSelectOneRadio getFlagArquivado() {
        return flagArquivado;
    }

    public void setTabelaResultados(RichTable tabelaResultados) {
        this.tabelaResultados = tabelaResultados;
    }

    public RichTable getTabelaResultados() {
        return tabelaResultados;
    }

    public void setListaAno(List<SelectItem> listaAno) {
        this.listaAno = listaAno;
    }

    public List<SelectItem> getListaAno() {
        return listaAno;
    }

    public void setTemProcessoNaoArquivado(boolean temProcessoNaoArquivado) {
        this.temProcessoNaoArquivado = temProcessoNaoArquivado;
    }

    public boolean isTemProcessoNaoArquivado() {
        return temProcessoNaoArquivado;
    }
}
