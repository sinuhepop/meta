package tk.spop.meta.seal;

public @interface Sealed {

    Class<?>[] classes() default {};

}
