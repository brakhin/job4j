package ru.bgbrakhi.carseller.web.controllers;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bgbrakhi.carseller.Application;
import ru.bgbrakhi.carseller.models.*;
import ru.bgbrakhi.carseller.service.CarServiceImpl;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarsControllerTest {

    @MockBean
    private CarServiceImpl service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenGetCarThenPageCar() throws Exception {

        Car car = new Car(
                new User("login", "password"),
                new City("City"),
                new CarModel(new CarType("carType"), new CarMark("carMark"), "carModel"),
                new CarBody("carBody"),
                2000,
                300000,
                "",
                false,
                System.currentTimeMillis()
        );
        given(this.service.findWithFilter(null, true))
                .willReturn(new ArrayList<Car>(Lists.newArrayList(car)));

        this.mvc.perform(
                get("/").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("cars")
        );
    }

    @Test
    public void whenPostCarThenPageCar() throws Exception {

        this.mvc.perform(
                post("/")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("cars")
        );
    }

}