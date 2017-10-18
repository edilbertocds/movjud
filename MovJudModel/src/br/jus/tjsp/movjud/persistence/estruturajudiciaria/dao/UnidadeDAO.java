package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UnidadeDAO extends BaseDAO<Unidade>{
    
    List<Unidade> listarUnidadesOrdenadoPorNome(Unidade unidade);
    
    List<Unidade> listarUnidadeOrdenadoPorRegiao(Unidade unidade);
    
    List<Unidade> listarUnidadesCodigoDescricaoPorForo(Foro foro);
    
    List<Unidade> listarUnidadesCodigoDescricaoPorUsuario(Usuario usuario);
    
    List<Unidade> listarUnidadesCodigoDescricaoPorForoEUsuario(Foro foro,Usuario usuario);
    
    List<Unidade> listarUnidadesCodigoDescricao();
    
    Unidade procurarUnidadePorCodigoUnidade(Unidade unidade);
    
    List<Unidade> listarUnidadesComVinculo();
}
