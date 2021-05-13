package airquality.cache;

import airquality.model.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityCacheTest {
    private static CityCache<String, City> cityCache;

    @BeforeEach
    public void setUp() {
        cityCache = new CityCache<>(2);
    }

    @Test
    void putTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);

        assertEquals(aveiro, cityCache.get("Aveiro"));
    }

    @Test
    void getTest() {
        City porto = new City("Porto");
        cityCache.put("Aveiro", porto);

        assertEquals(porto, cityCache.get("Aveiro"));
    }

    @Test
    void deleteTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);
        int cacheSize = cityCache.size();

        cityCache.delete("Aveiro");

        assertEquals(cityCache.size() + 1, cacheSize);
    }

    @Test
    void sizeTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);

        assertEquals(1, cityCache.size());
    }

    @Test
    void getHitCountTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);

        cityCache.get("Aveiro");

        assertEquals(1, cityCache.getHitCount());
    }

    @Test
    void getMissCountTest() {
        cityCache.get("Aveiro");

        assertEquals(1, cityCache.getMissCount());
    }

    @Test
    void getRequestCountTest() {
        City aveiro = new City("Aveiro");

        cityCache.put("Aveiro", aveiro);
        cityCache.get("Aveiro");
        cityCache.get("Porto");

        assertEquals(2, cityCache.getRequestCount());
    }

}
