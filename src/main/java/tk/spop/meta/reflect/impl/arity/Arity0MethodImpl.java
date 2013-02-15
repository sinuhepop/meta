package tk.spop.meta.reflect.impl.arity;

import tk.spop.meta.reflect.impl.MethodImpl;
import tk.spop.meta.reflect.model.arity.Arity0;

public abstract class Arity0MethodImpl<C, T> extends MethodImpl<C, T> implements Arity0<C, T> {

    public Arity0MethodImpl(String name, Class<C> targetClass, Class<?>[] invocationTypes, Class<T> returnType) {
        super(name, targetClass, invocationTypes, returnType);
    }

    @Override
    public T invoke(C target, Object... args) {
        return invoke(target);
    }

}
