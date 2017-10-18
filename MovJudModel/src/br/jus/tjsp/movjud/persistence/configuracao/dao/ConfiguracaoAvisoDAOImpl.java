package br.jus.tjsp.movjud.persistence.configuracao.dao;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.AbrangenciaType;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class ConfiguracaoAvisoDAOImpl extends BaseDAOImpl<ConfiguracaoAviso> implements ConfiguracaoAvisoDAO {

    @Override
    public Class<ConfiguracaoAviso> getPersistentClass() {
        return ConfiguracaoAviso.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ConfiguracaoAviso filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("nomeTitulo", filter.getNomeTitulo(), SQLOperatorType.LikeFullNoCase));
        if (filter.getAvisoEstrutura() != null && filter.getAvisoEstrutura().getIdAvisoEstrutura() != null) {
            parametros.add(new ParametroOperacao("avisoEstrutura.idAvisoEstrutura", 
                filter.getAvisoEstrutura().getIdAvisoEstrutura(), SQLOperatorType.Equal));
        }
        if (filter.getAvisoEstrutura() != null && filter.getAvisoEstrutura().getTipoAviso() != null
                && filter.getAvisoEstrutura().getTipoAviso().getIdTipoAviso() != null) {
            parametros.add(new ParametroOperacao("avisoEstrutura.tipoAviso.idTipoAviso", 
                filter.getAvisoEstrutura().getTipoAviso().getIdTipoAviso(), SQLOperatorType.Equal));
        }
        if (filter.getTipoPeriodicidade() != null && filter.getTipoPeriodicidade().getIdTipoPeriodicidade() != null) {
            parametros.add(new ParametroOperacao("tipoPeriodicidade.idTipoPeriodicidade", 
                filter.getTipoPeriodicidade().getIdTipoPeriodicidade(), SQLOperatorType.Equal));
        }
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
        
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(ConfiguracaoAviso filter) {
        return Collections.emptyList();
    }
    
    @Override
    public List<ConfiguracaoAviso> listarConfiguracoesAvisoDoUsuario(Usuario usuario) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select configuracaoAviso from ConfiguracaoAviso configuracaoAviso ");
        jpaQl.append("  left outer join fetch configuracaoAviso.perfisAbrangencia perfil ");
        jpaQl.append("  left outer join fetch configuracaoAviso.usuariosAbrangencia usuario ");
        jpaQl.append(" where configuracaoAviso.flagTipoSituacao = ?1 ");
        jpaQl.append(" and ( ");
        
        // TODOS
        jpaQl.append(" configuracaoAviso.tipoAbrangenciaAviso.codigoAbrangenciaAviso = ?2 ");

        // PERFIS
        jpaQl.append(" OR (configuracaoAviso.tipoAbrangenciaAviso.codigoAbrangenciaAviso = ?3 ");
        jpaQl.append(" AND perfil.idPerfil = ?4) ");
        
        // USUARIOS ESPECIFICOS
        jpaQl.append(" OR (configuracaoAviso.tipoAbrangenciaAviso.codigoAbrangenciaAviso = ?5 ");
        jpaQl.append(" AND usuario.idUsuario = ?6) ");
        jpaQl.append(" ) ");
        
        return getPersistenceManager().listarPorJPQL(jpaQl, /* 1 */ ConstantesMovjud.FLAG_SITUACAO_ATIVA,
                                                            /* 2 */ AbrangenciaType.TODOS.getCodigo(),
                                                            /* 3 */ AbrangenciaType.PERFIL.getCodigo(),
                                                            /* 4 */ usuario.getPerfil().getIdPerfil(),
                                                            /* 5 */ AbrangenciaType.USUARIO_ESPECIFICO.getCodigo(),
                                                            /* 6 */ usuario.getIdUsuario());
    }
}
