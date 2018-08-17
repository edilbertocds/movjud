package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_USUARIO")
@SequenceGenerator(name = "SEQ_CAD_USUARIO", sequenceName = "SEQ_CAD_USUARIO", allocationSize = 1)
public class Usuario extends BaseEntity<Long> {

    @SuppressWarnings("compatibility:383392472732907548")
    private static final long serialVersionUID = -6500711902578508627L;

    @Id
    @Column(name = "ID_CAD_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAD_USUARIO")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "FK_CAD_PERFIL")
    private Perfil perfil;

    @Column(name = "CD_USUARIO", nullable = false, length = 25)
    private String codigoUsuario;

    @Column(name = "NM_USUARIO", length = 200)
    private String nome;

    @Column(name = "DS_TELEFONE", length = 18)
    private String telefone;

    @Column(name = "DS_MATRICULA", length = 100)
    private String matricula;

    @Column(name = "NM_EMAIL")
    private String email;

    @Column(name = "CD_CNJ", length = 50)
    private String codigoCnj;

    @Column(name = "SAJPG_CDUSUARIO")
    private Long codigoUsuarioSaj;

    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<UsuarioAcao> acoesUsuario;

    @OneToMany(mappedBy = "usuarioMagistrado", cascade = { CascadeType.ALL }, orphanRemoval=true)
    private List<ProcessoGabinete> processosGabinete;
    
    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.MERGE, CascadeType.PERSIST } )
    private List<ProcessoConcluso> processosConclusos;

    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    private List<Aviso> avisos;
    
     private transient String senha;

    public Usuario() {

    }
    
    public Usuario(Long id) {
        this.idUsuario = id;
    }
    
    public Usuario(Long id, String nome) {
        this.idUsuario = id;
        this.nome = nome;
    }

    public Usuario(String codigoCnj, String codigoUsuario, String matricula, String telefone, Date dataAtualizacao,
                   Date dataInclusao, Perfil perfil, Long idUsuario, String email, String nome, Long codigoUsuarioSaj,
                   String flagTipoSituacao) {

        this.codigoCnj = codigoCnj;
        this.codigoUsuario = codigoUsuario;
        this.matricula = matricula;
        this.telefone = telefone;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.perfil = perfil;
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.codigoUsuarioSaj = codigoUsuarioSaj;
        this.flagTipoSituacao = flagTipoSituacao;
    }


    @Override
    public Long getId() {
        return getIdUsuario();
    }

    @Override
    public void setId(Long id) {
        setIdUsuario(id);
    }


    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setCodigoCnj(String codigoCnj) {
        this.codigoCnj = codigoCnj;
    }

    public String getCodigoCnj() {
        return codigoCnj;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigoUsuarioSaj(Long codigoUsuarioSaj) {
        this.codigoUsuarioSaj = codigoUsuarioSaj;
    }

    public Long getCodigoUsuarioSaj() {
        return codigoUsuarioSaj;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
        this.flagTipoSituacao = flagTipoSituacao;
    }

    public String getFlagTipoSituacao() {
        return flagTipoSituacao;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Perfil getPerfil() {
        return perfil;
    }


    public void setAcoesUsuario(List<UsuarioAcao> acoesUsuario) {
        this.acoesUsuario = acoesUsuario;
    }

    public List<UsuarioAcao> getAcoesUsuario() {
        if (acoesUsuario == null) {
            acoesUsuario = new ArrayList<UsuarioAcao>();
        }
        return acoesUsuario;
    }


    public void setProcessosGabinete(List<ProcessoGabinete> processosGabinete) {
        this.processosGabinete = processosGabinete;
    }

    public List<ProcessoGabinete> getProcessosGabinete() {
        if(this.processosGabinete == null){
            processosGabinete = new ArrayList<ProcessoGabinete>();
        }
        Collections.sort(processosGabinete);
        
        if (!processosGabinete.isEmpty()) {
            if (processosGabinete.size() > 1) {
                for (ProcessoGabinete processoGabinete : processosGabinete) {
                    processoGabinete.setUltimoRegistro(false);
                }
            }

            processosGabinete.get(processosGabinete.size() - 1).setUltimoRegistro(true);
        }        
        
        return processosGabinete;
    }

    public void setProcessosConclusos(List<ProcessoConcluso> processosConclusos) {
        this.processosConclusos = processosConclusos;
    }

    public List<ProcessoConcluso> getProcessosConclusos() {
        if(this.processosConclusos == null){
            processosConclusos= new ArrayList<ProcessoConcluso>();
        }
        return processosConclusos;
    }

    public void setAvisos(List<Aviso> avisos) {
        this.avisos = avisos;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }


    public void setSenha(String senha) {
	this.senha = senha;
    }

    public String getSenha() {
	return senha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID Usuario = ");
        sb.append(idUsuario);
        sb.append("\n");

        if (perfil != null) {
            sb.append("Perfil = ");
            sb.append(perfil.getNomePerfil());
            sb.append("\n");
        }

        sb.append("Codigo Usuario = ");
        sb.append(codigoUsuario);
        sb.append("\n");

        sb.append("Nome Usuario = ");
        sb.append(nome);
        sb.append("\n");

        sb.append("Matricula = ");
        sb.append(matricula);
        sb.append("\n");

        sb.append("Telefone = ");
        sb.append(telefone);
        sb.append("\n");

        sb.append("Email = ");
        sb.append(email);
        sb.append("\n");

        sb.append("Codigo CNJ = ");
        sb.append(codigoCnj);
        sb.append("\n");

        sb.append("Codigo Usuario SAJ = ");
        sb.append(codigoUsuarioSaj);
        sb.append("\n");

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
}
