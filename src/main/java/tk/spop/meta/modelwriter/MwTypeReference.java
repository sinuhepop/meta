package tk.spop.meta.modelwriter;

import java.util.*;

import lombok.*;

@Data
@RequiredArgsConstructor
public class MwTypeReference implements MwElement, Comparable<MwTypeReference> {

    private final String pkg;
    private final String name;
    private final List<MwTypeReference> generics = new ArrayList<>();

    public MwTypeReference(String name) {
        int p = name.lastIndexOf('.');
        this.name = name.substring(p + 1);
        pkg = (p >= 0) ? name.substring(0, p) : null;
    }

    public MwTypeReference(Class<?> clss) {
        this(clss.getName());
    }

    public String getQualifiedName() {
        return (pkg != null ? pkg + '.' : "") + getName();
    }

    @Override
    public void wr(Context ctx) {
        ctx.wr(getQualifiedName());
        if (!generics.isEmpty()) {
            ctx.wr('<');
            for (MwTypeReference ref : generics) {
                ref.wr(ctx);
                ctx.wr(", ");
            }
            ctx.removeEnd(", ");
            ctx.wr('>');
        }
    }

    @Override
    public int compareTo(MwTypeReference o) {
        return getQualifiedName().compareTo(o.getQualifiedName());
    }

}
