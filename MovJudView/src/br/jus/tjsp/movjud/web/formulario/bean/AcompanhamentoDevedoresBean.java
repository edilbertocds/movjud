package br.jus.tjsp.movjud.web.formulario.bean;

import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;
import br.jus.tjsp.movjud.web.relatorio.ExtensaoRelatorio;
import br.jus.tjsp.movjud.web.relatorio.GeracaoRelatorio;
import br.jus.tjsp.movjud.web.relatorio.RelatorioAcompanhamentoDevedores;
import br.jus.tjsp.movjud.web.relatorio.Template;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "acompanhamentoDevedoresBean")
@ViewScoped
public class AcompanhamentoDevedoresBean extends BaseBean<Unidade> {

    final static Logger logger = Logger.getLogger(AcompanhamentoDevedoresBean.class);
    private Template templateRelatorio;
    private ExtensaoRelatorio extensaoRelatorio;
    private transient RelatorioAcompanhamentoDevedores relatorioAcompanhamentoDevedores;
    private transient GeracaoRelatorio gerarRelatorio;

    private String mesReferencia;

    private String comunicadoCgNumero;

    private Date dataEnvio;

    private Date dataLimite;

    private FormularioService formularioService;

    private RichPopup popUpEmissaoRelatorioDevedores;
    private RichPanelSplitter panelSplit;

    public AcompanhamentoDevedoresBean() {
        formularioService = getBean(FormularioService.class);
        formularioService = getBean(FormularioService.class);
        templateRelatorio = Template.ACOMPANHAMENTO_DEVEDORES;
        extensaoRelatorio = ExtensaoRelatorio.EXTENSAO_RTF;
        relatorioAcompanhamentoDevedores = new RelatorioAcompanhamentoDevedores();
        gerarRelatorio = new GeracaoRelatorio();
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        mesReferencia = null;
        comunicadoCgNumero = null;
        dataEnvio = null;
        dataLimite = null;
        pesquisarEntidade();
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    @Override
    public String pesquisarEntidade() {
        this.listaEntidade = formularioService.carregarUnidadesDevedoras();
        return null;
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

    public void emitirRelatorioDevedores(FacesContext facesContext, OutputStream outputStream) throws JRException,
                                                                                                      IOException {
        logger.info(AppBundleProperties.getString("msg.acompanhamentoDevedores.log"));

        JRDataSource dataSource = relatorioAcompanhamentoDevedores.obterDataSourceColecao(listaEntidade);
        Map<String, Object> parametros =
            relatorioAcompanhamentoDevedores.obterParametros(mesReferencia, comunicadoCgNumero, dataEnvio, dataLimite);
        gerarRelatorio.processarRelatorio(extensaoRelatorio, templateRelatorio, outputStream, facesContext, parametros,
                                          dataSource, "1BEJH");

        // FECHA O POPUP
        // this.getPopUpEmissaoRelatorioDevedores().hide();
        //this.mesReferencia = null;
        //this.comunicadoCgNumero = null;
        //this.dataEnvio = null;
        //this.dataLimite = null;

    }

    private boolean validarCampos() {
        if ((mesReferencia == null || mesReferencia.isEmpty()) ||
            (comunicadoCgNumero == null || comunicadoCgNumero.isEmpty()) || dataEnvio == null || dataLimite == null) {
            return false;
        } else {
            return true;
        }
    }

    public String fecharPopupEmissaoRelatorioDevedores() {
        // FECHA O POPUP
        this.getPopUpEmissaoRelatorioDevedores().hide();
        pesquisarEntidade();
        return null;
    }

    public String panelSplitRelatorio() {
        if (panelSplit.isCollapsed()) {
            getPanelSplit().setCollapsed(false);
            atualizarComponenteDeTela(getPanelSplit());
        } else {
            getPanelSplit().setCollapsed(true);
            atualizarComponenteDeTela(getPanelSplit());
        }
        return null;
    }

    public int getTamanhoUnidadesDevedoras() {
        return listaEntidade.size();
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setComunicadoCgNumero(String comunicadoCgNumero) {
        this.comunicadoCgNumero = comunicadoCgNumero;
    }

    public String getComunicadoCgNumero() {
        return comunicadoCgNumero;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setPopUpEmissaoRelatorioDevedores(RichPopup popUpEmissaoRelatorioDevedores) {
        this.popUpEmissaoRelatorioDevedores = popUpEmissaoRelatorioDevedores;
    }

    public RichPopup getPopUpEmissaoRelatorioDevedores() {
        return popUpEmissaoRelatorioDevedores;
    }

    public String getNomeRelatorioDownload() {
        return templateRelatorio.getNomeArquivoDownload() + extensaoRelatorio.getExtensaoArquivo();
    }

    public String getContentType() {
        return extensaoRelatorio.getContentType();
    }

    public void habilitarImpressao(ValueChangeEvent valueChangeEvent) {
        atualizarComponenteDeTela("b2");
    }

    public void setPanelSplit(RichPanelSplitter panelSplit) {
        this.panelSplit = panelSplit;
    }

    public RichPanelSplitter getPanelSplit() {
        return panelSplit;
    }

    public String atualizar() {
        init();
        atualizarComponenteDeTela(getPanelSplit());
        //atualizarPagina();
        return null;
    }
}
