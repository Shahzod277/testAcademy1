package uz.jurayev.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractData<Integer> {

    @Column(unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return Objects.equals(roleName, role1.roleName) && Objects.equals(users, role1.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, users);
    }
}
