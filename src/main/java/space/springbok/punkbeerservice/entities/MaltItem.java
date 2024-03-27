package space.springbok.punkbeerservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "malt_item")
@NoArgsConstructor
@AllArgsConstructor
public class MaltItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Ingredients ingredients;
}
