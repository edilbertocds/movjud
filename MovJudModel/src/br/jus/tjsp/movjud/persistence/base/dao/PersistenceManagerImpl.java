package br.jus.tjsp.movjud.persistence.base.dao;


import br.jus.tjsp.movjud.core.exception.ModelException;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.helper.AuditoriaHelper;
import br.jus.tjsp.movjud.persistence.base.types.AcaoType;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaEntityManager;


/**
 * Classe utilitaria para gerenciar consultas e acesso a dados.
 *
 * @author cds
 */
@Stateless
public class PersistenceManagerImpl implements PersistenceManager {

    @Resource
    private SessionContext sessionContext;

    /**
     * Manager JPA
     */
    @PersistenceContext(unitName = "MovJudModel")
    private JpaEntityManager manager;


    /**
     * Atualiza a entidade com os dados da base de dados.
     *
     * @param entity Entidade.
     */
    public void refresh(Serializable entity) {
        manager.refresh(entity);
    }

    /**
     * Salva um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    public <T extends Serializable> void salvar(T to) throws RuntimeException{

            manager.persist(to);

            manager.flush();
            /*
            String usuarioCorrente = sessionContext.getCallerPrincipal().getName();
                  AuditoriaHelper.auditar(manager, to, AcaoType.INSERIR,
                                          usuarioCorrente);
            */
    }

    /**
     * Executa um comando de insercao no controlador de entidade.
     *
     * @param JPQL.
     * @param parameters.
     *
     */
    public int salvarPorJPQL(CharSequence JPQL, Object... parameters) {
        try {
            Query query = manager.createQuery(JPQL.toString());
            fillParameters(query, parameters);
            return query.executeUpdate();
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Atualiza um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    public <T extends Serializable> T atualizar(T to) throws RuntimeException{
            /*
            String usuarioCorrente = sessionContext.getCallerPrincipal().getName();
                  AuditoriaHelper.auditar(manager, to, AcaoType.ATUALIZAR,
                                          usuarioCorrente);
            */
            T toReturn = manager.merge(to);

            manager.flush();

            return toReturn;
    }

    /**
     * Anexa novamento um objeto ao entity manager
     *
     * @param <T>
     * @param to
     * @return
     */
    public <T extends Serializable> T reAttach(T to) {
        try {
            return manager.merge(to);

        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Executa um commando de atualizacao no controlador de entidade.
     *
     * @param JPQL.
     * @param parameters.
     *
     */
    public int atualizarPorJPQL(CharSequence JPQL, Object... parameters) {
        try {
            Query query = manager.createQuery(JPQL.toString());
            fillParameters(query, parameters);
            return query.executeUpdate();
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Deleta um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    public <T extends Serializable> void excluir(T to) {
        try {

            to = manager.merge(to);
            
            /*
            String usuarioCorrente = sessionContext.getCallerPrincipal().getName();
                   AuditoriaHelper.auditar(manager, to, AcaoType.EXCLUIR,
                                           usuarioCorrente);
            */
            manager.remove(to);

        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL.
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */
    public <T extends Serializable> List<T> listarPorJPQL(CharSequence JPQL, Object... parameters) {
        return listarPorJPQL(JPQL, 0, 0, parameters);
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando SQL Nativo.
     *
     * @param QL.
     * @param parameters.
     * @return lista de objetos.
     */
    public List<Object> listarPorNativeQuery(CharSequence QL, Object... parameters) {
        return listarPorNativeQuery(QL, 0, 0, parameters);
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL, posicao inicial e quantidade de registros.
     *
     * @param <T>.
     * @param JPQL.
     * @param offset.
     * @param maxRegister.
     * @param parameters.
     * @return T.
     */
    public <T extends Serializable> List<T> listarPorJPQL(CharSequence JPQL, int offset, int maxRegister, Object... parameters) {
        try {
            Query query = manager.createQuery(JPQL.toString());

            if (parameters != null) {
                fillParameters(query, parameters);
            }

            if (maxRegister != 0) {
                query.setFirstResult(offset);
                query.setMaxResults(maxRegister);
            }

            List<T> result = query.getResultList();

            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }
    
    /**
     * Recupera o objeto no controlador de entidade baseado em um
     * commando JPQL
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */
    public <T extends Serializable> T procurarPorJPQLSingleResult(CharSequence JPQL, Object... parameters) {
        T result = null;
        try {
            Query query = manager.createQuery(JPQL.toString());

            if (parameters != null) {
                fillParameters(query, parameters);
            }
            
            result = (T) query.getSingleResult();
            
        } catch (RuntimeException e) {
            //throw new ModelException(e);
        }
        return result;
        
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando SQL Nativo, posicao inicial e quantidade de registros.
     *
     * @param QL.
     * @param offset.
     * @param maxRegister.
     * @param parameters.
     * @return lista de objetos.
     */
    public List<Object> listarPorNativeQuery(CharSequence QL, int offset, int maxRegister, Object... parameters) {
        try {
            Query query = manager.createNativeQuery(QL.toString());

            if (parameters != null) {
                fillParameters(query, parameters);
            }

            if (maxRegister != 0) {
                query.setFirstResult(offset);
                query.setMaxResults(maxRegister);
            }

            List<Object> result = query.getResultList();

            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera um resultado unico de objetos no controlador de entidade baseado em um
     * commando SQL Nativo.
     *
     * @param QL.
     * @param parameters.
     * @return lista de objetos.
     */
    public Object procurarPorNativeQuerySingleResult(CharSequence QL, Object... parameters) {
        try {
            Query query = manager.createNativeQuery(QL.toString());

            if (parameters != null) {
                fillParameters(query, parameters);
            }

            Object result = query.getSingleResult();

            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * @param sequenceName
     * @return
     */
    public Long getNextValSequence(String sequenceName) {
        CharSequence query = "select " + sequenceName + ".nextval from dual";
        Object returnObj = procurarPorNativeQuerySingleResult(query);
        Long nextVal = Long.valueOf(returnObj.toString());
        return nextVal;
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL, posicao inicial e quantidade de registros.
     *
     * @param <T>.
     * @param JPQL.
     * @param offset.
     * @param maxRegister.
     * @return T.
     */
    public <T extends Serializable> List<T> listarPorJPQL(CharSequence JPQL, int offset, int maxRegister) {
        try {
            Query query = manager.createQuery(JPQL.toString());

            if (maxRegister != 0) {
                query.setFirstResult(offset);
                query.setMaxResults(maxRegister);
            }

            List<T> result = query.getResultList();

            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL, posicao inicial e quantidade de registros.
     *
     * @param qr.
     * @param offset.
     * @param maxRegister.
     * @return lista de objetos.
     */
    public List<Object> listarPorNativeQuery(CharSequence qr, int offset, int maxRegister) {
        try {
            Query query = manager.createNativeQuery(qr.toString());

            if (maxRegister != 0) {
                query.setFirstResult(offset);
                query.setMaxResults(maxRegister);
            }

            List<Object> result = query.getResultList();

            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Consulta por named query.
     *
     * @param JPQL.
     * @return List.
     */
    public <T extends Serializable> List<T> listarPorNamedQuery(String JPQL) {
        try {
            Query query = manager.createNamedQuery(JPQL);
            List<T> result = query.getResultList();
            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Consulta por named query.
     *
     * @param JPQL.
     * @param values
     * @return List.
     */
    public <T extends Serializable> List<T> listarPorNamedQuery(String JPQL, Object... values) {
        try {
            Query query = manager.createNamedQuery(JPQL);

            if (values != null) {
                int i = 1;
                for (Object obj : values) {
                    query.setParameter(i, obj);
                    i++;
                }
            }

            List<T> result = query.getResultList();
            return result;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL e uma paginacao.
     *
     * @param <T>.
     * @param JPQL.
     * @param pagging.
     * @param parameters.
     * @return T
     */
    public <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging,
                                                            List<Object> parameters) {
        return listarPorJPQL(JPQL, pagging, parameters.toArray());
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL e uma paginacao.
     *
     * @param <T>.
     * @param JPQL.
     * @param pagging.
     * @param parameters.
     * @return T.
     */
    public <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging,
                                                            Object... parameters) {
        try {
            Long count = getByJPQL(createJPQLCount(JPQL.toString()), parameters);
            pagging.setTamanhoMaximo(count.intValue());

            List<T> list;
            if (count != 0) {
                list = listarPorJPQL(JPQL, pagging.getIndiceRegistro(), pagging.getQtdItensPorPagina(), parameters);
            } else {
                list = new ArrayList<T>();
            }

            pagging.setLista(list);

            return pagging;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }
    
    
    
    public <T extends Serializable> Paginacao listarPorUnionJPQL(CharSequence JPQL, CharSequence JPQL2, Paginacao pagging,
							    Object... parameters) {
	try {
	    Long count = (Long)getByJPQL(createJPQLCount(JPQL.toString()), parameters) +
			 (Long)getByJPQL(createJPQLCount(JPQL2.toString()), parameters);
	    pagging.setTamanhoMaximo(count.intValue());

	    List<T> list = new ArrayList<T>();
	    if (count != 0) {
		list.addAll(listarPorJPQL( JPQL, pagging.getIndiceRegistro(), pagging.getQtdItensPorPagina(), parameters));
	        list.addAll(listarPorJPQL( JPQL2, pagging.getIndiceRegistro(), pagging.getQtdItensPorPagina(), parameters));
	    } else {
		list = new ArrayList<T>();
	    }
            while(list.size()>pagging.getQtdItensPorPagina()){
                list.remove(list.size()-1);
            }
            
	    pagging.setLista(list);

	    return pagging;
	} catch (RuntimeException e) {
	    throw new ModelException(e);
	}
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL e uma paginacao.
     *
     * @param <T>.
     * @param JPQL.
     * @param pagging.
     * @return T.
     */
    public <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging) {
        try {
            Long count = getByJPQL(createJPQLCount(JPQL.toString()));
            pagging.setTamanhoMaximo(count.intValue());

            List<T> list;
            if (count != 0) {
                list = listarPorJPQL(JPQL, pagging.getIndiceRegistro(), pagging.getQtdItensPorPagina());
            } else {
                list = new ArrayList<T>();
            }

            pagging.setLista(list);

            return pagging;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL e uma paginacao.
     *
     * @param <T>.
     * @param query.
     * @param pagging.
     * @return T.
     */
    public <T extends Serializable> Paginacao listarPorNativeQuery(CharSequence query, Paginacao pagging) {
        try {
            Long count = getCountNativeQuery(query);

            pagging.setTamanhoMaximo(count.intValue());

            List<Object> list;
            if (count != 0) {
                list = listarPorNativeQuery(query, pagging.getIndiceRegistro(), pagging.getQtdItensPorPagina());
            } else {
                list = new ArrayList<Object>();
            }

            pagging.setLista(list);

            return pagging;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera o objeto no controlador de entidade baseado no seu identificador.
     *
     * @param <T>.
     * @param to.
     * @param id.
     * @return T.
     */
    public <T extends Serializable> T procurarPorId(Class<T> to, Serializable id) {
        try {
            T object = manager.find(to, id);

            return object;
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera um objeto no controlador de entidade baseado em um commando de
     * selecao.
     *
     * @param <T>
     * @param JPQL
     * @param parameters
     * @return T
     */
    public <T extends Serializable> T getByJPQL(CharSequence JPQL, Object... parameters) {
        try {
            List<T> result = listarPorJPQL(JPQL, parameters);

            if (result.size() == 0) {
                return null;
            }
            if (result.size() != 1) {
                throw new NonUniqueResultException("Mais de um objeto encontrado!");
            }

            return result.iterator().next();
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Recupera um objeto no controlador de entidade baseado em um commando de
     * selecao.
     *
     * @param QL
     * @param parameters
     * @return quantidade de registros.
     */
    public Long getCountNativeQuery(CharSequence QL, Object... parameters) {
        try {
            String query = "select count(*) from (" + QL.toString() + ")";

            List<Object> result = listarPorNativeQuery(query, parameters);

            if (result.size() == 0) {
                return null;
            }
            if (result.size() != 1) {
                throw new NonUniqueResultException("Mais de um objeto encontrado!");
            }

            return Long.valueOf(String.valueOf(result.iterator().next()));
        } catch (RuntimeException e) {
            throw new ModelException(e);
        }
    }

    /**
     * Preenche os parametros em uma objeto Query criado.
     *
     * @param query.
     * @param parameters.
     */
    private void fillParameters(Query query, Object... parameters) {
        int offset = 1;
        for (int cont = 0; cont < parameters.length; cont++) {  
            if(parameters[cont] instanceof ParametroOperacao) {
                ParametroOperacao param = (ParametroOperacao) parameters[cont];
                
                if(!SQLOperatorType.IsNull.equals(param.getOperacao())
                    && !SQLOperatorType.IsNotNull.equals(param.getOperacao())){
                    query.setParameter(cont + offset, param.getValor());                    
                }

                if(SQLOperatorType.Between.equals(param.getOperacao())) {
                    offset++;              
                    query.setParameter(cont + offset, param.getValor2());   
                } 
            }
            else {
                query.setParameter(cont + offset, parameters[cont]);
            }
        }
    }

    /**
     * Gera um commando count baseado em um outro commando JPQL.
     *
     * @param JPQL.
     * @return stringJPQL.
     */
    private String createJPQLCount(CharSequence JPQL) {
        String newJPQL = JPQL.toString();

        // Remove espacos desnecessarios
        while (newJPQL.contains("  ")) {
            newJPQL = newJPQL.replaceAll("  ", " ");
        }

        // Recupera o alias do JPQL
        String alias = newJPQL;
        int posFrom = newJPQL.indexOf("from") + 5;
        alias = alias.substring(posFrom);

        int posSpace = alias.indexOf(" ");
        alias = alias.substring(posSpace + 1);

        posSpace = alias.indexOf(" ");
        if (posSpace != -1) {
            alias = alias.substring(0, posSpace);
        }

        return "select count(" + alias + ") from " + newJPQL.substring(newJPQL.indexOf("from") + 4);
    }

    /**
     * Obtem o manager.
     *
     * @return manager.
     */
    public EntityManager getManager() {
        return this.manager;
    }
    
    /**
     *Retorna JSQL query by entity JPA.
     *
     * @param <T> Tipo da entidade.
     * @param entity Entidade.
     * @return String.
     */
    public <T extends Serializable> String getQueryNativeByEntity(Class<T> entity) {
        return "select entidade from " + entity.getSimpleName() + " entidade ";
    }
}
