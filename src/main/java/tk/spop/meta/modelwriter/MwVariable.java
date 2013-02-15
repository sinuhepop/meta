package tk.spop.meta.modelwriter;

import java.util.*;

import javax.lang.model.element.Modifier;

import lombok.*;

@RequiredArgsConstructor
public class MwVariable implements MwElement {

    protected final String name;
    protected final MwTypeReference type;
    protected final Set<Modifier> modifiers = new TreeSet<>();
    protected final List<MwAnnotation> annotations = new ArrayList<>();

    public MwVariable add(Modifier modifier) {
        modifiers.add(modifier);
        return this;
    }

    public MwVariable add(MwAnnotation annotation) {
        annotations.add(annotation);
        return this;
    }

    protected void writeModifiers(Context ctx) {
        for (Modifier m : modifiers) {
            ctx.wr(m + " ");
        }
    }

    protected void writeAnnotations(Context ctx) {
        for (val annotation : annotations) {
            annotation.wr(ctx);
            ctx.wr('\n');
        }
    }

    @Override
    public void wr(Context ctx) {
        writeAnnotations(ctx);
        writeModifiers(ctx);
        ctx.wr(type).wr(" ").wr(name);
    }

}
