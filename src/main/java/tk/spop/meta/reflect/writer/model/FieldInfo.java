package tk.spop.meta.reflect.writer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FieldInfo extends MemberInfo {

    private final boolean isStatic;
    private final TypeInfo type;

    public FieldInfo(String name, boolean isStatic, TypeInfo type) {
        super(name);
        this.isStatic = isStatic;
        this.type = type;
    }

    @Override
    public boolean sameSignature(MemberInfo o) {
        return o instanceof FieldInfo && getName().equals(o.getName());
    }
}
