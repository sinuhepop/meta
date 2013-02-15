package tk.spop.meta.reflect.model;

public interface PropertyMutator<C, P> extends PropertyRelated<C, P> {

    void set(C target, P value);

}
