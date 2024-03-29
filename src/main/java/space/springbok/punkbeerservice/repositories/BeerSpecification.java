package space.springbok.punkbeerservice.repositories;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.utils.DateUtils;
import space.springbok.punkbeerservice.web.controller.BeerFilter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 *
 */
@Slf4j
@AllArgsConstructor
@Getter
public class BeerSpecification implements Specification<Beer> {

    private BeerFilter filter;

    @Override
    public Predicate toPredicate(Root<Beer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (!isNull(filter.getAbv_gt())) {
            return builder.greaterThan(root.get("abv"), filter.getAbv_gt());
        } else if (!isNull(filter.getAbv_lt())) {
            return builder.lessThan(root.get("abv"), filter.getAbv_lt());
        } else if (!isNull(filter.getIbu_gt())) {
            return builder.greaterThan(root.get("ibu"), filter.getIbu_gt());
        } else if (!isNull(filter.getIbu_lt())) {
            return builder.lessThan(root.get("ibu"), filter.getIbu_lt());
        } else if (!isNull(filter.getEbc_gt())) {
            return builder.greaterThan(root.get("ebu"), filter.getEbc_gt());
        } else if (!isNull(filter.getEbc_lt())) {
            return builder.lessThan(root.get("ebu"), filter.getEbc_lt());
        } else if (!isNull(filter.getBeer_name())) {
            return builder.like(root.get("name"), filter.getBeer_name());
        } else if (!isNull(filter.getBrewed_after())) {
            Date firstBrewed = null;
            try {
                firstBrewed = DateUtils.stringToDate(filter.getBrewed_after());
            } catch (ParseException e) {
                log.error("Error parsing the date string brewed_after {}", filter.getBrewed_after());
            }
            return builder.greaterThan(root.get("firstBrewed"), firstBrewed);
        } else if (!isNull(filter.getBrewed_before())) {
            Date firstBrewed = null;
            try {
                firstBrewed = DateUtils.stringToDate(filter.getBrewed_after());
            } catch (ParseException e) {
                log.error("Error parsing the date string brewed_before {}", filter.getBrewed_before());
            }
            return builder.lessThan(root.get("firstBrewed"), firstBrewed);
        } else if (!isNull(filter.getYeast())){
            // TODO implement filter on yeast
            return builder.like(root.get("ingredients").get("yeast"), filter.getYeast());
        } else if (!isNull(filter.getIds())) {
            // TODO implement filter on IDS
            List<Integer> ids = Arrays.stream(filter.getIds().split("|"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return null;
        }
        return null;
    }

}
