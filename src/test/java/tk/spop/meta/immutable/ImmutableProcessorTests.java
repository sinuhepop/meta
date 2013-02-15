package tk.spop.meta.immutable;

import org.junit.Test;

import tk.spop.meta.AptTests;

public class ImmutableProcessorTests extends AptTests {

    @Test
    public void test() {
        compile(new ImmutableProcessor(), "ImmutableTest", "ImmutableChildTest", "ImmutableChildChildTest");
    }

}
