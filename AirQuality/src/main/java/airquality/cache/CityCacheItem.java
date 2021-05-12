package airquality.cache;

public class CityCacheItem<K, V> {
    private final K key;
    private final V value;
    private int hitCount = 0;
    private CityCacheItem<K, V> prev;
    private CityCacheItem<K, V> next;

    public CityCacheItem(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void incrementHitCount() {
        this.hitCount++;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getHitCount() {
        return hitCount;
    }

    public CityCacheItem<K, V> getPrev() {
        return prev;
    }

    public void setPrev(CityCacheItem<K, V> prev) {
        this.prev = prev;
    }

    public CityCacheItem<K, V> getNext() {
        return next;
    }

    public void setNext(CityCacheItem<K, V> next) {
        this.next = next;
    }
}
