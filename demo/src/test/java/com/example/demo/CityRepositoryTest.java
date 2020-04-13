package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void whenFindByName_thenReturnCity() {
        // given
        City ct = new City("Paredes");
        entityManager.persist(ct);
        entityManager.flush();

        // when
        City found = cityRepository.findByName(ct.getName());

        // then
        assertThat(found.getName()).isEqualTo(ct.getName());
    }
    @Test
    public void whenInvalidName_thenReturnNull() {
        City fromDb = cityRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }
}
