package br.jus.tjsp.movjud.persistence.base.dao;


import java.io.Serializable;

import java.util.List;

import javax.ejb.Local;

import javax.persistence.EntityManager;


/**
 * Interface para o persistence
 */
@Local
public interface PersistenceManager {

    /**
     * Atualiza a entidade com os dados da base de dados.
     *
     * @param entity Entidade.
     */
    void refresh(Serializable entity);

    /**
     * Salva um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    <T extends Serializable> void salvar(T to) throws RuntimeException;

    /**
     * Executa um comando de insercao no controlador de entidade.
     *
     * @param JPQL.
     * @param parameters.
     *
     */
    int salvarPorJPQL(CharSequence JPQL, Object... parameters);

    /**
     * Atualiza um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    <T extends Serializable> T atualizar(T to) throws RuntimeException;

    /**
     * ReAnexa a entidade
     * @param <T>
     * @param to
     * @return
     */
    <T extends Serializable> T reAttach(T to);

    /**
     * Executa um commando de atualizacao no controlador de entidade.
     *
     * @param JPQL.
     * @param parameters.
     *
     */
    int atualizarPorJPQL(CharSequence JPQL, Object... parameters);

    /**
     * Deleta um objeto no controlador de entidade.
     *
     * @param <T>.
     * @param to.
     */
    <T extends Serializable> void excluir(T to);
    
    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL.
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */


    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL.
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */
    <T extends Serializable> List<T> listarPorJPQL(CharSequence JPQL, Object... parameters);

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando SQL Nativo.
     *
     * @param QL.
     * @param parameters.
     * @return lista de objetos.
     */
    List<Object> listarPorNativeQuery(CharSequence QL, Object... parameters);

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
    <T extends Serializable> List<T> listarPorJPQL(CharSequence JPQL, int offset, int maxRegister, Object... parameters);

    /**
     * Recupera o objeto no controlador de entidade baseado em um
     * commando JPQL
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */
    <T extends Serializable> T procurarPorJPQLSingleResult(CharSequence JPQL, Object... parameters);
    
    
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
    List<Object> listarPorNativeQuery(CharSequence QL, int offset, int maxRegister, Object... parameters);

   
    /**
     * Recupera um resultado unico de objetos no controlador de entidade baseado em um
     * commando SQL Nativo.
     *
     * @param QL.
     * @param parameters.
     * @return lista de objetos.
     */
    Object procurarPorNativeQuerySingleResult(CharSequence QL, Object... parameters);


    /**
     * @param sequenceName
     * @return
     */
    Long getNextValSequence(String sequenceName);

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
    <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging, List<Object> parameters);

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
    <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging, Object... parameters);

    <T extends Serializable> Paginacao listarPorUnionJPQL(CharSequence JPQL, CharSequence JPQL2, Paginacao pagging,
							    Object... parameters);
    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL e uma paginacao.
     *
     * @param <T>.
     * @param JPQL.
     * @param pagging.
     * @return T.
     */
    <T extends Serializable> Paginacao listarPorJPQL(CharSequence JPQL, Paginacao pagging);

    /**
     * Recupera uma lista de objetos no controlador de entidade baseado em um
     * commando JPQL nativo e uma paginacao.
     *
     * @param query.
     * @param pagging.
     * @return lista de objetos.
     */
    <T extends Serializable> Paginacao listarPorNativeQuery(CharSequence query, Paginacao pagging);

    /**
     * Recupera o objeto no controlador de entidade baseado no seu identificador.
     *
     * @param <T>.
     * @param to.
     * @param id.
     * @return T.
     */
    <T extends Serializable> T procurarPorId(Class<T> to, Serializable id);

    /**
     * Recupera um objeto no controlador de entidade baseado em um commando de
     * selecao.
     *
     * @param <T>.
     * @param JPQL.
     * @param parameters.
     * @return T.
     */
    <T extends Serializable> T getByJPQL(CharSequence JPQL, Object... parameters);

    /**
     * Consulta por named query.
     *
     * @param JPQL.
     * @param values
     * @return List.
     */
    <T extends Serializable> List<T> listarPorNamedQuery(String JPQL, Object... values);

    /**
     * Consulta por named query.
     *
     * @param JPQL.
     * @return List.
     */
    <T extends Serializable> List<T> listarPorNamedQuery(String JPQL);

    /**
     * Obtem o manager.
     *
     * @return manager.
     */
     EntityManager getManager();

    /**
     *
     * @param QL
     * @param parameters
     * @return
     */
     Long getCountNativeQuery(CharSequence QL, Object... parameters);
    
    /**
     *Retorna JSQL query by entity JPA.
     *
     * @param <T> Tipo da entidade.
     * @param entity Entidade.
     * @return String.
     */
    <T extends Serializable> String getQueryNativeByEntity(Class<T> entity);
}
