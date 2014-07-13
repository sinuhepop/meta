package tk.spop.meta.tuple;

import static lombok.AccessLevel.NONE;
import lombok.Data;
import lombok.Getter;
import tk.spop.meta.Meta;

@Data
@Getter(NONE)
public class T2<P1, P2> implements Tuple {

    public final P1 _1;
    public final P2 _2;

    public T2<P2, P1> swap() {
        return Meta.t(_2, _1);
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2 };
    }

    public <X> T2<X, P2> with1(X x) {
        return Meta.t(x, _2);
    }

    public <X> T2<P1, X> with2(X x) {
        return Meta.t(_1, x);
    }

    public <X> T3<P1, P2, X> plus(X x) {
        return Meta.t(_1, _2, x);
    }
}
