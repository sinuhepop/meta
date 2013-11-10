package tk.spop.meta.proxy;

import tk.spop.meta.reflect.model.Method;
import tk.spop.meta.tuple.Tuple;

public interface Interceptor {

    <T> T invoke(Method<?, T> method, Tuple args);
}
