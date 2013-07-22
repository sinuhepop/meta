package tk.spop.meta.collection.traversable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Traversers {

    public static <T> Traverser<T> of(Iterator<T> iterator) {
        if (iterator == null) {
            iterator = Collections.emptyIterator();
        }
        return new TraverserImpl<T>(iterator);
    }

    public static <T> Traverser<T> of(Iterable<T> iterable) {
        if (iterable == null) {
            iterable = Collections.emptyList();
        }
        return of(iterable.iterator());
    }

    public static <T> Traverser<T> of(T element) {
        return of(Arrays.asList(element));
    }
}
