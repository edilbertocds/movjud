package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.TipoSituacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class TipoSituacaoDAOImpl extends BaseDAOImpl<TipoSituacao> implements TipoSituacaoDAO {
    @SuppressWarnings("compatibility:-7747840001481191171")
    private static final long serialVersionUID = 1L;

    public TipoSituacaoDAOImpl() {
        super();
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(TipoSituacao filter) {
        List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
        if (filter.getCodigoSituacao() != null) {
            parametros.add(new ParametroOperacao("codigoSituacao", filter.getCodigoSituacao(), SQLOperatorType.Equal));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(TipoSituacao filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<TipoSituacao> getPersistentClass() {
        return TipoSituacao.class;
    }
}
