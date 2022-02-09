package com.disneyworld.demo.repositories.specifications;

import com.disneyworld.demo.dto.CharactersFiltersDto;
import com.disneyworld.demo.entities.Characters;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.disneyworld.demo.entities.Films;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class CharactersSpecification {

    public Specification<Characters> getFiltered(CharactersFiltersDto charactersFilters) {

        // LAMBDA Function:
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(charactersFilters.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + charactersFilters.getName().toLowerCase() + "%"
                        )
                );
            }

            if (charactersFilters.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), charactersFilters.getAge()));
            }

            if (!CollectionUtils.isEmpty(charactersFilters.getFilms())) {
                Join<Characters, Films> join = root.join("charactersFilms", JoinType.INNER);
                Expression<String> filmsId = join.get("id");
                predicates.add(filmsId.in(charactersFilters.getFilms()));
            }

            query.distinct(true);

            query.orderBy(criteriaBuilder.asc(root.get("name")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
