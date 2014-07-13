package tk.spop.meta.util;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import lombok.val;

public class Utils {

    @SafeVarargs
    public static final <T> Set<T> set(T... elements) {
        val set = new ConcurrentSkipListSet<T>();
        for (val el : elements) {
            set.add(el);
        }
        return set;
    }

}
