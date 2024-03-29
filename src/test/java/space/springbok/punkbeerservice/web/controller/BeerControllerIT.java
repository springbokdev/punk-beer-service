package space.springbok.punkbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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
        mockMvc.perform(get(BeerController.BEER_PATH + "/" + beer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(beer.getName())));
    }
    @Test
    void getRandomBeer() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH + "/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", isA(Integer.class)))
                .andExpect(jsonPath("$.name", isA(String.class)));
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
    void listBeersFilteredByAbvGt() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "6")
                        .queryParam("abv_gt", "9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(6)))
                .andExpect(jsonPath("$.content.size()", is(2)));
    }

    @Test
    void listBeersFilteredAbvLt() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "6")
                        .queryParam("abv_lt", "7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(6)))
                .andExpect(jsonPath("$.content.size()", is(4)));
    }

    @Test
    void listBeersFilteredIbuLt() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "10")
                        .queryParam("ibu_gt", "30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.content.size()", is(8)));
    }

    @Test
    void listBeersByName() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "10")
                        .queryParam("beer_name", "77 Lager"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(10)))
                .andExpect(jsonPath("$.content[0].name", is("77 Lager")))
                .andExpect(jsonPath("$.content.size()", is(1)));
    }

    @Test
    void listBeersByMalt() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "25")
                        .queryParam("malt", "Extra Pale"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(25)))
                .andExpect(jsonPath("$.content.size()", is(11)));
    }

    @Test
    void listBeersByHops() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "25")
                        .queryParam("hops", "Chinook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(25)))
                .andExpect(jsonPath("$.content.size()", is(3)));
    }

    @Test
    void listBeersByBrewedAfter() throws Exception{
        mockMvc.perform(get(BeerController.BEER_PATH)
                        .queryParam("page","0")
                        .queryParam("perPage", "25")
                        .queryParam("brewed_after", "04/2000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(0)))
                .andExpect(jsonPath("$.size", is(25)))
                .andExpect(jsonPath("$.content.size()", is(11)));
    }


}