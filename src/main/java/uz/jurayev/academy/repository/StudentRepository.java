package uz.jurayev.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            " where u.username=:username",countQuery ="SELECT count(*) FROM USERS WHERE username =:username", nativeQuery = true)
    Page<Student> getAllStudent(@Param("username") String username, Pageable pageable);

    @Query(value = "select * from student s inner join student_group sg on s.group_id = sg.id " +
            " where sg.group_name:=groupname", nativeQuery = true)
    List<Student> getAllStudentByGroup(@Param("groupname") String groupname);


    List<Student> findStudentByGroup_GroupName(String group_groupName);

    @Query(value = "select *from student s inner join student_group sg on sg.id = s.group_id\n" +
            "    inner join tutor t on t.id = sg.tutor_id\n" +
            "        inner join users u on u.id = t.user_id\n" +
            "            where u.username=:username AND ( s.firstname like CONCAT('%',:word,'%') or s.lastname like CONCAT('%',:word,'%'))", nativeQuery = true)
    List<Student> searchFirstnameAndLastname(@Param("username") String username,
                                             @Param("word") String word);

}