package br.jus.tjsp.movjud.persistence.base.helper;

import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.utils.helper.ReflectionHelper;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.dao.PersistenceManager;
import br.jus.tjsp.movjud.persistence.base.types.AcaoType;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;

import br.jus.tjsp.movjud.persistence.entity.BaseEntity;

import java.util.Date;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.sessions.Session;

import javax.annotation.Resource;

import javax.ejb.SessionContext;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import org.eclipse.persistence.queries.DeleteObjectQuery;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.queries.UpdateObjectQuery;
import org.eclipse.persistence.queries.WriteObjectQuery;
import org.eclipse.persistence.sessions.changesets.ChangeRecord;
import org.eclipse.persistence.sessions.changesets.DirectToFieldChangeRecord;
import org.eclipse.persistence.sessions.changesets.ObjectChangeSet;
import org.eclipse.persistence.sessions.changesets.ObjectReferenceChangeRecord;
import org.eclipse.persistence.sessions.changesets.UnitOfWorkChangeSet;

public class AuditListener extends DescriptorEventAdapter implements DescriptorCustomizer, SessionCustomizer {
    private static ThreadLocal currentUser = new ThreadLocal();
    
    @PersistenceContext(unitName = "MovJudModel")
    private EntityManager entityManager;
    
    @Resource
    private SessionContext sessionContext;

    private String getUserName() {
        try {
            InitialContext initialContext = new InitialContext();
            SessionContext sessionContext = (SessionContext) initialContext.lookup("java:comp/EJBContext");
            return sessionContext.getCallerPrincipal().getName();
        } catch (NamingException | NullPointerException ex) {
            return AuditListener.currentUser != null ? (String) AuditListener.currentUser.get() : "Sistema";
        }
    }

    private void audit(DescriptorEvent event, String strDe, String strPara, AcaoType acaoType) {
        String dominio = "base";
        Audit ann = event.getObject()
                         .getClass()
                         .getAnnotation(Audit.class);
        if ((ann != null) && (ann.dominio() != null)) {
            dominio = ann.dominio().getNomeDominio();
        }
        if (!strDe.isEmpty() || !strPara.isEmpty()) {
            Auditoria auditoria = new Auditoria();
            auditoria.setAcao(acaoType.getNomeAcao());
            auditoria.setDataInclusao(new Date());
            auditoria.setDataAtualizacao(new Date());
            auditoria.setDe(strDe.isEmpty() ? " " : (strDe.length() > 4000 ? strDe.substring(0, 3996) + "..." : strDe));
            auditoria.setPara(strPara.isEmpty() ? " " : (strPara.length() > 4000 ? strPara.substring(0, 3996) + "..." : strPara));
            auditoria.setUsuario(getUserName());
            auditoria.setDominio(dominio);
            auditoria.setEntidade(event.getObject()
                                       .getClass()
                                       .getSimpleName());
            auditoria.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
            event.getSession().insertObject(auditoria);
        }
    }

    @Override
    public void customize(ClassDescriptor descriptor) throws Exception {
        descriptor.getEventManager().addListener(this);
    }

    @Override
    public void customize(Session session) throws Exception {
        for(ClassDescriptor descriptor : session.getDescriptors().values()) {
            if(descriptor.getJavaClass() != Auditoria.class) {
                customize(descriptor);
            }
        }
    }
    
    @Override
    public void aboutToInsert(DescriptorEvent event) {
        if(event.getSource() != null) {
            audit(event, "", event.getSource().toString(), AcaoType.INSERIR);
        }
    }
    
    @Override
    public void aboutToUpdate(DescriptorEvent event) {
        // <epr 0.7.43+> resolver problema da alteração sem alteração, só pela modificação incondicional da dt_atualizacao.
        if(event.getObject() instanceof BaseEntity && (event.getRecord() != null)) {
            event.getRecord().put("DT_ATUALIZACAO", new Date());
        }
        // </epr 0.7.43+>
        UpdateObjectQuery updateObjectQuery = (UpdateObjectQuery) event.getQuery();
        if ((updateObjectQuery != null) && (updateObjectQuery.getObjectChangeSet() != null)) {
            List<ChangeRecord> changes = updateObjectQuery.getObjectChangeSet().getChanges();
            if ((changes != null) && !changes.isEmpty()) {
                StringBuilder de = new StringBuilder();
                StringBuilder para = new StringBuilder();

                for (ChangeRecord change : changes) {
                    Object newValue = null;
                    if (change instanceof DirectToFieldChangeRecord) {
                        DirectToFieldChangeRecord fieldChange = (DirectToFieldChangeRecord) change;
                        if(fieldChange != null) {
                            newValue = fieldChange.getNewValue();
                        }
                    } else if (change instanceof ObjectReferenceChangeRecord) {
                        ObjectReferenceChangeRecord referenceChange = (ObjectReferenceChangeRecord) change;
                        if(referenceChange != null) {
                            newValue = referenceChange.getNewValue() != null ? referenceChange.getNewValue().getId() : null;
                        }
                    }
                    if (de.length() > 0) {
                        de.append(System.lineSeparator());
                    }
                    de.append(change.getAttribute() + "=" + (change.getOldValue() != null ? change.getOldValue().toString() : "null"));
                    if (para.length() > 0) {
                        para.append(System.lineSeparator());
                    }
                    para.append(change.getAttribute() + "=" + (newValue != null ? newValue.toString() : "null"));                        
                }
                audit(event, de.toString(), para.toString(), AcaoType.ATUALIZAR);
            }
        }
    }
    
    @Override
    public void aboutToDelete(DescriptorEvent event) {
        if(event.getSource() != null) {
            audit(event, event.getSource().toString(), "", AcaoType.EXCLUIR);
        }
    }
}
