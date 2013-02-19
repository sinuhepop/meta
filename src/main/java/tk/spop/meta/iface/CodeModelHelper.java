package tk.spop.meta.iface;

import java.util.*;

import javax.lang.model.element.*;
import javax.lang.model.type.*;

import lombok.*;

import com.sun.codemodel.*;

@RequiredArgsConstructor
public class CodeModelHelper {

    private final JCodeModel model;
    private final Map<String, JTypeVar> genericMap = new HashMap<>();

    public JType getType(TypeMirror mirror) {
        switch (mirror.getKind()) {
            case ARRAY:
                val arrayType = (ArrayType) mirror;
                val comp = getType(arrayType.getComponentType());
                return comp.array();
            case BOOLEAN:
                return model.BOOLEAN;
            case BYTE:
                return model.BYTE;
            case CHAR:
                return model.CHAR;
            case DECLARED:
                val type = (DeclaredType) mirror;
                val el = (TypeElement) type.asElement();
                JClass clss = model.ref(el.getQualifiedName().toString());
                for (TypeMirror tm : type.getTypeArguments()) {
                    val t = getType(tm);
                    clss = clss.narrow(t);
                }
                return clss;
            case DOUBLE:
                return model.DOUBLE;
            case ERROR:
                break;
            case EXECUTABLE:
                break;
            case FLOAT:
                return model.FLOAT;
            case INT:
                return model.INT;
            case LONG:
                return model.LONG;
            case NONE:
                break;
            case NULL:
                return model.NULL;
            case OTHER:
                break;
            case PACKAGE:
                break;
            case SHORT:
                return model.SHORT;
            case TYPEVAR:
                val typeVariable = (TypeVariable) mirror;
                val id = typeVariable.toString();
                val typeVar = genericMap.get(id);
                return typeVar;
            case UNION:
                break;
            case VOID:
                return model.VOID;
            case WILDCARD:
                val wildcardType = (WildcardType) mirror;
                val exBound = wildcardType.getExtendsBound();
                if (exBound == null) {
                    val superBound = wildcardType.getSuperBound();
                    if (superBound == null) {
                        return model.wildcard();
                    } else {
                        val bound = (JClass) getType(superBound);
                        return new JTypeWildcardSuper(bound);
                    }
                } else {
                    val bound = (JClass) getType(exBound);
                    return bound.wildcard();
                }
            default:
                break;
        }
        throw new RuntimeException("Kind not supported: " + mirror.getKind());
    }

    public void generify(Parameterizable element, JGenerifiable generifiable) {
        for (val typeParameter : element.getTypeParameters()) {
            val bounds = new JTypeMultiple(null);
            for (val bound : typeParameter.getBounds()) {
                val boundClass = (JClass) getType(bound);
                bounds.bound(boundClass);
            }
            val typeVar = generifiable.generify(typeParameter.getSimpleName().toString(), bounds);
            addGeneric(typeVar);
        }
    }

    private void addGeneric(JTypeVar type) {
        String id = type.name();
        genericMap.put(id, type);
    }

}
