package tk.spop.meta.reflect.model;

public interface Settable<C, T> {

    void set(C target, T value);

}
