package tk.spop.meta.x;

import tk.spop.meta.immutable.Immutable;

public abstract class SyncLazyValue<T> implements Immutable {

    private transient boolean memoized = false;
    private T value;

    public T get() {
        if (!memoized) {
            synchronized (this) {
                if (!memoized) {
                    value = evaluate();
                    memoized = true;
                }
            }
        }
        return value;
    }

    protected abstract T evaluate();

}
