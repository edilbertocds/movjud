package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class TipoMateriaDTO extends BaseDTO<Long> implements Comparable<TipoMateriaDTO>{
    
    private Long codigoTipoMateria;
    private String nomeTipoMateria;


    public void setCodigoTipoMateria(Long codigoTipoMateria) {
        this.codigoTipoMateria = codigoTipoMateria;
    }

    public Long getCodigoTipoMateria() {
        return codigoTipoMateria;
    }

    public void setNomeTipoMateria(String nomeTipoMateria) {
        this.nomeTipoMateria = nomeTipoMateria;
    }

    public String getNomeTipoMateria() {
        return nomeTipoMateria;
    }

    public TipoMateriaDTO() {
        super();
    }

    @Override
    public void setId(Long id) {
        setCodigoTipoMateria(id);
    }

    @Override
    public Long getId() {
        return getCodigoTipoMateria();
    }

    @Override
    public int compareTo(TipoMateriaDTO o) {
        return nomeTipoMateria.compareTo(o.nomeTipoMateria);
    }
}
