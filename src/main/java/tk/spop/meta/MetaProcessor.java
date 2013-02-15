package tk.spop.meta;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class MetaProcessor extends ProcessorAdapter {

    @Override
    protected Class<?>[] getSupportedAnnotations() {
        return new Class<?>[] { Meta.class };
    }

//    @Override
//    protected void processClass(TypeElement element, TypeElement annotation, RoundEnvironment env) {
//        error("@" + Meta.class.getSimpleName() + " can only be used on annotations and interfaces.", annotation);
//    }

    @Override
    protected void processInterface(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        // Is ok
    }

    @Override
    protected void processAnnotation(TypeElement element, TypeElement annotation, RoundEnvironment env) {
        // Is ok
    }

}
