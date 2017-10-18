package br.jus.tjsp.movjud.persistence.base.annotation;

import br.jus.tjsp.movjud.persistence.base.types.DominioType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação utilizada para auditar as alterações.
 * 
 * @author cds
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
    
    /**
     * Mantém o nome do domínio.
     * 
     * @return dominio.
     */
    public DominioType dominio();
}
