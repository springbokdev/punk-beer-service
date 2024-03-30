package space.springbok.punkbeerservice.bootstrap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import space.springbok.punkbeerservice.entities.HopsItem;
import space.springbok.punkbeerservice.entities.Ingredients;
import space.springbok.punkbeerservice.entities.MaltItem;
import space.springbok.punkbeerservice.mapper.BeerMapper;
import space.springbok.punkbeerservice.mapper.HopsItemMapper;
import space.springbok.punkbeerservice.mapper.IngredientsMapper;
import space.springbok.punkbeerservice.mapper.MaltItemMapper;
import space.springbok.punkbeerservice.model.BeerDto;
import space.springbok.punkbeerservice.repositories.BeerRepository;
import space.springbok.punkbeerservice.repositories.HopsItemRepository;
import space.springbok.punkbeerservice.repositories.IngredientsRepository;
import space.springbok.punkbeerservice.repositories.MaltItemRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Loads the JSON beer data from the resource folder
 * and stores it in the database.
 */
@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private static final String BEER_RESOURCE_PATH = "classpath:data/json/beerdata/";

    private final ObjectMapper objectMapper;

    private final BeerMapper beerMapper;

    private final IngredientsMapper ingredientsMapper;

    private final MaltItemMapper maltItemMapper;

    private final HopsItemMapper hopsItemMapper;

    private final ResourcePatternResolver resourcePatternResolver;

    private final BeerRepository beerRepository;

    private final IngredientsRepository ingredientsRepository;

    private final MaltItemRepository maltItemRepository;

    private final HopsItemRepository hopsItemRepository;

    @Transactional
    @Override
    public void run (String... args) throws Exception {
        loadBeerData();
    }

    private void loadBeerData() throws FileNotFoundException, IOException {
        if (beerRepository.count() == 0) {

            Resource[] res = resourcePatternResolver.getResources(BEER_RESOURCE_PATH + "*.json");
            for (Resource resource: res) {
                File file = ResourceUtils.getFile(BEER_RESOURCE_PATH + resource.getFilename());
                TypeReference<BeerDto> typeReference = new TypeReference<BeerDto>() {};
                BeerDto beerDto = objectMapper.readValue(file, typeReference);
                space.springbok.punkbeerservice.entities.Beer beer = beerMapper.beerDtoToBeer(beerDto);

                Ingredients savedIngredients = ingredientsRepository.saveAndFlush(ingredientsMapper.ingredientsDtoToIngredient(beerDto.getIngredients()));

                List<MaltItem> savedMaltItems = maltItemRepository.saveAllAndFlush( maltItemMapper.maltItemDtoToMaltItem(beerDto.getIngredients().getMalt()));
                savedMaltItems.stream().forEach(maltItem -> maltItem.setIngredients(savedIngredients));
                savedIngredients.setMalt(savedMaltItems);

                List<HopsItem> savedHopsItems = hopsItemRepository.saveAllAndFlush(hopsItemMapper.hopsItemDtoToHopsItem(beerDto.getIngredients().getHops()));
                savedHopsItems.stream().forEach(hopsItem -> hopsItem.setIngredients(savedIngredients));
                savedIngredients.setHops(savedHopsItems);

                beer.setIngredients(savedIngredients);
                beerRepository.save(beer);
            }
        }
    }
}
