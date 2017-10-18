package br.jus.tjsp.movjud.web.relatorio;

public enum Template {
    ACOMPANHAMENTO_DEVEDORES("relatorios", "AcompanhamentoDevedores","Acompanhamento_Devedores"),
    FORMULARIO("relatorios", "Formulario","Formulario");
    private String diretorioRelatorio;
    private String nomeRelatorio;
    private String nomeArquivoDownload;
    
    public final String EXTENSAO_JRXML  = ".jrxml";
    public final String EXTENSAO_JASPER = ".jasper";
    
    Template(String diretorioRelatorio, String nomeRelatorio, String nomeArquivoDownload){
      this.diretorioRelatorio=diretorioRelatorio;
      this.nomeRelatorio=nomeRelatorio;
      this.nomeArquivoDownload=nomeArquivoDownload;
    }

    public static Template getACOMPANHAMENTO_DEVEDORES() {
        return ACOMPANHAMENTO_DEVEDORES;
    }
    
    public static Template getFORMULARIO() {
        return FORMULARIO;
    }

    public void setDiretorioRelatorio(String diretorioRelatorio) {
        this.diretorioRelatorio = diretorioRelatorio;
    }

    public String getDiretorioRelatorio() {
        return diretorioRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeArquivoDownload(String nomeArquivoDownload) {
        this.nomeArquivoDownload = nomeArquivoDownload;
    }

    public String getNomeArquivoDownload() {
        return nomeArquivoDownload;
    }
    public String getNomeRelatorioFonte() {
        return getNomeRelatorio() + EXTENSAO_JRXML;
    }
    
    public String getNomeRelatorioCompilado() {
        return getNomeRelatorio() + EXTENSAO_JASPER;
    }

}
