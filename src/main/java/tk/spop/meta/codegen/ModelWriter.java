package tk.spop.meta.codegen;

import java.io.*;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;

import lombok.*;

import com.sun.codemodel.*;

@RequiredArgsConstructor
public class ModelWriter {

    private final Filer filer;

    @SneakyThrows
    public void write(JCodeModel model, final Element... origin) {

        model.build(new CodeWriter() {

            private OutputStream os;

            @Override
            public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
                String name = fileName.substring(0, fileName.indexOf('.'));
                String qualifiedName = (pkg.name().equals("") ? "" : pkg.name() + ".") + name;
                val file = filer.createSourceFile(qualifiedName, origin);
                os = file.openOutputStream();
                return os;
            }

            @Override
            public void close() throws IOException {
                os.close();
            }
        });
    }

}
