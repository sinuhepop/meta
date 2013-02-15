package tk.spop.meta.reflect.impl;

import java.util.*;

import tk.spop.meta.reflect.model.*;

@SuppressWarnings("unchecked")
public class ReflectiveMembersImpl<C> implements ReflectiveMembers<C> {

    @lombok.Getter
    private final Class<C> targetClass;

    private final Map<Class<? extends Member<C>>, List<? extends Member<C>>> map;

    @SafeVarargs
    public ReflectiveMembersImpl(Class<C> targetClass, Member<C>... members) {

        this.targetClass = targetClass;

        map = new HashMap<>();
        for (@SuppressWarnings("rawtypes") Class<? extends Member> clss : ReflectiveMembers.TYPES) {
            List<Member<C>> list = new ArrayList<>();
            for (Member<C> member : members) {
                if (clss.isInstance(member)) {
                    list.add(member);
                }
            }
            map.put((Class<? extends Member<C>>) clss, list);
        }
    }

    @Override
    public List<Member<C>> getMembers() {
        return null;
    }

    @Override
    public List<Constructor<C>> getConstructors() {
        return get(Constructor.class);
    }

    @Override
    public List<Field<C, ?>> getFields() {
        return get(Field.class);
    }

    @Override
    public List<PropertyAccessor<C, ?>> getAccessors() {
        return get(PropertyAccessor.class);
    }

    @Override
    public List<Getter<C, ?>> getGetters() {
        return get(Getter.class);
    }

    @Override
    public List<Method<C, ?>> getMethods() {
        return get(Method.class);
    }

    @Override
    public List<PropertyMutator<C, ?>> getMutators() {
        return get(PropertyMutator.class);
    }

    @Override
    public List<Setter<C, ?>> getSetters() {
        return get(Setter.class);
    }

    private <T extends Member<C>> List<T> get(Class<T> clss) {
        return (List<T>) map.get(clss);
    }

}
