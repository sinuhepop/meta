package tk.spop.meta.reflect.impl;

import lombok.Getter;
import tk.spop.meta.reflect.model.Invocable;

public abstract class InvocableImpl<C> extends MemberImpl<C> implements Invocable<C> {

    @Getter
    private final Class<?>[] invocationTypes;

    public InvocableImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes) {
        super(name, targetClass);
        this.invocationTypes = invocationTypes;
    }

}
