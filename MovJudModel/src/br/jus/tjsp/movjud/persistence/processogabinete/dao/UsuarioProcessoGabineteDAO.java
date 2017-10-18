package br.jus.tjsp.movjud.persistence.processogabinete.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.UsuarioProcessoGabinete;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UsuarioProcessoGabineteDAO extends BaseDAO<UsuarioProcessoGabinete> {
    List<UsuarioProcessoGabinete> listarMagistradosProcessoGabinetePaginado(UsuarioProcessoGabinete filter, Paginacao paginacao);    
}
