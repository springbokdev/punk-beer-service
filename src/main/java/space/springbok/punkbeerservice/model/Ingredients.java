package space.springbok.punkbeerservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients{
	private List<HopsItem> hops;
	private String yeast;
	private List<MaltItem> malt;
}