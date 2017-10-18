package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.PreCarga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
@Stateless
public class PreCargaDAOImpl extends BaseDAOImpl<PreCarga> implements PreCargaDAO{
    public PreCargaDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(PreCarga filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        if (filter.getUnidade() != null) {
            parametros.add(new ParametroOperacao("unidade.idUnidade", filter.getUnidade().getIdUnidade(),
                                                 SQLOperatorType.Equal));
        }
        if (filter.getUsuario() != null) {
            parametros.add(new ParametroOperacao("usuario.idUsuario", filter.getUsuario().getIdUsuario(),
                                                 SQLOperatorType.Equal));
        }
        parametros.add(new ParametroOperacao("ano",
                                             filter.getAno(),
                                             SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("mes",
                                             filter.getMes(),
                                             SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("descricaoSrcFormulario",
                                             filter.getDescricaoSrcFormulario(),
                                             SQLOperatorType.EqualNoCase));
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(PreCarga filter) {
        return Collections.emptyList();
    }

    @Override
    public Class<PreCarga> getPersistentClass() {
        return PreCarga.class;
    }
}
