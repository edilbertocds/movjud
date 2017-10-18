package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.TipoArea;

import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;

import java.util.List;

import javax.ejb.Local;
@Local
public interface TipoAreaDAO extends BaseDAO<TipoArea>{
    
    public List<TipoArea> listarTipoAreaOrdenadoPorNome();
}
