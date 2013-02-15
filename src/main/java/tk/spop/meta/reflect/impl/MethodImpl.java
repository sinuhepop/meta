package tk.spop.meta.reflect.impl;

import lombok.Getter;
import tk.spop.meta.reflect.Reflections;
import tk.spop.meta.reflect.model.Method;

public abstract class MethodImpl<C, T> extends InvocableImpl<C> implements Method<C, T> {

    @Getter
    private final Class<T> returnType;

    @Getter
    private final int arity;

    @Getter
    private final java.lang.reflect.Method javaMember;

    public MethodImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes, Class<T> returnType) {
        super(name, targetClass, invocationTypes);
        this.returnType = returnType;
        this.javaMember = Reflections.method(targetClass, name, invocationTypes);
        this.arity = invocationTypes.length;
    }

    @Override
    protected int getOrder() {
        return 2;
    }
}
