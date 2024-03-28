package space.springbok.punkbeerservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import space.springbok.punkbeerservice.mapper.BeerMapper;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.repositories.BeerRepository;
import space.springbok.punkbeerservice.repositories.BeerSpecification;
import space.springbok.punkbeerservice.services.BeerService;
import space.springbok.punkbeerservice.web.controller.BeerFilter;
import space.springbok.punkbeerservice.web.controller.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private static final Random random = new Random();

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(Long id) {
        Optional<space.springbok.punkbeerservice.entities.Beer> beer = beerRepository.findById(id);

        if (beer.isPresent()) {
            BeerDto beerDto = beerMapper.beerToBeerDto(beer.get());
            return beerDto;
        } else {
            log.warn("Cannot find beer with id: {}", id);
            throw new NotFoundException("Cannot find beer with id: " + id);
        }
    }

    @Override
    public BeerDto getRandomBeer() {
        List<Long> idList = beerRepository.findAll().stream().map(space.springbok.punkbeerservice.entities.Beer::getId).toList();
        int randomIndex = random.nextInt(idList.size());
        Long randomId = idList.get(randomIndex);
        Optional<space.springbok.punkbeerservice.entities.Beer> randomBeer = beerRepository.findById(randomId);

        if (randomBeer.isPresent()) {
            return getBeerById(randomBeer.get().getId());
        } else {
            log.warn("Cannot get a random beer");
            throw new NotFoundException("Cannot get a random beer");
        }
    }

    @Override
    public Page<BeerDto> findBeers(BeerFilter beerFilter) {

        BeerSpecification spec = new BeerSpecification(beerFilter);
        Pageable pageable = PageRequest.of(beerFilter.getPer(), beerFilter.getPerPage());

        if (!isNull(beerFilter.getHops()) || !isNull(beerFilter.getMalt())) {
            return beerRepository.findByIngredients(beerFilter.getHops(), beerFilter.getMalt(),pageable)
                .map(beer -> BeerMapper.INSTANCE.beerToBeerDto(beer));
        } else {
            return beerRepository.findAll(spec, pageable).map(BeerMapper.INSTANCE::beerToBeerDto);
        }

    }

}
