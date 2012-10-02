package tk.spop.meta.immutable;

import java.math.*;
import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;

import tk.spop.meta.*;


@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ImmutableProcessor extends ProcessorAdapter {

    private static final List<String> immutableClasses = Arrays.asList(new String[] { String.class.getName(), //
            Byte.class.getName(), Character.class.getName(), Short.class.getName(), //
            Integer.class.getName(), Float.class.getName(), Long.class.getName(), Double.class.getName(), //
            BigInteger.class.getName(), BigDecimal.class.getName(), //
            Locale.class.getName() //
            });


    protected Class<?>[] getSupportedAnnotations() {
        return new Class[] { Immutable.class };
    }


    protected void processClass(TypeElement clss, TypeElement annotation, RoundEnvironment env) {
        process(clss);
    }


    protected void process(TypeElement clss) {
        if (clss == null) {
            return;
        }

        for (VariableElement field : ProcessorUtils.getFields(clss)) {
            process(field);
        }

        process(ProcessorUtils.getSuperclass(clss));
    }


    protected void process(VariableElement field) {
        if (ProcessorUtils.is(field, Modifier.STATIC)) {
            return;
        }

        if (!ProcessorUtils.is(field, Modifier.FINAL)) {
            error("All fields in immutable objects must be final.", field);
            return;
        }

        if (ProcessorUtils.isPrimitive(field)) {
            return;
        }

        if (immutableClasses.contains(ProcessorUtils.getClassName(field))) {
            return;
        }

        error("Unknown as immutable type.", field);
    }
}