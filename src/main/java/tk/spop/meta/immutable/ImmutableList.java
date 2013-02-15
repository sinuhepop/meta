package tk.spop.meta.immutable;

import java.util.*;

import lombok.*;

@EqualsAndHashCode
public final class ImmutableList<T> implements List<T>, ImmutableCollection<T> {

    @Delegate
    private final List<T> delegate;

    public ImmutableList() {
        this(Collections.<T> emptyList());
    }

    public ImmutableList(List<T> elements) {
        delegate = Collections.unmodifiableList(new ArrayList<>(elements));
    }

    private ImmutableList(ImmutableList<T> original, Collection<T> elements) {
        ArrayList<T> all = new ArrayList<>(original);
        all.addAll(elements);
        delegate = Collections.unmodifiableList(all);
    }

    public ImmutableList<T> with(@SuppressWarnings("unchecked") T... elements) {
        return new ImmutableList<>(this, Arrays.asList(elements));
    }

    public ImmutableList<T> with(Collection<T> elements) {
        return new ImmutableList<>(this, elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

}
