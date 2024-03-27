package space.springbok.punkbeerservice.model;

import java.util.List;
import lombok.Data;

@Data
public class Method{
	private List<MashTempItem> mashTemp;
	private Fermentation fermentation;
	private Object twist;
}