package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.PermissaoUnidadeTemporaria;

import java.util.List;

import javax.ejb.Local;


@Local
public interface PermissaoUnidadeTemporariaDAO extends BaseDAO<PermissaoUnidadeTemporaria> {
    
    List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaOrdenadaPorNomeComPaginacao(PermissaoUnidadeTemporaria filter, Paginacao paginacao);
    
    List<PermissaoUnidadeTemporaria> listarPermissaoUnidadeTemporariaPorUsuarioEDataAtual(PermissaoUnidadeTemporaria filter);
}
