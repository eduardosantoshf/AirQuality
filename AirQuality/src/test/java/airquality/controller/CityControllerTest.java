package airquality.controller;

import airquality.util.JsonUtil;
import airquality.model.City;
import airquality.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(CityController.class)
//Integration Tests
class CityControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @Test
    void getCityByNameTest() throws Exception {
        City aveiro = new City("Aveiro");

        when(cityService.getCityByName("Aveiro")).thenReturn(aveiro);

        mvc.perform(get("/city/{name}", "Aveiro").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Aveiro")));
    }

    @Test
    void getCityByLatAndLonTest() throws Exception {
        City aveiro = new City("Aveiro", "PT", 40.64427, -8.64554, 41, 270.784, 57.0, 0.708736, 0.57969,"Molds");

        when(cityService.getCityByLatAndLon(40.64427, -8.64554)).thenReturn(aveiro);

        mvc.perform(get("/city/{lat}/{lon}", 40.64427, -8.64554).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.latitude", is(40.64427))).andExpect(jsonPath("$.longitude", is(-8.64554)));
    }

    @Test
    void getCacheDetailsTest() throws Exception {
        Map<String, Object> details = new HashMap<>();
        details.put("hits", 1);
        details.put("misses", 1);
        details.put("requests", 1);

        when(cityService.getCacheDetails()).thenReturn(details);

        mvc.perform(get("/cacheDetails").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.hits", is(1))).andExpect(jsonPath("$.misses", is(1))).andExpect(jsonPath("$.misses", is(1)));
    }
}
