package space.springbok.punkbeerservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Ingredients{
	private List<HopsItem> hops;
	private String yeast;
	private List<MaltItem> malt;
}