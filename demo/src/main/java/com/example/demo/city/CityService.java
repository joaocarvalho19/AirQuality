package com.example.demo.city;

import java.util.List;

public interface CityService {

    public City getCitiesById(Long id)throws Exception ;

    public City getCitiesByName(String name)throws Exception;

    public List<City> getAllCities()throws Exception ;

    public boolean exists(String email)throws Exception ;

    public City save(City city)throws Exception ;
}
