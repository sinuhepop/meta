package tk.spop.meta.reflect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import tk.spop.meta.processing.EnhancedProcessor;
import tk.spop.meta.util.Utils;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ReflectiveProcessor extends EnhancedProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Utils.set(Reflective.class.getName());
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
