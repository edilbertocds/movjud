package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.persistence.entity.TipoEntrancia;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adf.view.rich.util.ResetUtils;

import org.apache.log4j.Logger;

@ManagedBean(name = "foroBean")
@ViewScoped
public class ForoBean extends BaseBean<Foro> {

    final static Logger logger = Logger.getLogger(ForoBean.class);

    @SuppressWarnings("compatibility:6659452652146587467")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciaria;

    private List<TipoEntrancia> listaTipoEntrancia;

    public ForoBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        entidadeFiltro.setComarca(new Comarca());
        entidadeFiltro.getComarca().setCircunscricao(new Circunscricao());
        entidadeFiltro.getComarca().getCircunscricao().setRegiao(new Regiao());
        listaTipoEntrancia = estruturaJudiciaria.listarTipoEntrancia();
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarForoComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirForo(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public Class<Foro> getClasseEntidade() {
        return Foro.class;
    }

    public void alterarNomeComarcaSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setComarca(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                                 Comarca.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  entidadePersistencia.getComarca().getNomeComarca());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    @Override
    public List listarAutoCompletar(String parametro) {
        Comarca entidadeComarca = new Comarca();
        entidadeComarca.setNomeComarca(parametro);
        return estruturaJudiciaria.listarComarcaComFiltroPaginacao(entidadeComarca, paginacaoSeguestao);
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        List<SelectItem> listaSugestoesComarcas = null;
        if (listaParametros != null) {
            listaSugestoesComarcas = new ArrayList<SelectItem>();
            for (Comarca item : ((List<Comarca>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNomeComarca());
                selectItem.setValue(item.getIdComarca());
                listaSugestoesComarcas.add(selectItem);
            }
        }
        return listaSugestoesComarcas;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.foro.log") + entidadePersistencia.getNomeForo());
        
        estruturaJudiciaria.salvarForo(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirForo")) {
            popUpConfirmacao("popupInfoInclusao");
        }
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogAlterarForo")) {
            popUpConfirmacao("popupInfoAlterado");
        }
        pesquisarEntidade();
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        super.initDialogInserir(popupFetchEvent);
        entidadePersistencia.setComarca(new Comarca());
        entidadePersistencia.setTipoEntrancia(new TipoEntrancia());
        sugestao = "";
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        sugestao = entidadePersistencia.getComarca().getNomeComarca();
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        super.initDialogVisualizar(popupFetchEvent);
        sugestao = entidadePersistencia.getComarca().getNomeComarca();
    }

    public void setListaTipoEntrancia(List<TipoEntrancia> listaTipoEntrancia) {
        this.listaTipoEntrancia = listaTipoEntrancia;
    }

    public List<TipoEntrancia> getListaTipoEntrancia() {
        return listaTipoEntrancia;
    }

    @Override
    public String excluirEntidade() {
        return null;
    }
}
