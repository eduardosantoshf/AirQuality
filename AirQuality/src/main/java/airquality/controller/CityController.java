package airquality.controller;

import airquality.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import airquality.service.CityService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/city/{name}")
    public City getCityByName(@PathVariable(value = "name") String name) throws IOException, URISyntaxException {
        return cityService.getCityByName(name);
    }

    @GetMapping("/city/{lat}/{lon}")
    public City getCityByLatAndLon(@PathVariable(value = "lat") Double lat, @PathVariable(value = "lon") Double lon) throws IOException, URISyntaxException {
        return cityService.getCityByLatAndLon(lat, lon);
    }

    @GetMapping("/cacheDetails")
    public Map<String, Object> getCacheDetails() {
        return cityService.getCacheDetails();
    }
}
