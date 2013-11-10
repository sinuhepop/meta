package tk.spop.meta.tuple;

import tk.spop.meta.immutable.Persistent;

public interface Tuple extends Persistent {

    int getSize();

    Object[] toArray();

}
