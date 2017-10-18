package br.jus.tjsp.movjud.web.commons.bean;

import br.jus.tjsp.movjud.business.configuracao.service.ConfiguracaoService;
import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.business.processogabinete.service.ProcessoGabineteService;
import br.jus.tjsp.movjud.business.recursos.service.AuditoriaService;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "injectBean")
@RequestScoped
public class InjectBean {
    @EJB
    private FormularioService formularioService;

    @EJB
    private ConfiguracaoService configuracaoService;

    @EJB
    private EstruturaJudiciariaService estruturaJudiciariaService;

    @EJB
    private AuditoriaService auditoriaService;

    @EJB
    private ProcessoGabineteService processoGabineteService;


    public InjectBean() {
    }

    public void setFormularioService(FormularioService formularioService) {
	this.formularioService = formularioService;
    }

    public FormularioService getFormularioService() {
	return formularioService;
    }

    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
	this.configuracaoService = configuracaoService;
    }

    public ConfiguracaoService getConfiguracaoService() {
	return configuracaoService;
    }

    public void setEstruturaJudiciariaService(EstruturaJudiciariaService estruturaJudiciariaService) {
	this.estruturaJudiciariaService = estruturaJudiciariaService;
    }

    public EstruturaJudiciariaService getEstruturaJudiciariaService() {
	return estruturaJudiciariaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
	this.auditoriaService = auditoriaService;
    }

    public AuditoriaService getAuditoriaService() {
	return auditoriaService;
    }

    public void setProcessoGabineteService(ProcessoGabineteService processoGabineteService) {
	this.processoGabineteService = processoGabineteService;
    }

    public ProcessoGabineteService getProcessoGabineteService() {
	return processoGabineteService;
    }

}
