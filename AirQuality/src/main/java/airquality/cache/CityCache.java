package airquality.cache;

import java.util.HashMap;
import java.util.Map;

public class CityCache<K, V> {
    private Map<K, CityCacheItem> cacheMap;
    private CityCacheItem first, last;
    private int size;
    private final int CAPACITY;
    private int hitCount = 0;
    private int missCount = 0;

    public CityCache(int capacity) {
        CAPACITY = capacity;
        cacheMap = new HashMap<>(CAPACITY);
    }

    public void put(K key, V value) {
        CityCacheItem node = new CityCacheItem(key, value);

        if (!cacheMap.containsKey(key)) {
            if (size() >= CAPACITY) deleteNode(first);

            addNodeToLast(node);
        }

        cacheMap.put(key, node);
    }

    public V get(K key) {
        if (!cacheMap.containsKey(key)) return null;

        CityCacheItem node = (CityCacheItem) cacheMap.get(key);

        node.incrementHitCount();
        reorder(node);

        return (V) node.getValue();
    }

    public void delete(K key) {
        deleteNode(cacheMap.get(key));
    }

    private void deleteNode(CityCacheItem node) {
        if (node == null) return;
        if (last == node) last = node.getPrev();
        if (first == node) first = node.getNext();

        cacheMap.remove(node.getKey());
        node = null;
        size--;
    }

    private void addNodeToLast(CityCacheItem node) {
        if(last != null) {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;

        if(first == null) first = node;

        size++;
    }

    private void reorder(CityCacheItem node) {
        if (last == node) return;

        if (first == node) first = node.getNext();
        else node.getPrev().setNext(node.getNext());

        last.setNext(node);
        node.setPrev(last);
        node.setNext(null);
        last = node;
    }

    public int size() {
        return size;
    }
    public int getHitCount() {
        return hitCount;
    }

    public int getMissCount() {
        return missCount;
    }
}
