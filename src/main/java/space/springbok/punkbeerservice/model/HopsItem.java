package space.springbok.punkbeerservice.model;

import lombok.Data;

@Data
public class HopsItem {
	private String add;
	private Amount amount;
	private String name;
	private String attribute;
}