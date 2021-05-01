package airquality.controller;

import airquality.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import airquality.repository.CityRepository;
import airquality.service.CityService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class CityController {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityService cityService;

    @GetMapping("/city/{name}")
    public City getCityByName(@PathVariable(value = "name") String name) throws IOException, URISyntaxException {
        City city = cityService.getCityByName(name);

        return city;
    }
}
