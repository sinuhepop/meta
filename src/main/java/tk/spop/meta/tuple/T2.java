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

}
