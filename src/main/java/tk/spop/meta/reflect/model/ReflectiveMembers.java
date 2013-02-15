package tk.spop.meta.reflect.model;

import java.util.*;

import tk.spop.meta.immutable.Immutable;

public interface ReflectiveMembers<C> extends Immutable {

    // TODO: Immutable
    @SuppressWarnings("rawtypes")
    public static final List<Class<? extends Member>> TYPES = Arrays.asList( //
            Member.class, //
            Constructor.class, //
            Field.class, //
            PropertyAccessor.class, //
            Getter.class, //
            Method.class, //
            PropertyMutator.class, //
            Setter.class //
            );

    Class<C> getTargetClass();

    List<Member<C>> getMembers();

    List<Constructor<C>> getConstructors();

    List<Field<C, ?>> getFields();

    List<PropertyAccessor<C, ?>> getAccessors();

    List<Getter<C, ?>> getGetters();

    List<Method<C, ?>> getMethods();

    List<PropertyMutator<C, ?>> getMutators();

    List<Setter<C, ?>> getSetters();

}
