package space.springbok.punkbeerservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String yeast;

    @OneToMany(mappedBy = "ingredients")
    private List<MaltItem> malt;

    @OneToMany(mappedBy = "ingredients")
    private List<HopsItem> hops;
}
