package tk.spop.meta;

import static javax.tools.StandardLocation.*;

import java.io.File;
import java.util.*;

import javax.annotation.processing.Processor;
import javax.tools.*;

import lombok.*;

import org.junit.*;

public abstract class AptTests {

    @SuppressWarnings("serial")
    protected static final Map<StandardLocation, File> locations = new HashMap<StandardLocation, File>() {
        {
            put(SOURCE_PATH, new File("src/test/source"));
            put(CLASS_OUTPUT, new File("target/test/output"));
            put(SOURCE_OUTPUT, new File("target/test/generated"));
        }
    };

    @Before
//    @After
    public void clear() {
        for (val path : Arrays.asList(CLASS_OUTPUT, SOURCE_OUTPUT)) {
            val folder = locations.get(path);
            deleteFolder(folder);
            folder.mkdirs();
        }
    }

    protected CompilationResult compile(Processor processor, String... classes) {
        File path = new File(locations.get(SOURCE_PATH), processor.getClass().getPackage().getName().replace('.', '/'));
        return compile(Arrays.asList(processor), path, classes);
    }

    @SneakyThrows
    protected CompilationResult compile(List<Processor> processors, File path, String... classes) {

        val sources = new ArrayList<File>();
        for (String clss : classes) {
            String fileName = clss.replace('.', '/') + ".java";
            File file = new File(path, fileName);
            sources.add(file);
        }

        val compiler = ToolProvider.getSystemJavaCompiler();
        val diagnostics = new DiagnosticCollector<JavaFileObject>();

        @Cleanup val fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        for (val entry : locations.entrySet()) {
            fileManager.setLocation(entry.getKey(), Arrays.asList(entry.getValue()));
        }

        val units = fileManager.getJavaFileObjectsFromFiles(sources);
        val task = compiler.getTask(null, fileManager, diagnostics, null, null, units);
        task.setProcessors(processors);

        boolean ok = task.call();
        val generated = getFiles(locations.get(SOURCE_OUTPUT));
        val result = new CompilationResult(ok, diagnostics.getDiagnostics(), generated);
        System.out.println(result);
        return result;
    }

    protected List<File> getFiles(File folder) {
        val list = new ArrayList<File>();
        for (val file : folder.listFiles()) {
            if (file.isDirectory()) {
                list.addAll(getFiles(file));
            } else {
                list.add(file);
            }
        }
        return list;
    }

    protected void deleteFolder(File folder) {
        for (val file : folder.listFiles()) {
            if (file.isDirectory()) {
                deleteFolder(file);
            }
            file.delete();
        }
    }

}
