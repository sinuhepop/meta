package tk.spop.meta.modelwriter;

import java.util.*;

import lombok.*;

@Data
public class Context {

    private final SortedMap<String, MwTypeReference> importMap = new TreeMap<>();
    private final StringBuilder sb = new StringBuilder();

    public Context wr(MwTypeReference type) {
        val in = importMap.get(type.getName());
        if (in == null) {
            importMap.put(type.getName(), type);
            sb.append(type.getName());
        } else if (in.equals(type)) {
            sb.append(type.getName());
        } else {
            sb.append(type.getQualifiedName());
        }
        return this;
    }

    public Context wr(Object o) {
        sb.append(o);
        return this;
    }

    public Context removeEnd(String end) {
        if (sb.length() > end.length()) {
            int p = sb.length() - end.length();
            String currentEnd = sb.substring(p);
            if (currentEnd.equals(end)) {
                sb.delete(p, sb.length());
            }
        }
        return this;
    }

    public String getImports() {
        val s = new StringBuilder();
        val imports = new ArrayList<>(importMap.values());
        Collections.sort(imports);
        for (val i : imports) {
            if (!i.getPkg().equals("java.lang")) {
                s.append("import ").append(i.getQualifiedName()).append(";\n");
            }
        }
        return s.append('\n').toString();
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
