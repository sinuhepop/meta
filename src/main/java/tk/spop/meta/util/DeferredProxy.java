package tk.spop.meta.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.Data;
import lombok.val;
import tk.spop.meta.collection.List;

public class DeferredProxy {

    private static final ClassLoader CLASS_LOADER = DeferredProxy.class.getClassLoader();

    @Data
    public static class InvocationInfo {

        private final Method method;
        private final Object[] args;
    }

    public static interface Proxed {

        List<String> setCommands();
    }

    @Data
    public static class ProxoInvocationHandler implements InvocationHandler {

        private final List<InvocationInfo> previous;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {

            val returnType = method.getReturnType();
            if (!returnType.isInterface()) {
                throw new UnsupportedOperationException("Only methods which return interfaces or void are allowed. " + method + " returns "
                        + returnType);
            }

            val current = previous.append(new InvocationInfo(method, args));
            val handler = new ProxoInvocationHandler(current);
            return Proxy.newProxyInstance(CLASS_LOADER, new Class<?>[] { returnType }, handler);
        }
    }

    public static <T> T of(Class<T> clss) {
        List<InvocationInfo> nil = List.nil();
        val handler = new ProxoInvocationHandler(nil);
        return clss.cast(Proxy.newProxyInstance(CLASS_LOADER, new Class<?>[] { clss }, handler));
    }
}
