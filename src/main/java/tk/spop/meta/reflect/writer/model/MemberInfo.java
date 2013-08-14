package tk.spop.meta.reflect.writer.model;

public abstract class MemberInfo implements Comparable<MemberInfo> {

    @Override
    public int compareTo(MemberInfo o) {
        return o == null ? -1 : getName().compareTo(o.getName());
    }

    public abstract String getName();

}
