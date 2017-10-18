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
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
@Table(name = "CAD_ESTAB_PRISIONAL")
public class EstabelecimentoPrisional extends BaseEntity<Long> {
    
    private static final long serialVersionUID = 8880440020170157254L;
    
    @Id
    @Column(name = "ID_CAD_ESTAB_PRISIONAL", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_ESTAB_PRISIONAL")  
    @SequenceGenerator(name = "SEQ_CAD_ESTAB_PRISIONAL", sequenceName = "SEQ_CAD_ESTAB_PRISIONAL", allocationSize = 1)
    private Long idEstabelecimentoPrisional;
    

    
    @Column(name = "NM_ESTAB_PRISIONAL", length = 200)
    private String nomeEstabelecimentoPrisional;
    
    @Column(name = "NM_MUNICIPIO", length = 100)
    private String nomeMunicipio;
    
    @Column(name = "CD_UF")
    private String codigoUf;
    
    @Column(name = "FL_INTERNACAO")
    private String flagInternacao;
    
    @Column(name = "FL_PRISIONAL")
    private String flagPrisional;
    
    @Column(name = "SAJPG_CDLOCAL_PRISAO_ID")
    private Long codigoLocalPrisaoSaj;
    
    @Column(name = "TP_SITUACAO", nullable = false)
    private String flagTipoSituacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;
    
    @OneToMany(mappedBy = "estabelecimentoPrisional", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<UnidadeEstabelecimentoPrisional> unidadeEstabelecimentosPrisionais;
    
    @OneToMany(mappedBy = "estabelecimentoPrisional", cascade = { CascadeType.PERSIST, CascadeType.MERGE }) 
    private List<InspecaoEstabelecimentoPrisional> InspecoesEstabelecimentoPrisional;

    public EstabelecimentoPrisional() {
	super();
	unidadeEstabelecimentosPrisionais = new ArrayList<UnidadeEstabelecimentoPrisional>(); 
        flagTipoSituacao = "A";
    }
    
    public EstabelecimentoPrisional(Long idEstabelecimento) {
        this.idEstabelecimentoPrisional = idEstabelecimento;
        flagTipoSituacao = "A";
    }

    public EstabelecimentoPrisional(Long idEstabelecimentoPrisional, Unidade unidade,
                                    String nomeEstabelecimentoPrisional, String nomeMunicipio, String codigoUf,
                                    String flagInternacao, String flagPrisional, Long codigoLocalPrisaoSaj,
                                    String flagTipoSituacao, Date dataAtualizacao, Date dataInclusao, List<InspecaoEstabelecimentoPrisional> InspecoesEstabelecimentoPrisional) {
	this();
        this.idEstabelecimentoPrisional = idEstabelecimentoPrisional;
        
        this.nomeEstabelecimentoPrisional = nomeEstabelecimentoPrisional;
        this.nomeMunicipio = nomeMunicipio;
        this.codigoUf = codigoUf;
        this.flagInternacao = flagInternacao;
        this.flagPrisional = flagPrisional;
        this.codigoLocalPrisaoSaj = codigoLocalPrisaoSaj;
        this.flagTipoSituacao = flagTipoSituacao;
        this.dataAtualizacao = dataAtualizacao;
        this.dataInclusao = dataInclusao;
        this.InspecoesEstabelecimentoPrisional = InspecoesEstabelecimentoPrisional;
    }

    public void setIdEstabelecimentoPrisional(Long idEstabelecimentoPrisional) {
        this.idEstabelecimentoPrisional = idEstabelecimentoPrisional;
    }

    public Long getIdEstabelecimentoPrisional() {
        return idEstabelecimentoPrisional;
    }

   

    public void setNomeEstabelecimentoPrisional(String nomeEstabelecimentoPrisional) {
        this.nomeEstabelecimentoPrisional = nomeEstabelecimentoPrisional;
    }

    public String getNomeEstabelecimentoPrisional() {
        return nomeEstabelecimentoPrisional;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setCodigoUf(String codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getCodigoUf() {
        return codigoUf;
    }

    public void setFlagInternacao(String flagInternacao) {
        this.flagInternacao = flagInternacao;
    }

    public String getFlagInternacao() {
        return flagInternacao;
    }

    public void setFlagPrisional(String flagPrisional) {
        this.flagPrisional = flagPrisional;
    }

    public String getFlagPrisional() {
        return flagPrisional;
    }

    public void setCodigoLocalPrisaoSaj(Long codigoLocalPrisaoSaj) {
        this.codigoLocalPrisaoSaj = codigoLocalPrisaoSaj;
    }

    public Long getCodigoLocalPrisaoSaj() {
        return codigoLocalPrisaoSaj;
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

    public void setUnidadeEstabelecimentosPrisionais(List<UnidadeEstabelecimentoPrisional> unidadeEstabelecimentosPrisionais) {
	this.unidadeEstabelecimentosPrisionais = unidadeEstabelecimentosPrisionais;
    }

    public List<UnidadeEstabelecimentoPrisional> getUnidadeEstabelecimentosPrisionais() {
	return unidadeEstabelecimentosPrisionais;
    }


    public void setInspecoesEstabelecimentoPrisional(List<InspecaoEstabelecimentoPrisional> InspecoesEstabelecimentoPrisional) {
        this.InspecoesEstabelecimentoPrisional = InspecoesEstabelecimentoPrisional;
    }

    public List<InspecaoEstabelecimentoPrisional> getInspecoesEstabelecimentoPrisional() {
        if(InspecoesEstabelecimentoPrisional == null){
            InspecoesEstabelecimentoPrisional = new ArrayList<InspecaoEstabelecimentoPrisional>();
        }
        return InspecoesEstabelecimentoPrisional;
    }

    public UnidadeEstabelecimentoPrisional addEstabelecimentoPrisional(UnidadeEstabelecimentoPrisional unidadeEstabelecimentosPrisionais) {
	getUnidadeEstabelecimentosPrisionais().add(unidadeEstabelecimentosPrisionais);
	unidadeEstabelecimentosPrisionais.setEstabelecimentoPrisional(this);
	return unidadeEstabelecimentosPrisionais;
    }

    public UnidadeEstabelecimentoPrisional removeEstabelecimentoPrisional(UnidadeEstabelecimentoPrisional unidadeEstabelecimentosPrisionais) {
	getUnidadeEstabelecimentosPrisionais().remove(unidadeEstabelecimentosPrisionais);
	unidadeEstabelecimentosPrisionais.setEstabelecimentoPrisional(null);
	return unidadeEstabelecimentosPrisionais;
    }
    
    @Override
    public Long getId() {
	return getIdEstabelecimentoPrisional();
    }

    @Override
    public void setId(Long id) {
	setIdEstabelecimentoPrisional(id);
    }
    
    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	    
	sb.append("ID Estabelecimento Prisional = ");
	sb.append(idEstabelecimentoPrisional);
	sb.append("\n");
	
	sb.append("Nome Estabelecimento Prisional = ");
	sb.append(nomeEstabelecimentoPrisional);
	sb.append("\n");
	
	
	
	sb.append("Nome Municipio = ");
	sb.append(nomeMunicipio);
	sb.append("\n");
	
	sb.append("Codigo UF = ");
	sb.append(codigoUf);
	sb.append("\n");
	
	sb.append("Flag Internacao = ");
	sb.append(flagInternacao);
	sb.append("\n");
	
	sb.append("Flag Prisional = ");
	sb.append(flagPrisional);
	sb.append("\n");
	
	sb.append("Codigo Local Prisao SAJ = ");
	sb.append(codigoLocalPrisaoSaj);
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
