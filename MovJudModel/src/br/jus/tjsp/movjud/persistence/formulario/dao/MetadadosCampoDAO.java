package br.jus.tjsp.movjud.persistence.formulario.dao;

import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.persistence.base.dao.BaseDAO;
import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;
import br.jus.tjsp.movjud.persistence.entity.MetadadosCampo;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MetadadosCampoDAO extends BaseDAO<MetadadosCampo>{
    
    Long countUtilizacaoCampo(MetadadosCampo metadadosCampo);
    
    List<MetadadosCampo> recuperarCampoParaReaproveitamento(MetadadosCampo metadadosCampo, Paginacao paginacao, List<Long> listaCampos);
}
