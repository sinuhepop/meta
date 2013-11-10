package tk.spop.meta.reflect.writer;

import java.io.File;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import tk.spop.meta.reflect.model.Constructor;
import tk.spop.meta.reflect.model.Method;
import tk.spop.meta.reflect.writer.model.ClassInfo;
import tk.spop.meta.reflect.writer.model.ConstructorInfo;
import tk.spop.meta.reflect.writer.model.FieldInfo;
import tk.spop.meta.reflect.writer.model.MethodInfo;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;

@RequiredArgsConstructor
public class ReflectiveWriter {

    public static final String SUFFIX = "$";
    public static final int PFS = JMod.PUBLIC | JMod.STATIC | JMod.FINAL;

    private final File path;

    @SneakyThrows
    public void write(ClassInfo info) {

        info.checkNames();
        val model = new JCodeModel();
        val type = model._class(info.getName() + SUFFIX);

        for (val m : info.getOfType(ConstructorInfo.class)) {
            addConstructor(type, m);
        }
        for (val m : info.getOfType(MethodInfo.class)) {
            addMethod(type, m);
        }
        for (val m : info.getOfType(FieldInfo.class)) {
            addField(type, m);
        }

        model.build(path);
    }

    protected void addConstructor(JDefinedClass type, ConstructorInfo m) {
        type.field(PFS, Constructor.class, m.getName(), JExpr._null());
    }

    protected void addMethod(JDefinedClass type, MethodInfo m) {
        type.field(PFS, Method.class, m.getName(), JExpr._null());
    }

    protected void addField(JDefinedClass type, FieldInfo m) {
        type.field(PFS, Method.class, m.getName(), JExpr._null());
    }

}
