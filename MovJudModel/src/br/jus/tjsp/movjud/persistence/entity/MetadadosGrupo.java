package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.formulario.dto.GrupoDTO;
import br.jus.tjsp.movjud.business.formulario.dto.SecaoDTO;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.CONTROLE_FORMULARIOS)
@Table(name = "MD_GRUPO")
public class MetadadosGrupo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 9191012830354173352L;
    
    @Id
    @Column(name = "ID_MD_GRUPO", nullable = false)
    @GeneratedValue(generator = "SEQ_MD_GRUPO")     
    @SequenceGenerator(name = "SEQ_MD_GRUPO", sequenceName = "SEQ_MD_GRUPO", allocationSize = 1)    
    private Long idMetadadosGrupo;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_SECAO")
    private MetadadosSecao metadadosSecao;
    
    @Column(name = "CD_SIGLA", nullable = false, length = 25)
    private String codigoSigla;

    @Column(name = "DS_NOME", nullable = false, length = 100)
    private String descricaoNome;
    
    @Column(name = "DS_TXT_INFORMATIVO", nullable = false, length = 4000)
    private String descricaoTextoInformativo;
    
    @Column(name = "NR_ORDEM", nullable = false)
    private Long numeroOrdem;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;  
    
    @Column(name = "FL_INVERTER_MD_TIPO_REGRA")
    private String flagInverterTipoRegra;
    
    @Column(name = "CD_DOMINIO_BI", length = 50)
    private String codigoDominioBI;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @OneToMany(mappedBy = "metadadosGrupo", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<MetadadosGrupoCampo> metadadosGruposCampo;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_TIPO_REGRA")
    private MetadadosTipoRegra metadadosTipoRegra;
    
    public MetadadosGrupo() {
    }
    
    public MetadadosGrupo(String codigoDominioBI) {
        this.codigoDominioBI = codigoDominioBI;
    }

    public MetadadosGrupo(Long idMetadadosGrupo, MetadadosSecao metadadosSecao, 
                          String descricaoNome,  Long numeroOrdem, String flagInverterTipoRegra,
                          String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao,
                          String codigoSigla, String descricaoTextoInformativo, String codigoDominioBI) {
        super();
        this.idMetadadosGrupo = idMetadadosGrupo;
        this.metadadosSecao = metadadosSecao;
        this.descricaoNome = descricaoNome;
        this.numeroOrdem = numeroOrdem;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
        this.codigoSigla = codigoSigla;
        this.descricaoTextoInformativo = descricaoTextoInformativo;
        this.codigoDominioBI = codigoDominioBI;
        this.flagInverterTipoRegra = flagInverterTipoRegra;
    }

    public void setIdMetadadosGrupo(Long idMetadadosGrupo) {
        this.idMetadadosGrupo = idMetadadosGrupo;
    }

    public Long getIdMetadadosGrupo() {
        return idMetadadosGrupo;
    }

    public void setFlagInverterTipoRegra(String flagInverterTipoRegra) {
        this.flagInverterTipoRegra = flagInverterTipoRegra;
    }

    public String getFlagInverterTipoRegra() {
        return flagInverterTipoRegra;
    }

    public void setMetadadosSecao(MetadadosSecao metadadosSecao) {
        this.metadadosSecao = metadadosSecao;
    }

    public MetadadosSecao getMetadadosSecao() {
        return metadadosSecao;
    }

    public void setDescricaoNome(String descricaoNome) {
        this.descricaoNome = descricaoNome;
    }

    public String getDescricaoNome() {
        return descricaoNome;
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

    public void setMetadadosGruposCampo(List<MetadadosGrupoCampo> metadadosGruposCampo) {
        this.metadadosGruposCampo = metadadosGruposCampo;
    }

    public List<MetadadosGrupoCampo> getMetadadosGruposCampo() {
        return metadadosGruposCampo;
    }

    public MetadadosGrupoCampo addMetadadosGrupoCampo(MetadadosGrupoCampo metadadosGrupoCampo) {
        getMetadadosGruposCampo().add(metadadosGrupoCampo);
        metadadosGrupoCampo.setMetadadosGrupo(this);
        return metadadosGrupoCampo;
    }

    public MetadadosGrupoCampo removeMetadadosGrupoCampo(MetadadosGrupoCampo metadadosGrupoCampo) {
        getMetadadosGruposCampo().remove(metadadosGrupoCampo);
        metadadosGrupoCampo.setMetadadosGrupo(null);
        return metadadosGrupoCampo;
    }
    
    public void setCodigoSigla(String codigoSigla) {
        this.codigoSigla = codigoSigla;
    }

    public String getCodigoSigla() {
        return codigoSigla;
    }

    public void setDescricaoTextoInformativo(String descricaoTextoInformativo) {
        this.descricaoTextoInformativo = descricaoTextoInformativo;
    }

    public String getDescricaoTextoInformativo() {
        return descricaoTextoInformativo;
    }

    public void setCodigoDominioBI(String codigoDominioBI) {
        this.codigoDominioBI = codigoDominioBI;
    }

    public String getCodigoDominioBI() {
        return codigoDominioBI;
    }

    @Override
    public Long getId() {
        return getIdMetadadosGrupo();
    }

    @Override
    public void setId(Long id) {
        setIdMetadadosGrupo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Metadados Grupo = ");
        sb.append(idMetadadosGrupo);
        sb.append("\n");
        
        if (metadadosSecao != null) {
            sb.append("Secao = ");
            sb.append(metadadosSecao.getDescricaoNome());
            sb.append("\n");
        }
        
        sb.append("Descricao Nome = ");
        sb.append(descricaoNome);
        sb.append("\n");
        
        sb.append("Código Sigla = ");
        sb.append(codigoSigla);
        sb.append("\n");
        
        
        sb.append("Descricao Texto Informativo = ");
        sb.append(descricaoTextoInformativo);
        sb.append("\n");
        
        
        sb.append("Código Domínio BI  = ");
        sb.append(codigoDominioBI);
        sb.append("\n");
        
        
        sb.append("Numero Ordem = ");
        sb.append(numeroOrdem);
        sb.append("\n");
                
        sb.append("Tipo Situacao = ");
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
        }
        
        if (metadadosTipoRegra != null) {
            sb.append("Tipo Regra = ");
            sb.append(metadadosTipoRegra.getDescricaoTipoRegra());
            sb.append("\n");
        }
        
        return sb.toString();
    }

    public void setNumeroOrdem(Long numeroOrdem) {
        this.numeroOrdem = numeroOrdem;
    }

    public Long getNumeroOrdem() {
        return numeroOrdem;
    }

    public void setMetadadosTipoRegra(MetadadosTipoRegra metadadosTipoRegra) {
        this.metadadosTipoRegra = metadadosTipoRegra;
    }

    public MetadadosTipoRegra getMetadadosTipoRegra() {
        return metadadosTipoRegra;
    }
    
    public GrupoDTO createGrupoDTO(SecaoDTO secaoDTO){
        GrupoDTO result = new GrupoDTO();
        result.setIdMetadadosGrupo(this.getIdMetadadosGrupo());
        result.setCodigoGrupo(this.getCodigoSigla());
        result.setLabelGrupo(this.getDescricaoNome());
        result.setOrdemGrupo(this.getNumeroOrdem());
        result.setDominioBI(this.getCodigoDominioBI());
        result.setTextoInformativo(this.getDescricaoTextoInformativo());
        result.setSituacao(this.getFlagTipoSituacao());
        result.setDataInclusao(this.getDataInclusao());
        if (this.getMetadadosTipoRegra() != null){
            result.setTipoRegraDTO(this.getMetadadosTipoRegra().createTipoRegraDTO());
        }
        if (result.getTipoRegraDTO() != null)
            result.getTipoRegraDTO().setInverterRegra((this.getFlagInverterTipoRegra().equals(ConstantesMovjud.FLAG_SITUACAO_SIM) ?
                                                         true : false));
        if (this.getMetadadosGruposCampo() != null){
            
            int size = this.getMetadadosGruposCampo().size();
            for(int j = 0; j < size ; j++){
                result.getListaCampos().add(this.getMetadadosGruposCampo().get(j).createCampoDTO(result));
            }
        }
        result.setSecao(secaoDTO);
        return result;
    }
    
}

