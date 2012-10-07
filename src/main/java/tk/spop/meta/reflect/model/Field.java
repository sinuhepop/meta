package tk.spop.meta.reflect.model;

public interface Field<C, T> extends Member<C>, Gettable<C, T>, Settable<C, T> {

    @Override
    java.lang.reflect.Field unwrap();

}
