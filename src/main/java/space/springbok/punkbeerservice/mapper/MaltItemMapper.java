package space.springbok.punkbeerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import space.springbok.punkbeerservice.entities.MaltItem;

import java.util.List;

@Mapper
public interface MaltItemMapper {

    MaltItemMapper INSTANCE = Mappers.getMapper(MaltItemMapper.class);

    List<MaltItem> maltItemDtoToMaltItem(List<space.springbok.punkbeerservice.model.MaltItem> maltItems);
}
