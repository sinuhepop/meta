package tk.spop.meta.tuple;

@SuppressWarnings("unchecked")
public class T1<P1> extends Tuple {

    protected T1(Object... args) {
        super(args);
    }

    public T1(P1 p1) {
        super(p1);
    }

    public P1 _1() {
        return (P1) get(0);
    }

}
