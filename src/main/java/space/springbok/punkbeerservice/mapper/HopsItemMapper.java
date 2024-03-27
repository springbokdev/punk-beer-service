package space.springbok.punkbeerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import space.springbok.punkbeerservice.entities.HopsItem;
import java.util.List;

@Mapper
public interface HopsItemMapper {

    HopsItemMapper INSTANCE = Mappers.getMapper(HopsItemMapper.class);

    List<HopsItem> hopsItemDtoToHopsItem(List<space.springbok.punkbeerservice.model.HopsItem> hopsItems);

}
