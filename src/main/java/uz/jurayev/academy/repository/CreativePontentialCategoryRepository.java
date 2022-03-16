package uz.jurayev.academy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.CreativePotentialCategory;

import java.util.Optional;

public interface CreativePontentialCategoryRepository extends JpaRepository<CreativePotentialCategory, Integer> {
    Optional<CreativePotentialCategory> findByValue(String value);
}
