package tk.spop.meta.reflect.impl;

import lombok.Getter;
import tk.spop.meta.reflect.Reflections;
import tk.spop.meta.reflect.model.Field;

public abstract class FieldImpl<C, T> extends MemberImpl<C> implements Field<C, T> {

    @Getter
    private final Class<T> propertyType;

    @Getter
    private final java.lang.reflect.Field javaMember;

    public FieldImpl(String name, Class<C> targetClass, Class<T> propertyType) {
        super(name, targetClass);
        this.propertyType = propertyType;
        this.javaMember = Reflections.field(targetClass, name);
    }

    @Override
    public String getPropertyName() {
        return getName();
    }

    @Override
    protected int getOrder() {
        return 0;
    }

}
