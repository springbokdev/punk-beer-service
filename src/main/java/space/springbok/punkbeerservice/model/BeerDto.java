package space.springbok.punkbeerservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

	private Long id;

	private String name;

	private String tagline;

	private String firstBrewed;

	private String description;

	private String imageUrl;

	private Integer abv;

	private Integer ibu;

	private Integer targetFg;

	private Integer targetOg;

	private Integer ebc;

	private Double srm;

	private Double ph;

	private Integer attenuationLevel;

	private Volume volume;

	private BoilVolume boilVolume;

	private Method method;

	private Ingredients ingredients;

	private List<String> foodPairing;

	private String brewersTips;

	private String contributedBy;

}