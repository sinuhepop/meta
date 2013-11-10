package tk.spop.meta.proxy;

import tk.spop.meta.collection.List;

public interface Proxy {

    List<Interceptor> getInterceptors();

    void setInterceptors(List<Interceptor> list);

}
