package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.utils.helper.ModelUtils;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "CAD_UNID_ESTAB_PRIS")
@Audit(dominio = DominioType.ESTRUTURA_JUDICIARIA)
public class UnidadeEstabelecimentoPrisional extends BaseEntity<Long> {
    private static final long serialVersionUID = 5249835069666570273L;

    @Id
    @Column(name = "ID_CAD_UNID_ESTAB_PRIS", nullable = false)
    @GeneratedValue(generator = "SEQ_CAD_UNID_ESTAB_PRIS")
    @SequenceGenerator(name = "SEQ_CAD_UNID_ESTAB_PRIS", sequenceName = "SEQ_CAD_UNID_ESTAB_PRIS", allocationSize = 1)
    private Long idUnidadeEstabelecimentoPrisional;

    @JoinColumn(name = "FK_CAD_ESTAB_PRISIONAL", nullable = false)
    private EstabelecimentoPrisional estabelecimentoPrisional;

    @JoinColumn(name = "FK_CAD_UNIDADE", nullable = false)
    private Unidade unidade;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INICIO")
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FIM")
    private Date dataFim;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_ATUALIZACAO")
    private Date dataAtualizacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_INCLUSAO", insertable = false, updatable = false)
    private Date dataInclusao;

    @Column(name = "TP_SITUACAO")
    private String flagTipoSituacao;


    public UnidadeEstabelecimentoPrisional() {
        super();
        setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
    }

    public UnidadeEstabelecimentoPrisional(Date dataAtualizacao, Date dataInclusao, Date dataFim, Date dataInicio, String flagTipoSituacao, EstabelecimentoPrisional estabelecimentoPrisional, Unidade unidade,
					   Long idUnidadeEstabelecimentoPrisional) {
	this.dataAtualizacao = dataAtualizacao;
	this.dataInclusao = dataInclusao;
	this.dataFim = dataFim;
	this.dataInicio = dataInicio;
	this.flagTipoSituacao = flagTipoSituacao;
	this.estabelecimentoPrisional = estabelecimentoPrisional;
	this.unidade = unidade;
	this.idUnidadeEstabelecimentoPrisional = idUnidadeEstabelecimentoPrisional;
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
	this.dataInclusao = dataInclusao;
    }

    public void setDataFim(Date dataFim) {
	this.dataFim = dataFim;
    }

    public Date getDataFim() {
	return dataFim;
    }

    public Date getDataInicio() {
	return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
	this.dataInicio = dataInicio;
    }

    public String getFlagTipoSituacao() {
	return flagTipoSituacao;
    }

    public void setFlagTipoSituacao(String flagTipoSituacao) {
	this.flagTipoSituacao = flagTipoSituacao;
    }

    public void setEstabelecimentoPrisional(EstabelecimentoPrisional estabelecimentoPrisional) {
	this.estabelecimentoPrisional = estabelecimentoPrisional;
    }

    public EstabelecimentoPrisional getEstabelecimentoPrisional() {
	return estabelecimentoPrisional;
    }

    public void setUnidade(Unidade unidade) {
	this.unidade = unidade;
    }

    public Unidade getUnidade() {
	return unidade;
    }

    public Long getIdUnidadeEstabelecimentoPrisional() {
	return idUnidadeEstabelecimentoPrisional;
    }

    public void setIdUnidadeEstabelecimentoPrisional(Long idUnidadeEstabelecimentoPrisional) {
	this.idUnidadeEstabelecimentoPrisional = idUnidadeEstabelecimentoPrisional;
    }

    @Override
    public void setId(Long id) {
	setIdUnidadeEstabelecimentoPrisional(id);
    }

    @Override
    public Long getId() {
	return getIdUnidadeEstabelecimentoPrisional();
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();

	sb.append("ID Unidade Estabelecimento Prisional = ");
	sb.append(idUnidadeEstabelecimentoPrisional);
	sb.append("\n");

	if (unidade != null) {
	    sb.append("ID Unidade = ");
	    sb.append(unidade.getIdUnidade());
	    sb.append("\n");

	    sb.append("Nome Unidade = ");
	    sb.append(unidade.getNomeUnidade());
	    sb.append("\n");
	}
	
	if (estabelecimentoPrisional != null) {
	    sb.append("ID Estabelecimento Prisional = ");
	    sb.append(estabelecimentoPrisional.getIdEstabelecimentoPrisional());
	    sb.append("\n");

	    sb.append("Nome Estabelecimento Prisional = ");
	    sb.append(estabelecimentoPrisional.getNomeEstabelecimentoPrisional());
	    sb.append("\n");
	}
	
	if (dataInicio != null) {
	    sb.append("Data Inicio = ");
	    sb.append(ModelUtils.formatarDataToStr(dataInicio));
	    sb.append("\n");
	}
	
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
