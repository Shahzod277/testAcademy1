package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
