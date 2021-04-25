package repository;

import model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, String> {
    public City findByName(String name);
    public List<City> findAll();
}