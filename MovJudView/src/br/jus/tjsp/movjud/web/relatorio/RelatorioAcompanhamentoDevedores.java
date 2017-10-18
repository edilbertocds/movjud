package br.jus.tjsp.movjud.web.relatorio;

import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.persistence.entity.Unidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;

public class RelatorioAcompanhamentoDevedores extends RelatorioImpl {
    public RelatorioAcompanhamentoDevedores() {
        super();
    }

    public Map<String, Object> obterParametros(String mesReferencia, String comunicadoCgNumero, Date dataEnvio,
                                               Date dataLimite) {
        Map<String, Object> parametros = super.obterParametros();
        parametros.put("mesReferencia", mesReferencia);
        parametros.put("comunicadoCgNumero", comunicadoCgNumero);
        parametros.put("dataEnvio", dataEnvio);
        parametros.put("dataLimite", dataLimite);
        return parametros;
    }

    public JRDataSource obterDataSourceColecao(List<Unidade> listaEntidade) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        for (Unidade unidade : listaEntidade) {
            Map<String, Object> ad = new HashMap<String, Object>();
            ad.put("foroNome", unidade.getForo().getNomeForo());
            ad.put("unidadeNome", unidade.getNomeUnidade());
            ad.put("formulariosPendentes", unidade.getFormulariosPendentes());
            listMap.add(ad);
        }
        return listMapToDataSource(listMap);
    }
}
