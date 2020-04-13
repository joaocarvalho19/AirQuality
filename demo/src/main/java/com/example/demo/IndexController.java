package com.example.demo;

import com.example.demo.city.City;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public ModelAndView save(@ModelAttribute City city)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("city-data");
        modelAndView.addObject("city", city);
        return modelAndView;
    }
}
