package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;


@Stateless
public class AcaoDAOImpl extends BaseDAOImpl<Acao> implements AcaoDAO {

    public Class<Acao> getPersistentClass() {
        return Acao.class;
    }
      
    @Override
    public List<Acao> listarAcaoOrdenadoPorNome() {
        return listarComOrdenacao("nomeAcao", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Acao filter) {
        return Collections.emptyList();
    }

    @Override
    public List<Acao> listarAcaoPermissoesUsuario(Usuario usuario) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select usuAcao.acao from UsuarioAcao usuAcao" +
                     " where UPPER(usuAcao.flagPermitido) = 'S'" +
                     " and usuAcao.usuario.idUsuario = ?1" +
                     " union" +
                     " select acao from Usuario usuario" +
                     " inner join usuario.perfil.acoes acao " +
                     " where usuario.idUsuario = ?1 " +
                      "and acao.idAcao not in(select usuAcao.acao.idAcao from UsuarioAcao usuAcao" +
                                             " where UPPER(usuAcao.flagPermitido) = 'N'" +
                                             " and usuAcao.usuario.idUsuario = ?1)");
        
        return getPersistenceManager().listarPorJPQL(jpaQl, usuario.getIdUsuario());
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Acao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }
}

