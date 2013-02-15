package tk.spop.meta.reflect.impl.arity;

import tk.spop.meta.reflect.impl.MethodImpl;
import tk.spop.meta.reflect.model.arity.Arity3;

public abstract class Arity3MethodImpl<C, T, P1, P2, P3> extends MethodImpl<C, T> implements Arity3<C, T, P1, P2, P3> {

    public Arity3MethodImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes, Class<T> returnType) {
        super(name, targetClass, invocationTypes, returnType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T invoke(C target, Object... args) {
        return invoke(target, (P1) args[0], (P2) args[1], (P3) args[2]);
    }

}
