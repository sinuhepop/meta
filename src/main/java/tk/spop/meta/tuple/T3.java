package tk.spop.meta.tuple;

import static lombok.AccessLevel.NONE;
import lombok.Data;
import lombok.Getter;

@Data
@Getter(NONE)
public class T3<P1, P2, P3> implements Tuple {

    public final P1 _1;
    public final P2 _2;
    public final P3 _3;

    @Override
    public int getSize() {
        return 3;
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1, _2, _3 };
    }
}
