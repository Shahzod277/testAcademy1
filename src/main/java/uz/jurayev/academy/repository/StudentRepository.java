package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jurayev.academy.domain.Student;


import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(  value = "select * from student s inner join student_group sg on s.student_group_id = sg.id " +
            " inner join tutor t on t.id = sg.tutor_id" +
            " inner join users u on u.id = t.user_id"+
            " where u.username=:username", nativeQuery = true)
    List<Student> getAllStudent(@Param("username") String username);
}