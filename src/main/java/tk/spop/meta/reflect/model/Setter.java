package tk.spop.meta.reflect.model;

import tk.spop.meta.reflect.model.arity.Arity1;

public interface Setter<C, P1> extends Method<C, Void>, PropertyMutator<C, P1>, Arity1<C, Void, P1> {

}
