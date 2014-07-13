package tk.spop.meta.codegen;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

import lombok.SneakyThrows;
import lombok.val;
import tk.spop.meta.reflect.writer.ReflectiveWriter;
import tk.spop.meta.reflect.writer.builder.ReflectionBuilder;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        val source = new File(args[0]);
        source.mkdirs();

        val builders = Arrays.asList(

        new ReflectionBuilder(Object.class, true) //
                .renameMethod("waitMillis", "wait", long.class) //
                .renameMethod("waitNanos", "wait", long.class, int.class),

        new ReflectionBuilder(String.class, true) //
                .renameConstructor("fromArray", byte[].class) //
                .renameConstructor("fromArrayHiBite", byte[].class, int.class) //
                .renameConstructor("fromSubarray", byte[].class, int.class, int.class) //
                .renameConstructor("fromSubarrayHiBite", byte[].class, int.class, int.class, int.class) //
                .renameConstructor("fromSubarrayCharsetName", byte[].class, int.class, int.class, String.class) //
                .renameConstructor("fromSubarrayCharset", byte[].class, int.class, int.class, Charset.class) //
                .renameConstructor("fromArrayCharsetName", byte[].class, String.class) //
                .renameConstructor("fromArrayCharset", byte[].class, Charset.class) //
                .renameConstructor("fromCharArray", char[].class) //
                .renameConstructor("fromCharSubarray", char[].class, int.class, int.class) //
                .renameConstructor("fromUnicodes", int[].class, int.class, int.class) //
                .renameConstructor("fromString", String.class) //
                .renameConstructor("fromStringBuffer", StringBuffer.class) //
                .renameConstructor("fromStringBuilder", StringBuilder.class) //
                .renameMethod("copyValueOfSubarray", "copyValueOf", char[].class, int.class, int.class) //
                .renameMethod("formatLocalized", "format", Locale.class, String.class, Object[].class) //
                .renameMethod("valueOfBoolean", "valueOf", boolean.class) //
                .renameMethod("valueOfChar", "valueOf", char.class) //
                .renameMethod("valueOfArray", "valueOf", char[].class) //
                .renameMethod("valueOfSubarray", "valueOf", char[].class, int.class, int.class) //
                .renameMethod("valueOfDouble", "valueOf", double.class) //
                .renameMethod("valueOfFloat", "valueOf", float.class) //
                .renameMethod("valueOfInt", "valueOf", int.class) //
                .renameMethod("valueOfLong", "valueOf", long.class) //
                .renameMethod("contentEqualsStringBuffer", "contentEquals", StringBuffer.class) //
                .renameMethod("getBytesOfSubarray", "getBytes", int.class, int.class, byte[].class, int.class) //
                .renameMethod("getBytesWithCharsetName", "getBytes", String.class) //
                .renameMethod("getBytesWithCharset", "getBytes", Charset.class) //
                .renameMethod("getCharsRange", "getChars", int.class, int.class, char[].class, int.class) //
                .renameMethod("indexOfFromIndex", "indexOf", int.class, int.class) //
                .renameMethod("indexOfString", "indexOf", String.class) //
                .renameMethod("indexOfStringFromIndex", "indexOf", String.class, int.class) //
                .renameMethod("joinIterable", "join", CharSequence.class, Iterable.class) //
                .renameMethod("lastIndexOfFromIndex", "lastIndexOf", int.class, int.class) //
                .renameMethod("lastIndexOfString", "lastIndexOf", String.class) //
                .renameMethod("lastIndexOfStringFromIndex", "lastIndexOf", String.class, int.class) //
                .renameMethod("regionMatchesIgnoreCase", "regionMatches", boolean.class, int.class, String.class, int.class, int.class) //
                .renameMethod("replaceChar", "replace", char.class, char.class) //
                .renameMethod("splitLimit", "split", String.class, int.class) //
                .renameMethod("startsWithFromIndex", "startsWith", String.class, int.class) //
                .renameMethod("substringToEnd", "substring", int.class) //
                .renameMethod("toLowerCaseLocalized", "toLowerCase", Locale.class) //
                .renameMethod("toUpperCaseLocalized", "toUpperCase", Locale.class) //

                );

        val writer = new ReflectiveWriter(source);
        for (val builder : builders) {
            writer.write(builder.getDelegate());
        }
    }
}
