import java.io.Serializable;
import java.util.*;

import tk.spop.meta.Meta;
import tk.spop.meta.iface.Interface;

@Interface(pkg = "tk.spop.meta.test.iface", name = "IPojo")
public class Pojo<Z, X extends Number & Serializable, Y extends List<? super Meta>> {

    public String a(int a, Integer b) {
        return null;
    }

    public void b() {
    }

    public List<String> c(Integer[] a) {
        return null;
    }

    public X d() {
        return null;
    }

    public List<X> e() {
        return null;
    }

    public List<? extends X> f() {
        return null;
    }

    public List<Map<? extends Comparable<Y>, X>> g() {
        return null;
    }

    public <T> T first(Iterable<T> iterable) {
        return null;
    }
}