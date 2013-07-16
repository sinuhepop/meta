package tk.spop.meta.x;

public @interface Arg1 {

    Class<?> value();

    Arg2[] types() default {};

}
