package space.springbok.punkbeerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import space.springbok.punkbeerservice.entities.Beer;

import java.util.List;

public interface BeerRepository extends JpaRepository<Beer, Long>, JpaSpecificationExecutor<Beer> {

    @Query(
            value = "SELECT distinct b.* " +
                    "FROM BEERS b " +
                    "INNER JOIN ingredients i on i.id = b.ingredients_id " +
                    "INNER JOIN hops_item hi on hi.ingredients_id = i.id " +
                    "WHERE soundex(hi.name) = soundex(:hopsName) " +
                    "UNION " +
                    "SELECT distinct b.* " +
                    "FROM BEERS b " +
                    "INNER JOIN ingredients i on i.id = b.ingredients_id " +
                    "INNER JOIN malt_item mi on mi.ingredients_id = i.id " +
                    "WHERE soundex(mi.name) = soundex(:maltName) ",
            nativeQuery = true
    )
    List<Beer> findByIngredients(String hopsName, String maltName);


}
