package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPClient {
    private final String url = "http://api.weatherbit.io/v2.0/current/airquality";
    private final String key = "21d09b87cc3f45d8ab6b568dbd0823c7";

    private CloseableHttpClient httpClient;

    public HTTPClient() {
        this.httpClient = HttpClients.createDefault();
    }

    public String makeRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url + "&key=" + key);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
        } catch (Exception e) {
            System.err.println(e);
        }

        return null;
    }
}
