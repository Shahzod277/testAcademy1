package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.Region;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
