package tk.spop.meta.reflect.writer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FieldInfo extends MemberInfo {

    private final String name;
    private final boolean isStatic;
    private final TypeInfo type;
}
