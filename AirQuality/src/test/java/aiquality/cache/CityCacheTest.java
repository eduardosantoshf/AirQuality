package aiquality.cache;

import airquality.cache.CityCache;
import airquality.model.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityCacheTest {
    private static CityCache<String, City> cityCache;

    @BeforeAll
    static void setUp() {
        cityCache = new CityCache<>(2);
    }

    @Test
    public void putTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);

        assertEquals(aveiro, cityCache.get("Aveiro"));
    }

    @Test
    public void getTest() {
        City porto = new City("Porto");
        cityCache.put("Aveiro", porto);

        assertEquals(porto, cityCache.get("Aveiro"));
    }

    @Test
    public void deleteTest() {
        City aveiro = new City("Aveiro");
        cityCache.put("Aveiro", aveiro);
        int cacheSize = cityCache.size();

        cityCache.delete("Aveiro");

        assertEquals(cacheSize, cityCache.size() + 1);
    }

    @Test
    public void

}
