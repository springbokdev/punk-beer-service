package space.springbok.punkbeerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.model.BeerDto;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer beerDtoToBeer(BeerDto beer);

    BeerDto beerToBeerDto(Beer beer);
}
