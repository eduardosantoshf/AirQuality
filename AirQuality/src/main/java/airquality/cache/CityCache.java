package airquality.cache;

import java.util.HashMap;
import java.util.Map;

public class CityCache<K, V> {
    private Map<K, CityCacheItem> cacheMap;
    private CityCacheItem first, last;
    private int size;
    private final int CAPACITY; // maximum number of Cities to save
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
        if (!cacheMap.containsKey(key)) {
            this.missCount++;
            return null;
        } else {

            CityCacheItem node = (CityCacheItem) cacheMap.get(key);

            this.hitCount++;
            node.incrementHitCount();
            reorder(node);

            return (V) node.getValue();
        }
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
        if(last == node) {
            return;
        }
        CityCacheItem nextNode = node.getNext();
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

    public Map<String, Object> getCacheDetails() {
        Map<String, Object> detailsMap = new HashMap<>();

        detailsMap.put("hits", getHitCount());
        detailsMap.put("misses", getMissCount());

        return detailsMap;
    }
}
