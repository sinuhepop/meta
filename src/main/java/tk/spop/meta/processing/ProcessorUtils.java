package tk.spop.meta.processing;

import java.util.*;

import javax.lang.model.element.*;
import javax.lang.model.type.*;

public class ProcessorUtils {

    public static List<VariableElement> getFields(TypeElement clss) {
        return getElements(clss, VariableElement.class, ElementKind.FIELD);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Element> List<T> getElements(Element element, Class<T> returnClass, ElementKind... kinds) {
        List<ElementKind> kindList = Arrays.asList(kinds);
        List<T> list = new ArrayList<>();
        for (Element e : element.getEnclosedElements()) {
            if (kindList.contains(e.getKind())) {
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

    public static boolean isInstanceOf(TypeElement clss, String superclassName) {

        if (clss == null) {
            return false;
        }

        if (clss.getQualifiedName().toString().equals(superclassName)) {
            return true;
        }

        for (TypeMirror type : clss.getInterfaces()) {

            if (isInstanceOf(asType(type), superclassName)) {
                return true;
            }
        }

        return isInstanceOf(getSuperclass(clss), superclassName);
    }

    public static TypeElement asType(TypeMirror type) {
        return (TypeElement) ((DeclaredType) type).asElement();
    }

    public static VariableElement asVariable(TypeMirror type) {
        return (VariableElement) ((TypeVariable) type).asElement();
    }

    public static boolean is(Element element, Modifier modifier) {
        return element.getModifiers().contains(modifier);
    }

    @Deprecated
    public static boolean isPrimitive(Element element) {
        return element.asType().getKind().isPrimitive();
    }

    public static boolean isPrimitive(TypeMirror type) {
        return type.getKind().isPrimitive();
    }

    public static String getClassName(TypeMirror element) {
        DeclaredType type = (DeclaredType) element;
        TypeElement typeElement = (TypeElement) type.asElement();
        return typeElement.getQualifiedName().toString();
    }

    public static TypeElement getAnnotation(Element element, TypeElement annotation) {
        for (AnnotationMirror mirror : element.getAnnotationMirrors()) {
            TypeElement currentAnnotation = (TypeElement) mirror.getAnnotationType().asElement();
            if (currentAnnotation.equals(annotation)) {
                return currentAnnotation;
            }
        }
        return null;
    }
}
