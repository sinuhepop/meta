package tk.spop.meta.collection;

import java.lang.reflect.Array;
import java.util.LinkedList;


public class Util {
    
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
    
    public static <T> T[] getArray(Iterable<T> iterable) {
        LinkedList<T> list = new LinkedList<>();
        for (T current : iterable) {
            list.add(current);
        }
        return list.toArray(Array.newInstance(componentType, length));
    }

}
