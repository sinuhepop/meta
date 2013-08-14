package tk.spop.meta.collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Tree<T extends Comparable<T>> {

    private static final Tree<?> EMPTY = new Tree<>(null, false, null, null);

    private final T value;
    private final boolean red;
    private final Tree<T> left;
    private final Tree<T> right;

    @SuppressWarnings("unchecked")
    public static <S extends Comparable<S>> Tree<S> empty() {
        return (Tree<S>) EMPTY;
    }
}
