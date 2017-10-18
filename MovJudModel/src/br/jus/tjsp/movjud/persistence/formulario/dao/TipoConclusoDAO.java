package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.TipoConcluso;

import java.util.List;

import javax.ejb.Local;
@Local
public interface TipoConclusoDAO extends BaseDAO<TipoConcluso>{
    
    public List<TipoConcluso> listarTipoConclusoPorDescricao();
}
