package br.jus.tjsp.movjud.business.recursos.service;

import br.jus.tjsp.movjud.business.estruturajudiciaria.service.EstruturaJudiciariaService;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;
import br.jus.tjsp.movjud.persistence.entity.Circunscricao;
import br.jus.tjsp.movjud.persistence.entity.Comarca;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Parametro;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.Regiao;
import br.jus.tjsp.movjud.persistence.entity.TipoEntrancia;
import br.jus.tjsp.movjud.persistence.entity.TipoLocal;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.AcaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.CircunscricaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ComarcaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ForoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.ParametroDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.PerfilDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.RegiaoDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.TipoEntranciaDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.TipoLocalDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UnidadeDAO;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;

import br.jus.tjsp.movjud.persistence.recursos.dao.AuditoriaDAO;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.log4j.Logger;

@Stateless(name="AuditoriaService", mappedName="MovJudModel")
public class AuditoriaServiceImpl implements AuditoriaService {
    final static Logger logger = Logger.getLogger(AuditoriaServiceImpl.class);
    
    @EJB
    private AuditoriaDAO auditoriaDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Auditoria> listarAuditoriasComFiltroPaginacao(Auditoria auditoria, Paginacao paginacao) {
        return auditoriaDAO.listarAuditoriasComFiltroPaginacao(auditoria, paginacao);
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> listarUsuariosAuditoria() {
        return auditoriaDAO.listarUsuariosAuditoria();
    }
        
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<String> listarEntidadesAuditoria() {
        return auditoriaDAO.listarEntidadesAuditoria();
    }

}

