package tk.spop.meta;

import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;


public abstract class ProcessorAdapter extends AbstractProcessor {

    public Set<String> getSupportedAnnotationTypes() {

        Class<?>[] classes = getSupportedAnnotations();
        if (classes != null) {
            Set<String> annotations = new TreeSet<>();
            for (Class<?> clss : classes) {
                annotations.add(clss.getName());
            }
            return annotations;
        }

        return super.getSupportedAnnotationTypes();
    }


    protected Class<?>[] getSupportedAnnotations() {
        return null;
    }


    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        if ((!env.processingOver()) && (!env.errorRaised())) {
            for (TypeElement annotation : annotations) {
                Set<? extends Element> elements = env.getElementsAnnotatedWith(annotation);
                for (Element element : elements) {
                    switch (element.getKind()) {
                        case CLASS:
                            processClass((TypeElement) element, annotation, env);
                            break;
                        default:
                            unsupported(element.getKind().name(), annotation);
                    }
                }

            }

            return true;
        }
        return false;
    }


    protected void processClass(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        unsupported("Class", annotation);
    }


    private void unsupported(String processType, TypeElement annotation) {
        throw new UnsupportedOperationException(processType + " process is not implemented for annotation " + annotation.getQualifiedName());
    }


    protected void error(String msg, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element);
    }
}
