package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.domain.User;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    Optional<Tutor> findByUser_Username(String username);

    @Query(value = "select * from tutor t inner join users u on u.id = t.user_id " +
            "where t.avtor=:username",nativeQuery = true)
    List<Tutor> getAllTutorByUser(@Param("username") String username);
}