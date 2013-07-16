package tk.spop.meta;

import org.junit.Test;

public class X {

    private int calls;

    @Test
    public void stackTest() {

        try {
            calls = 0;
            infinite();
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        try {
            calls = 0;
            infinite();
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        try {
            calls = 0;
            infinite("a", "b", "c", "d");
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        try {
            calls = 0;
            infinite("a", "b", "c", "d");
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        try {
            calls = 0;
            infinite("a", "b", "c", "d");
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        try {
            calls = 0;
            infinite();
        } catch (StackOverflowError e) {
            System.out.println("Calls: " + calls);
        }

        int[] x = new int[100000000];
        System.out.println("Array size: " + x.length);
    }

    private void infinite() {
        calls++;
        infinite();
    }

    private void infinite(String a, String b, String c, String d) {
        calls++;
        infinite(a, b, c, d);
    }
}
