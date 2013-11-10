package tk.spop.meta;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tk.spop.meta.tuple.T1;
import tk.spop.meta.tuple.T2;
import tk.spop.meta.tuple.T3;
import tk.spop.meta.tuple.Tuple;
import tk.spop.meta.tuple.Tx;

@NoArgsConstructor(access = AccessLevel.NONE)
public class Meta {

    public static <P1> T1<P1> t(P1 p1) {
        return new T1<P1>(p1);
    }

    public static <P1, P2> T2<P1, P2> t(P1 p1, P2 p2) {
        return new T2<P1, P2>(p1, p2);
    }

    public static <P1, P2, P3> T3<P1, P2, P3> t(P1 p1, P2 p2, P3 p3) {
        return new T3<P1, P2, P3>(p1, p2, p3);
    }

    public static Tuple t(Object... args) {
        switch (args.length) {
            case 1:
                return t(args[0]);
            case 2:
                return t(args[0], args[1]);
            case 3:
                return t(args[0], args[1], args[2]);
            default:
                return new Tx(args);
        }
    }

}
