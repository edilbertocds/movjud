package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Foro;
import br.jus.tjsp.movjud.persistence.entity.Unidade;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.TypedQuery;

@Stateless
public class UnidadeDAOImpl extends BaseDAOImpl<Unidade> implements UnidadeDAO {

    public UnidadeDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Unidade filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeUnidade", filter.getNomeUnidade(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        
        if(filter.getForo() != null){
            parametros.add(new ParametroOperacao("foro.nomeForo", filter.getForo().getNomeForo(), SQLOperatorType.LikeFullNoCase));
        
            if(filter.getForo().getComarca() != null){
                parametros.add(new ParametroOperacao("foro.comarca.nomeComarca", filter.getForo().getComarca().getNomeComarca(), SQLOperatorType.LikeFullNoCase));             
            
                if(filter.getForo().getComarca().getCircunscricao() != null){
                    parametros.add(new ParametroOperacao("foro.comarca.circunscricao.nomeCircunscricao", filter.getForo().getComarca().getCircunscricao().getNomeCircunscricao(), SQLOperatorType.LikeFullNoCase));
                
                if(filter.getForo().getComarca().getCircunscricao().getRegiao() != null)
                    parametros.add(new ParametroOperacao("foro.comarca.circunscricao.regiao.nomeRegiao", filter.getForo().getComarca().getCircunscricao().getRegiao().getNomeRegiao(), SQLOperatorType.LikeFullNoCase));
                }
            }
        }
        if(filter.getUsuario() != null){
            parametros.add(new ParametroOperacao("usuario.idUsuario", filter.getUsuario().getIdUsuario(), SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Unidade filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<Unidade> getPersistentClass() {
        return Unidade.class;
    }

    @Override
    public List<Unidade> listarUnidadesOrdenadoPorNome(Unidade unidade) {
        return listarComFiltroOrdenacao(unidade, "nomeUnidade", true);
    }
    
    @Override
    public List<Unidade> listarUnidadeOrdenadoPorRegiao(Unidade unidade) {
        return listarComFiltroOrdenacao(unidade, "foro.comarca.circunscricao.regiao.nomeRegiao", true);
    }

    @Override
    public List<Unidade> listarUnidadesCodigoDescricaoPorUsuario(Usuario usuario) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select u.foro.nomeForo, " +
                        " u.idUnidade, " +
                        " u.nomeUnidade " +
                        " from Unidade u " +
                        " where u.usuario.idUsuario = " + usuario.getIdUsuario() + 
                        " union " +
                        " select p.unidade.foro.nomeForo, " +
                        " p.unidade.idUnidade, " +
                        " p.unidade.nomeUnidade " +
                        " from PermissaoUnidadeTemporaria p " +
                        " where p.usuario.idUsuario = " + usuario.getIdUsuario() + 
                        " and CURRENT_DATE between p.dataInicio and p.dataValidade");                                  
                       // " order by 1");
        TypedQuery<Object[]> query = getPersistenceManager().getManager().createQuery(jpaQl.toString(), Object[].class);
                      
        return listaUnidadesConverter(query.getResultList());
    }
    
    @Override
    public List<Unidade> listarUnidadesCodigoDescricaoPorForo(Foro foro){
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select u.foro.nomeForo, " +
                        "u.idUnidade, " +
                        "u.nomeUnidade " +
                        "from Unidade u " +
                        "where (u.foro.idForo = " + foro.getIdForo() +
                        " or u.foro.nomeForo = " + foro.getNomeForo() + 
                        ") order by u.foro.nomeForo, u.nomeUnidade");
        TypedQuery<Object[]> query = getPersistenceManager().getManager().createQuery(jpaQl.toString(), Object[].class);
                      
        return listaUnidadesConverter(query.getResultList());
    }
    
    @Override
        public List<Unidade> listarUnidadesCodigoDescricaoPorForoEUsuario(Foro foro,Usuario usuario){
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select u.foro.nomeForo, " +
                        " u.idUnidade, " +
                        " u.nomeUnidade " +
                        " from Unidade u " +
                        " where u.usuario.idUsuario = " + usuario.getIdUsuario() + 
                        " and u.foro.idForo =" + foro.getIdForo() +
                        " union " +
                        " select p.unidade.foro.nomeForo, " +
                        " p.unidade.idUnidade, " +
                        " p.unidade.nomeUnidade " +
                        " from PermissaoUnidadeTemporaria p " +
                        " where p.usuario.idUsuario = " + usuario.getIdUsuario() + 
                        " and CURRENT_DATE between p.dataInicio and p.dataValidade" +
                        " and p.unidade.foro.idForo =" + foro.getIdForo());                                  
                       // " order by 1");
        TypedQuery<Object[]> query = getPersistenceManager().getManager().createQuery(jpaQl.toString(), Object[].class);
                      
        return listaUnidadesConverter(query.getResultList());
    }
    
    @Override
    public List<Unidade> listarUnidadesCodigoDescricao() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select u.foro.nomeForo, " +
                        "u.idUnidade, " +
                        "u.nomeUnidade " +
                        "from Unidade u " +
                        "order by u.foro.nomeForo, u.nomeUnidade"); 
        TypedQuery<Object[]> query = getPersistenceManager().getManager().createQuery(jpaQl.toString(), Object[].class);
        
        return listaUnidadesConverter(query.getResultList());
    }
    
    private List<Unidade> listaUnidadesConverter(List<Object[]> listaObjeto){
        List<Unidade> retorno = new ArrayList<>();
        for(Object[] obj:listaObjeto){
            Unidade u = new Unidade();
            Foro f = new Foro();
            f.setNomeForo((String)obj[0]);
            u.setForo(f);
            u.setIdUnidade((Long)obj[1]);
            u.setNomeUnidade((String)obj[2]);
            retorno.add(u);
        }
               
        return retorno;
    }

    @Override
    public Unidade procurarUnidadePorCodigoUnidade(Unidade unidade) {
        return getPersistenceManager().getManager().find(Unidade.class, unidade.getIdUnidade());
    }

    @Override
    public List<Unidade> listarUnidadesComVinculo() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select DISTINCT(Unidade) from Unidade unidade ");
        jpaQl.append("inner join unidade.formulariosVinculacao formularioVinculacao where");
        jpaQl.append(" formularioVinculacao.metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO'");
        return getPersistenceManager().listarPorJPQL(jpaQl);
    }
}
