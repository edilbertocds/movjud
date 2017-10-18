package br.jus.tjsp.movjud.business.utils.helper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe helper utilizada para percorre e encontrara as propriedades do objeto.
 *
 * @author cds
 */
public class ReflectionHelper {

    /**
     * Obtém o atributo (Field) da classe informada.
     *
     * @param object objeto a ser procurado o atributo.
     * @param filedName nome do atributo
     * @return Field.
     */
    public static Field getField(Object object, String filedName) {
        Field field = null;

        for (Class<?> clazz = object.getClass();
             !clazz.equals(Object.class) && field == null;
             clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(filedName);
            } catch (SecurityException e) {
                throw new RuntimeException("Nao foi possível acessar o campo " +
                                           filedName + ".", e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return field;
    }

    /**
     * Obtém o atributo (Field) pela anotação.
     *
     * @param object objeto a ser verificado a anotação.
     * @param annotation anotação procurada.
     * @return lista de atributos (Field).
     */
    public static List<Field> getField(Object object,
                                       Class<? extends Annotation> annotation) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> klass = object.getClass(); !klass.equals(Object.class);
             klass =     klass.getSuperclass()) {
            try {
                for (Field current : klass.getDeclaredFields()) {
                    if (current.getAnnotation(annotation) != null) {
                        fields.add(current);
                    }
                }
            } catch (SecurityException e) {
                throw new RuntimeException("Nao foi possível acessar o campo com a anotação " +
                                           annotation + ".", e);
            }
        }
        return fields;
    }

    /**
     * Obtém a propriedade pelo nome.
     * 
     * @param object objeto a ser procurado a propriedade.
     * @param propertyName nome da propriedade.
     * @return objeto.
     */
    public static Object getObjectProprety(Object object, String propertyName) {
        try {
            Method method = findGetter(object, propertyName);
            return method != null ? method.invoke(object) : null;
        } catch (Exception e) {
            throw new RuntimeException("Não achou a propriedade: " +
                                       propertyName, e);
        }
    }

    /**
     * Obtém o método getter do objeto.
     * 
     * @param object objeto a ser procurado o getter.
     * @param propertyName nome da propriedade.
     * @return Method.
     */
    public static Method findGetter(Object object, String propertyName) {
        Class<? extends Object> beanClass = object.getClass();
        return findGetter(beanClass, propertyName);
    }

    /**
     * Obtém o método getter do objeto.
     * 
     * @param clazz classe a ser procurado o getter.
     * @param propertyName nome da propriedade.
     * @return Method.
     */
    public static Method findGetter(Class<? extends Object> clazz,
                                    String propertyName) {
        Method method = null;
        String capitalized = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1); 
        for (Class<?> current = clazz;
             !current.equals(Object.class) && method == null;
             current = current.getSuperclass()) {
            try {
                method = current.getMethod("get" + capitalized);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

}
