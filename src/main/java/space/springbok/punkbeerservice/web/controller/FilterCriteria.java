package space.springbok.punkbeerservice.web.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FilterCriteria {

    private Integer abv_gt;
    private Integer abv_lt;
    private Integer ibu_gt;
    private Integer ibu_lt;
    private Integer ebc_gt;
    private Integer ebc_lt;
    private String beer_name;
    private String yeast;
    private LocalDateTime brewed_before;
    private LocalDateTime brewed_after;
    private String hops;
    private String malt;
    private String food;
    private String ids;
}
