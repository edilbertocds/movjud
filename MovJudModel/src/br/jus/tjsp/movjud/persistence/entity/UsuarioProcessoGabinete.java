package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

    public ProcessoGabinete addProcessoGabinete() {
        ProcessoGabinete processoGabinete = new ProcessoGabinete();
        processoGabinete.setUsuarioMagistrado(this.getUsuario());        
        processoGabinete.setFlagArquivado(ConstantesMovjud.FLAG_SITUACAO_NAO);
        processoGabinete.setDataInclusao(new Date());

        usuario.getProcessosGabinete().add(processoGabinete);
        return processoGabinete;
    }

    public int removeUltimoProcessoGabinete() {
        if (usuario != null && usuario.getProcessosGabinete() != null &&
            !usuario.getProcessosGabinete().isEmpty()) {
            
            for (int i = 0; i < usuario.getProcessosGabinete().size(); i++) {
                if (usuario.getProcessosGabinete().get(i).isUltimoRegistro()) {
                    usuario.getProcessosGabinete().remove(i);
                    return i;
                }
            }
        }
        return -1;
    }
}