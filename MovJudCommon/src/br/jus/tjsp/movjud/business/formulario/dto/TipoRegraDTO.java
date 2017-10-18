package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;


public class TipoRegraDTO extends BaseDTO<Long>{
    
    private Long codigoTipoRegra;
    private String nomeTipoRegra;
    private String descricaoTipoRegra;
    private boolean inverterRegra;
    
    public TipoRegraDTO() {
        super();
    }

    @Override
    public void setId(Long id) {
        setCodigoTipoRegra(id);
    }

    @Override
    public Long getId() {
        return getCodigoTipoRegra();
    }

    public void setCodigoTipoRegra(Long codigoTipoRegra) {
        this.codigoTipoRegra = codigoTipoRegra;
    }

    public Long getCodigoTipoRegra() {
        return codigoTipoRegra;
    }

    public void setNomeTipoRegra(String nomeTipoRegra) {
        this.nomeTipoRegra = nomeTipoRegra;
    }

    public String getNomeTipoRegra() {
        return nomeTipoRegra;
    }

    public void setDescricaoTipoRegra(String descricaoTipoRegra) {
        this.descricaoTipoRegra = descricaoTipoRegra;
    }

    public String getDescricaoTipoRegra() {
        return descricaoTipoRegra;
    }

    public void setInverterRegra(boolean inverterRegra) {
        this.inverterRegra = inverterRegra;
    }

    public boolean isInverterRegra() {
        return inverterRegra;
    }
}
