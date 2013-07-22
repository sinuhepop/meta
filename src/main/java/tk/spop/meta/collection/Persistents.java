package tk.spop.meta.collection;

import java.util.Iterator;
import java.util.LinkedList;

import lombok.val;
import tk.spop.meta.collection.traversable.Traversers;
import tk.spop.meta.tuple.T2;
import tk.spop.meta.tuple.T3;
import tk.spop.meta.tuple.Tuple;

public abstract class Persistents {

    private static final int HASH_PRIME = 31;

    @SuppressWarnings("unchecked")
    public static <S, T extends S> List<S> cast(List<T> list, Class<S> type) {
        return (List<S>) list;
    }

    public static boolean iteratorEquals(Object a, Object b) {
        if (a instanceof Iterable && b instanceof Iterable) {
            Iterator<?> ita = ((Iterable<?>) a).iterator();
            Iterator<?> itb = ((Iterable<?>) b).iterator();
            while (ita.hasNext() && itb.hasNext()) {
                if (Util.equals(ita.next(), itb.next())) {
                    return false;
                }
            }
            return !ita.hasNext() && !itb.hasNext();
        }
        return false;
    }

    public static int hashCode(Iterable<?> iterable) {
        int result = 1;
        for (Object element : iterable) {
            result = HASH_PRIME * result + (element == null ? 0 : element.hashCode());
        }
        return result;
    }

    public enum ZipMode {
        THROW, STOP, ADD_NULLS
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <P1, P2> List<T2<P1, P2>> zip(Iterable<P1> it1, Iterable<P2> it2) {
        List list = zip(new Iterable[] { it1, it2 }, ZipMode.ADD_NULLS);
        return (List<T2<P1, P2>>) list;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <P1, P2, P3> List<T3<P1, P2, P3>> zip(Iterable<P1> it1, Iterable<P2> it2, Iterable<P3> it3) {
        List list = zip(new Iterable[] { it1, it2, it3 }, ZipMode.ADD_NULLS);
        return (List<T3<P1, P2, P3>>) list;
    }

    private List<Tuple> zip(Iterable<?>[] iterables, ZipMode mode) {

        int n = iterables.length;
        List<Tuple> list = List.nil();
        Object[] values = new Object[n];
        Iterator<?>[] iterators = new Iterator<?>[n];
        for (int i = 0; i < n; i++) {
            iterators[i] = iterables[i].iterator();
        }

        while (all(iterators, true)) {
            for (int i = 0; i < n; i++) {
                values[i] = iterators[i].next();
            }
            list.cons(Tuple.of(values));
        }

        switch (mode) {
            case ADD_NULLS:
                while (!all(iterators, false)) {
                    for (int i = 0; i < n; i++) {
                        values[i] = iterators[i].hasNext() ? iterators[i].next() : null;
                    }
                    list.cons(Tuple.of(values));
                }
                break;

            case STOP:
                break;

            case THROW:
                if (!all(iterators, false)) {
                    throw new RuntimeException("Not all iterators have same length.");
                }
                break;
        }

        return list.reverse();
    }

    private boolean all(Iterator<?>[] iterators, boolean hasNext) {
        for (val it : iterators) {
            if (it.hasNext() != hasNext) {
                return false;
            }
        }
        return true;
    }

    public static String makeString(Iterable<?> iterable) {
        return makeString(iterable, "(", ",", ")");
    }

    public static String makeString(Iterable<?> iterable, String sep) {
        return makeString(iterable, "", sep, "");
    }

    public static String makeString(Iterable<?> iterable, String start, String sep, String end) {
        val sb = new StringBuilder(start);
        val it = Traversers.of(iterable);
        for (val current : it) {
            sb.append(current);
            if (!it.isFirst()) {
                sb.append(sep);
            }
        }
        return sb.append(end).toString();
    }

    public static <T> List<T> flatten(List<? extends Iterable<? extends T>> list) {
        LinkedList<T> l = new LinkedList<>();
        for (Iterable<? extends T> it : list) {
            for (T current : it) {
                l.add(current);
            }
        }
        return List.of(l);
    }

}
