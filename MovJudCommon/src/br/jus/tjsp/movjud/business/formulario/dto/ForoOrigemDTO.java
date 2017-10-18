package br.jus.tjsp.movjud.business.formulario.dto;

import br.jus.tjsp.movjud.business.base.dto.BaseDTO;

public class ForoOrigemDTO extends BaseDTO<Long>{
    
    private Long idFormularioForoOrigem;
    private Long idSecao;
    private Long idUnidade;
    private String nomeUnidade;
    private Long idForo;
    private String nomeForo;
    private String valorCampo;
    
    public ForoOrigemDTO() {
        super();
    }

    public void setIdSecao(Long idSecao) {
        this.idSecao = idSecao;
    }

    public Long getIdSecao() {
        return idSecao;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public void setIdFormularioForoOrigem(Long idFormularioForoOrigem) {
        this.idFormularioForoOrigem = idFormularioForoOrigem;
    }

    public Long getIdFormularioForoOrigem() {
        return idFormularioForoOrigem;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setIdForo(Long idForo) {
        this.idForo = idForo;
    }

    public Long getIdForo() {
        return idForo;
    }

    public void setNomeForo(String nomeForo) {
        this.nomeForo = nomeForo;
    }

    public String getNomeForo() {
        return nomeForo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    @Override
    public void setId(Long id) {
        setIdFormularioForoOrigem(id);
    }

    @Override
    public Long getId() {
        // TODO Implement this method
        return getIdFormularioForoOrigem();
    }
}
