package tk.spop.meta.reflect.impl.arity;

import tk.spop.meta.reflect.impl.MethodImpl;
import tk.spop.meta.reflect.model.arity.Arity2;

public abstract class Arity2MethodImpl<C, T, P1, P2> extends MethodImpl<C, T> implements Arity2<C, T, P1, P2> {

    public Arity2MethodImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes, Class<T> returnType) {
        super(name, targetClass, invocationTypes, returnType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T invoke(C target, Object... args) {
        return invoke(target, (P1) args[0], (P2) args[1]);
    }

}
