package tk.spop.meta.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.PriorityQueue;
import java.util.Queue;

import lombok.SneakyThrows;
import tk.spop.meta.tuple.KeyValue;

public abstract class Reflections {

    @SneakyThrows
    public static <T> T _new(Class<T> clss, Object... args) {
        return _ctor(clss, args).newInstance(args);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> Constructor<T> _ctor(Class<T> clss, Object... args) {
        Queue<KeyValue<Integer, Constructor>> ctors = new PriorityQueue<>();
        for (Constructor c : clss.getDeclaredConstructors()) {
            if (c.getParameterTypes().length == args.length) {
                ctors.add(KeyValue.of(0, c));
            }
        }
        return ctors.size() > 0 ? ctors.peek().getValue() : null;
    }

    public static Field field(Class<?> c, String name) {
        return null;
    }

    @SneakyThrows
    public static Object get(Object target, Field field) {
        field.setAccessible(true);
        return field.get(target);
    }

    public static Class<?>[] _classes(Object... o) {
        Class<?>[] c = new Class<?>[o.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = o[i] == null ? Object.class : o[i].getClass();
        }
        return c;
    }

}
