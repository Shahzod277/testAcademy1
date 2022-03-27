package uz.jurayev.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tutor extends AbstractData<Integer> {

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Attachment attachment;

    private String category;
    private String level;
    private String description;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private List<StudentGroup> studentGroups = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    String avtor;

    public void addGroup(StudentGroup studentGroup) {
        studentGroups.add(studentGroup);
        studentGroup.setTutor(this);
    }

    public void removeGroup(StudentGroup group) {
        this.studentGroups.remove(group);
        group.setTutor(null);
    }

    public void removeGroups() {
        for (StudentGroup group : new ArrayList<>(studentGroups)) {
            removeGroup(group);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(address, tutor.address) && Objects.equals(category, tutor.category) && Objects.equals(level, tutor.level) && Objects.equals(description, tutor.description) && Objects.equals(studentGroups, tutor.studentGroups) && Objects.equals(user, tutor.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, category, level, description, studentGroups, user);
    }
}