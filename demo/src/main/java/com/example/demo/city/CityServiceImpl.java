package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City getCitiesById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public City getCitiesByName(String name) {
        /*final String uri = "https://api.waqi.info/feed/"+ name +"/?token=3b42a1a4b7028abfb26999365a4f97594cb54212";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;*/
        return cityRepository.findByName(name);
    }

    @Override
    public boolean exists(String name) {
        if (cityRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
