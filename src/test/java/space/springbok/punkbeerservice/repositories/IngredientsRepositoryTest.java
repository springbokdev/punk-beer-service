package space.springbok.punkbeerservice.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import space.springbok.punkbeerservice.entities.Ingredients;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class IngredientsRepositoryTest {

    @Autowired
    IngredientsRepository ingredientsRepository;

    @BeforeEach
    void setUp() {
        Ingredients ingredients1 = Ingredients.builder()
                .yeast("yeast")
                .build();
        Ingredients ingredients2 = Ingredients.builder()
                .yeast("yeast2")
                .build();
        ingredientsRepository.saveAll(Arrays.asList(ingredients1, ingredients2));
    }

    @Test
    void testGetAllIngredients() {
        assertThat(ingredientsRepository.findAll().size()).isEqualTo(2);
    }

    @AfterEach
    void tearDown() {
        ingredientsRepository.deleteAll();
    }
}
