package tk.spop.meta.reflect.newmodel.impl;

import tk.spop.meta.reflect.newmodel.Function;
import tk.spop.meta.tuple.T3;

public class Function3<P1, P2, P3, R> implements Function<T3<P1, P2, P3>, R> {

    @Override
    public R apply(T3<P1, P2, P3> args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T3<Class<P1>, Class<P2>, Class<P3>> getArgs() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<R> getReturnType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getArity() {
        // TODO Auto-generated method stub
        return 0;
    }

}
