package org.meows.repositories;

import jakarta.persistence.criteria.Predicate;
import org.meows.entities.CatEntity;
import org.meows.models.filter.CatFilterRequest;
import org.meows.models.utils.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CatEntityRepository extends JpaRepository<CatEntity, Long>, JpaSpecificationExecutor<CatEntity> {

    static Specification<CatEntity> anyMatch(CatFilterRequest filterRequest) {
        if (filterRequest == null) {
            return (cat, query, cb) -> cb.and();
        }

        return (cat, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterRequest.getName() != "") {
                predicates.add(cb.like(cat.get("name"), filterRequest.getName()));
            }

            if (filterRequest.getBreed() != "") {
                predicates.add(cb.like(cat.get("breed"), filterRequest.getBreed()));
            }

            if (filterRequest.getColor() != Color.Default) {
                predicates.add(cb.equal(cat.get("color"), filterRequest.getColor()));
            }

            if (filterRequest.getOwner() != Long.MIN_VALUE) {
                predicates.add(cb.equal(cat.get("owner").get("id"), filterRequest.getOwner()));
            }

            if (filterRequest.getBirthDate() != LocalDate.MIN) {
                predicates.add(cb.equal(cat.get("birthDate"), filterRequest.getBirthDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    Page<CatEntity> findAll(Specification<CatEntity> spec, Pageable page);
}
