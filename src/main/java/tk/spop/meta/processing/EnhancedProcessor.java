package tk.spop.meta.processing;

import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.util.*;
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

            Set<Element> elements = new HashSet<>();
            for (TypeElement annotation : annotations) {
                elements.addAll(env.getElementsAnnotatedWith(annotation));
            }
            for (Element element : elements) {
                process(element);
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
    
    protected Set<String> asString(Class<?> ... annotations) {
        Set<String> set = new HashSet<>();
        for (Class<?> a:annotations) {
            set.add(a.getName());
        }
        return set;
    }

}
