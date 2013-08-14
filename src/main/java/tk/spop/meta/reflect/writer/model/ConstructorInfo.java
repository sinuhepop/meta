package tk.spop.meta.reflect.writer.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ConstructorInfo extends MemberInfo {

    public static final String DEFAULT_CONSTRUCTOR_NAME = "_";

    private final String name;
    private final List<TypeInfo> types;
}
