package tk.spop.meta.x;

public class Test {

    public static class A {

        public String a() {
            return "Aa";
        }

        public String b() {
            return this.a();
        }
    }

    public static class B extends A {

        @Override
        public String a() {
            return "Ba";
        }
    }

    public static void main(String... args) {
        System.out.println(new B().b());
    }

}
