package tk.spop.meta.reflect.writer;

import java.io.File;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import tk.spop.meta.reflect.model.Constructor;
import tk.spop.meta.reflect.writer.model.ClassInfo;
import tk.spop.meta.reflect.writer.model.ConstructorInfo;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMod;

@RequiredArgsConstructor
public class ReflectiveWriter {

    public static final String SUFFIX = "$";

    private final File path;

    @SneakyThrows
    public void write(ClassInfo info) {

        info.checkNames();
        val model = new JCodeModel();
        val type = model._class(info.getName() + SUFFIX);

        for (val ctor : info.getOfType(ConstructorInfo.class)) {
            addConstructor(type, ctor);
        }

        model.build(path);
    }

    protected void addConstructor(JDefinedClass type, ConstructorInfo ctor) {
        type.field(JMod.STATIC | JMod.FINAL, Constructor.class, ctor.getName(), JExpr._null());
    }

}
