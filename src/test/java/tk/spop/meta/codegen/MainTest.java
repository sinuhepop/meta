package tk.spop.meta.codegen;

import lombok.val;

import org.junit.Test;

public class MainTest {

    @Test
    public void test() {
        Main.main(new String[] { "target/generated-sources/codegen" });
        
        val x = java.lang.String$.finalize;
        
        
    }

}
