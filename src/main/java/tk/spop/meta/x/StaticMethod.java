package tk.spop.meta.x;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target({ TYPE })
@Retention(RUNTIME)
public @interface StaticMethod {

    String name();

    Arg returType() default @Arg(Void.class);

    Arg[] types() default {};

    public @interface StaticMethods {

        StaticMethod[] value() default {};

    }

}
