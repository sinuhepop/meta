package tk.spop.meta.collection;

import java.util.LinkedHashMap;
import java.util.concurrent.Callable;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class Cache<T> extends LinkedHashMap<Callable<T>, T> {

    private static final long serialVersionUID = 495247627959591440L;

    private final int maxEntries;

    @SneakyThrows
    public T call(Callable<T> callable) {
        if (containsKey(callable)) {
            return get(callable);
        }
        T value = callable.call();
        put(callable, value);
        return value;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<Callable<T>, T> eldest) {
        return size() > maxEntries;
    }

}
