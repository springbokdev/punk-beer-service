package space.springbok.punkbeerservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "beers")
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    private String tagline;

    private Date firstBrewed;

    @Size(max = 500)
    private String description;

    private String imageUrl;

    private Integer abv;

    private Integer ibu;

    private Integer targetFg;

    private Integer targetOg;

    private Integer ebc;

    private Double srm;

    private Double ph;

    private Integer attenuationLevel;

    @ManyToOne
    private Ingredients ingredients;

    @Size(max = 500)
    private String brewersTips;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "beer_food_pairing", joinColumns = @JoinColumn(name = "beer_id"))
    @Column(name = "food_pairing")
    private List<String> foodPairing;

    private String contributedBy;

}
