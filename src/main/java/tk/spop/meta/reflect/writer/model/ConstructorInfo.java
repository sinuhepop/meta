package tk.spop.meta.reflect.writer.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConstructorInfo extends MemberInfo {

    public static final String DEFAULT_CONSTRUCTOR_NAME = "_";

    private final List<TypeInfo> types;

    public ConstructorInfo(String name, List<TypeInfo> types) {
        super(name);
        this.types = types;
    }

    @Override
    public boolean sameSignature(MemberInfo o) {
        return o instanceof ConstructorInfo //
                && TypeInfo.sameSignature(types, ((ConstructorInfo) o).types);
    }

}
