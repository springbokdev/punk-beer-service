package space.springbok.punkbeerservice.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.services.BeerService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    private final BeerService beerService;

    public static final String BEER_PATH = "/api/v1/beers";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @GetMapping(value = BEER_PATH_ID)
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") Long beerId){

        log.debug("Get Beer by Id - in controller");

        return new ResponseEntity(beerService.getBeerById(beerId).orElseThrow(NotFoundException::new), HttpStatus.OK);
    }


    @GetMapping(value = BEER_PATH)
    public ResponseEntity<Page<BeerDto>> listBeers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return new ResponseEntity<>(beerService.findAll(pageable), HttpStatus.OK);


    }

    @GetMapping(value = BEER_PATH + "/random")
    public ResponseEntity<BeerDto> getRandomBeer() {
        log.info("getRandomBeer");
        return new ResponseEntity<>(beerService.getRandomBeer().orElseThrow(NotFoundException::new), HttpStatus.OK);
    }

    @GetMapping(value = BEER_PATH + "/ingredients")
    public ResponseEntity<List<BeerDto>> listByIngredients(
            @RequestParam(required = false) String hops,
            @RequestParam(required = false) String malt) {
        return new ResponseEntity<>(beerService.findByIngredients(hops, malt), HttpStatus.OK);

    }

}
