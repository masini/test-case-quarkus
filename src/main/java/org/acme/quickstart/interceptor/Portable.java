package org.acme.quickstart.interceptor;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Portable {
  /**
   * Indicate that the annotated class should be treated as an alias of an existing marshalling mapping, and should
   * not be directly mapped itself.
   * @return
   */
  Class<?> aliasOf() default Object.class;

  /*
   * Indicate that concrete supertypes of the annotated class should have marshalling mappings generated as if marked
   * portable.
   */
  boolean mapSuperTypes() default false;
}