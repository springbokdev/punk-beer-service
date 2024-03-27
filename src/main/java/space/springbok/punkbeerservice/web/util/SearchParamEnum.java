package space.springbok.punkbeerservice.web.util;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * This enum is used for mapping the query parameters with the search criteria
 * for the beer specification.
 */
@Getter
@AllArgsConstructor
public enum SearchParamEnum {
    ABV_GT("abv_gt", "abv"),
    ABV_LT("abv_lt", "abv"),
    IBU_GT("ibu_gt", "ibu"),
    IBU_LT("ibu_lt", "ibu"),
    EBC_GT("ebc_gt", "ebc"),
    EBC_LT("ebc_lt", "ebc"),
    BEER_NAME("beer_name", "name");

    private final String paramName;
    private final String propertyName;

}
