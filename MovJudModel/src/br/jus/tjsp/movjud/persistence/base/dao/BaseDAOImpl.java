package br.jus.tjsp.movjud.persistence.base.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;

import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;


/**
 * Classe abstrata para gerencias as DAOs de acesso ao banco de dados.
 *
 * @author cds
 */
public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {
    private static final int MAX_LIST_SIZE=1000;
    /**
     * Auxiliador no acesso aos dados.
     */
    @EJB
    private PersistenceManager persistenceManager;

    /**
     * Obtem o PersistenceManager.
     *
     * @return PersistenceManager.
     */
    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    /**
     * Metodo responsavel por deletar o objeto.
     *
     * @param t.
     */
    @Override
    public void excluir(T t) {
        getPersistenceManager().excluir(t);
    }

    /**
     * Metodo responsavel por pesquisar pelo id.
     *
     * @param id.
     * @return T.
     */
    @Override
    public T procurarPorId(Serializable id) {
        return getPersistenceManager().procurarPorId(getPersistentClass(), id);
    }

    /**
     * Metodo responsavel por salvar.
     *
     * @param t.
     * @return T.
     */
    @Override
    public T salvar(T t) throws RuntimeException{
        Boolean merge = false;
        if (t instanceof BaseEntity) {
            Serializable id = (Serializable)t.getId();
            merge = id != null && procurarPorId(id) != null;
        }
        t.setDataAtualizacao(new Date());
        if (merge) {
            t = atualizar(t);
        } else {
            t.setDataInclusao(new Date());
            getPersistenceManager().salvar(t);
        }
        //objeto passa a ser gerenciado
        return t;
    }

    /**
     * Metodo responsavel por atualizar.
     *
     * @param t.
     * @return T.
     */
    @Override
    public T atualizar(T t) throws RuntimeException{
        t.setDataAtualizacao(new Date());
        t = getPersistenceManager().atualizar(t);
        //objeto passa a ser gerenciado
        return t;
    }

    /**
     * Metodo responsavel por realizar o refresh do objeto.
     *
     * @param t.
     */
    @Override
    public void refresh(T t) {
        getPersistenceManager().refresh(t);
    }

    /**
     * Metodo responsavel por auxiliar a criar do jpaQl.
     *
     * @param jpaQl
     */
    protected void whereAnd(StringBuilder jpaQl) {
        if (jpaQl.toString().toUpperCase().contains("WHERE")) {
            jpaQl.append(" AND ");
        } else {
            jpaQl.append(" WHERE ");
        }
    }

  
    protected void equalLong(StringBuilder jpaQl, String nomeCampo, Long valorCampo) {
        if (valorCampo != null && valorCampo > 0) {
            whereAnd(jpaQl);
            jpaQl.append(nomeCampo + " = " + valorCampo);
        }
    }

    /**
     * Metodo responsavel por auxiliar a criar do jpaQl.
     *
     * @param jpaQl
     */
    protected void equalData(StringBuilder jpaQl, String nomeCampo, String valorCampo) {
        if (valorCampo != null) {
            whereAnd(jpaQl);
            jpaQl.append(nomeCampo + "= TO_DATE('" + valorCampo + "', 'dd/MM/yyyy')");
        }
    }

    /**
     * Metodo responsavel por auxiliar a criar do jpaQl.
     *
     * @param jpaQl
     */
    protected void inList(StringBuilder jpaQl, String nomeCampo, List valorCampo) {
        if (valorCampo != null) {
            int size = valorCampo.size();
            int count = 0;
            int end = size > MAX_LIST_SIZE ? MAX_LIST_SIZE : size;
            while (count < size) {
                
                whereAnd(jpaQl);
                jpaQl.append(nomeCampo + " in(");
    
                for (int i = 0; i < valorCampo.size(); i++) {
                    jpaQl.append(valorCampo.get(i));
                    if (i < valorCampo.size() - 1) {
                        jpaQl.append(",");
                    }
                }
                jpaQl.append(")");
                count = end;
                end += MAX_LIST_SIZE;
                end = size > end ? end : size;
            }
        }
    }

    protected void inListString(StringBuilder jpaQl, String nomeCampo, List<String> valorCampo) {
        if (valorCampo != null) {
            int size = valorCampo.size();
            int count = 0;
            int end = size > MAX_LIST_SIZE ? MAX_LIST_SIZE : size;
            while (count < size) {

                whereAnd(jpaQl);
                jpaQl.append(nomeCampo + " in('");

                for (int i = count; i < end; i++) {
                    jpaQl.append(valorCampo.get(i));
                    if (i < end - 1) {
                        jpaQl.append("','");
                    }
                }
                jpaQl.append("')");
                count = end;
                end += MAX_LIST_SIZE;
                end = size > end ? end : size;
            }
        }
    }

    protected void notInList(StringBuilder jpaQl, String nomeCampo, List valorCampo) {
        if (valorCampo != null) {
            int size = valorCampo.size();
            int count = 0;
            int end = size > MAX_LIST_SIZE ? MAX_LIST_SIZE : size;
            while (count < size) {
                whereAnd(jpaQl);
                jpaQl.append(nomeCampo + " not in(");
                for (int i = count; i < end; i++) {
                    jpaQl.append(valorCampo.get(i));
                    if (i < end - 1) {
                        jpaQl.append(",");
                    }
                }
                jpaQl.append(")");
                count = end;
                end += MAX_LIST_SIZE;
                end = size > end ? end : size;
            }
        }
    }

    /**
     * Metodo responsavel por auxiliar a criar do jpaQl.
     *
     * @param jpaQl
     */
    protected void whereOr(StringBuilder jpaQl) {
        if (jpaQl.toString().toUpperCase().contains("WHERE")) {
            jpaQl.append(" OR ");
        } else {
            jpaQl.append(" WHERE ");
        }
    }

    /**
     * Metodo responsavel por auxiliar a criar do jpaQl.
     *
     * @param jpaQl
     * @param campoOrdenacao
     * @param ascOrdenacao
     */
    protected void orderBy(StringBuilder jpaQl, String campoOrdenacao, Boolean ascOrdenacao) {
        if (jpaQl.toString().toUpperCase().contains("ORDER BY")) {
            jpaQl.append(" , entidade." + campoOrdenacao);
        } else {
            jpaQl.append(" ORDER BY entidade." + campoOrdenacao);
        }

        if (ascOrdenacao) {
            jpaQl.append(" ASC ");
        } else {
            jpaQl.append(" DESC ");
        }

    }


    /**
     * Metodo generico responsavel por listar todos os objetos.
     *
     * @return lista de objetos.
     */
    private List<T> listar(T filter, String[] campoOrdenacao, Boolean []ascOrdenacao, Paginacao paginacao) {
        StringBuilder jpaQl = new StringBuilder();
        List<T> lista = null;
        List<ParametroOperacao> parametros = null;
        
        jpaQl.append(getPersistenceManager().getQueryNativeByEntity(getPersistentClass()));
        if (filter != null) {
            parametros = parametrosFiltroByEntity(filter);
            
            if (parametros != null && parametros.size() > 0) {
                for (int i = 0; i < parametros.size(); i++) {
                    ParametroOperacao param = parametros.get(i);
                    if (param.isEmpty()) {
                        parametros.remove(param);
                        i--;
                    } else {
                        whereAnd(jpaQl);
                        jpaQl.append(param.getParametro(i+1));
                    }
                }
            }
        }

        if(campoOrdenacao != null && campoOrdenacao.length > 0) {
	    for(int i=0; i<campoOrdenacao.length;  i++) {
		orderBy(jpaQl, campoOrdenacao[i], ascOrdenacao[i]);
	    }
        }
        
        if (parametros == null || parametros.size() == 0) {
            if (paginacao == null) {
                lista = getPersistenceManager().listarPorJPQL(jpaQl); 
            } 
            else {
                lista = getPersistenceManager().listarPorJPQL(jpaQl, paginacao).getLista();   
            }
        } 
        else {              
            if (paginacao == null) {
                lista = getPersistenceManager().listarPorJPQL(jpaQl, parametros.toArray());
            }
            else {
                lista = getPersistenceManager().listarPorJPQL(jpaQl, paginacao, parametros.toArray()).getLista();    
            }
        }
        
        if(lista != null && lista.size() == 0) {
            lista = null;
        }
        
        return lista;
    }


    /**
     * Metodo responsavel por listar todos os objetos.
     *
     * @return lista de objetos.
     */
    @Override
    public List<T> listar() {
        return listar(null, null, null, null);
    }

    /**
     * Metodo responsavel por listar todos os objetos com paginacao
     *
     * @param paginacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listar(Paginacao paginacao) {
        return listar(null, null, null, paginacao);
    }

    /**
     * Metodo responsavel por listar todos os objetos com ordenacao.
     *
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComOrdenacao(String []campoOrdenacao, Boolean []ascOrdenacao) {
        return listar(null, campoOrdenacao, ascOrdenacao, null);
    }
    
    @Override
    public List<T> listarComOrdenacao(String campoOrdenacao, Boolean ascOrdenacao) {
	return listar(null, new String []{campoOrdenacao}, new Boolean []{ascOrdenacao}, null);
    }

    /**
     * Metodo responsavel por listar todos os objetos com ordenacao e paginacao.
     *
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @param paginacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComOrdenacao(String []campoOrdenacao, Boolean []ascOrdenacao, Paginacao paginacao) {
        return listar(null, campoOrdenacao, ascOrdenacao, paginacao);
    }
    
    @Override
    public List<T> listarComOrdenacao(String campoOrdenacao, Boolean ascOrdenacao, Paginacao paginacao) {
	return listar(null, new String []{campoOrdenacao}, new Boolean []{ascOrdenacao}, paginacao);
    }


    /**
     * Metodo responsavel por listar os objetos com filtro.
     *
     * @param filtro
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComFiltro(T filter) {
        return listar(filter, null, null, null);
    }

    /**
     * Metodo responsavel por listar os objetos com filtro e paginacao.
     *
     * @param filtro
     * @param paginacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComFiltro(T filter, Paginacao paginacao) {
        return listar(filter, null, null, paginacao);
    }
    

    /**
     * Metodo responsavel por listar os objetos com filtro e ordenacao.
     *
     * @param filtro
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComFiltroOrdenacao(T filter, String []campoOrdenacao, Boolean []ascOrdenacao) {
        return listar(filter, campoOrdenacao, ascOrdenacao, null);
    }
    
    @Override
    public List<T> listarComFiltroOrdenacao(T filter, String campoOrdenacao, Boolean ascOrdenacao) {
	return listar(filter, new String []{campoOrdenacao}, new Boolean []{ascOrdenacao}, null);
    }

    /**
     * Metodo responsavel por listar os objetos com filtro, ordenacao e paginacao.
     *
     * @param filtro
     * @param campoOrdenacao
     * @param ascOrdenacao
     * @param paginacao
     * @return lista de objetos.
     */
    @Override
    public List<T> listarComFiltroOrdenacao(T filter, String []campoOrdenacao, Boolean []ascOrdenacao,
                                            Paginacao paginacao) {
        return listar(filter, campoOrdenacao, ascOrdenacao, paginacao);
    }
    
    @Override
    public List<T> listarComFiltroOrdenacao(T filter, String campoOrdenacao, Boolean ascOrdenacao,
					    Paginacao paginacao) {
	return listar(filter, new String []{campoOrdenacao}, new Boolean []{ascOrdenacao}, paginacao);
    }
    
    
    /**
     * Metodo responsavel por procurar objeto com filtro
     *
     * @param filtro
     * @return T.
     */
    @Override
    public T procurarComFiltro(T filter) throws RuntimeException{
        StringBuilder jpaQl = new StringBuilder();
        List<ParametroOperacao> parametros = null;
        
        jpaQl.append(getPersistenceManager().getQueryNativeByEntity(getPersistentClass()));
       
        if (filter != null) {
            parametros = parametrosFiltroSingleByEntity(filter);
            
            if (parametros != null && parametros.size() > 0) {
                for (int i = 0; i < parametros.size(); i++) {
                    ParametroOperacao param = parametros.get(i);
                    if (param.isEmpty()) {
                        parametros.remove(param);
                        i--;
                    } else {
                        whereAnd(jpaQl);
                        jpaQl.append(param.getParametro(i+1));
                    }
                }
            }
        
        }

        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl, parametros.toArray());
    }
}
