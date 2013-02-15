package tk.spop.meta.modelwriter;

import java.io.IOException;
import java.util.*;

import javax.lang.model.element.Modifier;
import javax.tools.*;

import lombok.val;

import org.junit.Test;

public class ModelWriterTests {

    @Test
    public void test1() {
        check("public class A {}");
    }

    @Test
    public void test2() {

        MwType type = new MwType(new MwTypeReference("tk.spop.Test"));
        type.addGeneratedAnnotation(this.getClass(), "This is just a test.");
        type.add(Modifier.PUBLIC);

        type.add(new MwMethod("write", new MwTypeReference(String.class)) //
                .addException(new MwTypeReference(IOException.class)) //
                .addParamenter(new MwVariable("ctx", new MwTypeReference(Context.class))));

        String result = type.toString();
        System.out.println(result);
    }

    private void check(String clss) {

        System.out.println(clss + "\n\n\n\n");

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(clss));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        for (val diagnostic : diagnostics.getDiagnostics()) {
            System.out.println(diagnostic.getKind() + ":\t Line [" + diagnostic.getLineNumber() + "] \t Position [" + diagnostic.getPosition() + "]\t"
                    + diagnostic.getMessage(Locale.ROOT));
        }

    }
}
