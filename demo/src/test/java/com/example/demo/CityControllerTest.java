package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityRestController;
import com.example.demo.city.CityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService service;

    @Test
    public void whenPostCity_thenCreateCity() throws Exception {
        City tarouca = new City("Tarouca");
        given(service.save(Mockito.any())).willReturn(tarouca);

        mvc.perform(post("/air/save").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(tarouca))).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("Tarouca")));
        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCities_whenGetCities_thenReturnJsonArray()
            throws Exception {

        City ct = new City("Paredes");

        List<City> allEmployees = Arrays.asList(ct);

        given(service.getAllCities()).willReturn(allEmployees);

        mvc.perform(get("/air/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(ct.getName())));
    }
}