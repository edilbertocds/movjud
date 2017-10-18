package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.business.formulario.dto.FormularioDTO;
import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.MetadadosFormulario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
@SuppressWarnings("oracle.jdeveloper.java.serialversionuid-field-missing")
public class MetadadosFormularioDAOImpl  extends BaseDAOImpl<MetadadosFormulario> implements MetadadosFormularioDAO{
    public MetadadosFormularioDAOImpl() {
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(MetadadosFormulario filter) {
        List<ParametroOperacao>parametros = new ArrayList<ParametroOperacao>();
        
        parametros.add(new ParametroOperacao("descricaoSourceFormulario", filter.getDescricaoSourceFormulario(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("descricaoNome", filter.getDescricaoNome(), SQLOperatorType.LikeFullNoCase));
        parametros.add(new ParametroOperacao("numeroInstancia", filter.getNumeroInstancia(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("dataInclusao", filter.getDataFiltroCriacao(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("numeroVersao", filter.getNumeroVersao(), SQLOperatorType.Equal));
        parametros.add(new ParametroOperacao("flagTipoSituacao", filter.getFlagTipoSituacao(), SQLOperatorType.EqualNoCase));
       
        if(filter.getMetadadosTipoSituacao() != null){
            parametros.add(new ParametroOperacao("metadadosTipoSituacao.idMetadadosTipoSituacao", filter.getMetadadosTipoSituacao().getIdMetadadosTipoSituacao(), SQLOperatorType.Equal));
	    parametros.add(new ParametroOperacao("metadadosTipoSituacao.descricaoSituacao", filter.getMetadadosTipoSituacao().getDescricaoSituacao(), SQLOperatorType.EqualNoCase));
            parametros.add(new ParametroOperacao("metadadosTipoSituacao.tipoSituacao", filter.getMetadadosTipoSituacao().getTipoSituacao(), SQLOperatorType.EqualNoCase));
        }
        return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(MetadadosFormulario filter) {
        // TODO Implement this method
        return Collections.emptyList();
    }

    @Override
    public Class<MetadadosFormulario> getPersistentClass() {
        return MetadadosFormulario.class;
    }

    @Override
    public List<MetadadosFormulario> listarMetadadosFormularioOrdenadoPorDescricaoSource(MetadadosFormulario filter,
                                                                        Paginacao paginacao) {
        return listarComFiltroOrdenacao(filter, new String[] {"descricaoSourceFormulario", "numeroVersao"}, 
                                                new Boolean[] { true, false }, paginacao);
    }

    @Override
    public List<MetadadosFormulario> listarMetadadosFormularioOrdenadoPorDescricaoNome(MetadadosFormulario filter) {
	return listarComFiltroOrdenacao(filter, "descricaoNome", true);
    }

    @Override
    public Long countTotalMetadadosFormulariosEmUso() {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select count(MetadadosFormulario) from MetadadosFormulario metadadosFormulario where ");
        jpaQl.append(" metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO'");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl);
    }

    @Override
    public MetadadosFormulario recuperarMetadadosFormularioEmUso(MetadadosFormulario metadadosFormulario) {
        StringBuilder jpaQl = new StringBuilder();
        jpaQl.append("select metadadosFormulario from MetadadosFormulario metadadosFormulario where");
        jpaQl.append(" metadadosFormulario.metadadosTipoSituacao.tipoSituacao = 'EM_USO' and");
        jpaQl.append(" UPPER(metadadosFormulario.descricaoSourceFormulario) = UPPER(?1)");
        return getPersistenceManager().procurarPorJPQLSingleResult(jpaQl, metadadosFormulario.getDescricaoSourceFormulario());
    }
}
