package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.CreativePotential;

import java.util.Optional;

public interface CreativePotentialRepository extends JpaRepository<CreativePotential, Integer> {
    Optional<CreativePotential> findByType(String type);
}