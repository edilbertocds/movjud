package br.jus.tjsp.movjud.persistence.processogabinete.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.ProcessoGabinete;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ProcessoGabineteDAO extends BaseDAO<ProcessoGabinete> {
    List<ProcessoGabinete> listarProcessosGabineteDoMagistrado(Usuario usuario);   
}
