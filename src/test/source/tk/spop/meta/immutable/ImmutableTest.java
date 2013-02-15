package tk.spop.meta.test.immutable;

import lombok.Getter;
import tk.spop.meta.immutable.Immutable;

@Getter
public class ImmutableTest implements Immutable {

    private final ImmutableChildTest a;

    public ImmutableTest(int a) {
        this.a = null;
    }

}
