package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class EstabelecimentoPrisionalDAOImpl extends BaseDAOImpl<EstabelecimentoPrisional> implements EstabelecimentoPrisionalDAO {

    public EstabelecimentoPrisionalDAOImpl() {
    }


    @Override
    public Class<EstabelecimentoPrisional> getPersistentClass() {
        return EstabelecimentoPrisional.class;
    }


    @Override
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                          Paginacao paginacao) {

        return listarComFiltroOrdenacao(estabelecimentoPrisional, "nomeEstabelecimentoPrisional", true, paginacao);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(EstabelecimentoPrisional filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        parametros.add(new ParametroOperacao("nomeEstabelecimentoPrisional", filter.getNomeEstabelecimentoPrisional(),
                                             SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("nomeMunicipio", filter.getNomeMunicipio(),
                                             SQLOperatorType.LikeFullNoCase));
	parametros.add(new ParametroOperacao("codigoUf", filter.getCodigoUf(),
					     SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(),
                                             SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("flagInternacao", filter.getFlagInternacao(),
                                             SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("flagPrisional", filter.getFlagPrisional(),
                                             SQLOperatorType.EqualNoCase));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(EstabelecimentoPrisional filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisOrdenadoPorNome(EstabelecimentoPrisional estabelecimentoPrisional) {
        return listarComFiltroOrdenacao(estabelecimentoPrisional, "nomeEstabelecimentoPrisional", true);
    }
}
