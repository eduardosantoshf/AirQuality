package airquality.service;

import airquality.model.City;
import airquality.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    /*
     * lenient is required because we load some expectations in the setup
     + that are not used in all the tests. As an alternative, the expectations
     * could move into each test method and be trimmed: no need for lenient
    */
    @Mock(lenient = true)
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setUp() {
        City aveiro = new City("Aveiro");
        City porto = new City("Porto");
        City lisboa = new City("Lisboa");

        List<City> cities = Arrays.asList(aveiro, porto, lisboa);

        Mockito.when(cityRepository.findByName(aveiro.getName())).thenReturn(aveiro);
        Mockito.when(cityRepository.findByName(porto.getName())).thenReturn(porto);
        Mockito.when(cityRepository.findByName(lisboa.getName())).thenReturn(lisboa);
        Mockito.when(cityRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(cityRepository.findAll()).thenReturn(cities);
    }

    @Test
    public void whenValidName_thenCityShouldBeFound() throws IOException, URISyntaxException {
        String name = "Aveiro";
        City foundCity = cityService.getCityByName(name);

        assertThat(foundCity.getName()).isEqualTo(name);
    }

    @Test
    public void whenValidlatAndLon_thenCityShouldBeFound() throws IOException, URISyntaxException {
        Double latitude = 40.64;
        Double longitude = -8.64;
        City foundCity = cityService.getCityByLatAndLon(latitude, longitude);

        assertThat(foundCity.getLatitude()).isEqualTo(latitude);
        assertThat(foundCity.getLongitude()).isEqualTo(longitude);
    }
}