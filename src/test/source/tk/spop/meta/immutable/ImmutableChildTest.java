package tk.spop.meta.test.immutable;

import lombok.Getter;
import tk.spop.meta.immutable.ImmutableList;

@Getter
public class ImmutableChildTest extends ImmutableTest {

    private final List<String> list;

    public ImmutableChildTest(int value, List<String> list) {
        super(value);
        this.list = list;
    }

}
