package space.springbok.punkbeerservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
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

    private String add;

    @Embedded
    private Amount amount;

    private String name;
    private String attribute;

    @ManyToOne
    private Ingredients ingredients;

}
