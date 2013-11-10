package tk.spop.meta.proxy;

import lombok.Getter;
import lombok.Setter;
import tk.spop.meta.collection.List;

public class MyClassProxy extends MyClass implements Proxy {

    @Getter
    @Setter
    private List<Interceptor> interceptors;

    public MyClassProxy(int id) {
        super(id);
    }

    @Override
    public int getId() {
        return Proxies.invoke(interceptors, MyClass$.getId, null, null);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        super.setName(name);
    }

}
