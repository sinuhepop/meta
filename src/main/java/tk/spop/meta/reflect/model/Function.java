package tk.spop.meta.reflect.model;

public interface Function<T> extends Invocable<Object> {

    T invoke(Object... args);

    Class<T> getReturnType();

    @Override
    java.lang.reflect.Method getJavaMember();

}
