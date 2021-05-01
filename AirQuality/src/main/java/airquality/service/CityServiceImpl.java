package service;

import model.City;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Transactional
public class CityServiceImpl implements CityService{
    private final String url = "http://api.weatherbit.io/v2.0/current/airquality";

    @Override
    public City getCityByName(String name) throws IOException, URISyntaxException {
        City city = consumeFromAPI(url + "&city=" + name);

        return city;
    }

    @Override
    public City getCityByLatAndLon(Double lat, Double lon) throws IOException, URISyntaxException {
        City city = consumeFromAPI(url + "&lat=" + lat + "&lon=" + lon);

        return city;
    }

    public City consumeFromAPI(String url) throws IOException, URISyntaxException {
        HTTPClient httpClient = new HTTPClient();
        URIBuilder uriBuilder = new URIBuilder(url);

        String response = httpClient.makeRequest(uriBuilder.build().toString());

        try {
            JSONObject fullJSON = (JSONObject) new JSONObject(response);

            String name = (String) fullJSON.get("city_name");
            String countryCode = (String) fullJSON.get("country_code");
            Double lat = Double.parseDouble(fullJSON.get("lat").toString());
            Double lon = Double.parseDouble(fullJSON.get("lon").toString());

            // ainda não sei como é que vou buscar um valor dentro de um objeto dentro de um array
            //Integer aqi = (Integer) data.get(0)

            JSONArray data = new JSONArray(fullJSON.get("data").toString());

            JSONObject dataObject = (JSONObject) data.get(0);

            Integer aqi = (Integer) dataObject.get("aqi"); // not sure this will work
            Double co = Double.parseDouble(dataObject.get("co").toString()); // not sure this will work
            String predominantPollenType = (String) dataObject.get("predominant_pollen_type"); // not sure this will work

            City city = new City(name, countryCode, lat, lon, aqi, co, predominantPollenType);

            return city;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
