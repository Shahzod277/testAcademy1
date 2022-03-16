package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.domain.User;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    Optional<Tutor> findByUser_Username(String username);


}