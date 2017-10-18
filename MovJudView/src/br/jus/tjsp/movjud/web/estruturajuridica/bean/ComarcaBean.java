package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
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

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "comarcaBean")
@ViewScoped
public class ComarcaBean extends BaseBean<Comarca> {

    final static Logger logger = Logger.getLogger(ComarcaBean.class);

    private EstruturaJudiciariaService estruturaJudiciaria;

    public ComarcaBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        entidadeFiltro.setCircunscricao(new Circunscricao());
        entidadeFiltro.getCircunscricao().setRegiao(new Regiao());
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarComarcaComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirComarca(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public Class<Comarca> getClasseEntidade() {
        return Comarca.class;
    }

    @Override
    public List listarAutoCompletar(String parametro) {
        Circunscricao circunscricaoEntity = new Circunscricao();
        circunscricaoEntity.setNomeCircunscricao(parametro);
        return estruturaJudiciaria.listarCircunscricoesComFiltroPaginacao(circunscricaoEntity, paginacaoSeguestao);
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        List<SelectItem> listaSugestoesRegioes = null;
        if (listaParametros != null) {
            listaSugestoesRegioes = new ArrayList<SelectItem>();
            for (Circunscricao item : ((List<Circunscricao>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNomeCircunscricao());
                selectItem.setValue(item.getIdCircunscricao());
                listaSugestoesRegioes.add(selectItem);
            }
        }
        return listaSugestoesRegioes;
    }

    public void alterarNomeCircunscricaoSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setCircunscricao(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                                       Circunscricao.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  entidadePersistencia.getCircunscricao().getNomeCircunscricao());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.comarca.log") + entidadePersistencia.getNomeComarca());
        
        estruturaJudiciaria.salvarComarca(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirComarca")) {
            popUpConfirmacao("popupInfoInclusao");
        }
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogAlterarComarca")) {
            popUpConfirmacao("popupInfoAlterado");
        }
        pesquisarEntidade();
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        super.initDialogInserir(popupFetchEvent);
        entidadePersistencia.setCircunscricao(new Circunscricao());
        sugestao = "";
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        sugestao = entidadePersistencia.getCircunscricao().getNomeCircunscricao();
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        super.initDialogVisualizar(popupFetchEvent);
        sugestao = entidadePersistencia.getCircunscricao().getNomeCircunscricao();
    }

    @Override
    public String excluirEntidade() {
        return null;
    }
}
