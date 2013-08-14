package tk.spop.meta.reflect.writer.model;

import java.util.ArrayList;
import java.util.List;

import lombok.val;
import tk.spop.meta.tuple.T2;
import tk.spop.meta.tuple.Tuple;

public class MemberCollisionException extends RuntimeException {

    private static final long serialVersionUID = -3665429377521903453L;

    private final List<T2<MemberInfo, MemberInfo>> list = new ArrayList<>();

    public void add(MemberInfo a, MemberInfo b) {
        list.add(Tuple.t(a, b));
    }

    @Override
    public String getMessage() {
        val sb = new StringBuilder();
        sb.append(list.size()).append(" collisions found.");
        for (val c : list) {
            sb.append("  \n").append(c._1()).append(" === ").append(c._2());
        }
        return sb.toString();
    }

    public void throwIfNeeded() {
        if (!list.isEmpty()) {
            throw this;
        }
    }

}
