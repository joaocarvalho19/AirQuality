package com.example.demo;

import com.example.demo.city.City;
import com.example.demo.city.CityController;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
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

}