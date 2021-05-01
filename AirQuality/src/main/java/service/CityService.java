package service;

import model.City;

import java.util.List;

public interface CityService {
    City getCityByName(String name);
    City getCityByLatAndLon(Double lat, Double lon);
}
