package tk.spop.meta.modelwriter;

import java.util.*;

import lombok.Setter;

public class MwMethod extends MwVariable {

    private final List<MwVariable> parameters = new ArrayList<>();
    private final List<MwTypeReference> exceptions = new ArrayList<>();

    @Setter
    private MwElement body;

    public MwMethod(String name, MwTypeReference returnType) {
        super(name, returnType);
    }

    public MwMethod addParamenter(MwVariable parameter) {
        parameters.add(parameter);
        return this;
    }

    public MwMethod addException(MwTypeReference type) {
        exceptions.add(type);
        return this;
    }

    @Override
    public void wr(Context ctx) {
        super.wr(ctx);
        ctx.wr("(");
        for (MwVariable par : parameters) {
            par.wr(ctx);
            ctx.wr(", ");
        }
        ctx.removeEnd(", ").wr(")");

        if (!exceptions.isEmpty()) {
            ctx.wr(" throws ");
            for (MwTypeReference ex : exceptions) {
                ctx.wr(ex);
                ctx.wr(", ");
            }
            ctx.removeEnd(", ");
        }

        if (body == null) {
            ctx.wr(";\n\n");
        } else {
            ctx.wr(" {\n");
            body.wr(ctx);
            ctx.wr("}\n\n");
        }
    }

}
