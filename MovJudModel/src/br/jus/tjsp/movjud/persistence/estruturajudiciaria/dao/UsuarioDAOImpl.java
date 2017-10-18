package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Perfil;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    public Class<Usuario> getPersistentClass() {
        return Usuario.class;
    }


    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Usuario filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        parametros.add(new ParametroOperacao("nome", filter.getNome(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("codigoUsuario", filter.getCodigoUsuario(),
                                             SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(),
                                             SQLOperatorType.EqualNoCase));
        if (filter.getPerfil() != null) {
            parametros.add(new ParametroOperacao("perfil.idPerfil", filter.getPerfil().getIdPerfil(),
                                                 SQLOperatorType.Equal));
            parametros.add(new ParametroOperacao("perfil.codigoPerfil", filter.getPerfil().getCodigoPerfil(),
                                                 SQLOperatorType.EqualNoCase));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Usuario filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

        parametros.add(new ParametroOperacao("codigoUsuario", filter.getCodigoUsuario(), SQLOperatorType.EqualNoCase));

        return parametros;
    }

    @Override
    public List<Usuario> listarUsuarioOrdenadoPorNomeComPaginacao(Usuario filter, Paginacao paginacao) {
        return listarComFiltroOrdenacao(filter, "nome", true, paginacao);
    }

    @Override
    public List<Usuario> listarUsuarioMagistradoPorNomeComPaginacao(Usuario filter, Paginacao paginacao) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("from Usuario entidade " + " where UPPER(entidade.flagTipoSituacao) = 'A'" +
                     " and UPPER(entidade.nome) like TRIM(UPPER(?1)) " +
                     " and entidade.perfil.codigoPerfil in ('MAGISTRADO','DESEMBARGA', 'MASSESCORR')");

        return getPersistenceManager().listarPorJPQL(jpaQl, paginacao, "%" + filter.getNome() + "%").getLista();
    }

    @Override
    public List<Usuario> listarUsuariosPorPerfilPermissaoTemporaria(Usuario usuario, Perfil perfil,
                                                                    Paginacao paginacao) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("from Usuario entidade " + " where UPPER(entidade.flagTipoSituacao) = 'A'" +
                     " and UPPER(entidade.nome) LIKE ");
        jpaQl.append("'%" + usuario.getNome().toUpperCase() + "%'");
        if (ConstantesMovjud.PERFIL_COD_RESPONSAVEL.equals(perfil.getCodigoPerfil())) {
            jpaQl.append(" and entidade.perfil.codigoPerfil in ('FUNCICARTO')");
        } else if (ConstantesMovjud.PERFIL_COD_ADMIN.equals(perfil.getCodigoPerfil())) {
            jpaQl.append(" and entidade.perfil.codigoPerfil in ('FUNCICARTO','RESPCARTO')");
        } else {
            return new ArrayList<Usuario>();
        }

        return getPersistenceManager().listarPorJPQL(jpaQl, paginacao).getLista();
    }


}

