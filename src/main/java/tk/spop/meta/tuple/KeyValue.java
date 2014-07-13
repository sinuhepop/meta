package tk.spop.meta.tuple;

public class KeyValue<K extends Comparable<K>, V> extends T2<K, V> implements Comparable<KeyValue<K, V>> {

    public KeyValue(K key, V value) {
        super(key, value);
    }

    public K getKey() {
        return _1;
    }

    public V getValue() {
        return _2;
    }

    @Override
    public int compareTo(KeyValue<K, V> o) {
        return _1.compareTo(o._1);
    }

    public static <K extends Comparable<K>, V> KeyValue<K, V> of(K key, V value) {
        return new KeyValue<K, V>(key, value);
    }

}
