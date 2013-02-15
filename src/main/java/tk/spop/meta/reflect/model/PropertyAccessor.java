package tk.spop.meta.reflect.model;

public interface PropertyAccessor<C, T> extends PropertyRelated<C, T> {

    T get(C target);

}
