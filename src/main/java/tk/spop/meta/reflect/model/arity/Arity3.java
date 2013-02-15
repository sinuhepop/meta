package tk.spop.meta.reflect.model.arity;

import tk.spop.meta.reflect.model.Invocable;

public interface Arity3<C, T, P1, P2, P3> extends Invocable<C> {

    T invoke(C target, P1 p1, P2 p2, P3 p3);

}
