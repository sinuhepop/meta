package tk.spop.meta.immutable;

import java.util.*;

import lombok.*;

@EqualsAndHashCode
public final class ImmutableSet<T> implements Set<T>, ImmutableCollection<T> {

    @Delegate
    private final Set<T> delegate;

    public ImmutableSet() {
        this(Collections.<T> emptySet());
    }

    public ImmutableSet(Set<T> elements) {
        delegate = Collections.unmodifiableSet(new HashSet<>(elements));
    }

    private ImmutableSet(ImmutableSet<T> original, Collection<T> elements) {
        HashSet<T> all = new HashSet<>(original);
        all.addAll(elements);
        delegate = Collections.unmodifiableSet(all);
    }

    public ImmutableSet<T> with(@SuppressWarnings("unchecked") T... elements) {
        return new ImmutableSet<>(this, Arrays.asList(elements));
    }

    public ImmutableSet<T> with(Collection<T> elements) {
        return new ImmutableSet<>(this, elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
