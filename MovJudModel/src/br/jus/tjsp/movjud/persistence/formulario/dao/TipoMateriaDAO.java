package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.TipoMateria;

import java.util.List;

import javax.ejb.Local;

@Local
public interface TipoMateriaDAO extends BaseDAO<TipoMateria>{
    
    public List<TipoMateria> listarTipoMateriaOrdenadoPorNome();
}
