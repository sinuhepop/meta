package tk.spop.meta.reflect.model;

public interface Constructor<C> extends Member<C> {

    C invoke(Object[] args);

    @Override
    java.lang.reflect.Constructor<C> unwrap();

}
