package tk.spop.meta.reflect.newmodel;

import tk.spop.meta.tuple.Tuple;

public interface Function<T extends Tuple, R> {

    R apply(T args);

    Tuple getArgs();

    Class<R> getReturnType();

    int getArity();

}
