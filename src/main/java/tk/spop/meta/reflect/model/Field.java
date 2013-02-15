package tk.spop.meta.reflect.model;

public interface Field<C, T> extends Member<C>, PropertyAccessor<C, T>, PropertyMutator<C, T> {

    @Override
    java.lang.reflect.Field getJavaMember();

}
