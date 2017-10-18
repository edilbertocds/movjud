package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.TipoMotivoBaixa;

import java.util.List;

import javax.ejb.Local;
@Local
public interface TipoMotivoBaixaDAO extends BaseDAO<TipoMotivoBaixa>{
    public List<TipoMotivoBaixa> listarTipoMotivoBaixaOrdenadoPorNome();
}
