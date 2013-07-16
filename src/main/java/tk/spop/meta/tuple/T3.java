package tk.spop.meta.tuple;

@SuppressWarnings("unchecked")
public class T3<P1, P2, P3> extends T2<P1, P2> {

    protected T3(Object... args) {
        super(args);
    }

    public T3(P1 p1, P2 p2, P3 p3) {
        super(p1, p2, p3);
    }

    public P3 _3() {
        return (P3) get(2);
    }

}
