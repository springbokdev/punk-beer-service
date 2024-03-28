package space.springbok.punkbeerservice.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.services.BeerService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    private final BeerService beerService;
    public static final String BEER_PATH = "/api/v1/beers";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @GetMapping(value = BEER_PATH_ID)
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") Long beerId){
        log.info("Get Beer by Id");
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @GetMapping(value = BEER_PATH + "/random")
    public ResponseEntity<BeerDto> getRandomBeer() {
        log.info("Get a random beer");
        return new ResponseEntity<>(beerService.getRandomBeer(), HttpStatus.OK);
    }

    @GetMapping(value = BEER_PATH)
    public ResponseEntity<Page<BeerDto>> listBeers(@ModelAttribute @Valid BeerFilter beerFilter) {
        return new ResponseEntity<>(beerService.findBeers(beerFilter), HttpStatus.OK);
    }

}
