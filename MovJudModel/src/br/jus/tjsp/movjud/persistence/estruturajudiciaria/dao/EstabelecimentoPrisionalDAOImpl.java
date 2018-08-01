package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.EstabelecimentoPrisional;

import br.jus.tjsp.movjud.persistence.entity.UnidadeEstabelecimentoPrisional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.Query;

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
    
    @Override
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(EstabelecimentoPrisional estabelecimentoPrisional) {
        Long idUnidadeEstabelecimentoAtual = null;
        if(estabelecimentoPrisional.getUnidadeEstabelecimentosPrisionais()!=null && !estabelecimentoPrisional.getUnidadeEstabelecimentosPrisionais().isEmpty()) {
            idUnidadeEstabelecimentoAtual = estabelecimentoPrisional.getUnidadeEstabelecimentosPrisionais().get(0).getEstabelecimentoPrisional().getId();            
        }
        String jpaQuery = "select e.* " + 
                          "from CAD_ESTAB_PRISIONAL e " + 
                          "where e.ID_CAD_ESTAB_PRISIONAL not in (" +
                          "   select u.FK_CAD_ESTAB_PRISIONAL from CAD_UNID_ESTAB_PRIS u where u.TP_SITUACAO = 'A' and u.FK_CAD_UNIDADE = " +
                          ") and e.NM_ESTAB_PRISIONAL like '%"+estabelecimentoPrisional.getNomeEstabelecimentoPrisional()+"%'";
        if(idUnidadeEstabelecimentoAtual != null) {
            jpaQuery += " and e.ID_CAD_ESTAB_PRISIONAL != " + idUnidadeEstabelecimentoAtual.toString();
        }
        jpaQuery += " order by e.NM_ESTAB_PRISIONAL";
        ArrayList<EstabelecimentoPrisional> result = new ArrayList<EstabelecimentoPrisional>();
        result.addAll(getPersistenceManager().getManager().createNativeQuery(jpaQuery, EstabelecimentoPrisional.class).getResultList());
        return result;
    }
    
    @Override
    public List<EstabelecimentoPrisional> listarEstabelecimentosPrisionaisSemUnidadeAtivoPorNome(EstabelecimentoPrisional estabelecimentoPrisional,
                                                                                                 Paginacao paginacao) {
        return listarComFiltroOrdenacao(estabelecimentoPrisional, "nomeEstabelecimentoPrisional", true, paginacao);
    }
    
    // <edilberto item 199>
    @Override
    public UnidadeEstabelecimentoPrisional obterVinculoMaisRecenteComUnidade(EstabelecimentoPrisional estabelecimentoPrisional) {
        if(estabelecimentoPrisional != null && estabelecimentoPrisional.getId() != null) {
            String jpaQuery = "select ue from UnidadeEstabelecimentoPrisional ue " +
                              "where not (ue.dataFim is null) and ue.estabelecimentoPrisional.idEstabelecimentoPrisional = " + estabelecimentoPrisional.getId().toString() + 
                              "order by DT_FIM desc";
            Query query = getPersistenceManager().getManager().createQuery(jpaQuery);
            return (UnidadeEstabelecimentoPrisional)query.getSingleResult();
        }
        return null;
    }
    // </edilberto item 199>
}
