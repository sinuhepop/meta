package tk.spop.meta.x;

// Ola
public @interface Arg {

    Class<?> value();

    String generic() default "";

    Arg1[] types() default {};

}
