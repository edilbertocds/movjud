package br.jus.tjsp.movjud.persistence.estruturajudiciaria.dao;


import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.Acao;
import br.jus.tjsp.movjud.persistence.entity.Perfil;

import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.Query;


@Stateless
public class PerfilDAOImpl extends BaseDAOImpl<Perfil> implements PerfilDAO {

    public Class<Perfil> getPersistentClass() {
        return Perfil.class;
    }

    @Override
    public List<Perfil> listarPerfilOrdenadoPorNome() {
        return listarComOrdenacao("nomePerfil", true);
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(Perfil filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("codigoPerfil", filter.getCodigoPerfil(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("nomePerfil", filter.getNomePerfil(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.LikeFullNoCase));
                    
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(Perfil filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }
}

