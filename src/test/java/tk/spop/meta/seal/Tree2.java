package tk.spop.meta.seal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class Tree2<T> {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Nil extends Tree2<Object> {

        public static final Nil NIL = new Nil();

    }

    @Data
    public class Node<T2> extends Tree2<T2> {

        private final T2 value;
        private final Tree2<T2> left;
        private final Tree2<T2> right;
    }

}
