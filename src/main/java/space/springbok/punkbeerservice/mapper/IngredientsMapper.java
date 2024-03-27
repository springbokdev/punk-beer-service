package space.springbok.punkbeerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import space.springbok.punkbeerservice.entities.Ingredients;

@Mapper
public interface IngredientsMapper {

    IngredientsMapper INSTANCE = Mappers.getMapper(IngredientsMapper.class);

    Ingredients ingredientsDtoToIngredient(space.springbok.punkbeerservice.model.Ingredients ingredients);

    space.springbok.punkbeerservice.model.Ingredients ingredientsToIngredientsDto(Ingredients beer);
}
