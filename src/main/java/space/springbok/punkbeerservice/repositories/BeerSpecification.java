package space.springbok.punkbeerservice.repositories;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.web.controller.BeerFilter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

/**
 *
 */
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
            // TODO implement filter on brewed_after
            return null;
        } else if (!isNull(filter.getBrewed_before())) {
            // TODO implement filter on brewed_before
            return null;
        } else if (!isNull(filter.getYeast())){
            // TODO implement filter on yeast
            return null;
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
