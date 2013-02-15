package tk.spop.meta;

import java.io.*;
import java.util.ArrayList;

import lombok.*;

import org.junit.Test;

import tk.spop.meta.immutable.*;

import com.sun.codemodel.*;

public class CodeModelTests {

    @Test
    @SneakyThrows
    public void makeClass() {

        JCodeModel model = new JCodeModel();
        val type = model._class("tk.spop.meta.Test");
        type._extends(model.ref(ArrayList.class).narrow(Integer.class));
        type._implements(StructurallyImmutable.class);
        type._implements(model.ref("tk.spop.meta.Calamar"));

        val method = type.method(JMod.PROTECTED | JMod.ABSTRACT, type, "of");
        method.body().directStatement("log.debug(null); return this;");
        method.javadoc().add("returns this instance");

        StringCodeWriter writer = new StringCodeWriter();
        model.build(writer);
        System.out.println(writer.toString());
    }

    @Test
    @SneakyThrows
    public void makeInterface() {

        JCodeModel model = new JCodeModel();
        val type = model._class("tk.spop.meta.Test", ClassType.INTERFACE);

        val method = type.method(JMod.NONE, model.VOID, "x");
        val typeT = method.generify("T");
        val typeS = method.generify("S", Iterable.class);

        val par1 = model.ref("tk.spop.ImmutableMap").narrow(typeT).narrow(model.wildcard());
        method.param(par1, "map");
        method.param(typeS, "lololo");
        method.body()._return(model.NULL.dotclass());

        StringCodeWriter writer = new StringCodeWriter();
        model.build(writer);
        System.out.println(writer.toString());
    }

    @Test
    @SneakyThrows
    public void generics() {

        JCodeModel model = new JCodeModel();
        val type = model._class("tk.spop.meta.Test", ClassType.INTERFACE);
        
        type.generify("T", Immutable.class);

        val method = type.method(JMod.NONE, model.VOID, "x");
        val typeT = method.generify("T");
        val typeS = method.generify("S", Iterable.class);

        val par1 = model.ref("tk.spop.ImmutableMap").narrow(typeT).narrow(model.wildcard());
        method.param(par1, "map");
        method.param(typeS, "lololo");
        method.body()._return(model.NULL.dotclass());

        StringCodeWriter writer = new StringCodeWriter();
        model.build(writer);
        System.out.println(writer.toString());
    }

    private class StringCodeWriter extends CodeWriter {

        private final ByteArrayOutputStream stream = new ByteArrayOutputStream();

        @Override
        public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
            return stream;
        }

        @Override
        public void close() throws IOException {
            stream.close();
        }

        @Override
        @SneakyThrows
        public String toString() {
            return stream.toString("UTF-8");
        }
    }
}
