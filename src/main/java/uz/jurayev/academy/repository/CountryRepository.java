package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
