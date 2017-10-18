package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.FORMULARIOS)
@Table(name = "CAD_FORM_FORO_ORIGEM")
public class FormularioForoOrigem implements Serializable {
    private static final long serialVersionUID = -6434274489011261279L;
    
    @Id
    @Column(name = "ID_CAD_FORM_FORO_ORIGEM", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_FORM_FORO_ORIGEM")  
    @SequenceGenerator(name = "SEQ_CAD_FORM_FORO_ORIGEM", sequenceName = "SEQ_CAD_FORM_FORO_ORIGEM", allocationSize = 1)
    private Long idFormularioForoOrigem;
    
    @ManyToOne
    @JoinColumn(name = "FK_CAD_FORO", nullable = false)
    private Foro foro;
    @ManyToOne
    @JoinColumn(name = "FK_CAD_SECAO")
    private Secao secao;
    @ManyToOne
    @JoinColumn(name = "FK_CAD_UNIDADE", nullable = false)
    private Unidade unidade;
    @Column(name = "VL_CAMPO", length = 2000)
    private String valorCampo;

    public FormularioForoOrigem() {
    }

    public FormularioForoOrigem(Foro foro, Secao secao, Unidade unidade, String valorCampo) {
        this.foro = foro;
        this.secao = secao;
        this.unidade = unidade;
        this.valorCampo = valorCampo;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            
        
        if (unidade != null) {
            sb.append("Unidade = ");
            sb.append(unidade.getNomeUnidade());
            sb.append("\n");
        }
        
        sb.append("Valor = ");
        sb.append(valorCampo);
        sb.append("\n");
    
        if (foro != null) {
            sb.append("Foro = ");
            sb.append(foro.getNomeForo());
            sb.append("\n");
        }
        
        return sb.toString();
    }


    public void setIdFormularioForoOrigem(Long idFormularioForoOrigem) {
        this.idFormularioForoOrigem = idFormularioForoOrigem;
    }

    public Long getIdFormularioForoOrigem() {
        return idFormularioForoOrigem;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public Foro getForo() {
        return foro;
    }

    public void setSecao(Secao secao) {
        this.secao = secao;
    }

    public Secao getSecao() {
        return secao;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public String getValorCampo() {
        return valorCampo;
    }

    public void setValorCampo(String valorCampo) {
        this.valorCampo = valorCampo;
    }
}
