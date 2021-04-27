package service;

import model.City;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;


import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class CityServiceImpl implements CityService{
    private final String url = "http://api.weatherbit.io/v2.0/current/airquality";
    private final String key = "21d09b87cc3f45d8ab6b568dbd0823c7";

    private CloseableHttpClient httpClient;

    @Override
    public City getCityByName() {
        return null;
    }

    @Override
    public City getCityByLatAndLon() {
        return null;
    }

    public String consumeFromAPI(String url) throws IOException {
        String APIResponse = null;

        HttpGet httpGet = new HttpGet(url + "&key=" + key);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        try {
            HttpEntity entity = httpResponse.getEntity();
            APIResponse = EntityUtils.toString(entity);
        } catch (Exception e) {
            System.err.println(e);
        }

        return APIResponse;
    }
}
