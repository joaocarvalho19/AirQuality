package com.example.demo.city;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityRepository {

    public City findById(Long id)throws Exception ;
    public City findByName(String name) throws Exception;
    public List<City> findAll() throws Exception;
    public City save(City city)throws Exception ;

    boolean exists(String some_name);
}


