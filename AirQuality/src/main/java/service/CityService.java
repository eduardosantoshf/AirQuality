package service;

import model.City;

import java.util.List;

public interface CityService {
    City getCityByName();
    City getCityByLatAndLon();
}
