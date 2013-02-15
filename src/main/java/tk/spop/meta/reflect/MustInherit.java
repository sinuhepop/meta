package tk.spop.meta.reflect;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

@Documented
@Target({ CONSTRUCTOR, METHOD })
@Retention(RUNTIME)
public @interface MustInherit {

}
