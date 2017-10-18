package br.jus.tjsp.movjud.web.recursos.bean;

import br.jus.tjsp.movjud.business.formulario.service.FormularioService;
import br.jus.tjsp.movjud.business.recursos.service.AuditoriaService;
import br.jus.tjsp.movjud.persistence.base.types.AcaoType;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;
import br.jus.tjsp.movjud.web.commons.bean.BaseBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.log4j.Logger;

@ManagedBean(name = "auditoriaBean")
@ViewScoped
public class AuditoriaBean extends BaseBean<Auditoria>{
    @SuppressWarnings("compatibility:-5866240918116897228")
    private static final long serialVersionUID = 1L;
    final static Logger logger = Logger.getLogger(AuditoriaBean.class);
    
    private List<DominioType> listaDominios;
    private List<AcaoType> listaAcoes;
    private List<String> listaUsuarios;

    private List<String> listaEntidades;
    
    private List<Auditoria> listaDePara;
    
    private AuditoriaService auditoria;
    
    public AuditoriaBean() {
	auditoria = getBean(AuditoriaService.class);
	listaDePara = new ArrayList<Auditoria>();
    }
    
    
    /* PostConstruct executa logo apos o managedBean ser instanciado  */
    @PostConstruct
    @Override
    public void init() {
        super.init();
        
        listaDominios = Arrays.asList(DominioType.values());
        listaAcoes    = Arrays.asList(AcaoType.values());
        listaUsuarios = auditoria.listarUsuariosAuditoria();
        listaEntidades = auditoria.listarEntidadesAuditoria();
        listaDePara.clear();
    }

    @Override
    public Class<Auditoria> getClasseEntidade() {
        return Auditoria.class;
    }

    @Override
    public void persistirEntidade(DialogEvent dialogEvent) {
    
    }

    @Override
    public String pesquisarEntidade() {
        entidadePersistencia = new Auditoria();
        listaDePara.clear();
        listaEntidade = auditoria.listarAuditoriasComFiltroPaginacao(getEntidadeFiltro(), getPaginacao());
        return null;
    }

    @Override
    public String excluirEntidade() {
        return null;
    }
    
    public void setListaDominios(List<DominioType> listaDominios) {
        this.listaDominios = listaDominios;
    }

    public List<DominioType> getListaDominios() {
        return listaDominios;
    }


    public void setListaAcoes(List<AcaoType> listaAcoes) {
        this.listaAcoes = listaAcoes;
    }

    public List<AcaoType> getListaAcoes() {
        return listaAcoes;
    }


    public void setListaUsuarios(List<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<String>  getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaEntidades(List<String>  listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public List<String> getListaEntidades() {
        return listaEntidades;
    }
    
    public void setListaDePara(List<Auditoria> listaDePara) {
        
    }

    public List<Auditoria> getListaDePara() {
        listaDePara.clear();
        listaDePara.add(getEntidadePersistencia());
        return listaDePara;
    }
}
