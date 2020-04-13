package com.example.demo.city;

import java.util.List;

public interface CityService {

    public City getCitiesById(Long id);

    public City getCitiesByName(String name);

    public List<City> getAllCities();

    public boolean exists(String email);

    public City save(City city);
}
