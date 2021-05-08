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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(CityController.class)
public class CityControllerIT {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    @Test
    public void givenCity_whenGetCityByName_thenReturnCityData() throws Exception {
        City aveiro = new City("Aveiro");

        when(cityService.getCityByName("Aveiro")).thenReturn(aveiro);

        mvc.perform(get("/city/{name}", "Aveiro").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(aveiro))).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Aveiro")));
    }

    @Test
    public void givenCity_whenGetCityByLatAndLon_thenReturnCityData() throws Exception {
        City aveiro = new City("Aveiro", "PT", 40.64427, -8.64554, 41, 270.784, "Molds");

        when(cityService.getCityByLatAndLon(40.64427, -8.64554)).thenReturn(aveiro);

        mvc.perform(get("/city/{lat}/{lon}", 40.64427, -8.64554).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(aveiro))).andExpect(status().isOk()).andExpect(jsonPath("$.latitude", is(40.64427))).andExpect(jsonPath("$.longitude", is(-8.64554)));
    }
}
