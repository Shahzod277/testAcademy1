package uz.jurayev.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "user.roles", attributeNodes = @NamedAttributeNode("roles"))
public class User extends AbstractData<Long> {

    private String username;

//    @Size(min = 6, max = 20, message = "Password size minimum is 6 and maximum 15 symbols")
    private String password;

    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Embedded
    private UserProfile userProfile;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
    public void removeRoles() {
        for (Role role : new ArrayList<>(roles)) {
            removeRole(role);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(roles, user.roles) && Objects.equals(userProfile, user.userProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, roles, userProfile);
    }
}
