package tk.spop.meta.x;

import tk.spop.meta.immutable.Immutable;

public abstract class LazyValue<T> implements Immutable {

    private boolean memoized = false;
    private T value;

    public T get() {
        if (!memoized) {
            value = evaluate();
            memoized = true;
        }
        return value;
    }

    protected abstract T evaluate();

}
