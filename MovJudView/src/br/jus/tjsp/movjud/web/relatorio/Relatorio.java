package br.jus.tjsp.movjud.web.relatorio;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

public interface Relatorio {
    Map<String, Object> obterParametros() throws Exception;
    JRDataSource obterDataSourceColecao() throws Exception;
}
