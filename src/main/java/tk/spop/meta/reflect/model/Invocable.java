package tk.spop.meta.reflect.model;

public interface Invocable<C> extends Member<C> {

    Class<?>[] getInvocationTypes();
    
    int getArity();
    
}
