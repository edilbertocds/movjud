package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class LiberacaoFormularioDTO extends BaseDTO<String>{
    
    private Long numeroMetadadosFormularioEmUso;
    private Long numeroUnidadesVinculadas;
    private Long numeroUnidadesXFormularios;
    private Long numeroTotalFormulariosVinculados;
    private Long numeroFormulariosAnoMesReferencia;
    
    public LiberacaoFormularioDTO() {
        super();
    }

    @Override
    public void setId(String id) {
        // TODO Implement this method
    }

    @Override
    public String getId() {
        // TODO Implement this method
        return null;
    }

    public void setNumeroMetadadosFormularioEmUso(Long numeroMetadadosFormularioEmUso) {
        this.numeroMetadadosFormularioEmUso = numeroMetadadosFormularioEmUso;
    }

    public Long getNumeroMetadadosFormularioEmUso() {
        if(numeroMetadadosFormularioEmUso==null){
            numeroMetadadosFormularioEmUso=0L;
        }
        return numeroMetadadosFormularioEmUso;
    }

    public void setNumeroUnidadesVinculadas(Long numeroUnidadesVinculadas) {
        this.numeroUnidadesVinculadas = numeroUnidadesVinculadas;
    }

    public Long getNumeroUnidadesVinculadas() {
        if(numeroUnidadesVinculadas==null){
            numeroUnidadesVinculadas = 0L;
        }
        return numeroUnidadesVinculadas;
    }

    public void setNumeroUnidadesXFormularios(Long numeroUnidadesXFormularios) {
        this.numeroUnidadesXFormularios = numeroUnidadesXFormularios;
    }

    public Long getNumeroUnidadesXFormularios() {
        if(numeroUnidadesXFormularios==null){
            numeroUnidadesXFormularios = 0L;
        }
        return numeroUnidadesXFormularios;
    }

    public void setNumeroTotalFormulariosVinculados(Long numeroTotalFormulariosVinculados) {
        this.numeroTotalFormulariosVinculados = numeroTotalFormulariosVinculados;
    }

    public Long getNumeroTotalFormulariosVinculados() {
        if(numeroTotalFormulariosVinculados==null){
            numeroTotalFormulariosVinculados = 0L;
        }
        return numeroTotalFormulariosVinculados;
    }

    public void setNumeroFormulariosAnoMesReferencia(Long numeroFormulariosAnoMesReferencia) {
        this.numeroFormulariosAnoMesReferencia = numeroFormulariosAnoMesReferencia;
    }

    public Long getNumeroFormulariosAnoMesReferencia() {
        if(numeroFormulariosAnoMesReferencia==null){
            numeroFormulariosAnoMesReferencia = 0L;
        }
        return numeroFormulariosAnoMesReferencia;
    }
}
