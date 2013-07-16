package tk.spop.meta.tuple;

import java.util.Arrays;

import tk.spop.meta.immutable.Persistent;

public class Tuple implements Persistent {

    private final Object[] array;

    protected Tuple(Object... array) {
        this.array = array;
    }

    public int getSize() {
        return array.length;
    }

    public Object get(int pos) {
        return array[pos];
    }

    public Object[] toArray() {
        return array.clone();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tuple && Arrays.equals(array, ((Tuple) o).array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static <P1> T1<P1> t(P1 p1) {
        return new T1<P1>(p1);
    }

    public static <P1, P2> T2<P1, P2> t(P1 p1, P2 p2) {
        return new T2<P1, P2>(p1, p2);
    }

    public static <P1, P2, P3> T3<P1, P2, P3> t(P1 p1, P2 p2, P3 p3) {
        return new T3<P1, P2, P3>(p1, p2, p3);
    }

    public static Tuple of(Object... args) {
        switch (args.length) {
            case 1:
                return t(args[0]);
            case 2:
                return t(args[0], args[1]);
            case 3:
                return t(args[0], args[1], args[2]);
            default:
                return new Tuple(args);
        }
    }

}
