package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.TipoNaturezaPrisao;

import java.util.List;

import javax.ejb.Local;
@Local
public interface TipoNaturezaPrisaoDAO extends BaseDAO<TipoNaturezaPrisao>{
    
    public List<TipoNaturezaPrisao> listarTipoNaturezaPrisaoOrdenadoPorNome(TipoNaturezaPrisao filter);
}
