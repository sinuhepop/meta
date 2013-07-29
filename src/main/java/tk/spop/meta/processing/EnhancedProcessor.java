package tk.spop.meta.processing;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import lombok.Delegate;

public abstract class EnhancedProcessor extends AbstractProcessor {

    @Delegate
    private Elements elements;

    @Delegate
    private Types types;

    @Override
    public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {

        if (!env.processingOver()) {

            elements = this.processingEnv.getElementUtils();
            types = this.processingEnv.getTypeUtils();

            if (getSupportedAnnotationTypes().contains("*")) {
                for (Element element : env.getRootElements()) {
                    process(element);
                }
            } else {
                Set<Element> elements = new HashSet<>();
                for (TypeElement annotation : annotations) {
                    elements.addAll(env.getElementsAnnotatedWith(annotation));
                }
                for (Element element : elements) {
                    process(element);
                }
            }
        }

        return false;
    }

    protected abstract void process(Element element);

    protected void error(String msg, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg, element);
    }

    protected void warn(String msg, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, msg, element);
    }

}
