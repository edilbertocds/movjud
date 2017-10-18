package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.dao.PersistenceManager;

import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.BaseEntity;
import br.jus.tjsp.movjud.persistence.entity.DominioBI;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DominioBIDAOImpl implements DominioBIDAO {
    @EJB
    private PersistenceManager persistenceManager;
    
    @Resource
    private SessionContext sessionContext;
    
    public DominioBI salvar(DominioBI dominioBI) throws RuntimeException {
        Boolean merge = false;
        if (dominioBI instanceof DominioBI) {
            Serializable id = (Serializable)dominioBI.getCodigoDominio();
            merge = id != null && procurarPorId(id) != null;
        }
        if (merge) {
            dominioBI.setDataAlteracao(new Date());
            dominioBI.setUsuarioAlt(sessionContext.getCallerPrincipal().getName());
            dominioBI = atualizar(dominioBI);
        } else {
            dominioBI.setDataInclusao(new Date());
            dominioBI.setUsuarioInc(sessionContext.getCallerPrincipal().getName());
            getPersistenceManager().salvar(dominioBI);
        }
        return dominioBI;        
    }
    
    public DominioBI atualizar(DominioBI dominioBI) throws RuntimeException{
        dominioBI.setDataAlteracao(new Date());
        return getPersistenceManager().atualizar(dominioBI);        
    }
    
    public DominioBI procurarPorId(Serializable t) {
        return getPersistenceManager().procurarPorId(getPersistentClass(), t);
    }

    public void excluir(DominioBI dominioBI) {
        getPersistenceManager().excluir(dominioBI);
    }

    public List<DominioBI> listar() {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        return getPersistenceManager().listarPorJPQL(jpaQl);
    }

    public List<DominioBI> listar(Paginacao paginacao) {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        return getPersistenceManager().listarPorJPQL(jpaQl, paginacao).getLista();        
    }

    public List<DominioBI> listarComOrdenacao() {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        jpaQl += " order by codigoDominioBI";
        return getPersistenceManager().listarPorJPQL(jpaQl);
    }
    
    public List<DominioBI> listarComOrdenacao(Paginacao paginacao) {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        jpaQl += " order by codigoDominioBI";
        return getPersistenceManager().listarPorJPQL(jpaQl, paginacao).getLista();                
    }
    
    private List<ParametroOperacao> parametrosFiltroByEntity(DominioBI filter) {
        List<ParametroOperacao> result = new ArrayList<ParametroOperacao>();
        result.add(new ParametroOperacao("codigoDominio", filter.getCodigoDominio(), SQLOperatorType.LikeFullNoCase));
        result.add(new ParametroOperacao("tipoDominio", filter.getTipoDominio() != null ? filter.getTipoDominio().substring(0, 1) : filter.getTipoDominio(), SQLOperatorType.Equal));
        return result;
    }

    public List<DominioBI> listarComFiltro(DominioBI filter) {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        if (filter != null) {
            List<ParametroOperacao> parametros = parametrosFiltroByEntity(filter);
            
            if (parametros != null && parametros.size() > 0) {
                for (int i = 0; i < parametros.size(); i++) {
                    ParametroOperacao param = parametros.get(i);
                    if (param.isEmpty()) {
                        parametros.remove(param);
                        i--;
                    } else {
                        jpaQl +=  " and ";
                        jpaQl += param.getParametro(i+1);
                    }
                }
            }
        }
        return getPersistenceManager().listarPorJPQL(jpaQl);                
    }

    public List<DominioBI> listarComFiltro(DominioBI filter, Paginacao paginacao) {
        String jpaQl = getPersistenceManager().getQueryNativeByEntity(getPersistentClass());
        if (filter != null) {
            List<ParametroOperacao> parametros = parametrosFiltroByEntity(filter);
            
            if (parametros != null && parametros.size() > 0) {
                boolean withFilter = false;
                boolean first = false;
                for (int i = 0; i < parametros.size(); i++) {
                    ParametroOperacao param = parametros.get(i);
                    if (param.isEmpty()) {
                        parametros.remove(param);
                        i--;
                    } else {
                        if(!withFilter) {
                            jpaQl += " where ";
                            withFilter = true;
                        }
                        if(!first) {
                            first = true;
                        } else {
                            jpaQl +=  " and ";
                        }
                        jpaQl += param.getParametro(i+1);
                    }
                }
                if(withFilter) {
                    return getPersistenceManager().listarPorJPQL(jpaQl, paginacao, parametros.toArray()).getLista();
                }
            }
        }        
        return getPersistenceManager().listarPorJPQL(jpaQl, paginacao).getLista();                
    }
    
    public void refresh(DominioBI dominioBI) {
        getPersistenceManager().refresh(dominioBI);
    }

    public Class<DominioBI> getPersistentClass() {
        return DominioBI.class;
    }

    public PersistenceManager getPersistenceManager() {
        return this.persistenceManager;
    }
}
