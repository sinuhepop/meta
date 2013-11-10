package tk.spop.meta.reflect.writer.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MethodInfo extends MemberInfo {

    private final boolean isStatic;
    private final TypeInfo returnType;
    private final List<TypeInfo> types;

    public MethodInfo(String name, boolean isStatic, TypeInfo returnType, List<TypeInfo> types) {
        super(name);
        this.isStatic = isStatic;
        this.returnType = returnType;
        this.types = types;
    }

    @Override
    public boolean sameSignature(MemberInfo o) {
        return o instanceof MethodInfo //
                && getName().equals(o.getName()) //
                && TypeInfo.sameSignature(types, ((MethodInfo) o).types);
    }

    @Override
    public String toString() {
        return (isStatic ? "static " : "") + returnType + " " + getName() + types;
    }
}
