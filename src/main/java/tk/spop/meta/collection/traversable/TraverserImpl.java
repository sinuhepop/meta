package tk.spop.meta.collection.traversable;

import java.util.Iterator;

import lombok.Data;

@Data
public class TraverserImpl<T> implements Traverser<T> {

    private final Iterator<T> iterator;
    private int index = -1;
    private T current;

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        advance();
        return current;
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public Traverser<T> advance() {
        current = next();
        index++;
        return this;
    }

    @Override
    public boolean isFirst() {
        return index == 0;
    }

    @Override
    public boolean isLast() {
        return !hasNext();
    }

    @Override
    public TraverserImpl<T> iterator() {
        return this;
    }

}
