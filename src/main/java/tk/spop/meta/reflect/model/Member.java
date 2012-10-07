package tk.spop.meta.reflect.model;

public interface Member<C> {

    String getName();

    Class<C> getTargetClass();
    
    java.lang.reflect.Member unwrap();

}
