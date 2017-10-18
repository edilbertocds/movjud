package br.jus.tjsp.movjud.persistence.processogabinete.dao;

import br.jus.tjsp.movjud.persistence.base.common.ParametroOperacao;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAOImpl;
import br.jus.tjsp.movjud.persistence.base.types.SQLOperatorType;
import br.jus.tjsp.movjud.persistence.entity.ProcessoGabinete;
import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class ProcessoGabineteDAOImpl extends BaseDAOImpl<ProcessoGabinete> implements ProcessoGabineteDAO {

    @Override
    public Class<ProcessoGabinete> getPersistentClass() {
	return ProcessoGabinete.class;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroByEntity(ProcessoGabinete filter) {
	List<ParametroOperacao> parametros = new ArrayList<ParametroOperacao>();

	if (filter.getUsuarioMagistrado() != null) {
	    parametros.add(new ParametroOperacao("usuarioMagistrado.idUsuario", filter.getUsuarioMagistrado().getIdUsuario(), SQLOperatorType.Equal));
	}
	return parametros;
    }

    @Override
    public List<ParametroOperacao> parametrosFiltroSingleByEntity(ProcessoGabinete filter) {
	return Collections.emptyList();
    }

    @Override
    public List<ProcessoGabinete> listarProcessosGabineteDoMagistrado(Usuario usuario) {
	ProcessoGabinete processoGabinete = new ProcessoGabinete();
	processoGabinete.setUsuarioMagistrado(usuario);

	String[] campoOrdenacao =  {"anoProcessoGabinete", "numeroProcessoGabinete"};
	Boolean[] ascOrdenacao = {false, false};
	
	return listarComFiltroOrdenacao(processoGabinete, campoOrdenacao, ascOrdenacao);

    }
}
