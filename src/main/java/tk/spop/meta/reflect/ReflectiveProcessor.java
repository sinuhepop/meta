package tk.spop.meta.reflect;

import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;

import tk.spop.meta.EnhancedProcessor;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
//@SupportedAnnotationTypes("tk.spop.meta.reflect.Reflective")
public class ReflectiveProcessor extends EnhancedProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return asString(Reflective.class);
    }

    @Override
    protected void process(Element element) {
        Map<String, Element> members = getMembers((TypeElement) element);
        System.out.println(members);
    }

    protected Map<String, Element> getMembers(TypeElement clss) {

        Map<String, Element> map = new HashMap<>();
        // List<Element> members = ProcessorUtils.getElements(clss, Element.class, FIELD, CONSTRUCTOR,
        // METHOD);
        List<? extends Element> members = this.getAllMembers(clss);
        for (Element member : members) {
            addMember(map, member);
        }

        return map;
    }

    protected void addMember(Map<String, Element> map, Element member) {
        map.put(member.getSimpleName().toString(), member);
    }

}