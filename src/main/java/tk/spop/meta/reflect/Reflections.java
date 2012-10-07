package tk.spop.meta.reflect;

import java.lang.reflect.*;

public class Reflections {

    public static Field field(Class<?> clss, String name) {
        try {
            return clss.getField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method method(Class<?> clss, String name, Class<?>... args) {
        try {
            return clss.getMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Constructor<T> constructor(Class<T> clss, Class<?>... args) {
        try {
            return clss.getConstructor(args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
