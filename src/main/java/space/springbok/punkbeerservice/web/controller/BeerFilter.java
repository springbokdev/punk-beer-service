package space.springbok.punkbeerservice.web.controller;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * This filter is used for listing the beers based on HTTP request parameters
 */
@Getter
@Setter
public class BeerFilter {

    @PositiveOrZero
    private Integer per = 0;

    @Positive
    private Integer perPage = 25;

    @Positive
    private Integer abv_gt;

    @Positive
    private Integer abv_lt;

    @Positive
    private Integer ibu_gt;

    @Positive
    private Integer ibu_lt;

    @Positive
    private Integer ebc_gt;

    @Positive
    private Integer ebc_lt;

    private String beer_name;
    private String yeast;
    private String brewed_before;
    private String brewed_after;
    private String hops;
    private String malt;
    private String food;
    private String ids;

}
