package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;
import br.jus.tjsp.movjud.persistence.entity.Regiao;

import br.jus.tjsp.movjud.persistence.entity.UnidadeEstabelecimentoPrisional;

import java.util.List;

import javax.ejb.Local;

@Local
public interface EstabelecimentoPrisionalDAO extends BaseDAO<EstabelecimentoPrisional> {

    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                   Paginacao paginacao);
    
    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional);
    
    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(EstabelecimentoPrisional estabelecimentoPrisional);
    List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                        Paginacao paginacao);
    // <edilberto item 199>
    UnidadeEstabelecimentoPrisional obterVinculoMaisRecenteComUnidade(EstabelecimentoPrisional estabelecimentoPrisional);
    // </edilberto item 199>
}
