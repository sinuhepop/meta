package tk.spop.meta.reflect.model;

import tk.spop.meta.immutable.Immutable;

public interface Member<C> extends Immutable, Comparable<Member<C>> {

    String getName();

    Class<C> getTargetClass();
    
    java.lang.reflect.Member getJavaMember();

}
