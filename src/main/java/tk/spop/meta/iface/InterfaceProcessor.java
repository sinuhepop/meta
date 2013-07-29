package tk.spop.meta.iface;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.meta.codegen.CodeModelHelper;
import tk.spop.meta.codegen.ModelWriter;
import tk.spop.meta.processing.EnhancedProcessor;
import tk.spop.meta.processing.ProcessorUtils;
import tk.spop.meta.util.Utils;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JMod;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class InterfaceProcessor extends EnhancedProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Utils.set(Interface.class.getName());
    }

    @Override
    @SneakyThrows
    protected void process(Element element) {

        val typeEl = (TypeElement) element;
        val iface = element.getAnnotation(Interface.class);
        val model = new JCodeModel();
        val helper = new CodeModelHelper(model);
        val type = model._class(getName(typeEl.getQualifiedName(), iface), ClassType.INTERFACE);

        helper.generify(typeEl, type);

        for (val el : getMethods(typeEl)) {

            val m = type.method(JMod.NONE, Object.class, el.getSimpleName().toString());
            helper.generify(el, m);

            val returnType = helper.getType(el.getReturnType());
            m.type(returnType);

            for (val par : el.getParameters()) {
                m.param(helper.getType(par.asType()), par.getSimpleName().toString());
            }
        }

        val writer = new ModelWriter(processingEnv.getFiler());
        writer.write(model, element);
    }

    protected List<ExecutableElement> getMethods(TypeElement clss) {
        val methods = ProcessorUtils.getElements(clss, ExecutableElement.class, ElementKind.METHOD);
        val it = methods.iterator();
        while (it.hasNext()) {
            val element = it.next();
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
