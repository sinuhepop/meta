package tk.spop.meta.modelwriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Generated;

import lombok.*;

@Getter
@Setter
public class MwType extends MwVariable {

    private final List<MwElement> elements = new ArrayList<>();
    private final List<MwTypeReference> interfaces = new ArrayList<>();
    private MwTypeReference superClass;
    private boolean iface = false;

    public MwType(MwTypeReference type) {
        super(type.getName(), type);
    }

    public void addGeneratedAnnotation(Class<?> generator, String comments) {
        add(new MwAnnotation(Generated.class) //
                .attr("value", generator.getName()) //
                .attr("date", new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ").format(new Date())) //
                .attr("comments", comments));
    }

    public void add(MwElement member) {
        elements.add(member);
    }

    public String getQualifiedName() {
        return type.getQualifiedName();
    }

    @Override
    public void wr(Context ctx) {
        writeAnnotations(ctx);
        writeModifiers(ctx);
        ctx.wr(iface ? "interface " : "class ");
        ctx.wr(type);
        // TODO: super, interfaces
        ctx.wr(" {\n\n");
        for (val member : elements) {
            member.wr(ctx);
        }
        ctx.wr("}\n");
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        write(sw);
        return sw.toString();
    }

    @SneakyThrows
    public void write(Writer writer) {
        Context ctx = new Context();
        wr(ctx);
        if (type.getPkg() != null) {
            writer.write("package " + type.getPkg() + ";\n\n");
        }
        writer.write(ctx.getImports());
        writer.write(ctx.getSb().toString());
    }

}
