package tk.spop.meta.reflect.writer.builder;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.val;
import tk.spop.meta.reflect.writer.model.ClassInfo;
import tk.spop.meta.reflect.writer.model.ConstructorInfo;
import tk.spop.meta.reflect.writer.model.FieldInfo;
import tk.spop.meta.reflect.writer.model.MethodInfo;
import tk.spop.meta.reflect.writer.model.TypeInfo;

@Data
public class ReflectionBuilder {

    private final ClassInfo delegate;
    private final boolean onlyPublic;

    public ReflectionBuilder(Class<?> clss, boolean onlyPublic) {

        delegate = new ClassInfo(clss.getName());
        this.onlyPublic = onlyPublic;

        for (val m : clss.getDeclaredConstructors())
            if (isValid(m)) //
                delegate.add(new ConstructorInfo( //
                        ConstructorInfo.DEFAULT_CONSTRUCTOR_NAME, //
                        getTypes(m.getGenericParameterTypes())));

        for (val m : clss.getDeclaredFields())
            if (isValid(m)) //
                delegate.add(new FieldInfo( //
                        m.getName(), //
                        isStatic(m.getModifiers()), //
                        getType(m.getGenericType())));

        for (val m : clss.getDeclaredMethods())
            if (isValid(m)) //
                delegate.add(new MethodInfo( //
                        m.getName(), //
                        isStatic(m.getModifiers()), //
                        getType(m.getGenericReturnType()), //
                        getTypes(m.getGenericParameterTypes())));
    }

    public ReflectionBuilder renameField(String name, String oldName) {
        // delegate.rename(new FieldInfo(), name);
        return this;
    }

    public ReflectionBuilder renameConstructor(String name, Class<?>... parameterTypes) {
        delegate.rename(new ConstructorInfo(null, getTypes(parameterTypes)), name);
        return this;
    }

    public ReflectionBuilder renameMethod(String name, String oldName, Class<?>... parameterTypes) {
        delegate.rename(new MethodInfo(oldName, false, null, getTypes(parameterTypes)), name);
        return this;
    }

    private static List<TypeInfo> getTypes(Type[] types) {
        val list = new ArrayList<TypeInfo>();
        for (val tv : types) {
            list.add(getType(tv));
        }
        return list;
    }

    private static TypeInfo getType(Type type) {
        return new TypeInfo(type.getTypeName());
    }

    private boolean isValid(Member m) {
        val mods = m.getModifiers();
        return !m.isSynthetic() && //
                (onlyPublic ? Modifier.isPublic(mods) : !isPrivate(mods));
    }

}
