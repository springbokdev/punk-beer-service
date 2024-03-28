package space.springbok.punkbeerservice.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.web.controller.BeerFilter;

import java.util.List;
import java.util.Optional;

public interface BeerService {

    BeerDto getBeerById(Long id);

    BeerDto getRandomBeer();

    Page<BeerDto> findBeers(BeerFilter beerFilter);

}
