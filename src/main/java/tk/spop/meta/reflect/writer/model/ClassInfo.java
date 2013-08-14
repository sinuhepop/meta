package tk.spop.meta.reflect.writer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import lombok.Data;
import lombok.val;

@Data
public class ClassInfo {

    private String name;

    private final PriorityQueue<MemberInfo> members = new PriorityQueue<MemberInfo>();

    public void add(MemberInfo member) {
        members.add(member);
    }

    public void checkNames() {
        val ex = new MemberCollisionException();
        MemberInfo previous = null;
        for (val member : members) {
            if (member.compareTo(previous) == 0) {
                ex.add(previous, member);
            }
            previous = member;
        }
        ex.throwIfNeeded();
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

}
