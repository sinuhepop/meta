package tk.spop.meta.reflect.model.arity;

import tk.spop.meta.reflect.model.Invocable;

public interface Arity0<C, T> extends Invocable<C> {

    T invoke(C target);

}
