package tk.spop.meta.codegen;

import java.io.File;
import java.util.Arrays;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.meta.reflect.writer.ReflectionBuilder;
import tk.spop.meta.reflect.writer.ReflectiveWriter;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        val source = new File(args[0]);
        source.mkdirs();

        val builders = Arrays.asList( //

                new ReflectionBuilder(Object.class),

                new ReflectionBuilder(String.class));

        val writer = new ReflectiveWriter(source);
        for (val builder : builders) {
            writer.write(builder.build());
        }
    }

}
