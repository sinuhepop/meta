package tk.spop.meta.immutable;

import org.junit.Test;

import tk.spop.meta.AptTests;

public class ImmutableProcessorTests extends AptTests {
    
    @Test
    public void test1() {
        compile(new ImmutableProcessor(), "ImmutableTest");
    }

    @Test
    public void test2() {
        compile(new ImmutableProcessor(), "ImmutableTest", "ImmutableChildTest", "ImmutableChildChildTest");
    }

}
