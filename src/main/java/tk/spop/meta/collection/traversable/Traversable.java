package tk.spop.meta.collection.traversable;

public interface Traversable<T> extends Iterable<T> {

    @Override
    TraverserImpl<T> iterator();

}
