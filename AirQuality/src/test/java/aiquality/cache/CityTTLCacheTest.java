package aiquality.cache;

import airquality.cache.CityTTLCache;
import airquality.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTTLCacheTest {
    private static CityTTLCache<String, City> cityTTLCache;

    @BeforeEach
    void setUp() {
        cityTTLCache = new CityTTLCache<>(5, 5);
    }

    @Test
    public void putTest() {
        City aveiro = new City("Aveiro");
        cityTTLCache.put("Aveiro", aveiro);

        assertEquals(aveiro, cityTTLCache.get("Aveiro"));
    }

    @Test
    public void getTest() {
        City porto = new City("Porto");
        cityTTLCache.put("Aveiro", porto);

        assertEquals(porto, cityTTLCache.get("Aveiro"));
    }

    @Test
    public void deleteTest() {
        City aveiro = new City("Aveiro");
        cityTTLCache.put("Aveiro", aveiro);
        int cacheSize = cityTTLCache.size();

        cityTTLCache.delete("Aveiro");

        assertEquals(cityTTLCache.size() + 1, cacheSize);
    }

    @Test
    public void sizeTest() {
        City aveiro = new City("Aveiro");
        cityTTLCache.put("Aveiro", aveiro);

        assertEquals(1, cityTTLCache.size());
    }

    @Test
    public void getHitsTest() {
        City aveiro = new City("Aveiro");
        cityTTLCache.put("Aveiro", aveiro);

        cityTTLCache.get("Aveiro");

        assertEquals(1, cityTTLCache.getHits());
    }

    @Test
    public void getMissesTest() {
        cityTTLCache.get("Aveiro");

        assertEquals(1, cityTTLCache.getMisses());
    }

    @Test
    public void getRequestsTest() {
        City aveiro = new City("Aveiro");

        cityTTLCache.put("Aveiro", aveiro);
        cityTTLCache.get("Aveiro");
        cityTTLCache.get("Porto");

        assertEquals(2, cityTTLCache.getRequests());
    }
}
