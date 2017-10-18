package br.jus.tjsp.movjud.business.processogabinete.service;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.ProcessoGabinete;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.entity.UsuarioProcessoGabinete;
import br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao.UsuarioDAO;
import br.jus.tjsp.movjud.persistence.processogabinete.dao.ProcessoGabineteDAO;
import br.jus.tjsp.movjud.persistence.processogabinete.dao.UsuarioProcessoGabineteDAO;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless(name = "ProcessoGabineteService", mappedName = "MovJudModel")
public class ProcessoGabineteServiceImpl implements ProcessoGabineteService {

    @EJB
    private ProcessoGabineteDAO processoGabineteDAO;

    @EJB
    private UsuarioProcessoGabineteDAO usuarioProcessoGabineteDAO;
    
    @EJB
    private UsuarioDAO usuarioDAO;


    public ProcessoGabineteServiceImpl() {
    }


    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<UsuarioProcessoGabinete> listarMagistradosProcessoGabinetePaginado(UsuarioProcessoGabinete filter,
                                                                                   Paginacao paginacao) {
        return usuarioProcessoGabineteDAO.listarMagistradosProcessoGabinetePaginado(filter, paginacao);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ProcessoGabinete> listarProcessosGabineteDoMagistrado(Usuario usuario) {
        return processoGabineteDAO.listarProcessosGabineteDoMagistrado(usuario);
    }


    @Override
    public ProcessoGabinete salvarProcessoGabinete(ProcessoGabinete processoGabinete) {        
        return processoGabineteDAO.salvar(processoGabinete);      
    }


    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        
        return usuarioDAO.salvar(usuario);
    }

}
