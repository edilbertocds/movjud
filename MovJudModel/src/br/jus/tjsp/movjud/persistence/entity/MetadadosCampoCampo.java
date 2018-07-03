package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormulaDTO;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_CAMPO_CAMPO")
public class MetadadosCampoCampo extends BaseEntity<Long> {
    private static final long serialVersionUID = -7009975482949414652L;

    @Id
    @Column(name = "ID_CAMPO_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_CAMPO_CAMPO")     
    @SequenceGenerator(name = "SEQ_MD_CAMPO_CAMPO", sequenceName = "SEQ_MD_CAMPO_CAMPO", allocationSize = 1)    
    private Long idCampoCampo;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } , fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAMPO_FILHO", nullable = false)
    private MetadadosCampo metadadosCampoFilho;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } , fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CAMPO_PAI", nullable = false)     
    private MetadadosCampo metadadosCampoPai;
    
    @Column(name = "NR_ORDEM", nullable = false)
    private Long numeroOrdem;
    
    @Column(name = "CD_SIGLA", length = 25)
    private String codigoSigla;

    @Column(name = "DS_SEQUENCIA", length = 25)
    private String descricaoSequencia;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Column(name = "DS_FORMULA", length = 1000)
    private String descricaoFormula;

    public MetadadosCampoCampo() {
    }

    public MetadadosCampoCampo(Date dataAtualizacao, Date dataInclusao, String flagTipoSituacao, Long idCampoCampo,
                               MetadadosCampo metadadosCampoFilho, MetadadosCampo metadadosCampoPai, Long numeroOrdem,
                               String codigoSigla, String descricaoSequencia) {
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.flagTipoSituacao = flagTipoSituacao;
        this.idCampoCampo = idCampoCampo;
        this.metadadosCampoFilho = metadadosCampoFilho;
        this.metadadosCampoPai = metadadosCampoPai;
        this.numeroOrdem = numeroOrdem;
        this.codigoSigla = codigoSigla;
        this.descricaoSequencia = descricaoSequencia;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Metadados Campo Campo = ");
        sb.append(idCampoCampo);
        sb.append("\n");
        
        if (metadadosCampoPai != null) {
            sb.append("Campo Pai = ");
            sb.append(metadadosCampoPai.getNomeCampo());
            sb.append("\n");
        }
        
        if (metadadosCampoFilho != null) {
            sb.append("Campo Filho = ");
            sb.append(metadadosCampoFilho.getNomeCampo());
            sb.append("\n");
        }
        
        sb.append("Número Ordem = ");
        sb.append(numeroOrdem);
        sb.append("\n");
        
        
        sb.append("Código Sigla = ");
        sb.append(codigoSigla);
        sb.append("\n");
        
           
        sb.append("Descrição Sequência = ");
        sb.append(descricaoSequencia);
        sb.append("\n"); 
        
        sb.append("Descricao Formula = ");
        sb.append(descricaoFormula);
        sb.append("\n");
        
        if(dataAtualizacao!=null){
            sb.append("Data Atualização = ");
            sb.append(dataAtualizacao);
            sb.append("\n");
        }
        
        if(dataInclusao!=null){
            sb.append("Data inclusão = ");
            sb.append(dataInclusao);
            sb.append("\n");
        }
            
        sb.append("Flag Tipo Situação = ");
        sb.append(flagTipoSituacao);
        sb.append("\n");
        
        
        return sb.toString();
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
        if(dataInclusao == null){
            dataInclusao = new Date();
        }
        this.dataInclusao = dataInclusao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public Long getIdCampoCampo() {
        return idCampoCampo;
    }

    public void setIdCampoCampo(Long idCampoCampo) {
        this.idCampoCampo = idCampoCampo;
    }

    public MetadadosCampo getMetadadosCampoFilho() {
        return metadadosCampoFilho;
    }

    public void setMetadadosCampoFilho(MetadadosCampo metadadosCampoFilho) {
        this.metadadosCampoFilho = metadadosCampoFilho;
    }

    public MetadadosCampo getMetadadosCampoPai() {
        return metadadosCampoPai;
    }

    public void setMetadadosCampoPai(MetadadosCampo metadadosCampoPai) {
        this.metadadosCampoPai = metadadosCampoPai;
    }

    public Long getNumeroOrdem() {
        return numeroOrdem;
    }

    public void setNumeroOrdem(Long numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }
    
    public void setCodigoSigla(String codigoSigla) {
        this.codigoSigla = codigoSigla;
    }

    public String getCodigoSigla() {
        return codigoSigla;
    }

    public void setDescricaoSequencia(String descricaoSequencia) {
        this.descricaoSequencia = descricaoSequencia;
    }

    public String getDescricaoSequencia() {
        return descricaoSequencia;
    }

    public void setDescricaoFormula(String descricaoFormula) {
        this.descricaoFormula = descricaoFormula;
    }

    public String getDescricaoFormula() {
        return descricaoFormula;
    }

    @Override
    public void setId(Long id) {
        setIdCampoCampo(id);
    }

    @Override
    public Long getId() {
        return getIdCampoCampo();
    }
    
    public CampoDTO createCampoDTO(CampoDTO campoPaiDTO){
        
        CampoDTO result = new CampoDTO();
        result.setIdMetadadosCampoCampo(this.getIdCampoCampo());
        result.setIdMetadadosCampo(this.getMetadadosCampoFilho().getIdMetadadosCampo());
        result.setDataInclusao(this.getDataInclusao());
        result.setSituacao(this.getFlagTipoSituacao());
        result.setHint(this.getMetadadosCampoFilho().getDescricaoTextoInformativo());
        result.setLabelCampo(this.getMetadadosCampoFilho().getNomeCampo());
        result.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(this.getMetadadosCampoFilho().getTipoCampo()));
        result.setRequerido(true);
        result.setCasasDecimais(this.getMetadadosCampoFilho().getNumeroCasasDecimais());
        result.setCodigoBI(this.getMetadadosCampoFilho().getCodigoDominioBI());
        result.setCodigoCampo(this.getCodigoSigla());
        if (this.getDescricaoFormula() != null && !this.getDescricaoFormula().isEmpty()){
            result.setFormula(new FormulaDTO(this.getDescricaoFormula()));
        }
        if(this.getMetadadosCampoFilho().getMetadadosTipoRegra() != null){
            result.setTipoRegraDTO(this.getMetadadosCampoFilho().getMetadadosTipoRegra().createTipoRegraDTO());
        }
        if (result.getTipoRegraDTO() != null){
            result.getTipoRegraDTO().setInverterRegra((this.getMetadadosCampoFilho().getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                       true : false));
        }
        result.setIndiceCampo(this.getDescricaoSequencia());
        
        
        if (this.getMetadadosCampoFilho().getMetadadosListaSelecao()!=null){
            int size = this.getMetadadosCampoFilho().getMetadadosListaSelecao().size();
            
            for(int j = 0; j < size ; j++){
                result.getListaItensSelecaoDTO().add(this.getMetadadosCampoFilho().getMetadadosListaSelecao().get(j).createItemSelecaoDTO());
            }
        }  
        
        if (this.getMetadadosCampoFilho().getMetadadosValidacaoCampo()!=null){
            int size = this.getMetadadosCampoFilho().getMetadadosValidacaoCampo().size();
            
            for(int j = 0; j < size ; j++){
                result.getListaValidacoes().add(this.getMetadadosCampoFilho().getMetadadosValidacaoCampo().get(j).createValidacaoDTO());
            }
        }  
        result.setNumeroMaximoCaracteres(this.getMetadadosCampoFilho().getNumeroMaximoCaracteres());
        result.setNumeroMinimoCaracteres(this.getMetadadosCampoFilho().getNumeroMinimoCaracteres());
        result.setOrdemCampo(this.getNumeroOrdem());
        result.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(this.getMetadadosCampoFilho().getTipoCampo()));
        
        
        if (this.getMetadadosCampoFilho().getMetadadosCamposPai()!=null){
            int size = this.getMetadadosCampoFilho().getMetadadosCamposPai().size();
            
            for(int j = 0; j < size ; j++){
                result.getListaCampos().add(this.getMetadadosCampoFilho().getMetadadosCamposPai().get(j).createCampoDTO(result));
            }
        } 
           
        result.setCampoPai(campoPaiDTO);
        return result;
    }
    
}
