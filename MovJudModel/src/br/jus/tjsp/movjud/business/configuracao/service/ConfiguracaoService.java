package br.jus.tjsp.movjud.business.configuracao.service;

import br.jus.tjsp.movjud.persistence.base.dao.Paginacao;

import br.jus.tjsp.movjud.persistence.entity.Aviso;
import br.jus.tjsp.movjud.persistence.entity.AvisoEstrutura;

import br.jus.tjsp.movjud.persistence.entity.ConfiguracaoAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAbrangenciaAviso;
import br.jus.tjsp.movjud.persistence.entity.TipoAviso;

import br.jus.tjsp.movjud.persistence.entity.TipoPeriodicidade;

import br.jus.tjsp.movjud.persistence.entity.Usuario;

import java.text.ParseException;

import java.util.List;

import javax.ejb.Local;

@Local
public interface ConfiguracaoService {
    
    List<ConfiguracaoAviso> listarConfiguracaoAvisoComFiltroPaginacao(ConfiguracaoAviso configuracaoAviso, Paginacao paginacao);

    ConfiguracaoAviso salvarConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso);

    void excluirConfiguracaoAviso(ConfiguracaoAviso configuracaoAviso);
    
    List<AvisoEstrutura> listarAvisoEstruturaComFiltroPaginacao(AvisoEstrutura avisoEstrutura, Paginacao paginacao);

    List<AvisoEstrutura> listarAvisoEstruturaOrdenadoPorNome();

    AvisoEstrutura salvarAvisoEstrutura(AvisoEstrutura avisoEstrutura);

    void excluirAvisoEstrutura(AvisoEstrutura avisoEstrutura);
    
    List<TipoAviso> listarTiposAviso();
    
    List<TipoPeriodicidade> listarTiposPeriodicidade();
    
    List<TipoAbrangenciaAviso> listarTiposAbrangenciaAviso();
    
    Aviso salvarAviso(Aviso aviso);
    
    List<Aviso> carregarAvisosUsuario(Usuario usuario, Paginacao paginacao) throws ParseException ;

    Long countAvisosNaoLidos(Usuario usuario);
}
