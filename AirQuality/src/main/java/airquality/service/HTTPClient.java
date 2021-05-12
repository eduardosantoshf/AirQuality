package airquality.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPClient {
    private static final String KEY = "21d09b87cc3f45d8ab6b568dbd0823c7";

    private CloseableHttpClient client;

    public HTTPClient() {
        this.client = HttpClients.createDefault();
    }

    public String makeRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url + "&key=" + KEY);

        // Create a Logger
        Logger logger
                = Logger.getLogger(
                HTTPClient.class.getName());

        CloseableHttpResponse httpResponse = client.execute(httpGet);
        try {
            HttpEntity httpEntity = httpResponse.getEntity();
            return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "ERROR: ", e);
        }

        return null;
    }
}
