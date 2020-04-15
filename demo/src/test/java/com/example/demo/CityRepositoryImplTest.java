package com.example.demo;

import com.example.demo.city.City;

import com.example.demo.city.CityRepositoryImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CityRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepositoryImpl cityRepository;


    @Test
    public void whenFindByName_thenReturnCity() throws Exception {
        // given
        City ct = new City("Paredes");

        // when
        City found = cityRepository.findByName(ct.getName());

        // then
        assertThat(found.getName()).isEqualTo(ct.getName());
    }
    @Test
    public void whenInvalidName_thenReturnNull() throws Exception {
        City fromDb = cityRepository.findByName("doesNotExist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindById_thenReturnCity() throws Exception {
        City ct = new City("test");
        entityManager.persistAndFlush(ct);

        City fromDb = cityRepository.findById(ct.getId());
        assertThat(fromDb.getName()).isEqualTo(ct.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() throws Exception {
        City fromDb = cityRepository.findById(-11l);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCities_whenFindAll_thenReturnAllCities() throws Exception {
        City tarouca = new City("Tarouca");
        City aveiro = new City("Aveiro");
        City ovar = new City("Ovar");

        entityManager.persist(tarouca);
        entityManager.persist(ovar);
        entityManager.persist(aveiro);
        entityManager.flush();

        List<City> allCities = cityRepository.findAll();

        assertThat(allCities).hasSize(3).extracting(City::getName).containsOnly(tarouca.getName(), aveiro.getName(), ovar.getName());
    }
}
