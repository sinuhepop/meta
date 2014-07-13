package tk.spop.meta.tuple;

import lombok.Data;

@Data
public class T0 implements Tuple {

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[] {};
    }

}
