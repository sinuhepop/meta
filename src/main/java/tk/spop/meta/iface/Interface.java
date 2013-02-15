package tk.spop.meta.iface;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

@Documented
@Target({ TYPE })
@Retention(RUNTIME)
public @interface Interface {

    String pkg() default ".";

    String name() default "I{name}";

    Class<?>[] includeInterfaces() default {};

    Class<?>[] excludeInterfaces() default {};

}
