package tk.spop.meta.immutable;

import java.math.*;
import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.*;
import javax.tools.Diagnostic;

import tk.spop.meta.processing.ProcessorUtils;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("*")
public class ImmutableProcessor extends AbstractProcessor {

    private static final Set<String> immutableClasses = new HashSet<>(Arrays.asList(new String[] { String.class.getName(), //
            Byte.class.getName(), Character.class.getName(), Short.class.getName(), //
            Integer.class.getName(), Float.class.getName(), Long.class.getName(), Double.class.getName(), //
            BigInteger.class.getName(), BigDecimal.class.getName(), //
            Locale.class.getName(), //
    }));

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        if (!env.processingOver()) {
            Set<? extends Element> elements = env.getRootElements();
            for (Element element : elements) {
                if (element.getKind() == ElementKind.CLASS) {
                    TypeElement clss = (TypeElement) element;
                    if (ProcessorUtils.isInstanceOf(clss, Immutable.class.getName())) {
                        processClass(clss, clss);
                    }
                }
            }
        }
        return false;
    }

    protected void processClass(TypeElement clss, TypeElement rootClass) {

        if (clss == null) {
            return;
        }

        for (VariableElement field : ProcessorUtils.getFields(clss)) {
            checkField(field, clss.equals(rootClass) ? field : rootClass);
        }

        processClass(ProcessorUtils.getSuperclass(clss), rootClass);
    }

    protected void checkField(VariableElement field, Element errorTarget) {

        if (ProcessorUtils.is(field, Modifier.STATIC)) {
            return;
        }

        if (!ProcessorUtils.is(field, Modifier.FINAL)) {
            error("Field " + field.getSimpleName() + " is not final.", errorTarget);
            return;
        }

        checkType(field.asType(), field.getSimpleName(), errorTarget);
    }

    protected void checkType(TypeMirror type, Name fieldName, Element errorTarget) {

        if (ProcessorUtils.isPrimitive(type)) {
            return;
        }

        String className = ProcessorUtils.getClassName(type);
        if (immutableClasses.contains(className) || //
                ProcessorUtils.isInstanceOf(ProcessorUtils.asType(type), Immutable.class.getName())) {
            for (TypeMirror genericType : ((DeclaredType) type).getTypeArguments()) {
                checkType(genericType, fieldName, errorTarget);
            }
            return;
        }

        error("Field " + fieldName + " type (" + className + ") is not of a known immutable type.", errorTarget);
    }

    protected void error(String msg, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element);
    }

}
