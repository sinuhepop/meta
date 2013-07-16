package tk.spop.meta.tuple;

@SuppressWarnings("unchecked")
public class T2<P1, P2> extends T1<P1> {

    protected T2(Object... args) {
        super(args);
    }

    public T2(P1 p1, P2 p2) {
        super(p1, p2);
    }

    public P2 _2() {
        return (P2) get(1);
    }

}
