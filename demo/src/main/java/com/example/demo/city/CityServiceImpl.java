package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepositoryImpl cityRepository;


    @Override
    public City getCitiesById(Long id) throws Exception {
        return cityRepository.findById(id);
    }

    @Override
    public City getCitiesByName(String name)throws Exception {
        return cityRepository.findByName(name);
    }

    @Override
    public boolean exists(String name)throws Exception {
        if (cityRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }

    @Override
    public City save(City city)throws Exception  {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() throws Exception{
        return cityRepository.findAll();
    }
}
