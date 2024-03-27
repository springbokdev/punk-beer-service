package space.springbok.punkbeerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import space.springbok.punkbeerservice.entities.Ingredients;

import java.util.Optional;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {

    Optional<Ingredients> findByYeast(String yeast);
}
