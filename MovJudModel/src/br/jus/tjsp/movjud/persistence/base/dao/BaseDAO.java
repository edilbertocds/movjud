package br.jus.tjsp.movjud.persistence.base.dao;


import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;

import java.io.Serializable;

import java.util.List;


/**
 * Define os metodos padroes para as DAOs
 * @author cds
 */
public interface BaseDAO<T extends BaseEntity> extends Serializable{

    /**
     * Metodo responsavel por salvar o objeto.
     *
     * @param t.
     * @return t.
     */
    T salvar(T t) throws RuntimeException;

    /**
     * Metodo responsavel por salvar o objeto.
     *
     * @param t.
     * @return objeto.
     */
    T atualizar(T t) throws RuntimeException;

    /**
     * Metodo responsavel por procurar por id.
     *
     * @param t.
     * @return objeto.
     */
    T procurarPorId(Serializable t);

    /**
     * Metodo responsavel por excluir o objeto.
     *
     * @param t.
     */
    void excluir(T t);


    /**
     * Metodo responsavel por listar todos os objetos.
     *
     * @return lista de objetos.
     */
    List<T> listar();


    /**
     * Metodo responsavel por listar todos os objetos com paginacao
     *
     * @param paginacao
     * @return lista de objetos.
     */
    List<T> listar(Paginacao paginacao);


    /**
     * Metodo responsavel por listar todos os objetos com ordenacao.
     *
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @return lista de objetos.
     */
    List<T> listarComOrdenacao(String []campoOrdenacao, Boolean []ascOrdenacao);
    
    List<T> listarComOrdenacao(String campoOrdenacao, Boolean ascOrdenacao);













    /**
     * Metodo responsavel por listar todos os objetos com ordenacao e paginacao.
     *
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @param paginacao
     * @return lista de objetos.
     */
    List<T> listarComOrdenacao(String []campoOrdenacao, Boolean []ascOrdenacao, Paginacao paginacao);
    
    List<T> listarComOrdenacao(String campoOrdenacao, Boolean ascOrdenacao, Paginacao paginacao);


    /**
     * Metodo responsavel por listar os objetos com filtro.
     *
     * @param filter
     * @return lista de objetos.
     */
    List<T> listarComFiltro(T filter);


    /**
     * Metodo responsavel por listar os objetos com filtro e paginacao.
     *
     * @param filter
     * @param paginacao
     * @return lista de objetos.
     */
    List<T> listarComFiltro(T filter, Paginacao paginacao);


    /**
     * Metodo responsavel por listar os objetos com filtro e ordenacao.
     *
     * @param filter
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @return lista de objetos.
     */
    List<T> listarComFiltroOrdenacao(T filter, String []campoOrdenacao, Boolean []ascOrdenacao);
    
    List<T> listarComFiltroOrdenacao(T filter, String campoOrdenacao, Boolean ascOrdenacao);


    /**
     * Metodo responsavel por listar os objetos com filtro, ordenacao e paginacao.
     *
     * @param filter
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @param paginacao
     * @return lista de objetos.
     */
    List<T> listarComFiltroOrdenacao(T filter, String []campoOrdenacao, Boolean []ascOrdenacao, Paginacao paginacao);
    
    List<T> listarComFiltroOrdenacao(T filter, String campoOrdenacao, Boolean ascOrdenacao, Paginacao paginacao);


    /**
     * Metodo responsavel por procurar objeto com filtro
     *
     * @param filtro
     * @return T.
     */
    T procurarComFiltro(T filter) throws RuntimeException;

    /**
     * Metodo responsavel pela query basica do filtro.
     *
     * @param filter.
     */
    List<ParametroOperacao> parametrosFiltroByEntity(T filter);
    
    /**
     * Metodo responsavel pela query basica do filtro.
     *
     * @param filter.
     */
    List<ParametroOperacao> parametrosFiltroSingleByEntity(T filter);

    /**
     * Metodo responsavel por executar o refresh do objeto.
     *
     * @param t.
     */
    void refresh(T t);

    /**
     * Metodo responsavel obter o tipoValidacao do objeto.
     *
     * @return Class.
     */
    Class<T> getPersistentClass();

    /**
     * Metodo responsavel por obter o PersistenceManager.
     *
     * @return PersistenceManager.
     */
    PersistenceManager getPersistenceManager();

}
