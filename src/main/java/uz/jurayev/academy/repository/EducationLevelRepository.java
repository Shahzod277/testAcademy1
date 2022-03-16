package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.EducationLevel;


public interface EducationLevelRepository extends JpaRepository<EducationLevel, Integer> {

}
