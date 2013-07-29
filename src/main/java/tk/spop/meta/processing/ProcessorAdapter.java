package tk.spop.meta.processing;

import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

@Deprecated
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
                        case INTERFACE:
                            processInterface((TypeElement) element, annotation, env);
                            break;
                        case ANNOTATION_TYPE:
                            processAnnotation((TypeElement) element, annotation, env);
                            break;
                        default:
                            unsupportedAnnotation(element, annotation);
                    }
                }

            }

            return true;
        }
        return false;
    }

    protected void processClass(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        unsupportedAnnotation(element, annotation);
    }

    protected void processInterface(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        unsupportedAnnotation(element, annotation);
    }

    protected void processAnnotation(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        unsupportedAnnotation(element, annotation);
    }

    private void unsupportedAnnotation(Element element, TypeElement annotation) {
        error("Annotation " + annotation.getQualifiedName() + " is not supported on " + element.getKind().name(), element);
    }

    protected void error(String msg, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element);
    }
}
