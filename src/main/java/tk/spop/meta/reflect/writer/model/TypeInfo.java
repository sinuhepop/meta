package tk.spop.meta.reflect.writer.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.val;

@Data
public class TypeInfo {

    private final String name;
    private final List<TypeInfo> types = new ArrayList<>();

    public static boolean sameSignature(List<TypeInfo> a, List<TypeInfo> b) {
        val ai = a.iterator();
        val bi = b.iterator();
        while (ai.hasNext() && bi.hasNext()) {
            if (!ai.next().getName().equals(bi.next().getName())) {
                return false;
            }
        }
        return !ai.hasNext() && !bi.hasNext();
    }

    @Override
    public String toString() {
        val sb = new StringBuilder();
        val p = name.lastIndexOf('.');
        sb.append(p < 0 ? name : name.substring(p + 1));
        if (!types.isEmpty()) {
            sb.append('<');
            for (val t : types) {
                sb.append(t).append(", ");
            }
            sb.replace(sb.length() - 2, sb.length(), ">");
        }
        return sb.toString();
    }
}
