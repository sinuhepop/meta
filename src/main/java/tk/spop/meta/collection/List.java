package tk.spop.meta.collection;

import static tk.spop.meta.function.Comparables.inv;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import tk.spop.meta.immutable.Persistent;
import tk.spop.meta.reflect.model.PropertyAccessor;

public class List<T> implements Persistent, Iterable<T>, Serializable {

    private static final long serialVersionUID = 1L;

    public static final class ListIterator<T> implements Iterator<T> {

        private List<T> list;

        public ListIterator(List<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public T next() {
            T head = list.head;
            list = list.tail;
            return head;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not allowed in persistent collections.");
        }

    }

    public static class Nil extends List<Object> {

        private static final long serialVersionUID = 1013103129038883203L;

        private Nil() {
            super(null, null);
        }

        @Override
        public Object head() {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public List<Object> tail() {
            throw new IndexOutOfBoundsException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

    }

    private static final List<Object> NIL = new Nil();

    private final T head;
    private final List<T> tail;

    @SuppressWarnings("unchecked")
    public static <T> List<T> nil() {
        return (List<T>) NIL;
    }

    public static <T> List<T> of(T[] array) {
        List<T> current = nil();
        for (int i = array.length - 1; i >= 0; i--) {
            current = new List<T>(array[i], current);
        }
        return current;
    }

    protected List(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        return head;
    }

    public List<T> tail() {
        return tail;
    }

    public List<T> cons(T element) {
        return new List<T>(element, this);
    }

    public List<T> cons(Iterable<T> iterable) {
        // TODO
        return null;
    }

    public List<T> append(Iterable<T> iterable) {
        // TODO
        return null;
    }

    public List<T> append(T element) {
        // TODO
        return null;
    }

    public int size() {
        int i = 0;
        for (@SuppressWarnings("unused") T current : this) {
            i++;
        }
        return i;
    }

    public boolean isEmpty() {
        return false;
    }

    public <X extends Collection<T>> X addTo(X collection) {
        for (T current : this) {
            collection.add(current);
        }
        return collection;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }

    public T get(int index) {
        for (T current : this) {
            if (index == 0) {
                return current;
            }
            index--;
        }
        throw new IndexOutOfBoundsException();
    }

    public T[] toArray() {
        @SuppressWarnings("unchecked") T[] array = (T[]) new Object[size()];
        int i = 0;
        for (T current : this) {
            array[i] = current;
            i++;
        }
        return array;
    }

    public List<T> sort(Comparator<? super T> comparator) {
        T[] array = toArray();
        Arrays.sort(array, comparator);
        return of(array);
    }

    public boolean contains(T element) {
        for (T current : this) {
            if (Util.equals(element, current)) {
                return true;
            }
        }
        return false;
    }

    public List<T> reverse() {
        List<T> list = nil();
        for (T current : this) {
            list = new List<T>(current, this);
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        return Persistents.iteratorEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Persistents.hashCode(this);
    }

    public <S> List<S> map(PropertyAccessor<T, S> f) {
        List<S> list = nil();
        for (T current : this) {
            list.cons(f.get(current));
        }
        return list.reverse();
    }

    public List<T> filter(PropertyAccessor<T, Boolean> f) {
        List<T> list = nil();
        for (T current : this) {
            if (f.get(current)) {
                list.cons(current);
            }
        }
        return list.reverse();
    }

    public Option<T> find(PropertyAccessor<T, Boolean> f) {
        for (T current : this) {
            if (f.get(current)) {
                return Option.of(current);
            }
        }
        return Option.empty();
    }

    public boolean exists(PropertyAccessor<T, Boolean> f) {
        for (T current : this) {
            if (f.get(current)) {
                return true;
            }
        }
        return false;
    }

    public boolean forAll(PropertyAccessor<T, Boolean> f) {
        return !exists(inv(f));
    }

    public <A> A foldl(Fun2<A, A, T> function, A z) {
        for (T current : this) {
            z = function.invoke(z, current);
        }
        return z;
    }

    public <A> A foldr(Fun2<A, A, T> function, A z) {
        return reverse().foldl(function, z);
    }

    public <A, B> Map<A, B> toMap(PropertyAccessor<T, A> key, PropertyAccessor<T, B> value) {
        Map<A, B> map = new HashMap<>();

        return map;
    }

    public <A, B> Map<A, Collection<B>> toMapList(PropertyAccessor<T, A> keyFunction, PropertyAccessor<T, B> valueFunction) {
        Map<A, Collection<B>> map = new HashMap<>();
        for (T current : this) {
            A key = keyFunction.get(current);
            B value = valueFunction.get(current);
            Collection<B> list = map.get(key);
            if (list == null) {
                list = new LinkedList<B>();
            }
            list.add(value);
        }
        return map;
    }

    @Override
    public String toString() {
        return Persistents.makeString(this);
    }

}
