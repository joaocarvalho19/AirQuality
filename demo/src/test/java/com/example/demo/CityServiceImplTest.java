package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityRepository;
import com.example.demo.city.CityService;
import com.example.demo.city.CityServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @TestConfiguration
    static class CityServiceImplTestContextConfiguration {

        @Bean
        public CityService cityService() {
            return new CityServiceImpl();
        }
    }

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock( lenient = true)
    private CityRepository cityRepository;

    @BeforeEach
    public void setUp() {
        City tar = new City("Tarouca");
        tar.setId(123L);

        City aveiro = new City("Aveiro");
        City ovar = new City("Ovar");

        List<City> allEmployees = Arrays.asList(tar, aveiro, ovar);

        Mockito.when(cityRepository.findByName(tar.getName())).thenReturn(tar);
        Mockito.when(cityRepository.findByName(ovar.getName())).thenReturn(ovar);
        Mockito.when(cityRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(cityRepository.findById(tar.getId())).thenReturn(Optional.of(tar));
        Mockito.when(cityRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(cityRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    public void whenNonExistingName_thenCityShouldNotExist() {
        boolean doesCityExist = cityService.exists("some_name");
        assertThat(doesCityExist).isEqualTo(false);

        verifyFindByNameIsCalledOnce("some_name");
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "Ovar";
        City found = cityService.getCitiesByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
    @Test
    public void whenInValidName_thenEmployeeShouldNotBeFound() {
        City fromDb = cityService.getCitiesByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        City fromDb = cityService.getCitiesById(123L);
        assertThat(fromDb.getName()).isEqualTo("Tarouca");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void whenInValidId_thenEmployeeShouldNotBeFound() {
        City fromDb = cityService.getCitiesById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Employees_whengetAll_thenReturn3Records() {
        City ovar = new City("Ovar");
        City tar = new City("Tarouca");
        City aveiro = new City("Aveiro");

        List<City> allEmployees = cityService.getAllCities();
        verifyFindAllEmployeesIsCalledOnce();
        assertThat(allEmployees).hasSize(3).extracting(City::getName).contains(ovar.getName(), tar.getName(), aveiro.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(cityRepository);
    }
    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(cityRepository);
    }

    private void verifyFindAllEmployeesIsCalledOnce() {
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(cityRepository);
    }
}
