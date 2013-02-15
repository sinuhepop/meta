package tk.spop.meta.reflect.model;

public interface PropertyRelated<C, T> extends Member<C> {

    Class<T> getPropertyType();

    String getPropertyName();

}
