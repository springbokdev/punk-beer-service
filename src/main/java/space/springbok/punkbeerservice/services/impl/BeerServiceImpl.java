package space.springbok.punkbeerservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import space.springbok.punkbeerservice.mapper.BeerMapper;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.repositories.BeerRepository;
import space.springbok.punkbeerservice.services.BeerService;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {
    private static final Random random = new Random();

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    @Override
    public Optional<BeerDto> getBeerById(Long id) {

        Optional<space.springbok.punkbeerservice.entities.Beer> beer = beerRepository.findById(id);

        if (beer.isPresent()) {
            BeerDto beerDto = beerMapper.beerToBeerDto(beer.get());
            return Optional.of(beerDto);
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<BeerDto> getRandomBeer() {
        List<Long> idList = beerRepository.findAll().stream().map(space.springbok.punkbeerservice.entities.Beer::getId).toList();
        int randomIndex = random.nextInt(idList.size());
        Long randomId = idList.get(randomIndex);
        Optional<space.springbok.punkbeerservice.entities.Beer> randomBeer = beerRepository.findById(randomId);

        if (randomBeer.isPresent()) {
            return getBeerById(randomBeer.get().getId());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Page<BeerDto> findAll(Pageable pageable) {
        return beerRepository.findAll(pageable).map(BeerMapper.INSTANCE::beerToBeerDto);

    }

    @Override
    public List<BeerDto> findByIngredients(String hopsName, String maltName) {
        return beerRepository.findByIngredients(hopsName, maltName).stream()
                .map(beer -> BeerMapper.INSTANCE.beerToBeerDto(beer))
                .collect(Collectors.toList());
    }

}
