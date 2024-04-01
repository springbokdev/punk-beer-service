package space.springbok.punkbeerservice.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.repositories.BeerRepository;
import space.springbok.punkbeerservice.repositories.BeerSpecification;
import space.springbok.punkbeerservice.web.controller.BeerFilter;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeerServiceImplTest {


    @MockBean
    BeerRepository beerRepository;

    @Autowired
    BeerServiceImpl beerService;

    @Test
    void getBeerById() {
        // Given
        Beer beer = Beer.builder()
                .id(1L)
                .name("A Beer")
                .firstBrewed(Date.from(Instant.now()))
                .build();
        when(beerRepository.findById(1L)).thenReturn(Optional.of(beer));

        // When
        BeerDto result =  beerService.getBeerById(1l);
        assertThat(result.getName()).isEqualTo("A Beer");
        verify(beerRepository).findById(any());
    }

    @Test
    void getRandomBeer() {
        Beer beer1 = Beer.builder()
                .id(1L)
                .name("A Beer 1")
                .firstBrewed(Date.from(Instant.now()))
                .build();
        Beer beer2 = Beer.builder()
                .id(2L)
                .name("A Beer 2")
                .firstBrewed(Date.from(Instant.now()))
                .build();
        Beer beer3 = Beer.builder()
                .id(3L)
                .name("A Beer 3")
                .firstBrewed(Date.from(Instant.now()))
                .build();
        when(beerRepository.findAll()).thenReturn(Arrays.asList(beer1, beer2, beer3));
        when(beerRepository.findById(any())).thenReturn(Optional.of(beer1));
        BeerDto randomBeer = beerService.getRandomBeer();
        assertThat(randomBeer).isNotNull();
        assertThat(randomBeer.getId()).isIn(1L, 2L, 3L);
        verify(beerRepository).findAll();
    }
}