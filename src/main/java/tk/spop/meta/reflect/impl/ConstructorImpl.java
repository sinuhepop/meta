package tk.spop.meta.reflect.impl;

import lombok.Getter;
import tk.spop.meta.reflect.Reflections;
import tk.spop.meta.reflect.model.Constructor;

public abstract class ConstructorImpl<C> extends InvocableImpl<C> implements Constructor<C> {

    @Getter
    private final java.lang.reflect.Constructor<C> javaMember;

    @Getter
    private final int arity;

    public ConstructorImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes) {
        super(name, targetClass, invocationTypes);
        this.javaMember = Reflections.constructor(super.getTargetClass(), invocationTypes);
        this.arity = invocationTypes.length;
    }

    @Override
    protected int getOrder() {
        return 1;
    }

}
