package service;

import model.City;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CityService {
    City getCityByName(String name) throws IOException, URISyntaxException;
    City getCityByLatAndLon(Double lat, Double lon) throws IOException, URISyntaxException;
}
