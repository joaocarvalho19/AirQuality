package com.example.demo;

import com.example.demo.city.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

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
    private CityRepositoryImpl cityRepository;

    @BeforeEach
    public void setUp() throws Exception {
        City tar = new City("Tarouca");
        tar.setId(123L);

        City aveiro = new City("Aveiro");
        City ovar = new City("Ovar");

        List<City> allCities = Arrays.asList(tar, aveiro, ovar);

        Mockito.when(cityRepository.findByName(tar.getName())).thenReturn(tar);
        Mockito.when(cityRepository.findByName(ovar.getName())).thenReturn(ovar);
        Mockito.when(cityRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(cityRepository.findAll()).thenReturn(allCities);
    }

    @Test
    public void whenNonExistingName_thenCityShouldNotExist() throws Exception {
        boolean doesCityExist = cityService.exists("some_name");
        assertThat(doesCityExist).isEqualTo(false);

        verifyFindByNameIsCalledOnce("some_name");
    }

    @Test
    public void whenValidName_thenCityShouldBeFound() throws Exception {
        String name = "Ovar";
        City found = cityService.getCitiesByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
    @Test
    public void whenInValidName_thenCityShouldNotBeFound() throws Exception {
        City fromDb = cityService.getCitiesByName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

    @Test
    public void whenInValidId_thenCityShouldNotBeFound() throws Exception {
        City fromDb = cityService.getCitiesById(-99L);
        verifyFindByIdIsCalledOnce();
        assertThat(fromDb).isNull();
    }

    @Test
    public void given3Cities_whengetAll_thenReturn3Records() throws Exception {
        City ovar = new City("Ovar");
        City tar = new City("Tarouca");
        City aveiro = new City("Aveiro");

        List<City> allCities = cityService.getAllCities();
        verifyFindAllCitiesIsCalledOnce();
        assertThat(allCities).hasSize(3).extracting(City::getName).contains(ovar.getName(), tar.getName(), aveiro.getName());
    }

    private void verifyFindByNameIsCalledOnce(String name) throws Exception {
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findByName(name);
        Mockito.reset(cityRepository);
    }
    private void verifyFindByIdIsCalledOnce() throws Exception {
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(cityRepository);
    }

    private void verifyFindAllCitiesIsCalledOnce() throws Exception{
        Mockito.verify(cityRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(cityRepository);
    }
}
