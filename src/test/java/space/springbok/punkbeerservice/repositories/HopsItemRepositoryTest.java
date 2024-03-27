package space.springbok.punkbeerservice.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import space.springbok.punkbeerservice.entities.HopsItem;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HopsItemRepositoryTest {

    @Autowired
    HopsItemRepository hopsItemRepository;

    @BeforeEach
    void setUp() {
        HopsItem hopsItem1 = HopsItem.builder()
                .name("hops1")
                .build();
        HopsItem hopsItem2 = HopsItem.builder()
                .name("hops2")
                .build();
        hopsItemRepository.saveAllAndFlush(Arrays.asList(hopsItem1, hopsItem2));
    }

    @Test
    void testGetAllHopsItems() {
        assertThat(hopsItemRepository.findAll().size()).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        hopsItemRepository.deleteAll();
    }
}