package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.FormularioVinculacao;

import br.jus.tjsp.movjud.persistence.entity.Unidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class FormularioVinculacaoDAOImpl extends BaseDAOImpl<FormularioVinculacao> implements FormularioVinculacaoDAO {
    public FormularioVinculacaoDAOImpl() {
    }


    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(FormularioVinculacao filter) {
	List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();
	if (filter.getUnidade() != null) {
	    parametros.add(new ParametroOperacao("unidade.idUnidade", filter.getUnidade().getIdUnidade(), SQLOperatorType.LikeFullNoCase));
	}
        if( filter.getMetadadosFormulario() != null){
            parametros.add(new ParametroOperacao("metadadosFormulario.idMetadadosFormulario", filter.getMetadadosFormulario().getIdMetadadosFormulario(), SQLOperatorType.Equal));
        }
	return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(FormularioVinculacao filter) {
	return Collections.emptyList();
    }

    @Override
    public Class<FormularioVinculacao> getPersistentClass() {
	return FormularioVinculacao.class;
    }

    @Override
    public Long countTotalFormulariosVinculados() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(FormularioVinculacao) from FormularioVinculacao formularioVinculacao where");
        jpaQl.append(" formularioVinculacao.metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO'");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl);
    }

    @Override
    public Long countTotalUnidadesVinculadas() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(DISTINCT(formularioVinculacao.unidade)) from FormularioVinculacao formularioVinculacao where");
        jpaQl.append(" formularioVinculacao.metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO'");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl);
    }
}
