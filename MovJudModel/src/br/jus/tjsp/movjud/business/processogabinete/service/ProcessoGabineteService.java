package br.jus.tjsp.movjud.business.processogabinete.service;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.ProcessoGabinete;
import br.jus.tjsp.movjud.persistence.entity.Usuario;
import br.jus.tjsp.movjud.persistence.entity.UsuarioProcessoGabinete;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProcessoGabineteService {
    List<UsuarioProcessoGabinete> listarMagistradosProcessoGabinetePaginado(UsuarioProcessoGabinete filter, Paginacao paginacao);
    
    List<ProcessoGabinete> listarProcessosGabineteDoMagistrado(Usuario usuario);
    
    ProcessoGabinete salvarProcessoGabinete(ProcessoGabinete processoGabinete);
    
    Usuario salvarUsuario(Usuario usuario);
    
   
}
