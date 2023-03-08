public class MyHashMap<K, V> {
    static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


    private int bucketLength = 5;
    private Node<K, V> bucket[];
    private int size;

    public MyHashMap() {
        bucket = new Node[bucketLength];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % bucketLength;
        Node<K, V> e = bucket[hash];

        if (e == null) {
            bucket[hash] = new Node<K, V>(key, value);
            size++;
        } else {
            while (e.next != null) {
                if (e.getKey() == key) {
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }
            if (e.getKey() == key) {
                e.setValue(value);
                return;
            }
            e.next = new Node<K, V>(key, value);
        }
    }

        public Node<K, V> remove (K key){
            int hash = key.hashCode() % bucketLength;
            Node<K, V> e = bucket[hash];

            if (e == null) {
                return null;
            }
            if (e.getKey() == key) {
                bucket[hash] = e.next;
                e.next = null;
                size--;
                return e;
            }

            Node<K, V> previous = e;
            e = e.next;

            while (e != null) {
                if (e.getKey() == key) {
                    previous.next = e.next;
                    e.next = null;
                    size--;
                    return e;
                }
            }
            return null;
        }

        public void clear () {
            bucket = new Node[bucketLength];
            size = 0;
        }

        public int size () {
            return size;
        }

        public V get (K key){
            int hash = key.hashCode() % bucketLength;
            Node<K, V> e = bucket[hash];

            if (e == null) {
                return null;
        }

            while (e != null) {
                if (e.getKey() == key) {
                    return e.getValue();
                }
                e = e.next;
            }
            return null;
    }
}
