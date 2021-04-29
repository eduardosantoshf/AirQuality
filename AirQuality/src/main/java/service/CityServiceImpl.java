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
    @Override
    public City getCityByName() {
        return null;
    }

    @Override
    public City getCityByLatAndLon() {
        return null;
    }

    public String consumeFromAPI(String url) throws IOException, URISyntaxException {
        HTTPClient httpClient = new HTTPClient();
        URIBuilder uriBuilder = new URIBuilder(url);

        String response = httpClient.makeRequest(uriBuilder.build().toString());

        try {
            JSONObject fullJSON = (JSONObject) new JSONObject(response);

            String name = (String) fullJSON.get("city_name");
            String countryCode = (String) fullJSON.get("country_code");
            Double lat = Double.parseDouble(fullJSON.get("lat").toString());
            Double lon = Double.parseDouble(fullJSON.get("lon").toString());

            JSONArray data = new JSONArray(fullJSON.get("data").toString());

            // ainda não sei como é que vou buscar um valor dentro de um objeto dentro de um array
           //Integer aqi = (Integer) data.get(0)

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
