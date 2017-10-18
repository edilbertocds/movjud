package br.jus.tjsp.movjud.persistence.entity;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_FORO")
public class Foro extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 8030567452451901851L;
    
    @Id
    @Column(name = "ID_CAD_FORO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORO")   
    @SequenceGenerator(name = "SEQ_CAD_FORO", sequenceName = "SEQ_CAD_FORO", allocationSize = 1)     
    private Long idForo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CAD_COMARCA", nullable = false)
    private Comarca comarca;
    
    @Column(name = "NM_FORO", length = 100, nullable = false)
    private String nomeForo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
        
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_CRIACAO", nullable = false)
    private Date dataCriacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataFim;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_TIPO_ENTRANCIA")
    private TipoEntrancia tipoEntrancia;
    
    @OneToMany(mappedBy = "foro", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Unidade> unidades;
    
    @ManyToMany(mappedBy = "forosRecursais")
    private List<Unidade> unidadesForoRecursal;

    public Foro() {
    }
    
    public Foro(Long idForo) {
        this.idForo = idForo;
    }

    public Foro(Long idForo, Comarca comarca, String nomeForo, String flagTipoSituacao,
                Date dataAtualizacao, Date dataInclusao, Date dataCriacao, Date dataFim, TipoEntrancia tipoEntrancia) {
        super();
        this.idForo = idForo;
        this.comarca = comarca;
        this.nomeForo = nomeForo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.dataCriacao = dataCriacao;
        this.dataFim = dataFim;
        this.tipoEntrancia = tipoEntrancia;
    }

    public void setIdForo(Long idForo) {
        this.idForo = idForo;
    }

    public Long getIdForo() {
        return idForo;
    }

    public void setComarca(Comarca comarca) {
        this.comarca = comarca;
    }

    public Comarca getComarca() {
        return comarca;
    }

    public void setNomeForo(String nomeForo) {
        this.nomeForo = nomeForo;
    }

    public String getNomeForo() {
        return nomeForo;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setTipoEntrancia(TipoEntrancia tipoEntrancia) {
        this.tipoEntrancia = tipoEntrancia;
    }

    public TipoEntrancia getTipoEntrancia() {
        return tipoEntrancia;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidadesForoRecursal(List<Unidade> unidadesForoRecursal) {
        this.unidadesForoRecursal = unidadesForoRecursal;
    }

    public List<Unidade> getUnidadesForoRecursal() {
        return unidadesForoRecursal;
    }

    public Unidade addUnidade(Unidade unidade) {
        getUnidades().add(unidade);
        unidade.setForo(this);
        return unidade;
    }

    public Unidade removeUnidade(Unidade unidade) {
        getUnidades().remove(unidade);
        unidade.setForo(null);
        return unidade;
    }

    @Override
    public Long getId() {
        return getIdForo();
    }

    @Override
    public void setId(Long id) {
        setIdForo(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Foro = ");
        sb.append(idForo);
        sb.append("\n");
        
        sb.append("Nome Foro = ");
        sb.append(nomeForo);
        sb.append("\n");
        
        if (comarca != null) {
            sb.append("Comarca = ");
            sb.append(comarca.getNomeComarca());
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
        
        if (dataCriacao != null) {
            sb.append("Data Criacao = ");
            sb.append(ModelUtils.formatarDataToStr(dataCriacao));
            sb.append("\n");
        }
        
        if (dataFim != null) {
            sb.append("Data Fim = ");
            sb.append(ModelUtils.formatarDataToStr(dataFim));
            sb.append("\n");
        }
        
        sb.append("Tipo Entrancia = ");
        sb.append(tipoEntrancia);
        
        return sb.toString();
    }
}
