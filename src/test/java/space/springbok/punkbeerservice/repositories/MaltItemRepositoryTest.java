package space.springbok.punkbeerservice.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import space.springbok.punkbeerservice.entities.MaltItem;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MaltItemRepositoryTest {

    @Autowired
    MaltItemRepository maltItemRepository;

    @BeforeEach
    void setUp() {
        MaltItem maltItem1 = MaltItem.builder()
                .name("malt1")
                .build();
        MaltItem maltItem2 = MaltItem.builder()
                .name("malt2")
                .build();
        maltItemRepository.saveAll(Arrays.asList(maltItem1, maltItem2));
    }

    @Test
    void getAllMaltItems() {
        assertThat(maltItemRepository.findAll().size()).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        maltItemRepository.deleteAll();
    }
}