package br.jus.tjsp.movjud.persistence.entity;

import br.jus.tjsp.movjud.persistence.base.annotation.Audit;

import br.jus.tjsp.movjud.persistence.base.helper.AuditListener;
import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@EntityListeners(AuditListener.class)
@Audit(dominio=DominioType.ESTRUTURA_JUDICIARIA)
@Table(name="DOMINIO_BI")
@SuppressWarnings("oracle.jdeveloper.ejb.entity-class-auto-id-gen")
public class DominioBI extends BaseEntity<String> {
    private static final long serialVersionUID = 6846739210587646789L;

    @Id
    @Column(name="CODIGO_DOMINIO", nullable=false)
    private String codigoDominio;

    @Column(name="TIPO_DOMINIO")
    private String tipoDominio;
    
    @Column(name="NOME_DOMINIO")
    private String nomeDominio;
    
    @Column(name="FLG_INTEGRACAO_BI")
    private String flgIntegracaoBI;
    
    @Column(name="FLG_PRECARGA")
    private String flgPreCarga;
    
    @Column(name="FLG_ATIVO")
    private String flgAtivo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_INCLUSAO")
    private Date dataInclusao;
    
    @Column(name="USUARIO_INC")
    private String usuarioInc;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_ALTERACAO")
    private Date dataAlteracao;
    
    @Column(name="USUARIO_ALT")
    private String usuarioAlt;
    
    public DominioBI() {
    }

    public DominioBI(String codigoDominio, String tipoDominio, String nomeDominio, String flgIntegracaoBI,
                     String flgPreCarga, String flgAtivo, Date dataInclusao, String usuarioInc, Date dataAlteracao,
                     String usuarioAlt) {
        this.codigoDominio = codigoDominio;
        this.tipoDominio = tipoDominio;
        this.nomeDominio = nomeDominio;
        this.flgIntegracaoBI = flgIntegracaoBI;
        this.flgPreCarga = flgPreCarga;
        this.flgAtivo = flgAtivo;
        this.dataInclusao = dataInclusao;
        this.usuarioInc = usuarioInc;
        this.dataAlteracao = dataAlteracao;
        this.usuarioAlt = usuarioAlt;
    }

    @Override
    public int hashCode() {
        return this.codigoDominio != null ? this.codigoDominio.hashCode() : "".hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if((object != null) && object instanceof DominioBI) {
            return ((DominioBI)object).codigoDominio.equals(this.codigoDominio);
        }
        return false;
    }

    public void setCodigoDominio(String codigoDominio) {
        this.codigoDominio = codigoDominio;
    }

    public String getCodigoDominio() {
        return codigoDominio;
    }

    public void setTipoDominio(String tipoDominio) {
        this.tipoDominio = tipoDominio;
    }

    public String getTipoDominio() {
        return tipoDominio;
    }

    public void setNomeDominio(String nomeDominio) {
        this.nomeDominio = nomeDominio;
    }

    public String getNomeDominio() {
        return nomeDominio;
    }

    public void setFlgIntegracaoBI(String flgIntegracaoBI) {
        this.flgIntegracaoBI = flgIntegracaoBI;
    }

    public String getFlgIntegracaoBI() {
        return flgIntegracaoBI;
    }

    public void setFlgPreCarga(String flgPreCarga) {
        this.flgPreCarga = flgPreCarga;
    }

    public String getFlgPreCarga() {
        return flgPreCarga;
    }

    public void setFlgAtivo(String flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public String getFlgAtivo() {
        return flgAtivo;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setUsuarioInc(String usuarioInc) {
        this.usuarioInc = usuarioInc;
    }

    public String getUsuarioInc() {
        return usuarioInc;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setUsuarioAlt(String usuarioAlt) {
        this.usuarioAlt = usuarioAlt;
    }

    public String getUsuarioAlt() {
        return usuarioAlt;
    }

    @Override
    public void setDataAtualizacao(Date dataAtualizacao) {
        // TODO Implement this method
    }

    @Override
    public void setId(String id) {
        this.codigoDominio = id;
    }

    @Override
    public String getId() {
        return this.codigoDominio;
    }
}
