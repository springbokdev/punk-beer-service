package space.springbok.punkbeerservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import space.springbok.punkbeerservice.model.BeerDto;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    Optional<BeerDto> getBeerById(Long id);

    Optional<BeerDto> getRandomBeer();

    Page<BeerDto> findAll(Pageable pageable);

    List<BeerDto> findByIngredients(String hopsName, String maltName);

}
