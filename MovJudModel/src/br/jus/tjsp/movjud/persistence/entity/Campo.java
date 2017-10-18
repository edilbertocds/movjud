package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
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
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_CAMPO")
public class Campo extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 3976031352179355916L;
    
    @Id
    @Column(name = "ID_CAD_CAMPO", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_CAMPO")    
    @SequenceGenerator(name = "SEQ_CAD_CAMPO", sequenceName = "SEQ_CAD_CAMPO", allocationSize = 1)
    private Long idCampo;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_USUARIO")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_GRUPO")
    private Grupo grupo;
    
    @ManyToOne
    @JoinColumn(name = "FK_MD_CAMPO")
    private MetadadosCampo metadadosCampo;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_CAMPO")
    private Campo campoPai;
    
    @Column(name = "VL_CAMPO", length = 2000)
    private String valorCampo;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;    
    
    @OneToMany(mappedBy = "campoPai", cascade = { CascadeType.ALL },orphanRemoval = true)
    private List<Campo> campos;

    public Campo() {
    }

    public Campo(Long idCampo, Usuario usuario, Grupo grupo, MetadadosCampo metadadosCampo, Campo campoPai,
                 String valorCampo, String flagTipoSituacao, Date dataInclusao, Date dataAtualizacao) {
        super();
        this.idCampo = idCampo;
        this.usuario = usuario;
        this.grupo = grupo;
        this.metadadosCampo = metadadosCampo;
        this.campoPai = campoPai;
        this.valorCampo = valorCampo;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataInclusao = dataInclusao;
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public Long getId() {
        return getIdCampo();
    }

    @Override
    public void setId(Long id) {
        setIdCampo(id);
    }

    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
    }

    public Long getIdCampo() {
        return idCampo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setMetadadosCampo(MetadadosCampo metadadosCampo) {
        this.metadadosCampo = metadadosCampo;
    }

    public MetadadosCampo getMetadadosCampo() {
        return metadadosCampo;
    }

    public void setCampoPai(Campo campoPai) {
        this.campoPai = campoPai;
    }

    public Campo getCampoPai() {
        return campoPai;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setDataInclusao(Date dataInclusao) {
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

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }

    public List<Campo> getCampos() {
        if(campos==null){
            campos = new ArrayList<Campo>();
        }
        return campos;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        sb.append("ID Campo = ");
        sb.append(idCampo);
        sb.append("\n");
        
        if (metadadosCampo != null) {
            sb.append("Nome Campo = ");
            sb.append(metadadosCampo.getNomeCampo());
            sb.append("\n");
        }
        
        sb.append("Valor Campo = ");
        sb.append(valorCampo);
        sb.append("\n");
        
        if (usuario != null) {
            sb.append("Usuario = ");
            sb.append(usuario.getNome());
            sb.append("\n");
        }
        
        if (grupo != null) {
            sb.append("ID Grupo = ");
            sb.append(grupo.getId());
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
        }    
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Campo)) {
            return false;
        }
        final Campo other = (Campo) object;
        if (!(idCampo == null ? other.idCampo == null : idCampo.equals(other.idCampo))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((idCampo == null) ? 0 : idCampo.hashCode());
        return result;
    }
}
