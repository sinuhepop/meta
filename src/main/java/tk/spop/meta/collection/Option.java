package tk.spop.meta.collection;

import java.util.NoSuchElementException;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Option<T> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static final Option<?> EMPTY_OPTION = new Option(true, null);

    private final boolean empty;
    private final T value;

    public Option(T value) {
        this(false, value);
    }

    public T get() {
        if (empty) {
            throw new NoSuchElementException();
        }
        return value;
    }

    @Override
    public String toString() {
        return empty ? "()" : "(" + value + ")";
    }

    public static <T> Option<T> of(T value) {
        return new Option<T>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> empty() {
        return (Option<T>) EMPTY_OPTION;
    }

}
