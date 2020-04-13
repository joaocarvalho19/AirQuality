package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/air")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/search/{name}")
    public City searchCities(@PathVariable String name){
        return cityService.getCitiesByName(name);
    }

    //@GetMapping(path="/cities", produces = "application/json")
    @GetMapping(path="/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping("/save")
    public ResponseEntity<City> save(@RequestBody City city) {
        HttpStatus status = HttpStatus.CREATED;
        City saved = cityService.save(city);
        return new ResponseEntity<>(saved, status);
    }

    @PutMapping("/update")
    public City update(@RequestBody City employee) {
        cityService.save(employee);
        return employee;
    }
}
