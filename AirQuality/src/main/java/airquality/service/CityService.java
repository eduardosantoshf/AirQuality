package airquality.service;

import airquality.model.City;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CityService {
    City getCityByName(String name) throws IOException, URISyntaxException;
    City getCityByLatAndLon(Double lat, Double lon) throws IOException, URISyntaxException;
}
