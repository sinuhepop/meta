package tk.spop.meta.reflect.writer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.val;

@Data
public class ClassInfo {

    private final String name;
    private final List<MemberInfo> members = new ArrayList<MemberInfo>();

    public void add(MemberInfo member) {
        members.add(member);
    }

    public void checkNames() {
        Collections.sort(members);
        val ex = new MemberCollisionException();
        MemberInfo previous = null;
        for (val member : members) {
            if (previous != null && member.compareTo(previous) == 0) {
                ex.add(previous, member);
            }
            previous = member;
        }
        ex.throwIfNotEmpty();
    }

    @SuppressWarnings("unchecked")
    public <T extends MemberInfo> List<T> getOfType(Class<T> type) {
        val list = new ArrayList<T>();
        for (val member : members) {
            if (type.isInstance(member)) {
                list.add((T) member);
            }
        }
        Collections.sort(list);
        return list;
    }

    public void rename(MemberInfo member, String name) {
        for (val m : members) {
            if (m.sameSignature(member)) {
                m.setName(name);
                return;
            }
        }
        throw new IllegalArgumentException("Member not found: " + member);
    }

}
