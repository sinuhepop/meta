package tk.spop.meta.reflect.impl;

import tk.spop.meta.reflect.impl.arity.Arity0MethodImpl;
import tk.spop.meta.reflect.model.Getter;

public abstract class GetterImpl<C, T> extends Arity0MethodImpl<C, T> implements Getter<C, T> {

    @lombok.Getter
    private final String propertyName;

    public GetterImpl(String name, Class<C> targetClass, Class<T> returnType, String propertyName) {
        super(name, targetClass, new Class<?>[] {}, returnType);
        this.propertyName = propertyName;
    }

    @Override
    public T invoke(C target, Object... args) {
        return get(target);
    }

    @Override
    public Class<T> getPropertyType() {
        return getReturnType();
    }
    
    @Override
    public T get(C target) {
        return invoke(target);
    }
    
}
