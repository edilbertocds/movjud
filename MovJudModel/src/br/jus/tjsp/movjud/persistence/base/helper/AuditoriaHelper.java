package br.jus.tjsp.movjud.persistence.base.helper;


import br.jus.tjsp.movjud.business.base.constantes.ConstantesMovjud;
import br.jus.tjsp.movjud.business.utils.helper.ReflectionHelper;
import br.jus.tjsp.movjud.persistence.base.annotation.Audit;
import br.jus.tjsp.movjud.persistence.base.types.AcaoType;
import br.jus.tjsp.movjud.persistence.entity.Auditoria;
import br.jus.tjsp.movjud.persistence.entity.Aviso;

import java.lang.reflect.Field;

import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityManager;


/**
 * Classe helper utilizada para auxiliar o processo de auditoria.
 *
 * @author cds
 */
public class AuditoriaHelper {
   
    public static final String USUARIO_SISTEMA = "Sistema";

    /**
     * Obtém o atributo da classe anotada com @Id.
     *
     * @param entity classe que representa a entidade.
     * @return objeto do atributo
     */
    public static Object getId(Object entity) {

        try {
            List<Field> fields = ReflectionHelper.getField(entity, javax.persistence.Id.class);

            if (fields.isEmpty()) {
                fields = ReflectionHelper.getField(entity, EmbeddedId.class);
            }

            if (!fields.isEmpty()) {

                Object result = ReflectionHelper.getObjectProprety(entity, fields.get(0).getName());

                if (result != null) {
                    if (result instanceof Number &&
                        ((Number)result).doubleValue() == 0) {
                        result = null;
                    }
                    if (result instanceof String && ((String)result).trim().isEmpty()) {
                        result = null;
                    }
                }

                return result;

            } else {
                throw new RuntimeException("O objeto não tem chave primária definida: " +
                                           entity.getClass().getName());
            }

        } catch (Exception exception) {
            throw new RuntimeException("Não foi possível recuperar a chave do objeto.",
                                       exception);
        }
    }

    /**
     * Método responsável por auditar as alterações.
     *
     * @param em manager.
     * @param objNovo objeto a ser auditado.
     * @param usuarioCorrente Usuario corrente conectado.
     */
    public static final void auditar(EntityManager em, Object objNovo,
                                     AcaoType acao, String usuarioCorrente) {

        // verifica os parâmetros
        if (objNovo != null && em != null && acao != null) {

            // obtém o id do objeto a ser auditado
            Object id = getId(objNovo);

            // obtém o objeto antigo caso exista
            Object objAntigo = null;

            if (id != null) {
                objAntigo = em.find(objNovo.getClass(), id);
            }

            // obtém a anotação utilizada para a auditoria
            Audit ann = objNovo.getClass().getAnnotation(Audit.class);

            // verifica o preenchimento da anotação
            boolean anotacaoExiste = ann != null;
            boolean dominioVazio =  ann != null ? ann.dominio().getNomeDominio().isEmpty() : false;
          //  boolean usuarioVazio = StringUtils.isBlank(usuarioCorrente);
            boolean usuarioVazio = (usuarioCorrente == null || usuarioCorrente.trim().isEmpty())? true : false;

            // verifica o preenchimento da anotação e usuário
            if (anotacaoExiste && dominioVazio) {
                throw new RuntimeException("A anotação @br.jus.tjsp.movjud.persistence.base.annotation.Audit " +
                                           "não poder ter o atributo domínio vazio");
            } else if (usuarioVazio) {
                throw new RuntimeException("É necessário carregar o usuário na classe " +
                                           "AuditoriaHelper.setUsuarioCorrente(String usuario) para auditar as alterações");
            } else if (anotacaoExiste) {
                String dominio = ann.dominio().getNomeDominio();
                String de = "";
                String para = objNovo.toString();

                if (AcaoType.INSERIR.equals(acao)) {
                    de = " ";
                } else if (AcaoType.ATUALIZAR.equals(acao)) {
                    de = String.valueOf(objAntigo);
                } else if (AcaoType.EXCLUIR.equals(acao)) {
                    de = String.valueOf(objAntigo);
                    para = " ";
                }
                if(objNovo instanceof Aviso){
                    usuarioCorrente = USUARIO_SISTEMA;
                }

                Auditoria auditoria = new Auditoria();
                auditoria.setAcao(acao.getNomeAcao());
                auditoria.setDataInclusao(new Date());
                auditoria.setDataAtualizacao(new Date());
                auditoria.setDe(de);
                auditoria.setPara(para);
                auditoria.setUsuario(usuarioCorrente);
                auditoria.setDominio(dominio);
                auditoria.setEntidade(objNovo.getClass().getSimpleName());
                auditoria.setFlagTipoSituacao(ConstantesMovjud.FLAG_SITUACAO_ATIVA);
                em.persist(auditoria);
            }
        }
    }
}
