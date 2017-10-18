package br.jus.tjsp.movjud.web.relatorio;

public enum ExtensaoRelatorio {
    EXTENSAO_DOC("Documento .doc", ".doc", "application/msword"),
    EXTENSAO_PDF("Documento .pdf", ".pdf", "application/pdf"),
    EXTENSAO_RTF("Documento .rtf", ".rtf", "application/rtf");

    private String descricaoArquivo;
    private String extensaoArquivo;
    private String contentType;

    ExtensaoRelatorio(String descricaoArquivo, String extensaoArquivo, String contentType) {
        this.descricaoArquivo = descricaoArquivo;
        this.extensaoArquivo = extensaoArquivo;
        this.contentType = contentType;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public String getExtensaoArquivo() {
        return extensaoArquivo;
    }

    public String getContentType() {
        return contentType;
    }

    public static ExtensaoRelatorio getExtensaoRelatorio(String strExtensao) {
        ExtensaoRelatorio extensaoRelatorio = ExtensaoRelatorio.EXTENSAO_DOC;

        if (strExtensao != null && !strExtensao.equals("")) {
            if (strExtensao.indexOf(".") < 0) {
                strExtensao = "." + strExtensao;
            }
            for (ExtensaoRelatorio er : ExtensaoRelatorio.values()) {
                if (er.getExtensaoArquivo() != null && er.getExtensaoArquivo().equals(strExtensao)) {
                    extensaoRelatorio = er;
                    break;
                }
            }
        }
        return extensaoRelatorio;
    }
}
