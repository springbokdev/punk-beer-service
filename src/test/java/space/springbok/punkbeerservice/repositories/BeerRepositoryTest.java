package space.springbok.punkbeerservice.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.entities.HopsItem;
import space.springbok.punkbeerservice.entities.Ingredients;
import space.springbok.punkbeerservice.entities.MaltItem;
import space.springbok.punkbeerservice.web.controller.BeerFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @BeforeEach
    void setUp() {

        HopsItem hopsItem1 = HopsItem.builder().name("hops 1").build();
        HopsItem hopsItem2 = HopsItem.builder().name("hops 2").build();
        HopsItem hopsItem3 = HopsItem.builder().name("hops 3").build();
        HopsItem hopsItem4 = HopsItem.builder().name("hops 4").build();

        MaltItem maltItem1 = MaltItem.builder().name("malt 1").build();
        MaltItem maltItem2 = MaltItem.builder().name("malt 2").build();
        MaltItem maltItem3 = MaltItem.builder().name("malt 3").build();
        MaltItem maltItem4 = MaltItem.builder().name("malt 4").build();


        Ingredients ingredients1 = Ingredients.builder()
                .yeast("yeast1")
                .build();
        Beer beer1 = Beer.builder()
                .id(1L)
                .name("New Beer 1")
                .abv(3)
                .build();
        Beer beer2 = Beer.builder()
                .id(2L)
                .name("New Beer 2")
                .abv(3)
                .build();
        Beer beer3 = Beer.builder()
                .id(3L)
                .name("New Beer 3")
                .abv(7)
                .build();
        Beer beer4 = Beer.builder()
                .id(4L)
                .name("New Beer 4")
                .abv(8)
                .build();
        beerRepository.saveAll(Arrays.asList(beer1, beer2, beer3, beer4));
    }

    @AfterEach
    void tearDown() {
        beerRepository.deleteAll();
    }

    @Test
    void testFindById() {
        Optional<Beer> savedBeer = beerRepository.findById(1L);

        if (savedBeer.isPresent()) {
            assertThat(savedBeer.get().getId()).isEqualTo(1);
            assertThat(savedBeer.get().getName()).isEqualTo("New Beer 1");
        }
    }

    @Test
    void testFindByPageable() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Beer> result = beerRepository.findAll(pageable);
        assertThat(result.getContent().size()).isEqualTo(2);
        assertThat(result.getTotalElements()).isEqualTo(4);
    }

    @Test
    void testWithProperties() {
        BeerFilter filter = new BeerFilter();
        filter.setAbv_gt(3);
        BeerSpecification spec = new BeerSpecification(filter);
        List<Beer> result = beerRepository.findAll(spec);
        assertThat(result.size()).isEqualTo(2);
    }

}