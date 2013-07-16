package tk.spop.meta.seal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Sealed
public interface Tree<T> {

    public static final Nil NIL = new Nil();

    boolean isLeaf();

    Tree<T> getLeft();

    Tree<T> getRight();

    T getValue();

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class Nil implements Tree<Object> {

        @Override
        public boolean isLeaf() {
            return true;
        }

        @Override
        public Tree<Object> getLeft() {
            throw new UnsupportedOperationException("I'm just a leaf!");
        }

        @Override
        public Tree<Object> getRight() {
            throw new UnsupportedOperationException("I'm just a leaf!");
        }

        @Override
        public Object getValue() {
            throw new UnsupportedOperationException("I'm just a leaf!");
        }

    }

    @Data
    public class Node<T> implements Tree<T> {

        private final T value;
        private final Tree<T> left;
        private final Tree<T> right;

        @Override
        public boolean isLeaf() {
            return false;
        }

    }

}
