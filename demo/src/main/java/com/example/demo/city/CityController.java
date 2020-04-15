package com.example.demo.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CityController {

    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("air/search")
    public String searchCities(@RequestParam("name") String name, Model model) throws Exception {

        City city = cityService.getCitiesByName(name);
        model.addAttribute("city", city);
        return "city_list";
    }

    //@GetMapping(path="/cities", produces = "application/json")
    @GetMapping(path="air/cities")
    public String getAllCities(Model model) throws Exception {
        List <City> city = cityService.getAllCities();
        model.addAttribute("city",city);
        return "allcities";
    }

    @PostMapping("air/save")
    public ResponseEntity<City> save(@RequestBody City city) throws Exception {
        HttpStatus status = HttpStatus.CREATED;
        City saved = cityService.save(city);
        return new ResponseEntity<>(saved, status);
    }

    @PutMapping("air/update")
    public City update(@RequestBody City employee) throws Exception {
        cityService.save(employee);
        return employee;
    }
}
