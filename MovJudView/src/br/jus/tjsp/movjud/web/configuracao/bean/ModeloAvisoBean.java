package br.jus.tjsp.movjud.web.configuracao.bean;

import br.jus.tjsp.movjud.business.configuracao.service.ConfiguracaoService;
import br.jus.tjsp.movjud.business.recursos.service.AuditoriaService;
import br.jus.tjsp.movjud.core.exception.MovJudErrorMessage;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;
import br.jus.tjsp.movjud.persistence.entity.Variavel;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "modeloAvisoBean")
@ViewScoped
public class ModeloAvisoBean extends BaseBean<AvisoEstrutura> {

    final static Logger logger = Logger.getLogger(ModeloAvisoBean.class);

    private static final long serialVersionUID = 20160819145607L;

    private ConfiguracaoService configuracaoService;

    private List<TipoAviso> listaTiposAviso;

    private Variavel variavel;

    public ModeloAvisoBean() {
        configuracaoService = getBean(ConfiguracaoService.class);
    }

    @PostConstruct
    public void init() {
        super.init();
        listaTiposAviso = configuracaoService.listarTiposAviso();
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        logger.info(AppBundleProperties.getString("msg.modeloAviso.log") + entidadePersistencia.getNomeAvisoEstrutura());
        
        configuracaoService.salvarAvisoEstrutura(entidadePersistencia);
        if (dialogEvent.getComponent().getClientId().toString().contains("dialogIncluirModeloAviso")) {
              popUpConfirmacao("popupInfoInclusao");
          }
          if (dialogEvent.getComponent().getClientId().toString().contains("alterarModeloAvisoDialog")) {
              popUpConfirmacao("popupInfoAlterado");
          }
        
        pesquisarEntidade();
    }

    @Override
    public String pesquisarEntidade() {
        listaEntidade = configuracaoService.listarAvisoEstruturaComFiltroPaginacao(entidadeFiltro, paginacao);
        return null;
    }

    @Override
    public void excluirEntidade(DialogEvent dialogEvent) {
        try {
            configuracaoService.excluirAvisoEstrutura(entidadePersistencia);
            pesquisarEntidade();
        } catch (Exception e) {
            MovJudErrorMessage.gerarErroMovjud(logger, e, dialogEvent.getComponent().getParent());
        }
    }

    @Override
    public Class<AvisoEstrutura> getClasseEntidade() {
        return AvisoEstrutura.class;
    }

    public void adicionarVariavelNoModeloAviso(ActionEvent actionEvent) {
        if (this.variavel != null) {
            String conteudo = this.entidadePersistencia.getDescricaoConteudo();
            if (conteudo == null) {
                entidadePersistencia.setDescricaoConteudo(variavel.getNomeVariavel());
            } else {
                entidadePersistencia.setDescricaoConteudo(conteudo + variavel.getNomeVariavel());
            }
        }
    }


    public void setListaTiposAviso(List<TipoAviso> listaTiposAviso) {
        this.listaTiposAviso = listaTiposAviso;
    }

    public List<TipoAviso> getListaTiposAviso() {
        return listaTiposAviso;
    }

    public void setVariavel(Variavel variavel) {
        this.variavel = variavel;
    }

    public Variavel getVariavel() {
        return variavel;
    }

    @Override
    public String excluirEntidade() {
        return null;
    }
}
