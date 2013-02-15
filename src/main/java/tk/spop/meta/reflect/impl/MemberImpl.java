package tk.spop.meta.reflect.impl;

import lombok.*;
import lombok.Getter;
import tk.spop.meta.reflect.model.*;

@RequiredArgsConstructor
public abstract class MemberImpl<C> implements Member<C> {

    @Getter
    private final String name;

    @Getter
    private final Class<C> targetClass;

    @Override
    public int compareTo(Member<C> o) {
        MemberImpl<C> oo = (MemberImpl<C>) o;
        if (getOrder() == oo.getOrder()) {
            return name.compareTo(oo.name);
        }
        return getOrder() - oo.getOrder();
    }

    protected abstract int getOrder();

}
