package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Aviso;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class AvisoDAOImpl extends BaseDAOImpl<Aviso> implements AvisoDAO {

    @Override
    public Class<Aviso> getPersistentClass() {
        return Aviso.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Aviso filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        if(filter.getUsuario() != null)
            parametros.add(new ParametroOperacao("usuario.idUsuario", filter.getUsuario().getIdUsuario(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("flagTipoSituacao", ConstantesMovjud.FLAG_SITUACAO_ATIVA, SQLOperatorType.EqualNoCase));
        
        return parametros; 
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Aviso filter) {
        return Collections.emptyList();
    }
    
    @Override
    public List<Aviso> listarAvisosExistentesDoUsuario(Usuario usuario) {
        Aviso filter = new Aviso();
        filter.setUsuario(usuario);
        List<Aviso> avisosExistente = listarComFiltroOrdenacao(filter, new String[]{"dataEnvio","idAviso"}, 
                                                               new Boolean[]{false, false});
        if (avisosExistente == null) {
            avisosExistente = new ArrayList<Aviso>();
        }
        return avisosExistente;
    }
    
    @Override
    public List<Aviso> listarAvisosExistentesDoUsuario(Usuario usuario, Paginacao paginacao) {
        Aviso filter = new Aviso();
        filter.setUsuario(usuario);
        List<Aviso> avisosExistente = listarComFiltroOrdenacao(filter, new String[]{"dataEnvio","idAviso"}, 
                                                               new Boolean[]{false, false}, paginacao);
        if (avisosExistente == null) {
            avisosExistente = new ArrayList<Aviso>();
        }
        return avisosExistente;
    }

    @Override
    public Long countAvisosNaoLidos(Usuario usuario) {
        StringBuilder jpaql = new StringBuilder();
        jpaql.append("select count(Aviso) from Aviso Aviso where ");
        jpaql.append("Aviso.usuario.idUsuario = ?1");
        jpaql.append(" and Aviso.flagLido = 'N'");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaql, usuario.getId());
        }
}
