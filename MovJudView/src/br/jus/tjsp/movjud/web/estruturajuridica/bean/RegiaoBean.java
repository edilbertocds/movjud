package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import br.jus.tjsp.movjud.web.login.bean.LoginBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "regiaoBean")
@ViewScoped
public class RegiaoBean extends BaseBean<Regiao> {
    final static Logger logger = Logger.getLogger(RegiaoBean.class);
    @SuppressWarnings("compatibility:-6769998247483965390")
    private static final long serialVersionUID = 1L;

    private boolean obrigatorio;

    private EstruturaJudiciariaService estruturaJudiciaria;
   
    public RegiaoBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        //entidadeFiltro = new Regiao();
        //  paginacao = new Paginacao();
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarRegioesOrdenadoPorNome(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirRegiao(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
        }
    }

    @Override
    public Class<Regiao> getClasseEntidade() {
        return Regiao.class;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.regiao.log") + entidadePersistencia.getNomeRegiao());
        
        estruturaJudiciaria.salvarRegiao(entidadePersistencia);        
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirRegiao")) {
            popUpConfirmacao("popupInfoInclusao");
        }
        if (dialogEvent.getComponent().getClientId().toString().contains("alterarRegiaoDialog")) {
            popUpConfirmacao("popupInfoAlterado");
        }
        
        
        pesquisarEntidade();
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

}
