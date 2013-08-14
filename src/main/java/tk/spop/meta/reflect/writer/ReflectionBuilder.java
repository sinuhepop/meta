package tk.spop.meta.reflect.writer;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isStatic;

import java.lang.reflect.Member;
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
import tk.spop.meta.util.Utils;

@Data
public class ReflectionBuilder {

    private final Class<?> clss;

    public ClassInfo build() {

        val info = new ClassInfo();
        info.setName(clss.getName());

        for (val m : clss.getDeclaredConstructors())
            if (!isPrivate(m.getModifiers())) //
                info.add(new ConstructorInfo( //
                        getName(m), //
                        getTypes(m.getGenericParameterTypes())));

        for (val m : clss.getDeclaredFields())
            if (!isPrivate(m.getModifiers())) //
                info.add(new FieldInfo( //
                        getName(m), //
                        isStatic(m.getModifiers()), //
                        getType(m.getGenericType())));

        for (val m : clss.getDeclaredMethods())
            if (!isPrivate(m.getModifiers())) //
                info.add(new MethodInfo( //
                        getName(m), //
                        isStatic(m.getModifiers()), //
                        getType(m.getGenericReturnType()), //
                        getTypes(m.getGenericParameterTypes())));

        return info;
    }

    public ReflectionBuilder renameConstructor(String name, Class<?>... parameterTypes) {
        return this;
    }

    public ReflectionBuilder renameField(String newName, String oldName) {
        return this;
    }

    public ReflectionBuilder renameMethod(String name, String oldName, Class<?>... parameterTypes) {
        return this;
    }

    private static List<TypeInfo> getTypes(Type[] types) {
        val list = new ArrayList<TypeInfo>();
        for (val tv : types) {
            list.add(getType(tv));
        }
        return list;
    }

    private static TypeInfo getType(Type genericType) {
        return null;
    }

    private int kk = 1;

    private String getName(Member member) {
        return Utils.fromLastDot(member.getName()) + (kk++);
    }

}
