package br.jus.tjsp.movjud.web.commons.bean;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "constantesBean")
@ApplicationScoped
public class ConstantesBean {
    
    public ConstantesBean() {
    
    }

    public String getFLAG_SITUACAO_ATIVA() {
	return ConstantesMovjud.FLAG_SITUACAO_ATIVA;
    }
    
    public String getFLAG_SITUACAO_INATIVA() {
	return ConstantesMovjud.FLAG_SITUACAO_INATIVA;
    }
    
    public String getFLAG_SITUACAO_SIM() {
	return ConstantesMovjud.FLAG_SITUACAO_SIM;
    }
    
    public String getFLAG_SITUACAO_NAO() {
	return ConstantesMovjud.FLAG_SITUACAO_NAO;
    }
    
    public String getPERFIL_COD_MAGISTRADO() {
	return ConstantesMovjud.PERFIL_COD_MAGISTRADO;
    }

}
