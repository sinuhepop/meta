package tk.spop.meta.test.immutable;

import lombok.Getter;

@Getter
public class ImmutableChildChildTest extends ImmutableChildTest {

    private final int c;

    public ImmutableChildChildTest(int a, int b, int c) {
        super(a, b);
        this.c = c;
    }

}
