package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.CampoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.FormulaDTO;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.types.TipoCampoType;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_GRUPO_CAMPO")
public class MetadadosGrupoCampo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 1244214715671467242L;
    
    @Id
    @Column(name = "ID_MD_GRUPO_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_GRUPO_CAMPO")       
    @SequenceGenerator(name = "SEQ_MD_GRUPO_CAMPO", sequenceName = "SEQ_MD_GRUPO_CAMPO", allocationSize = 1)    
    private Long idMetadadosGrupoCampo;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } , fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_MD_CAMPO")
    private MetadadosCampo metadadosCampo;
    
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } , fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_MD_GRUPO")
    private MetadadosGrupo metadadosGrupo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Column(name = "DS_FORMULA", length = 1000)
    private String descricaoFormula;
    
    @Column(name = "NR_ORDEM", nullable = false)
    private Long numeroOrdem;
    
    @Column(name = "CD_SIGLA", length = 25)
    private String codigoSigla;

    @Column(name = "DS_SEQUENCIA", length = 25)
    private String descricaoSequencia;

    public MetadadosGrupoCampo() {
    }

    public MetadadosGrupoCampo(Long idMetadadosGrupoCampo, MetadadosCampo metadadosCampo, MetadadosGrupo metadadosGrupo,
                               String flagTipoSituacao,
                               Date dataInclusao, Date dataAtualizacao, String descricaoFormula, Long numeroOrdem,
                               String codigoSigla, String descricaoSequencia) {
        super();
        this.idMetadadosGrupoCampo = idMetadadosGrupoCampo;
        this.metadadosCampo = metadadosCampo;
        this.metadadosGrupo = metadadosGrupo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.descricaoFormula = descricaoFormula;
        this.numeroOrdem = numeroOrdem;
        this.codigoSigla = codigoSigla;
        this.descricaoSequencia = descricaoSequencia;
    }

    public void setIdMetadadosGrupoCampo(Long idMetadadosGrupoCampo) {
        this.idMetadadosGrupoCampo = idMetadadosGrupoCampo;
    }

    public Long getIdMetadadosGrupoCampo() {
        return idMetadadosGrupoCampo;
    }

    public void setMetadadosCampo(MetadadosCampo metadadosCampo) {
        this.metadadosCampo = metadadosCampo;
    }

    public MetadadosCampo getMetadadosCampo() {
        return metadadosCampo;
    }

    public void setMetadadosGrupo(MetadadosGrupo metadadosGrupo) {
        this.metadadosGrupo = metadadosGrupo;
    }

    public MetadadosGrupo getMetadadosGrupo() {
        return metadadosGrupo;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        if(dataInclusao == null){
            dataInclusao = new Date();
        }
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDescricaoFormula(String descricaoFormula) {
        this.descricaoFormula = descricaoFormula;
    }

    public String getDescricaoFormula() {
        return descricaoFormula;
    }

    public void setNumeroOrdem(Long numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Long getNumeroOrdem() {
        return numeroOrdem;
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

    @Override
    public Long getId() {
        return getIdMetadadosGrupoCampo();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosGrupoCampo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Metadados Grupo Campo = ");
        sb.append(idMetadadosGrupoCampo);
        sb.append("\n");
        
        if (metadadosCampo != null) {
            sb.append("Campo = ");
            sb.append(metadadosCampo.getNomeCampo());
            sb.append("\n");
        }
        
        if (metadadosGrupo != null) {
            sb.append("Grupo = ");
            sb.append(metadadosGrupo.getDescricaoNome());
            sb.append("\n");
        }       
        
        sb.append("Flag Tipo Situacao = ");
        sb.append(flagTipoSituacao);
        sb.append("\n");
        
        if (dataAtualizacao != null) {
            sb.append("Data Atualizacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataAtualizacao));
            sb.append("\n");
        }
        
        if (dataInclusao != null) {
            sb.append("Data Inclusao = ");
            sb.append(ModelUtils.formatarDataToStr(dataInclusao));
            sb.append("\n");
        }
        
        sb.append("Descricao Formula = ");
        sb.append(descricaoFormula);
        sb.append("\n");
        
        sb.append("Numero Ordem = ");
        sb.append(numeroOrdem);
        sb.append("\n");
        
        sb.append("Código Sigla = ");
        sb.append(codigoSigla);
        sb.append("\n");
        
        sb.append("Descrição Sequência = ");
        sb.append(descricaoSequencia);
        sb.append("\n");
        
        return sb.toString();
    }
    
    public CampoDTO createCampoDTO(GrupoDTO grupoDTO){
        
        CampoDTO result = new CampoDTO();
        result.setIdMetadadosGrupoCampo(this.getIdMetadadosGrupoCampo());
        result.setIdMetadadosCampo(this.getMetadadosCampo().getIdMetadadosCampo());
        result.setDataInclusao(this.getDataInclusao());
        result.setSituacao(this.getFlagTipoSituacao());
        result.setHint(this.getMetadadosCampo().getDescricaoTextoInformativo());
        result.setLabelCampo(this.getMetadadosCampo().getNomeCampo());
        result.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(this.getMetadadosCampo().getTipoCampo()));
        result.setRequerido(true);
        result.setCasasDecimais(this.getMetadadosCampo().getNumeroCasasDecimais());
        result.setCodigoBI(this.getMetadadosCampo().getCodigoDominioBI());
        result.setCodigoCampo(this.getCodigoSigla());
        if (this.getDescricaoFormula() != null && !this.getDescricaoFormula().isEmpty()){
            result.setFormula(new FormulaDTO(this.getDescricaoFormula()));
        }
        if (this.getMetadadosCampo().getMetadadosTipoRegra() != null){
            result.setTipoRegraDTO(this.getMetadadosCampo().getMetadadosTipoRegra().createTipoRegraDTO());
        }
        if (result.getTipoRegraDTO() != null)
            result.getTipoRegraDTO().setInverterRegra((this.getMetadadosCampo().getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                         true : false));
        result.setIndiceCampo(this.getDescricaoSequencia());
        
        if (this.getMetadadosCampo().getMetadadosListaSelecao()!=null){
            int size = this.getMetadadosCampo().getMetadadosListaSelecao().size();
            for(int j = 0; j < size ; j++){
                result.getListaItensSelecaoDTO().add(this.getMetadadosCampo().getMetadadosListaSelecao().get(j).createItemSelecaoDTO());
            }
        }    
        
        if (this.getMetadadosCampo().getMetadadosValidacaoCampo()!=null){
            int size = this.getMetadadosCampo().getMetadadosValidacaoCampo().size();
            
            for(int j = 0; j < size ; j++){
                result.getListaValidacoes().add(this.getMetadadosCampo().getMetadadosValidacaoCampo().get(j).createValidacaoDTO());
            }
        }   
               
        result.setNumeroMaximoCaracteres(this.getMetadadosCampo().getNumeroMaximoCaracteres());
        result.setNumeroMinimoCaracteres(this.getMetadadosCampo().getNumeroMinimoCaracteres());
        result.setOrdemCampo(this.getNumeroOrdem());
        result.setTipoCampo(TipoCampoType.retornarTipoCampoEnum(this.getMetadadosCampo().getTipoCampo()));
        
        
        if (this.getMetadadosCampo().getMetadadosCamposPai()!=null){
            int size = this.getMetadadosCampo().getMetadadosCamposPai().size();
            
            for(int j = 0; j < size ; j++){
                result.getListaCampos().add(this.getMetadadosCampo().getMetadadosCamposPai().get(j).createCampoDTO(result));
            }
        }   
       
        result.setGrupo(grupoDTO);
        return result;
    }
    
    
    

}
