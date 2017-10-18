package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.persistence.entity.DominioBI;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

@ManagedBean(name = "dominioBIBean")
@ViewScoped
public class DominioBIBean extends BaseBean<DominioBI> {
    @SuppressWarnings("compatibility:8367282144919131726")
    private static final long serialVersionUID = 1L;
    private EstruturaJudiciariaService estruturaJudiciaria;
    private boolean alterando;
    
    public DominioBIBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }
    
    @PostConstruct
    public void init() {
        super.init();
        
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        estruturaJudiciaria.salvarDominioBI(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirDominioBI")) {
                   popUpConfirmacao("popupInfoInclusao");
               }
               if (dialogEvent.getComponent().getClientId().toString().contains("dialogAlterarDominioBI")) {
                   popUpConfirmacao("popupInfoAlterado");
               }
        pesquisarEntidade();
        alterando = false;
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarDominioBIComFiltroPaginacao(getEntidadeFiltro(), getPaginacao());
        return null;
    }

    @Override
    public String excluirEntidade() {
        estruturaJudiciaria.excluirDominioBI(entidadePersistencia);
        return null;
    }
    
    public void excluirEntidade(DialogEvent dialogEvent) {
        this.excluirEntidade();
        pesquisarEntidade();
    }
    
    @Override
    public Class getClasseEntidade() {
        return DominioBI.class;
    }
    
    @Override
    public void initDialogAlterar(PopupFetchEvent popupFetchEvent) {
        super.initDialogAlterar(popupFetchEvent);
        alterando = true;
    }

    public void setAlterando(boolean alterando) {
        this.alterando = alterando;
    }

    public boolean isAlterando() {
        return alterando;
    }
}
