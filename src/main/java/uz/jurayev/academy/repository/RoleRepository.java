package uz.jurayev.academy.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.jurayev.academy.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

//    @EntityGraph(attributePaths = {"user"})
    Optional<Role> findByRoleName(String name);
}
