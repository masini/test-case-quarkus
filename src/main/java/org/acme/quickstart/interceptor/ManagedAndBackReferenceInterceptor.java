package org.acme.quickstart.interceptor;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@ManagedAndBackReference
@Interceptor
@Priority(Interceptor.Priority.APPLICATION+102)
@Slf4j
public class ManagedAndBackReferenceInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {

        for (Object parameter : ctx.getParameters()) {

            jsonManagedAndBackReferenceResolver(parameter);
        }

        return ctx.proceed();
    }

    void jsonManagedAndBackReferenceResolver(Object parameter) {
        if( parameter instanceof Collection) {
            List parameterList = (List) parameter;

            for (Object p : parameterList) {
                checkForPortableManaged(p);
            }
        } else {
            checkForPortableManaged(parameter);
        }
    }

    private void checkForPortableManaged(Object entity) {
        if (entity != null && entity.getClass().getAnnotation(Portable.class) != null) {
            try {
                for (Field field : entity.getClass().getDeclaredFields()) {
                    if (field.getAnnotation(JsonManagedReference.class) != null && Collection.class.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        Collection oneToMany = (Collection) field.get(entity);

                        for (Object child : oneToMany) {
                            lookForJsonBackReference(entity, child);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                log.warn("Error executing check for Managed/Back reference", e);
            }
        }
    }

    private void lookForJsonBackReference(Object entity, Object child) throws IllegalAccessException {
        for (Field field : child.getClass().getDeclaredFields()) {
            if (field.getAnnotation(JsonBackReference.class) != null && field.getType().equals(entity.getClass())) {
                field.setAccessible(true);
                field.set(child, entity);

                break;
            }
        }

        jsonManagedAndBackReferenceResolver(child);
    }
}
