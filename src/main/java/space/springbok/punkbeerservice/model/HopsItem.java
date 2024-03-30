package space.springbok.punkbeerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HopsItem {
	private String add;
	private Amount amount;
	private String name;
	private String attribute;
}