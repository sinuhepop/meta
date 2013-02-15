package tk.spop.meta.reflect.model;

public interface Method<C, T> extends Invocable<C> {

    T invoke(C target, Object... args);

    Class<T> getReturnType();

    @Override
    java.lang.reflect.Method getJavaMember();

}
