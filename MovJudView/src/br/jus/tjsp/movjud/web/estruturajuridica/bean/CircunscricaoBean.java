package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.recursos.service.AuditoriaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.faces.validator.ValidatorException;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "circunscricaoBean")
@ViewScoped
public class CircunscricaoBean extends BaseBean<Circunscricao> {
    final static Logger logger = Logger.getLogger(CircunscricaoBean.class);
    @SuppressWarnings("compatibility:2211955057301357009")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciaria;
    private boolean obrigatorio;

    public CircunscricaoBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        entidadeFiltro.setRegiao(new Regiao());
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarCircunscricoesComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirCircunscricao(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public Class<Circunscricao> getClasseEntidade() {
        return Circunscricao.class;
    }


    public void alterarNomeRegiaoSelecionada(ValueChangeEvent valueChangeEvent) {
        try {
            entidadePersistencia.setRegiao(alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())),
                                                                                Regiao.class));
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(),
                                                  entidadePersistencia.getRegiao().getNomeRegiao());
        } catch (Exception e) {
            atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
        }
    }

    @Override
    public List listarAutoCompletar(String parametro) {
        Regiao regiaoEntity = new Regiao();
        regiaoEntity.setNomeRegiao(parametro);
        return estruturaJudiciaria.listarRegioesComFiltroPaginacao(regiaoEntity, paginacaoSeguestao);
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
        List<SelectItem> listaSugestoesRegioes = null;
        if (listaParametros != null) {
            listaSugestoesRegioes = new ArrayList<SelectItem>();
            for (Regiao item : ((List<Regiao>) listaParametros)) {
                SelectItem selectItem = new SelectItem();
                selectItem.setLabel(item.getNomeRegiao());
                selectItem.setValue(item.getIdRegiao());
                listaSugestoesRegioes.add(selectItem);
            }
        }
        return listaSugestoesRegioes;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.circunscricao.log") + entidadePersistencia.getNomeCircunscricao());
        
        estruturaJudiciaria.salvarCircunscricao(entidadePersistencia);

        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirCircunscricao")) {
            popUpConfirmacao("popupInfoInclusao");
        }
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogAlterarCircunscricao")) {
            popUpConfirmacao("popupInfoAlterado");
        }
        pesquisarEntidade();
    }

    @Override
    public void initDialogInserir(PopupFetchEvent popupFetchEvent) {
        super.initDialogInserir(popupFetchEvent);
        entidadePersistencia.setRegiao(new Regiao());
        sugestao = "";
    }

    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        sugestao = entidadePersistencia.getRegiao().getNomeRegiao();
    }

    @Override
    public void initDialogVisualizar(PopupFetchEvent popupFetchEvent) {
        super.initDialogVisualizar(popupFetchEvent);
        sugestao = entidadePersistencia.getRegiao().getNomeRegiao();
    }

    @Override
    public String excluirEntidade() {
        return null;
    }

    public void setEstruturaJudiciaria(EstruturaJudiciariaService estruturaJudiciaria) {
        this.estruturaJudiciaria = estruturaJudiciaria;
    }

    public EstruturaJudiciariaService getEstruturaJudiciaria() {
        return estruturaJudiciaria;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }
}
