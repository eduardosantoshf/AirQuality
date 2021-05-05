package airquality.cache;

public class CityCacheItem<K, V> {
    private K key;
    private V value;
    private int hitCount = 0;
    private CityCacheItem prev, next;

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

    public CityCacheItem getPrev() {
        return prev;
    }

    public void setPrev(CityCacheItem prev) {
        this.prev = prev;
    }

    public CityCacheItem getNext() {
        return next;
    }

    public void setNext(CityCacheItem next) {
        this.next = next;
    }
}
