package airquality.cache;

import java.util.HashMap;
import java.util.Map;

public class CityCache<K, V> {
    private final Map<K, CityCacheItem<K, V>> cacheMap;
    private CityCacheItem<K, V> first;
    private CityCacheItem<K, V> last;
    private int size;
    private final int capacity; // maximum number of Cities to save
    private int hitCount = 0;
    private int missCount = 0;
    private int requestCount = 0;

    public CityCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
    }

    public void put(K key, V value) {
        CityCacheItem<K, V> node = new CityCacheItem<>(key, value);

        if (!cacheMap.containsKey(key)) {
            if (size() >= capacity) deleteNode(first);

            addNodeToLast(node);
        }

        cacheMap.put(key, node);
    }

    public V get(K key) {
        this.requestCount++;

        if (!cacheMap.containsKey(key)) {
            this.missCount++;
            return null;
        } else {

            CityCacheItem<K, V> node = cacheMap.get(key);

            this.hitCount++;
            node.incrementHitCount();
            reorder(node);

            return node.getValue();
        }
    }

    public void delete(K key) {
        deleteNode(cacheMap.get(key));
    }

    private void deleteNode(CityCacheItem<K, V> node) {
        if (node == null) return;
        if (last == node) last = node.getPrev();
        if (first == node) first = node.getNext();

        cacheMap.remove(node.getKey());
        size--;
    }

    private void addNodeToLast(CityCacheItem<K, V> node) {
        if(last != null) {
            last.setNext(node);
            node.setPrev(last);
        }

        last = node;

        if(first == null) first = node;

        size++;
    }

    private void reorder(CityCacheItem<K, V> node) {
        if(last == node) {
            return;
        }
        CityCacheItem<K, V> nextNode = node.getNext();
        while (nextNode != null) {
            if(nextNode.getHitCount() > node.getHitCount()) {
                break;
            }
            if(first == node) {
                first = nextNode;
            }
            if(node.getPrev() != null) {
                node.getPrev().setNext(nextNode);
            }
            nextNode.setPrev(node.getPrev());
            node.setPrev(nextNode);
            node.setNext(nextNode.getNext());
            if(nextNode.getNext() != null) {
                nextNode.getNext().setPrev(node);
            }
            nextNode.setNext(node);
            nextNode = node.getNext();
        }
        if(node.getNext() == null) {
            last = node;
        }
    }

    public int size() {
        return this.size;
    }
    public int getHitCount() {
        return this.hitCount;
    }

    public int getMissCount() {
        return this.missCount;
    }

    public int getRequestCount() {
        return this.requestCount;
    }

    public Map<String, Object> getCacheDetails() {
        Map<String, Object> detailsMap = new HashMap<>();

        detailsMap.put("hits", getHitCount());
        detailsMap.put("misses", getMissCount());
        detailsMap.put("requests", getRequestCount());

        return detailsMap;
    }
}
