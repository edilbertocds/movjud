package br.jus.tjsp.movjud.business.cache;

import java.util.Map;
import java.util.HashMap;

import br.jus.tjsp.movjud.business.formulario.dto.*;

import java.util.List;

/**
 * Classe para tratamento de dados em cache e diminuicao de idas ao database
 */
public final class FormularioCache {
    private static Map<Long, List<SecaoDTO>> cacheMetaDadosSecoes = new HashMap<Long, List<SecaoDTO>>();
    private static Map<Long, List<HistoricoFormularioDTO>> cacheHistoricoAlteracoes = new HashMap<Long, List<HistoricoFormularioDTO>>();
    
    public static List<SecaoDTO> obtemSecaoMetaDadosEmCache(Long idFormMetaDados) {
        return cacheMetaDadosSecoes.get(idFormMetaDados);
    }
    
    public static void adicionaSecaoMetaDadosEmCache(Long idFormMetaDados, List<SecaoDTO> secoes) {
        if (!cacheMetaDadosSecoes.containsKey(idFormMetaDados))
            cacheMetaDadosSecoes.put(idFormMetaDados, secoes);
    }

    public static List<HistoricoFormularioDTO> obtemHistoricoAlteracoesEmCache(Long idForm) {
        return cacheHistoricoAlteracoes.get(idForm);
    }
    
    public static void adicionaHistoricoAlteracoesEmCache(Long idForm, List<HistoricoFormularioDTO> historicos) {
        if (!cacheHistoricoAlteracoes.containsKey(idForm))
            cacheHistoricoAlteracoes.put(idForm, historicos);
    }
}