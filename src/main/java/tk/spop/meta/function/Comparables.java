package tk.spop.meta.function;

import java.util.Comparator;

import lombok.val;
import tk.spop.meta.reflect.impl.GetterImpl;
import tk.spop.meta.reflect.model.PropertyAccessor;

public class Comparables {

    public static <T> Comparable<T> inv(final Comparable<T> comparable) {
        return new Comparable<T>() {
            @Override
            public int compareTo(T o) {
                return -comparable.compareTo(o);
            }
        };
    }

    @SafeVarargs
    public static <C, T extends Comparable<T>> Comparator<C> comparator(final PropertyAccessor<C, T>... accessors) {
        return new Comparator<C>() {
            @Override
            public int compare(C o1, C o2) {
                for (PropertyAccessor<C, T> accessor : accessors) {
                    val v1 = accessor.get(o1);
                    val v2 = accessor.get(o2);
                    int c = v1.compareTo(v2);
                    if (c != 0) {
                        return c;
                    }
                }
                return 0;
            }
        };
    }

    public static <C> PropertyAccessor<C, Boolean> inv(final PropertyAccessor<C, Boolean> accessor) {
        return new GetterImpl<C, Boolean>(accessor.getName() + "$inv", null, null, accessor.getPropertyName() + "$inv") {
            @Override
            public Boolean invoke(C target) {
                return !accessor.get(target);
            }
        };
    }
}
