package airquality.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CityTTLCache<K, V> {
    private final long timeToLive;
    private final long timer;
    private Map<K, CacheObject> data;
    private int requests;
    private int hits;
    private int misses;
    private final Object lockObj = new Object();

    protected class CacheObject {
        long lastAccessed = System.currentTimeMillis();
        V value;
        protected CacheObject(V value) {
            this.value = value;
        }
    }

    public CityTTLCache(long timeToLive, final long timer) {

        // Create a Logger
        Logger logger
                = Logger.getLogger(
                CityTTLCache.class.getName());

        if(timeToLive <= 0 || timer <= 0) throw new IllegalArgumentException("Time to live and timer must be greater than 0!");

        this.timeToLive = timeToLive * 1000;
        this.timer = timer * 1000;
        this.data = new HashMap<>();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(timer * 1000);
                    } catch (InterruptedException e) {
                        logger.log(Level.WARNING, "Interrupted!", e);
                        // Restore interrupted state...
                        Thread.currentThread().interrupt();
                    }
                    refresh();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void put(K key, V value) {
        synchronized (lockObj) {
            data.put(key, new CacheObject(value));
        }
    }

    public V get(K key) {
        synchronized (lockObj) {
            this.requests++;

            CacheObject cacheObject = data.get(key);
            if(cacheObject == null) {
                this.misses++;
                return null;
            }

            this.hits++;
            cacheObject.lastAccessed = System.currentTimeMillis();

            return cacheObject.value;
        }
    }

    public void refresh() {

        long now = System.currentTimeMillis();

        List<K> expiredObjects = new ArrayList<>();

        synchronized (lockObj) {
            for(K key : data.keySet()) {
                CacheObject cacheObject = data.get(key);

                if(cacheObject != null && now > (timeToLive + cacheObject.lastAccessed)) expiredObjects.add(key);
            }
        }

        for(K key: expiredObjects) {
            synchronized (lockObj) {
                data.remove(key);
            }

            Thread.yield();
        }
    }

    public void clean() {
        synchronized (lockObj) {
            data = new HashMap<>();
            requests = 0;
            hits = 0;
            misses = 0;
        }
    }

    public void delete(String key) {
        synchronized (lockObj) {
            data.remove(key);
        }
    }

    public int size() {
        synchronized (lockObj) {
            return data.size();
        }
    }

    public int getRequests() {
        return requests;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public Map<String, Object> getCacheDetails() {
        Map<String, Object> detailsMap = new HashMap<>();

        detailsMap.put("hits", getHits());
        detailsMap.put("misses", getMisses());
        detailsMap.put("requests", getRequests());

        return detailsMap;
    }
}
