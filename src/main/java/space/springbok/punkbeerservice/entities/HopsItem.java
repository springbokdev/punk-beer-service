package space.springbok.punkbeerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.springbok.punkbeerservice.model.Amount;

@Setter
@Builder
@Entity
@Table(name = "hops_item")
@NoArgsConstructor
@AllArgsConstructor
public class HopsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Ingredients ingredients;

}
