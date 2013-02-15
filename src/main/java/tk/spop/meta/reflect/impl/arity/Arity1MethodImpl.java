package tk.spop.meta.reflect.impl.arity;

import tk.spop.meta.reflect.impl.MethodImpl;
import tk.spop.meta.reflect.model.arity.Arity1;

public abstract class Arity1MethodImpl<C, T, P1> extends MethodImpl<C, T> implements Arity1<C, T, P1> {

    public Arity1MethodImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes, Class<T> returnType) {
        super(name, targetClass, invocationTypes, returnType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T invoke(C target, Object... args) {
        return invoke(target, (P1) args[0]);
    }

}
