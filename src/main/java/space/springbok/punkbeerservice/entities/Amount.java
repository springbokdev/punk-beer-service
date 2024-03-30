package space.springbok.punkbeerservice.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Embeddable
@AttributeOverrides({
        @AttributeOverride( name = "unit", column = @Column(name = "amount_unit")),
        @AttributeOverride( name = "value", column = @Column(name = "amount_value"))
})
public class Amount {
    private String unit;
    private Double value;
}
