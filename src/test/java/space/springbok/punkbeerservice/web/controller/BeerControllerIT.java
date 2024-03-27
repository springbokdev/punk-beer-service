package space.springbok.punkbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import space.springbok.punkbeerservice.mapper.BeerMapper;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.repositories.BeerRepository;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void getBeerById() throws Exception{
        space.springbok.punkbeerservice.entities.Beer beer = beerRepository.findAll().get(0);
        BeerDto beerDto = beerMapper.beerToBeerDto(beer);
        mockMvc.perform(get(BeerController.BEER_PATH + "/" + beerDto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(beerDto.getName())));
    }

    @Test
    void listBeers() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(6)))
                .andExpect(jsonPath("$.content.size()", is(6)));
    }

    @Test
    void getRandomBeer() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH + "/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", isA(Integer.class)))
                .andExpect(jsonPath("$.name", isA(String.class)));
    }
}