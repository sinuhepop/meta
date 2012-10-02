package tk.spop.meta;

import java.util.*;

import javax.lang.model.element.*;
import javax.lang.model.type.*;


public class ProcessorUtils {

    public static List<VariableElement> getFields(TypeElement clss) {
        return getElements(clss, ElementKind.FIELD, VariableElement.class);
    }


    @SuppressWarnings("unchecked")
    public static <T extends Element> List<T> getElements(Element element, ElementKind kind, Class<T> returnClass) {
        List<T> list = new ArrayList<>();
        for (Element e : element.getEnclosedElements()) {
            if (e.getKind().equals(kind)) {
                list.add((T) e);
            }
        }
        return list;
    }


    public static TypeElement getSuperclass(TypeElement clss) {
        TypeMirror type = clss.getSuperclass();
        if ((type instanceof DeclaredType)) {
            return (TypeElement) ((DeclaredType) type).asElement();
        }
        return null;
    }


    public static boolean is(Element element, Modifier modifier) {
        return element.getModifiers().contains(modifier);
    }


    public static boolean isPrimitive(Element element) {
        return element.asType().getKind().isPrimitive();
    }


    public static String getClassName(VariableElement element) {
        DeclaredType type = (DeclaredType) element.asType();
        TypeElement typeElement = (TypeElement) type.asElement();
        return typeElement.getQualifiedName().toString();
    }
}
