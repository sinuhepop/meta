package tk.spop.meta.reflect.model.arity;

import tk.spop.meta.reflect.model.Invocable;

public interface Arity1<C, T, P1> extends Invocable<C> {

    T invoke(C target, P1 p1);

}
