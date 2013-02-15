package tk.spop.meta.test.immutable;

import lombok.Getter;
import tk.spop.meta.immutable.ImmutableList;

@Getter
public class ImmutableChildTest extends ImmutableTest {

    private final ImmutableList<ImmutableList<String>> b;

    public ImmutableChildTest(int a, int b) {
        super(a);
        this.b = null;
    }

}
