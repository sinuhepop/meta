package tk.spop.meta.reflect;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

@Documented
@Target({ METHOD, CONSTRUCTOR })
@Retention(RUNTIME)
public @interface ReflectiveMember {

    public static final String DEFAULT_CONSTRUCTOR_NAME = "_";

    String value() default "";

    boolean skip() default false;

}
