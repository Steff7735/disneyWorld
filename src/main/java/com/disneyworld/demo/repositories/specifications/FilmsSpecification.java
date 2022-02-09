package com.disneyworld.demo.repositories.specifications;

import com.disneyworld.demo.dto.FilmsFiltersDto;
import com.disneyworld.demo.entities.Films;
import com.disneyworld.demo.entities.Genders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmsSpecification {

    public Specification<Films> getFiltered(FilmsFiltersDto filmsFilters) {

        // Lambda Function:
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filmsFilters.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filmsFilters.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filmsFilters.getGenders())) {
                Join<Films, Genders> join = root.join("filmsGenders", JoinType.INNER);
                Expression<String> gendersId = join.get("id");
                predicates.add(gendersId.in(filmsFilters.getGenders()));
            }

            query.distinct(true);

            String orderByField = "title";
            query.orderBy(
                    filmsFilters.isASC()
                    ? criteriaBuilder.asc(root.get(orderByField))
                    : criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
