package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;

import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ConfiguracaoAvisoDAO extends BaseDAO<ConfiguracaoAviso> {
    
    List<ConfiguracaoAviso> listarConfiguracoesAvisoDoUsuario(Usuario usuario);
}
