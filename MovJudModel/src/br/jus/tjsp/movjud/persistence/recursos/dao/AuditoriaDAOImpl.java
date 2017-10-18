package br.jus.tjsp.movjud.persistence.recursos.dao;


import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class AuditoriaDAOImpl extends BaseDAOImpl<Auditoria> implements AuditoriaDAO {
    @SuppressWarnings("compatibility:7735942839771279288")
    private static final long serialVersionUID = 1L;

    public Class<Auditoria> getPersistentClass() {
        return Auditoria.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Auditoria filter) {     
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
  
        parametros.add(new ParametroOperacao("usuario", filter.getUsuario(), SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("entidade", filter.getEntidade(), SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("dominio", filter.getDominio(), SQLOperatorType.EqualNoCase));
        parametros.add(new ParametroOperacao("acao", filter.getAcao(), SQLOperatorType.EqualNoCase));     
        parametros.add(new ParametroOperacao("dataInclusao", filter.getDataFiltroInicio(), SQLOperatorType.GreaterOrEqual, true));
        parametros.add(new ParametroOperacao("dataInclusao", filter.getDataFiltroFim(), SQLOperatorType.LessOrEqual, true));
          
        return parametros;    
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Auditoria filter) {
        return Collections.emptyList();
    }
    
    @Override
    public List<String> listarUsuariosAuditoria() {
        return getPersistenceManager().listarPorJPQL("select DISTINCT(a.usuario) from Auditoria a order by a.usuario");
    }
    
    @Override
    public List<String> listarEntidadesAuditoria() {
        return getPersistenceManager().listarPorJPQL("select DISTINCT(a.entidade) from Auditoria a order by a.entidade");
    }

    @Override
    public List<Auditoria> listarAuditoriasComFiltroPaginacao(Auditoria auditoria, Paginacao paginacao) {
        return listarComFiltroOrdenacao(auditoria, "dataInclusao", false, paginacao);
    }
}

