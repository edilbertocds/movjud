package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.TipoCompetencia;

import java.util.List;

import javax.ejb.Local;
@Local
public interface TipoCompetenciaDAO extends BaseDAO<TipoCompetencia>{
    
    public List<TipoCompetencia> listarTipoCompetenciaOrdenadoPorNome();
}
