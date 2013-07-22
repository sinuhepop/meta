package tk.spop.meta.codegen;

import java.io.File;
import java.util.List;

import javax.tools.*;

import lombok.*;

@Data
public class CompilationResult {

    private final boolean ok;
    private final List<Diagnostic<? extends JavaFileObject>> diagnostics;
    private final List<File> generated;

    @Override
    public String toString() {
        val sb = new StringBuilder();
        for (val d : diagnostics) {
            sb.append("\n" + d);
        }
        for (val file : generated) {
            sb.append("\n--> " + file);
        }
        return sb.toString();
    }

    public boolean has(Diagnostic.Kind kind, int line) {
        for (val d : diagnostics) {
            if (d.getKind().equals(kind) && d.getLineNumber() == line) {
                return true;
            }
        }
        return false;
    }
}