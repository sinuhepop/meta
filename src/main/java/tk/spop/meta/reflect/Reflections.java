package tk.spop.meta.reflect;

import java.lang.reflect.*;

import lombok.SneakyThrows;

public class Reflections {
    
//    public static final Void voidInstance = getVoidObject();
    

    public static Field field(Class<?> clss, String name) {
        try {
            return clss.getField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Method method(Class<?> clss, String name, Class<?>[] args) {
        try {
            return clss.getMethod(name, args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Constructor<T> constructor(Class<T> clss, Class<?>[] args) {
        try {
            return clss.getConstructor(args);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
//    @SneakyThrows
//    public static Void getVoidObject() {
//        Constructor<Void> ctr = Void.class.getConstructor(new Class<?>[0]);
//        ctr.setAccessible(true);
//        Void instance = ctr.newInstance();
//        return instance;
//    }
    
    @SneakyThrows
    public static void main(String ... args) {
        Constructor<Void> ctr = Void.class.getConstructor(new Class<?>[0]);
        ctr.setAccessible(true);
        Void instance = ctr.newInstance();
        System.out.println(instance);
    }
}
