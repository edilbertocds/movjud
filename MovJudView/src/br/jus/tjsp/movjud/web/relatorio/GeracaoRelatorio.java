package br.jus.tjsp.movjud.web.relatorio;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

public class GeracaoRelatorio {
    
    private static String SUBREPORT_DIR = "SUBREPORT_DIR";
    
    public GeracaoRelatorio() {
	super();
    }

  
    public void processarRelatorio(ExtensaoRelatorio extensaoRelatorio, Template template, OutputStream outputStream, FacesContext facesContext, Map<String, Object> parametros,
				   JRDataSource dataSource) throws JRException, IOException {
	ServletContext contexto = (ServletContext) facesContext.getExternalContext().getContext();
	String relatorioPath = contexto.getRealPath("/") + File.separator + template.getDiretorioRelatorio() + File.separator;
        String arquivoPath = relatorioPath + template.getNomeRelatorioCompilado();
	JRAbstractExporter jrExporter = null;
        
        dataSource = dataSource == null ? new JREmptyDataSource() : dataSource;
        
        parametros = parametros == null ? new HashMap<String, Object>() : parametros;
        parametros.put("SUBREPORT_DIR", relatorioPath);
     
	switch (extensaoRelatorio) {

	    case EXTENSAO_RTF:
	    case EXTENSAO_DOC:
		jrExporter = new JRRtfExporter();
		break;
	    case EXTENSAO_PDF:
		jrExporter = new JRPdfExporter();
		break;

	}
        parametros.put(SUBREPORT_DIR, relatorioPath);
	JasperPrint jasperPrint = JasperFillManager.fillReport(arquivoPath, parametros, dataSource);
	jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
	jrExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        
	jrExporter.exportReport();
	outputStream.flush();

    }

}
