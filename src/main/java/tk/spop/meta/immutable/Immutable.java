package tk.spop.meta.immutable;

import java.lang.annotation.*;


@Documented
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Immutable {
}
