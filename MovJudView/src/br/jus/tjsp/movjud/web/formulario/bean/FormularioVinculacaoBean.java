package br.jus.tjsp.movjud.web.formulario.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.base.dto.BaseObject;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;
import br.jus.tjsp.movjud.persistence.entity.MetadadosTipoRegra;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.estruturajuridica.bean.UnidadeBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;


public class FormularioVinculacaoBean extends BaseBean<Unidade> {
    private List<MetadadosFormulario> listaMetadadosFormularios;
    private List<MetadadosTipoRegra> listaMetadadosTiposRegra;
    private List<FormularioVinculacao> listaFormulariosVinculacao;
    private Boolean[][] matrizFormularioVsTipoRegra;
    private Paginacao paginacaoUnidades;

    final static Logger logger = Logger.getLogger(FormularioVinculacaoBean.class);

    private FormularioService formularioService;

    private UnidadeBean unidadeBean;

    public FormularioVinculacaoBean() {
	formularioService = getBean(FormularioService.class);
	
	listaMetadadosFormularios = formularioService.listarMetadadosFormulariosEmUso();
	listaMetadadosTiposRegra = formularioService.listarTiposRegra();
	listaFormulariosVinculacao = new ArrayList<FormularioVinculacao>();

	unidadeBean = getUnidadeBean();
        
        paginacaoUnidades = new Paginacao();
        paginacaoUnidades.setQtdItensPorPagina(300);
    }

    @PostConstruct
    @Override
    public void init() {
	super.initDefault();
	
	if(unidadeBean != null) {
	    entidadePersistencia = unidadeBean.getEntidadePersistencia();
	}
	
	pesquisarEntidade();
    }

    @Override
    public String pesquisarEntidade() {
	initMatrizFormularioVsTipoRegra();
	listaFormulariosVinculacao = entidadePersistencia.getFormulariosVinculacao();

	if (entidadePersistencia != null && entidadePersistencia.getIdUnidade() != null) {
	    sugestao = entidadePersistencia.getNomeUnidade() + " (" + entidadePersistencia.getForo().getNomeForo() + ")";
	}

	carregarMatrizFormularioVsTipoRegra();
	return null;
    }

    private void initMatrizFormularioVsTipoRegra() {
	if (listaMetadadosFormularios != null && listaMetadadosFormularios.size() > 0 && listaMetadadosTiposRegra != null && listaMetadadosTiposRegra.size() > 0) {
	    matrizFormularioVsTipoRegra = new Boolean[listaMetadadosFormularios.size()][listaMetadadosTiposRegra.size()];
	    for (int contForm = 0; contForm < listaMetadadosFormularios.size(); contForm++) {
	        listaMetadadosFormularios.get(contForm).setMarcado(false);
		for (int contTipoRegra = 0; contTipoRegra < listaMetadadosTiposRegra.size(); contTipoRegra++) {
		    matrizFormularioVsTipoRegra[contForm][contTipoRegra] = false;
		    //System.out.println("#### [init] matrizFormularioVsTipoRegra[" + contForm + "][" + contTipoRegra + "]: " + matrizFormularioVsTipoRegra[contForm][contTipoRegra]);
		}
	    }
	}
    }

    private void carregarMatrizFormularioVsTipoRegra() {
	if (listaMetadadosFormularios != null && listaMetadadosFormularios.size() > 0 && listaMetadadosTiposRegra != null && listaMetadadosTiposRegra.size() > 0) {
	    if (matrizFormularioVsTipoRegra == null) {
		matrizFormularioVsTipoRegra = new Boolean[listaMetadadosFormularios.size()][listaMetadadosTiposRegra.size()];
		//System.out.println("#### [carregar] matrizFormularioVsTipoRegra[matrizFormularioVsTipoRegra.length]: " + matrizFormularioVsTipoRegra.length);
	    }

	    for (int contForm = 0; contForm < listaMetadadosFormularios.size(); contForm++) {
		for (int contTipoRegra = 0; contTipoRegra < listaMetadadosTiposRegra.size(); contTipoRegra++) {
		    if (listaFormulariosVinculacao != null && listaFormulariosVinculacao.size() > 0) {
			for (FormularioVinculacao formVinc : listaFormulariosVinculacao) {
			    if (formVinc.getMetadadosFormulario().equals(listaMetadadosFormularios.get(contForm))) {
				listaMetadadosFormularios.get(contForm).setMarcado(true);
				if (formVinc.getListaMetadadosTipoRegra().indexOf(listaMetadadosTiposRegra.get(contTipoRegra)) >= 0) {
				    matrizFormularioVsTipoRegra[contForm][contTipoRegra] = true;
				}
			    }
			}
		    }
		       //System.out.println("#### [carregar] matrizFormularioVsTipoRegra[" + contForm + "][" + contTipoRegra + "]: " + matrizFormularioVsTipoRegra[contForm][contTipoRegra]);
		}
	    }
	}
    }


    public String persistirEntidade() {
	logger.info(AppBundleProperties.getString("msg.formularioVinculacao.logVinculacao") + entidadePersistencia.getNomeUnidade());
        montarListaFormulariosVinculacao();
	formularioService.salvarUnidade(entidadePersistencia);

	RichPopup.PopupHints hint = new RichPopup.PopupHints();
	RichPopup popupSalvoComSucesso = (RichPopup) findComponent("salvoComSucessoPopUp");
	popupSalvoComSucesso.show(hint);

	return "";
    }


    private void montarListaFormulariosVinculacao() {
	List<FormularioVinculacao> formulariosVinculacao = new ArrayList<FormularioVinculacao>();

	for (int contForm = 0; contForm < listaMetadadosFormularios.size(); contForm++) {
	    if (listaMetadadosFormularios.get(contForm).isMarcado()) {
		List<MetadadosTipoRegra> tiposRegra = new ArrayList<MetadadosTipoRegra>();
		for (int contTipoRegra = 0; contTipoRegra < listaMetadadosTiposRegra.size(); contTipoRegra++) {
		    if (matrizFormularioVsTipoRegra[contForm][contTipoRegra] == true) {
			tiposRegra.add(listaMetadadosTiposRegra.get(contTipoRegra));
		    }
		}

		FormularioVinculacao formVinculacao = new FormularioVinculacao();
		formVinculacao.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);

		formVinculacao.setListaMetadadosTipoRegra(tiposRegra);

		//System.out.println("#### tiposRegra.size(): " + tiposRegra.size());
		formVinculacao.setUnidade(entidadePersistencia);
		formVinculacao.setMetadadosFormulario(listaMetadadosFormularios.get(contForm));
		formulariosVinculacao.add(formVinculacao);
	    }
	}
	//System.out.println("#### formulariosVinculacao.size(): " + formulariosVinculacao.size());
	entidadePersistencia.setFormulariosVinculacao(formulariosVinculacao);
    }


    public void alterarNomeUnidadeSelecionada(ValueChangeEvent valueChangeEvent) {
        if(!String.valueOf(valueChangeEvent.getNewValue()).equals("")){
            
        try {
	    entidadePersistencia = alterarValorSelecionadoAutoCompletar(new Long(String.valueOf(valueChangeEvent.getNewValue())), Unidade.class);
	    atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), entidadePersistencia.getNomeUnidade() + " (" + entidadePersistencia.getForo().getNomeForo() + ")");
	    pesquisarEntidade();
	} catch (Exception e) {
	    atualizarComponenteTextoAutoCompletar(valueChangeEvent.getComponent(), null);
	}
        }else{
            unidadeBean = null;
            init();
            }
    }

    @Override
    public List listarAutoCompletar(String parametro) {
	Unidade unidade = new Unidade();
	unidade.setNomeUnidade(parametro);
	List<Unidade> listaUnidades = formularioService.listarUnidadesComFiltroPaginacao(unidade, paginacaoUnidades);
	//System.out.println("#### [listarAutoCompletar] listaUnidades.size(): " + listaUnidades.size());
	return listaUnidades;
    }

    @Override
    public List<SelectItem> montarSelectItem(List<? extends BaseObject> listaParametros) {
	//System.out.println("#### [montarSelectItem] listaParametros.size(): " + listaParametros.size());
	List<SelectItem> listaSugestoesUnidades = null;

	if (listaParametros != null) {
	    listaSugestoesUnidades = new ArrayList<SelectItem>();

	    for (Unidade item : ((List<Unidade>) listaParametros)) {
		SelectItem selectItem = new SelectItem();
		selectItem.setLabel(item.getNomeUnidade() + " (" + item.getForo().getNomeForo() + ")");
		selectItem.setValue(item.getIdUnidade());
		listaSugestoesUnidades.add(selectItem);
	    }
	}

	//System.out.println("#### [montarSelectItem] listaSugestoesUnidades.size(): " + listaSugestoesUnidades.size());
	return listaSugestoesUnidades;
    }


    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
	// TODO Implement this method
    }


    @Override
    public String excluirEntidade() {
	// TODO Implement this method
	return null;
    }

    @Override
    public Class<Unidade> getClasseEntidade() {
	return Unidade.class;
    }

    public void setListaMetadadosFormularios(List<MetadadosFormulario> listaMetadadosFormularios) {
	this.listaMetadadosFormularios = listaMetadadosFormularios;
    }

    public List<MetadadosFormulario> getListaMetadadosFormularios() {
	return listaMetadadosFormularios;
    }

    public void setListaMetadadosTiposRegra(List<MetadadosTipoRegra> listaMetadadosTiposRegra) {
	this.listaMetadadosTiposRegra = listaMetadadosTiposRegra;
    }

    public List<MetadadosTipoRegra> getListaMetadadosTiposRegra() {
	return listaMetadadosTiposRegra;
    }

    public void setMatrizFormularioVsTipoRegra(Boolean[][] matrizFormularioVsTipoRegra) {
	this.matrizFormularioVsTipoRegra = matrizFormularioVsTipoRegra;
    }

    public Boolean[][] getMatrizFormularioVsTipoRegra() {
	return matrizFormularioVsTipoRegra;
    }

    public int getQtdeFormulariosVinculados() {
	int qtdeFormulariosVinculados = 0;
	if (listaMetadadosFormularios != null) {
	    for (MetadadosFormulario form : listaMetadadosFormularios) {
		if (form.isMarcado()) {
		    qtdeFormulariosVinculados++;
		}
	    }
	}
	return qtdeFormulariosVinculados;
    }

    public int getQtdeMetadadosFormularios() {
	int qtdeMetadadosFormularios = 0;
	if (listaMetadadosFormularios != null) {
	    qtdeMetadadosFormularios = listaMetadadosFormularios.size();
	}
	return qtdeMetadadosFormularios;
    }


    public void liberarFormulariosUnidade(DialogEvent dialogEvent) {
        if(entidadePersistencia!=null && entidadePersistencia.getIdUnidade()!=null){
            logger.info(AppBundleProperties.getString("msg.formularioVinculacao.logLiberacaoFormularios") + entidadePersistencia.getNomeUnidade());
            montarListaFormulariosVinculacao();
            formularioService.salvarUnidade(entidadePersistencia);
            formularioService.liberarFormulariosParaUnidade(entidadePersistencia, null);
            ((RichPopup) findComponent("liberadoComSucessoPopUp")).show(new RichPopup.PopupHints());
        }
    }
}
