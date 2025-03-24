package net.weg.gestaolojainformatica.repository.specification;

import net.weg.gestaolojainformatica.model.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {
    public static Specification<Cliente> hasEndereco(String endereco) {
        return (root, query, criteriaBuilder) -> {
            if (endereco == null || endereco.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("endereco")), "%" + endereco.toLowerCase() + "%");
        };
    }

    public static Specification<Cliente> hasIdade(Integer idadeMin, Integer idadeMax) {
        return (root, query, criteriaBuilder) -> {
            if (idadeMin == null && idadeMax == null) {
                return criteriaBuilder.conjunction();
            }
            if (idadeMin != null && idadeMax != null) {
                return criteriaBuilder.between(root.get("idade"), idadeMin, idadeMax);
            }
            if (idadeMin != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("idade"), idadeMin);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("idade"), idadeMax);
        };
    }

}
