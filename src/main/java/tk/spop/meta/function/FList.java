package tk.spop.meta.function;

import java.util.*;

import lombok.*;
import tk.spop.meta.reflect.model.PropertyAccessor;

@RequiredArgsConstructor
public class FList<C> {

    @Getter
    private final List<C> list;

    public <T> FList<T> map(PropertyAccessor<C, T> accessor) {
        List<T> result = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            C target = list.get(i);
            T x = accessor.get(target);
            result.set(i, x);
        }
        return new FList<>(result);
    }

    public <T extends Comparable<T>> FList<C> sort(@SuppressWarnings("unchecked") PropertyAccessor<C, T>... accessors) {
        List<C> copy = new ArrayList<>(list);
        Collections.sort(copy, Comparables.comparator(accessors));
        return new FList<>(copy);
    }

    public FList<C> filter(PropertyAccessor<C, Boolean> accessor) {
        List<C> result = new ArrayList<>();
        for (C el : list) {
            if (accessor.get(el)) {
                result.add(el);
            }
        }
        return new FList<>(result);
    }

    public <T> Map<T, C> toMap(PropertyAccessor<C, T> accessor) {
        Map<T, C> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            C target = list.get(i);
            T x = accessor.get(target);
            map.put(x, target);
        }
        return map;
    }

    public <T> Map<T, List<C>> toListMap(PropertyAccessor<C, T> accessor) {
        Map<T, List<C>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            C target = list.get(i);
            T x = accessor.get(target);
            List<C> l = map.get(x);
            if (l == null) {
                l = new ArrayList<C>();
                map.put(x, l);
            }
            l.add(target);
        }
        return map;
    }

}
