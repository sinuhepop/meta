package tk.spop.meta.iface;

import lombok.val;

import org.junit.*;

import tk.spop.meta.AptTests;

public class InterfaceProcessorTests extends AptTests {

    @Test
    public void test() {
        val result = compile(new InterfaceProcessor(), "Pojo");
        Assert.assertTrue(result.isOk());
    }

}
