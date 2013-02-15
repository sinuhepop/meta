package tk.spop.meta.immutable;

import java.util.*;

import lombok.*;

@EqualsAndHashCode
public final class ImmutableMap<K, V> implements Map<K, V>, StructurallyImmutable {

    @Delegate
    private final Map<K, V> delegate;

    public ImmutableMap() {
        this(Collections.<K, V> emptyMap());
    }

    public ImmutableMap(Map<K, V> elements) {
        delegate = Collections.unmodifiableMap(new HashMap<>(elements));
    }

    private ImmutableMap(ImmutableMap<K, V> original, Map<K, V> elements) {
        HashMap<K, V> all = new HashMap<>(original);
        all.putAll(elements);
        delegate = Collections.unmodifiableMap(all);
    }

    public ImmutableMap<K, V> with(K key, V value) {
        Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return new ImmutableMap<>(this, map);
    }

    public ImmutableMap<K, V> with(Map<K, V> elements) {
        return new ImmutableMap<>(this, elements);
    }

}
