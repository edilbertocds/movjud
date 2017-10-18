package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

import java.io.Serializable;

import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "VW_USUARIO_PROCESSO_GAB")
public class UsuarioProcessoGabinete extends BaseEntity<Long> {
    private static final long serialVersionUID = 1890319621889264013L;

    @ManyToOne
    @Id
    @JoinColumn(name = "ID_CAD_USUARIO", nullable = false)
    private Usuario usuario;
    
    @ManyToOne   
    @JoinColumn(name = "ID_CAD_PROCESSO_GAB")
    private ProcessoGabinete processoGabinete;
    


    public UsuarioProcessoGabinete() {
    usuario = new Usuario();  
    processoGabinete= new ProcessoGabinete();
    
    }

    public UsuarioProcessoGabinete(Usuario usuario, ProcessoGabinete processoGabinete) {
        this.usuario=usuario;
        this.processoGabinete=processoGabinete;        
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setProcessoGabinete(ProcessoGabinete processoGabinete) {
        this.processoGabinete = processoGabinete;
    }

    public ProcessoGabinete getProcessoGabinete() {
        return processoGabinete;
    }


    @Override
    public void setDataAtualizacao(Date dataAtualizacao) {
	// TODO Implement this method
    }

    @Override
    public void setDataInclusao(Date dataInclusao) {
	// TODO Implement this method
    }

    @Override
    public void setId(Long id) {
	getUsuario().setIdUsuario(id);
    }

    @Override
    public Long getId() {
	return getUsuario().getIdUsuario();
    }
    public ProcessoGabinete addProcessoGabinete(ProcessoGabinete processoGabinete) {
        usuario.getProcessosGabinete().add(processoGabinete);
        processoGabinete.setUsuarioMagistrado(this.getUsuario());
        return processoGabinete;
    }

    public ProcessoGabinete removeProcessoGabinete(ProcessoGabinete processoGabinete) {
        usuario.getProcessosGabinete().remove(processoGabinete);
        processoGabinete.setUsuarioMagistrado(this.getUsuario());
        return processoGabinete;
    }

}