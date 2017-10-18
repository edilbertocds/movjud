package br.jus.tjsp.movjud.web.formulario.bean;


import br.jus.tjsp.movjud.business.formulario.dto.LiberacaoFormularioDTO;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.business.utils.helper.IndicadorProgressoUtil;
import br.jus.tjsp.movjud.core.util.AppBundleProperties;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import org.apache.myfaces.trinidad.event.PollEvent;


@ManagedBean(name = "liberacaoFormularioBean")
@ViewScoped
public class LiberacaoFormularioBean extends BaseBean<LiberacaoFormularioDTO> implements Runnable {
    @SuppressWarnings("compatibility:-6597225934246374143")
    private static final long serialVersionUID = 1L;
    private FormularioService formularioService;
    private LiberacaoFormularioDTO liberacaoFormularioDTO;
    private IndicadorProgressoUtil indicadorProgresso;

    public LiberacaoFormularioBean() {
        formularioService = BaseBean.getBean(FormularioService.class);
        indicadorProgresso = new IndicadorProgressoUtil();
    }

    @PostConstruct
    public void init() {
        setMesAnoCompetencia();
        liberacaoFormularioDTO = formularioService.countFormulariosLiberados();
        
    }

    public void iniciarLiberacaoFormularios(DialogEvent dialogEvent) {
        ((RichPopup) BaseBean.findComponent("progressoLiberacaoPopUp")).show(new RichPopup.PopupHints());
    }

    public void fecharDialogLiberacao(ActionEvent actionEvent) {
        ((RichPopup) BaseBean.findComponent("progressoLiberacaoPopUp")).hide();
    }

    public void initLiberacaoFormulario(PopupFetchEvent popupFetchEvent) {
        indicadorProgresso.init();
        indicadorProgresso.setValorMaximo(liberacaoFormularioDTO.getNumeroUnidadesVinculadas());
       
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            formularioService.liberarFormulariosTodasUnidades(indicadorProgresso);
        
        } catch (Exception ex) {
            indicadorProgresso.setErroProcessamento(true);
        }
        indicadorProgresso.setFinalizouProcessamento(true);
        
    }

    public void atualizarProgressoLiberacao(PollEvent pollEvent) {      
        if(indicadorProgresso.isFinalizouProcessamento() && !indicadorProgresso.isAtualizouInformacoes()) {
            indicadorProgresso.setIntervalo(IndicadorProgressoUtil.FIM_PROCESSAMENTO);
            indicadorProgresso.setAtualizouInformacoes(true);
            
            RichImage imagemBarra = (RichImage) BaseBean.findComponent("imagemCarregando");
            imagemBarra.setRendered(false);         
            RichImage imagemFinalizado = (RichImage) BaseBean.findComponent("imagemFinalizado");
            imagemFinalizado.setRendered(true);        
            RichMessage messageInformacao = (RichMessage) BaseBean.findComponent("messageInformacao");
            RichOutputLabel outputInformacao = (RichOutputLabel) BaseBean.findComponent("outputInformacao");
 
            if(indicadorProgresso.isErroProcessamento()) {
                messageInformacao.setMessage(AppBundleProperties.getString("msg.liberacaoFormulario.msgErroLiberacao"));
                outputInformacao.setValue(AppBundleProperties.getString("msg.liberacaoFormulario.infoErroLiberacao"));
                messageInformacao.setMessageType("error");               
            }
            else {
                messageInformacao.setMessage(AppBundleProperties.getString("msg.liberacaoFormulario.msgSucessoLiberacao"));
                outputInformacao.setValue(AppBundleProperties.getString("msg.liberacaoFormulario.infoSucessoLiberacao"));
                messageInformacao.setMessageType("confirmation");
            }
            
            BaseBean.atualizarComponenteDeTela(imagemBarra);
            BaseBean.atualizarComponenteDeTela(imagemFinalizado);
            BaseBean.atualizarComponenteDeTela(messageInformacao);  
            BaseBean.atualizarComponenteDeTela(outputInformacao);
        }
        BaseBean.atualizarComponenteDeTela(pollEvent.getComponent());
    }

    public void setLiberacaoFormularioDTO(LiberacaoFormularioDTO liberacaoFormularioDTO) {
        this.liberacaoFormularioDTO = liberacaoFormularioDTO;
    }

    public LiberacaoFormularioDTO getLiberacaoFormularioDTO() {
        return liberacaoFormularioDTO;
    }

    public void setIndicadorProgresso(IndicadorProgressoUtil indicadorProgresso) {
        this.indicadorProgresso = indicadorProgresso;
    }

    public IndicadorProgressoUtil getIndicadorProgresso() {
        return indicadorProgresso;
    }


    public long getMaximum() {
        return indicadorProgresso.getValorMaximo();
    }


    public long getValue() {
        return indicadorProgresso.getValorAtual();
    }

    public void fecharPopupLiberacao(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().equals(DialogEvent.Outcome.ok)) {
            init();
            atualizarPagina();
        }
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
        // TODO Implement this method
    }

    @Override
    public String pesquisarEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public String excluirEntidade() {
        // TODO Implement this method
        return null;
    }

    @Override
    public Class<LiberacaoFormularioDTO> getClasseEntidade() {
        // TODO Implement this method
        return null;
    }

}
