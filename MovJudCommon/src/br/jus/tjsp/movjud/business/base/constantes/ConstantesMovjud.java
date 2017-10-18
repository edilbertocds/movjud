package br.jus.tjsp.movjud.business.base.constantes;


//ConstantesMovjud.PERFIL_COD_ADMIN
public interface ConstantesMovjud {  
    String FLAG_SITUACAO_ATIVA 	= "A";
    String FLAG_SITUACAO_INATIVA = "I";
    String FLAG_SITUACAO_SIM 	= "S";
    String FLAG_SITUACAO_NAO = "N";
    
    //CÓDIGOS DE PERFIS DO USUÁRIO
    String PERFIL_COD_MAGISTRADO = "MAGISTRADO";
    String PERFIL_COD_ADMIN = "USUARADMIN";
    String PERFIL_COD_CONSULTA = "CONSULTA";
    String PERFIL_COD_DESEMBARGADOR = "DESEMBARGA";
    String PERFIL_COD_RESPONSAVEL = "RESPCARTO";
    String PERFIL_COD_FUNCIONARIO = "FUNCICARTO";
    String PERFIL_COD_MASSESCORR = "MASSESCORR";
    
    //Situação do metadado do formulário
    String SITUACAO_METADADO_EM_USO = "EM_USO"; 
    
    //VERIFICAR USO
    String PERFIL_CONS = "CONSULTA";
    String PERFIL_MASS = "MASSESCORR";
}

