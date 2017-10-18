package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.types.UfType;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "estabelecimentoPrisionalBean")
@ViewScoped
public class EstabelecimentoPrisionalBean extends BaseBean<EstabelecimentoPrisional> {
    final static Logger logger = Logger.getLogger(RegiaoBean.class);
    private EstruturaJudiciariaService estruturaJudiciaria;

    private List<UfType> listaUFs;

    private RichSelectOneChoice socUf;

    public EstabelecimentoPrisionalBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);


    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        listaUFs = Arrays.asList(UfType.values());

    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.estabelecimentoPrisional.log") + entidadePersistencia.getNomeEstabelecimentoPrisional());
         
        estruturaJudiciaria.salvarEstabelecimentoPrisional(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirEstabelecimentoPrisional")) {
            popUpConfirmacao("popupInfoInclusao");
        }
        if (dialogEvent.getComponent().getClientId().toString().contains("alterarEstabelecimentoPrisionalDialog")) {
            popUpConfirmacao("popupInfoAlterado");
        }
        pesquisarEntidade();
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarEstabelecimentosPrisionaisOrdenadoPorNome(entidadeFiltro, paginacao);
        return null;
    }


    @Override
    public Class<EstabelecimentoPrisional> getClasseEntidade() {
        return EstabelecimentoPrisional.class;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirEstabelecimentoPrisional(entidadePersistencia);
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


    public void setListaUFs(List<UfType> listaUFs) {
        this.listaUFs = listaUFs;
    }

    public List<UfType> getListaUFs() {
        return listaUFs;
    }


}
