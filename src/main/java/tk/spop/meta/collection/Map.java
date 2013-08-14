package tk.spop.meta.collection;

import lombok.Data;
import tk.spop.meta.collection.Map.Entry;

public class Map<K, V> extends Tree<Entry<K, V>> {

    @Data
    public static class Entry<EK extends Comparable<EK>, EV> implements Comparable<Entry<EK, EV>> {

        private final EK key;
        private final EV value;

        @Override
        public int compareTo(Entry<EK, EV> o) {
            return key.compareTo(o.key);
        }

    }

    public Map(Entry<K, V> value, boolean red, Tree<Entry<K, V>> left, Tree<Entry<K, V>> right) {
        super(value, red, left, right);
        // TODO Auto-generated constructor stub
    }

}
