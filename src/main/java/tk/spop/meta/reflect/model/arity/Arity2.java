package tk.spop.meta.reflect.model.arity;

import tk.spop.meta.reflect.model.Invocable;

public interface Arity2<C, T, P1, P2> extends Invocable<C> {

    T invoke(C target, P1 p1, P2 p2);

}
