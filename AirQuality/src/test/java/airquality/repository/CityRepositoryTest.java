package airquality.repository;

import airquality.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CityRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void whenFindAveiroByName_ThenReturnAveiroCity() {
        City aveiro = new City("Aveiro");
        entityManager.persistAndFlush(aveiro);

        City foundCity = cityRepository.findByName(aveiro.getName());
        assertThat(foundCity).isEqualTo(aveiro);
    }

    @Test
    public void whenInvalidCityName_thenReturnNull() {
        City cityFromDb = cityRepository.findByName("Does Not Exist");
        assertThat(cityFromDb).isNull();
    }

    @Test
    public void givenSetOfCities_whenFindAll_thenReturnAllCities() {
        City aveiro = new City("Aveiro");
        City porto = new City("Porto");
        City lisboa = new City("Lisboa");

        entityManager.persist(aveiro);
        entityManager.persist(porto);
        entityManager.persist(lisboa);
        entityManager.flush();

        List<City> allEmployees = cityRepository.findAll();

        assertThat(allEmployees).hasSize(3).extracting(City::getName).containsOnly(aveiro.getName(), porto.getName(), lisboa.getName());
    }
}
