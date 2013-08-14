package tk.spop.meta.reflect.writer.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MethodInfo extends MemberInfo {

    private final String name;
    private final boolean isStatic;
    private final TypeInfo returnType;
    private final List<TypeInfo> types;
}
