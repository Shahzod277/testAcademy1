package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.domain.StudentGroup;


import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from student s inner join student_group sg on s.group_id = sg.id " +
            " inner join tutor t on t.id = sg.tutor_id" +
            " inner join users u on u.id = t.user_id" +
            " where u.username=:username", nativeQuery = true)
    List<Student> getAllStudent(@Param("username") String username);

    @Query(value = "select * from student s inner join student_group sg on s.group_id = sg.id " +
            " where sg.group_name:=groupname", nativeQuery = true)
    List<Student> getAllStudentByGroup(@Param("groupname") String groupname);


    List<Student> findStudentByGroup_GroupName(String group_groupName);

    @Query(value = "Select *\n" +
            "from student u\n" +
            "where (u.firstname ilike %:word%" +
            "     or u.lastname ilike %:word%)", nativeQuery = true)
    List<Student> searchFirstnameAndLastname(@Param("word") String word);
}