package tk.spop.meta.iface;

import java.util.*;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;

import lombok.*;
import tk.spop.meta.*;

import com.sun.codemodel.*;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class InterfaceProcessor extends EnhancedProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Arrays.asList(Interface.class.getName()));
    }

    @Override
    @SneakyThrows
    protected void process(Element element) {

        TypeElement typeEl = (TypeElement) element;
        val iface = element.getAnnotation(Interface.class);
        val model = new JCodeModel();
        val helper = new CodeModelHelper(model);
        val type = model._class(getName(typeEl.getQualifiedName(), iface), ClassType.INTERFACE);

        for (val tp : typeEl.getTypeParameters()) {
            val bounds = new JTypeMultiple(null);
            for (val bound : tp.getBounds()) {
                val boundClass = (JClass) helper.getType(bound);
                bounds.bound(boundClass);
            }
            type.generify(tp.getSimpleName().toString(), bounds);
        }

        for (ExecutableElement el : getMethods(typeEl)) {

            JType returnType = helper.getType(el.getReturnType());
            val m = type.method(JMod.NONE, returnType, el.getSimpleName().toString());
            // val types = el.getTypeParameters();
            // for (val t : types) {
            // for (val bound : t.getBounds()) {
            // val tt = m.generify(t.getSimpleName().toString());
            // }
            // }

            for (val par : el.getParameters()) {
                m.param(helper.getType(par.asType()), par.getSimpleName().toString());
            }
        }

        ModelWriter writer = new ModelWriter(processingEnv.getFiler());
        writer.write(model, element);
    }

    protected List<ExecutableElement> getMethods(TypeElement clss) {
        List<ExecutableElement> methods = ProcessorUtils.getElements(clss, ExecutableElement.class, ElementKind.METHOD);
        Iterator<ExecutableElement> it = methods.iterator();
        while (it.hasNext()) {
            ExecutableElement element = it.next();
            if (ProcessorUtils.is(element, Modifier.STATIC) || !ProcessorUtils.is(element, Modifier.PUBLIC)) {
                it.remove();
            }
        }
        return methods;
    }

    protected void addMember(Map<String, Element> map, Element member) {
        map.put(member.getSimpleName().toString(), member);
    }

    private String getName(Name name, Interface iface) {
        return iface.pkg() + "." + iface.name();
    }

}