package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.classificators.EducationCategory;

public interface EducationCategoryRepository extends JpaRepository<EducationCategory, Integer> {

}
