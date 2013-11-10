package tk.spop.meta.tuple;

import static lombok.AccessLevel.NONE;
import lombok.Data;
import lombok.Getter;

@Data
@Getter(NONE)
public class T1<P1> implements Tuple {

    public final P1 _1;

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public Object[] toArray() {
        return new Object[] { _1 };
    }

}
