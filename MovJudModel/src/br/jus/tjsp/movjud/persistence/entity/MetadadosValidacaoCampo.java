package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.formulario.dto.FormulaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.ValidacaoDTO;
import br.jus.tjsp.movjud.business.formulario.types.TipoValidacaoType;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_VALIDACAO_CAMPO")
public class MetadadosValidacaoCampo extends BaseEntity<Long> implements Serializable {
    private static final long serialVersionUID = -5557277440381602858L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO", nullable = false)
    private Date dataAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @Column(name = "DS_FORMULA", nullable = false, length = 4000)
    private String descricaoFormula;

    @Column(name = "DS_MENSAGEM", length = 2000)
    private String descricaoMensagem;

    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    @Column(name = "TP_VALIDACAO", nullable = false, length = 50)
    private String flagTipoValidacao;

    @Id
    @Column(name = "ID_MD_VALIDACAO_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_VALIDACAO_CAMPO")
    @SequenceGenerator(name = "SEQ_MD_VALIDACAO_CAMPO", sequenceName = "SEQ_MD_VALIDACAO_CAMPO", allocationSize = 1)
    private Long idMetadadosValidacaoCampo;

    @ManyToOne
    @JoinColumn(name = "FK_MD_CAMPO", nullable = false)
    private MetadadosCampo metadadosCampo;

    public MetadadosValidacaoCampo() {
    }

    public MetadadosValidacaoCampo(Date dataAtualizacao, Date dataInclusao, String descricaoFormula,
                                   String descricaoMensagem, String flagTipoSituacao, String flagTipoValidacao,
                                   Long idMetadadosValidacaoCampo, MetadadosCampo metadadosCampo) {
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.descricaoFormula = descricaoFormula;
        this.descricaoMensagem = descricaoMensagem;
        this.flagTipoSituacao = flagTipoSituacao;
        this.flagTipoValidacao = flagTipoValidacao;
        this.idMetadadosValidacaoCampo = idMetadadosValidacaoCampo;
        this.metadadosCampo = metadadosCampo;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getDescricaoFormula() {
        return descricaoFormula;
    }

    public void setDescricaoFormula(String descricaoFormula) {
        this.descricaoFormula = descricaoFormula;
    }

    public String getDescricaoMensagem() {
        return descricaoMensagem;
    }

    public void setDescricaoMensagem(String descricaoMensagem) {
        this.descricaoMensagem = descricaoMensagem;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoValidacao() {
        return flagTipoValidacao;
    }

    public void setFlagTipoValidacao(String flagTipoValidacao) {
        this.flagTipoValidacao = flagTipoValidacao;
    }

    public Long getIdMetadadosValidacaoCampo() {
        return idMetadadosValidacaoCampo;
    }

    public void setIdMetadadosValidacaoCampo(Long idMetadadosValidacaoCampo) {
        this.idMetadadosValidacaoCampo = idMetadadosValidacaoCampo;
    }

    public MetadadosCampo getMetadadosCampo() {
        return metadadosCampo;
    }

    public void setMetadadosCampo(MetadadosCampo metadadosCampo) {
        this.metadadosCampo = metadadosCampo;
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosValidacaoCampo(id);
    }

    @Override
    public Long getId() {
        return getIdMetadadosValidacaoCampo();
    }
    
    public ValidacaoDTO createValidacaoDTO(){
        
        ValidacaoDTO result = new ValidacaoDTO();
        result.setCodigoValidacao(this.getIdMetadadosValidacaoCampo());
        result.setMensagem(this.getDescricaoMensagem());
        result.setSituacao(this.getFlagTipoSituacao());
        result.setTipoValidacao(TipoValidacaoType.getTipoValidacaoByCodigo(this.getFlagTipoValidacao()));
        result.setFormula(new FormulaDTO(this.getDescricaoFormula()));
        result.setDataInclusao(this.getDataInclusao());
        result.setSituacao(this.getFlagTipoSituacao());
        return result;  
    }
}
