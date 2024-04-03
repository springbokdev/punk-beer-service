package space.springbok.punkbeerservice.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import space.springbok.punkbeerservice.entities.Beer;


public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {

    @Query(
            value = "SELECT b.* " +
                    "FROM BEERS b " +
                    "INNER JOIN ingredients i on i.id = b.ingredients_id " +
                    "INNER JOIN hops_item hi on hi.ingredients_id = i.id " +
                    "WHERE soundex(hi.name) = soundex(:hopsName) " +
                    "UNION " +
                    "SELECT b.* " +
                    "FROM BEERS b " +
                    "INNER JOIN ingredients i on i.id = b.ingredients_id " +
                    "INNER JOIN malt_item mi on mi.ingredients_id = i.id " +
                    "WHERE soundex(mi.name) = soundex(:maltName) ",
            nativeQuery = true
    )
    Page<Beer> findByIngredientsNative(String hopsName, String maltName, Pageable pageable);

    @Query("SELECT b " +
            "FROM Beer b " +
            "JOIN Ingredients i ON i.id = b.ingredients.id " +
            "JOIN HopsItem hi ON hi.id = i.id " +
            "WHERE hi.name like :hopsName " +
            "UNION " +
            "SELECT b " +
            "FROM Beer b " +
            "JOIN Ingredients i ON i.id = b.ingredients.id " +
            "JOIN MaltItem mi on mi.id = i.id " +
            "WHERE mi.name like :maltName")
    Page<Beer> findByIngredientsJPA(String hopsName, String maltName, Pageable pageable);
}
