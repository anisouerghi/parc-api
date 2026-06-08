package com.transtu.pacbus.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.transtu.pacbus.dto.AgregaVehFilter;
import com.transtu.pacbus.entity.AgregaVeh;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

/**
 * Construit une {@link Specification} dynamique pour {@link AgregaVeh} à partir
 * d'un {@link AgregaVehFilter}.
 *
 * <p>Chaque critère renseigné est combiné avec les autres via un AND logique.
 * Un filtre vide (ou {@code null}) ne contraint rien et retourne donc tous les
 * enregistrements. Les méthodes utilitaires sont génériques afin de faciliter
 * l'ajout de nouveaux critères (notamment d'autres intervalles de dates).</p>
 */
public final class AgregaVehSpecification {

    private AgregaVehSpecification() {
        // Classe utilitaire : pas d'instanciation.
    }

    public static Specification<AgregaVeh> build(AgregaVehFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter != null) {
                addInPredicate(predicates, root.get("depcod"), filter.getListDepcod());
                addInPredicate(predicates, root.get("vehnum"), filter.getListVehnum());
                addInPredicate(predicates, root.get("vehimmat"), filter.getListVehimmat());

                addEqualsPredicate(predicates, cb, root.get("etacod"), filter.getEtacod());
                addEqualsPredicate(predicates, cb, root.get("etavehcod"), filter.getEtavehcod());

                addDateRangePredicate(predicates, cb, root.get("affectdatdb"),
                        filter.getAffectdatdbFrom(), filter.getAffectdatdbTo());
                addDateRangePredicate(predicates, cb, root.get("vehdatcartgris"),
                        filter.getVehdatcartgrisFrom(), filter.getVehdatcartgrisTo());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static <T> void addInPredicate(List<Predicate> predicates, Path<T> path,
            Collection<T> values) {
        if (!CollectionUtils.isEmpty(values)) {
            predicates.add(path.in(values));
        }
    }

    private static void addEqualsPredicate(List<Predicate> predicates, CriteriaBuilder cb,
            Path<String> path, String value) {
        if (StringUtils.hasText(value)) {
            predicates.add(cb.equal(path, value));
        }
    }

    private static void addDateRangePredicate(List<Predicate> predicates, CriteriaBuilder cb,
            Path<LocalDate> path, LocalDate from, LocalDate to) {
        if (from != null) {
            predicates.add(cb.greaterThanOrEqualTo(path, from));
        }
        if (to != null) {
            predicates.add(cb.lessThanOrEqualTo(path, to));
        }
    }
}
