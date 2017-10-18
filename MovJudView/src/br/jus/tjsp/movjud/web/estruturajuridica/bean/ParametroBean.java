package br.jus.tjsp.movjud.web.estruturajuridica.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Parametro;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "parametroBean")
@ViewScoped
public class ParametroBean extends BaseBean<Parametro> {

    final static Logger logger = Logger.getLogger(ParametroBean.class);

    @SuppressWarnings("compatibility:6659452652146587467")
    private static final long serialVersionUID = 1L;

    private EstruturaJudiciariaService estruturaJudiciaria;


    public ParametroBean() {
        estruturaJudiciaria = getBean(EstruturaJudiciariaService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = estruturaJudiciaria.listarParametroComFiltroPaginacao(getEntidadeFiltro(), getPaginacao());
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            estruturaJudiciaria.excluirParametro(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
            }
    }

    @Override
    public Class<Parametro> getClasseEntidade() {
        return Parametro.class;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.parametro.log") + entidadePersistencia.getNomeParametro());
        
        entidadePersistencia.setFlagVisivel(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
        estruturaJudiciaria.salvarParametro(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirParametro")) {
                   popUpConfirmacao("popupInfoInclusao");
               }
               if (dialogEvent.getComponent().getClientId().toString().contains("dialogAlterarParametro")) {
                   popUpConfirmacao("popupInfoAlterado");
               }
        pesquisarEntidade();
    }

    @Override
    public String excluirEntidade() {
        return null;
    }
}
