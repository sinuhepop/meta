package tk.spop.meta.immutable;

import java.lang.annotation.*;

import tk.spop.meta.Meta;

@Meta
@Documented
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Immutable {
}
