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
import java.util.NoSuchElementException;
import java.util.RandomAccess;

import tk.spop.meta.immutable.Persistent;
import tk.spop.meta.reflect.model.PropertyAccessor;
import tk.spop.meta.util.Option;

public class List<T> implements Persistent, Iterable<T>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final List<Object> NIL = new List<>(null, null);

    private final T head;
    private final List<T> tail;

    @SuppressWarnings("unchecked")
    public static <T> List<T> nil() {
        return (List<T>) NIL;
    }

    @SafeVarargs
    public static <T> List<T> of(T... array) {
        return of(Arrays.asList(array));
    }

    public static <T> List<T> of(Iterable<T> iterable) {
        List<T> list = nil();
        for (T current : iterable) {
            list = list.cons(current);
        }
        return list.reverse();
    }

    public static <T> List<T> of(java.util.List<T> list) {
        if (list instanceof RandomAccess) {
            List<T> l = nil();
            for (int i = list.size() - 1; i >= 0; i--) {
                l = l.cons(list.get(i));
            }
            return l;
        }
        return of((Iterable<T>) list);
    }

    protected List(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public T head() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return head;
    }

    public List<T> tail() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail;
    }

    public List<T> cons(T element) {
        return new List<T>(element, this);
    }

    public List<T> cons(Iterable<T> iterable) {
        List<T> l = this;
        for (T current : of(iterable).reverse()) {
            l = l.cons(current);
        }
        return l;
    }

    public List<T> append(T element) {
        List<T> l = nil();
        l.cons(element);
        for (T current : this.reverse()) {
            l = l.cons(current);
        }
        return l;
    }

    public List<T> append(Iterable<T> iterable) {
        List<T> l = of(iterable);
        for (T current : this.reverse()) {
            l = l.cons(current);
        }
        return l;
    }

    public List<T> update(int index, T value) {
        List<T> reversed = nil();
        List<T> l = this;
        int i = 0;
        while (i < index) {
            reversed = reversed.cons(l.head());
            l = l.tail;
            i++;
        }
        l = l.tail().cons(value);
        for (T current : reversed) {
            l = l.cons(current);
        }
        return l;
    }

    public List<T> remove(int index) {
        List<T> reversed = nil();
        List<T> l = this;
        int i = 0;
        while (i < index) {
            reversed = reversed.cons(l.head());
            l = l.tail;
            i++;
        }
        l = l.tail();
        for (T current : reversed) {
            l = l.cons(current);
        }
        return l;
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
            list = list.cons(f.get(current));
        }
        return list.reverse();
    }

    public List<T> filter(PropertyAccessor<T, Boolean> f) {
        List<T> list = nil();
        for (T current : this) {
            if (f.get(current)) {
                list = list.cons(current);
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

}
