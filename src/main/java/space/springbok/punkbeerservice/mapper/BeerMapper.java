package space.springbok.punkbeerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.model.BeerDto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    @Named("stringToDate")
    default Date stringToDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("MM/yyyy");
        return format.parse(dateString);
    }

    @Named("dateToString")
    default String dateToString(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        return sdf.format(date);
    }

    @Mapping(target = "firstBrewed", source = "firstBrewed", qualifiedByName = "stringToDate")
    Beer beerDtoToBeer(BeerDto beer);

    @Mapping(target = "firstBrewed", source = "firstBrewed", qualifiedByName = "dateToString")
    BeerDto beerToBeerDto(Beer beer);

}
