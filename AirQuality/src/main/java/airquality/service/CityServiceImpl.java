package airquality.service;

import airquality.cache.CityCache;
import airquality.cache.CityTTLCache;
import airquality.model.City;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Service
@Transactional
public class CityServiceImpl implements CityService{
    private static final String URL = "http://api.weatherbit.io/v2.0/current/airquality";

    /*
     * CACHE:
     * I used two types of cache, one that uses a maximum number of items and other that uses TTl (time to leave), just uncomment the one to be used
     */

    //private final CityCache<String, City> cityCache = new CityCache<>(3); // maximum number of Cities to save, if passed, the first city in cache is the first city to be removed (FIFO)
    private CityTTLCache<String, City> cityCache = new CityTTLCache<>(60, 60);

    @Override
    public City getCityByName(String name) throws IOException, URISyntaxException {
        City city = cityCache.get(name.toLowerCase());

        if (city == null) {
            city = consumeFromAPI(URL + "?city=" + name);

            if(city != null)
                cityCache.put(name.toLowerCase(), city);
        }

        return city;
    }

    @Override
    public City getCityByLatAndLon(Double lat, Double lon) throws IOException, URISyntaxException {
        return consumeFromAPI(URL + "?lat=" + lat + "&lon=" + lon);
    }

    @Override
    public Map<String, Object> getCacheDetails() {
        return cityCache.getCacheDetails();
    }

    public City consumeFromAPI(String url) throws IOException, URISyntaxException {
        HTTPClient httpClient = new HTTPClient();
        URIBuilder uriBuilder = new URIBuilder(url);

        String response = httpClient.makeRequest(uriBuilder.build().toString());

        try {
            JSONObject fullJSON = new JSONObject(response);

            String name = (String) fullJSON.get("city_name");
            String countryCode = (String) fullJSON.get("country_code");
            Double lat = Double.parseDouble(fullJSON.get("lat").toString());
            Double lon = Double.parseDouble(fullJSON.get("lon").toString());

            JSONArray data = new JSONArray(fullJSON.get("data").toString());

            JSONObject dataObject = (JSONObject) data.get(0);

            Integer aqi = (Integer) dataObject.get("aqi");
            Double co = Double.parseDouble(dataObject.get("co").toString());
            Double o3 = Double.parseDouble(dataObject.get("o3").toString());
            Double so2 = Double.parseDouble(dataObject.get("so2").toString());
            Double no2 = Double.parseDouble(dataObject.get("no2").toString());
            String predominantPollenType = (String) dataObject.get("predominant_pollen_type");

            return new City(name, countryCode, lat, lon, aqi, co, o3, so2, no2, predominantPollenType);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
