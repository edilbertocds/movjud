package br.jus.tjsp.movjud.web.relatorio;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

public abstract class RelatorioImpl implements Relatorio{
    private Map<String, Object> parametros;
    public RelatorioImpl() {
        super();
    }
    
    private void inicializarParametros() {
        parametros = new HashMap<String, Object>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "br"));
    }
    
    public Map<String, Object> obterParametros() {
      if(getParametros() == null) {
        inicializarParametros();
      }
        
      return getParametros();
    }

    public JRDataSource obterDataSourceColecao() {
        return listMapToDataSource(null);
    }

    public JRDataSource listMapToDataSource(List<Map<String, Object>> listaMap) {
        JRDataSource jrDataSource = new JREmptyDataSource();

        if (listaMap != null && !listaMap.isEmpty()) {
            jrDataSource = new JRBeanArrayDataSource(listaMap.toArray());
        }
        return jrDataSource;
    }

    public JRDataSource listToDataSource(List<?> lista) {
        JRDataSource jrDataSource = new JREmptyDataSource();

        if (lista != null && !lista.isEmpty()) {
            jrDataSource = new JRBeanArrayDataSource(lista.toArray(), false);
        }
        return jrDataSource;
    }
    public Map<String, Object> getParametros() {
        return parametros;
    }
    
}
