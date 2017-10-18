package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.TipoSegmento;

import java.util.List;

import javax.ejb.Local;

@Local
public interface TipoSegmentoDAO extends BaseDAO<TipoSegmento>{
    
    public List<TipoSegmento> listarTipoSegmenoOrdenadoPorNome();
}
