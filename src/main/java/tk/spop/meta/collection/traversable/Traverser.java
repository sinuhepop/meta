package tk.spop.meta.collection.traversable;

import java.util.Iterator;

public interface Traverser<T> extends Iterator<T>, Traversable<T> {

    int getIndex();

    T getCurrent();

    Traverser<T> advance();

    boolean isFirst();

    boolean isLast();

}
