package tk.spop.meta.reflect.writer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class MemberInfo implements Comparable<MemberInfo> {

    private String name;

    @Override
    public int compareTo(MemberInfo o) {
        return name.compareTo(o.name);
    }

    public abstract boolean sameSignature(MemberInfo o);

}
