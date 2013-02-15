package tk.spop.meta.modelwriter;

import java.util.*;
import java.util.Map.Entry;

import lombok.*;

@Data
@RequiredArgsConstructor
public class MwAnnotation implements MwElement {

    private final MwTypeReference type;
    private final Map<String, Object> attributes = new HashMap<>();

    public MwAnnotation(Class<?> clss) {
        this(new MwTypeReference(clss));
    }

    public MwAnnotation attr(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    @Override
    public void wr(Context ctx) {
        ctx.wr("@").wr(type);
        if (!attributes.isEmpty()) {
            ctx.wr("(");
            for (Entry<String, Object> entry : attributes.entrySet()) {
                ctx.wr(entry.getKey()).wr(" = ");
                print(ctx, entry.getValue());
                ctx.wr(", ");
            }
            ctx.removeEnd(", ").wr(") ");
        }
    }

    // TODO: ENUMS!!
    private void print(Context ctx, Object value) {
        if (value.getClass().isPrimitive()) {
            if (value instanceof Long) {
                ctx.wr(value + "L");
            } else if (value instanceof Float) {
                ctx.wr(value + "f");
            } else if (value instanceof Character) {
                ctx.wr("'" + value + "'");
            } else {
                ctx.wr(value + "");
            }
        } else if (value instanceof MwTypeReference) {
            ctx.wr((MwTypeReference) value).wr(".class");
        } else if (value instanceof Class) {
            ctx.wr(new MwTypeReference((Class<?>) value)).wr(".class");
        } else {
            ctx.wr('"' + value.toString() + '"');
        }
    }
}
