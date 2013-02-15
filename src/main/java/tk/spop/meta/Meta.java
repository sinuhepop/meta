package tk.spop.meta;

import java.lang.annotation.*;

@Meta(processor = MetaProcessor.class)
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Meta {

    Class<? extends ProcessorAdapter> processor();
}
