package space.springbok.punkbeerservice.repositories;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import space.springbok.punkbeerservice.entities.Beer;
import space.springbok.punkbeerservice.web.util.SearchCriteria;


import static space.springbok.punkbeerservice.web.util.SearchParamEnum.*;

@AllArgsConstructor
@Getter
public class BeerSpecification implements Specification<Beer> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Beer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
          if (criteria.getKey().equals(ABV_GT.getParamName())) {
              return builder.greaterThan(root.<Integer> get(ABV_GT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          } else if (criteria.getKey().equals(ABV_LT.getParamName())) {
              return builder.lessThan(root.<Integer> get(ABV_LT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          } else if (criteria.getKey().equals(IBU_GT.getParamName())) {
              return builder.greaterThan(root.<Integer> get(IBU_GT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          } else if (criteria.getKey().equals(IBU_LT.getParamName())) {
              return builder.lessThan(root.<Integer> get(IBU_LT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          } else if (criteria.getKey().equals(EBC_GT.getParamName())) {
              return builder.greaterThan(root.<Integer> get(EBC_GT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          } else if (criteria.getKey().equals(EBC_LT.getParamName())) {
              return builder.lessThan(root.<Integer> get(EBC_LT.getPropertyName()), Integer.parseInt(criteria.getValue().toString()));
          }

         return null;
    }

}
