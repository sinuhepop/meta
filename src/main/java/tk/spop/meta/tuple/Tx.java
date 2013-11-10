package tk.spop.meta.tuple;

import static lombok.AccessLevel.NONE;
import lombok.Data;
import lombok.Getter;

@Data
@Getter(NONE)
public class Tx implements Tuple {

    private final Object[] array;

    @Override
    public int getSize() {
        return array.length;
    }

    @Override
    public Object[] toArray() {
        return array.clone();
    }
}
