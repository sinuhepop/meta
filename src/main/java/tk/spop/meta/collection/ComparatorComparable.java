package tk.spop.meta.collection;

import java.util.Comparator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ComparatorComparable<T> implements Comparable<T> {

    private final Comparator<T> comparator;

    @Override
    public int compareTo(T o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
