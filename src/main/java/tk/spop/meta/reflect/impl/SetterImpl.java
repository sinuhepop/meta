package tk.spop.meta.reflect.impl;

import lombok.Getter;
import tk.spop.meta.reflect.impl.arity.Arity1MethodImpl;
import tk.spop.meta.reflect.model.Setter;

public abstract class SetterImpl<C, P> extends Arity1MethodImpl<C, Void, P> implements Setter<C, P> {

    @Getter
    private final String propertyName;

    public SetterImpl(String name, Class<C> targetClass, Class<P> invocationType, String propertyName) {
        super(name, targetClass, new Class<?>[] { invocationType }, Void.class);
        this.propertyName = propertyName;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Void invoke(C target, Object... args) {
        set(target, (P) args[0]);
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<P> getPropertyType() {
        return (Class<P>) getInvocationTypes()[0];
    }

    @Override
    public void set(C target, P value) {
        invoke(target, value);
    }

}
