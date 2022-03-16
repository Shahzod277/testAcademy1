package uz.jurayev.academy.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uz.jurayev.academy.domain.classificators.CreativePotentialCategory;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractData<Integer> {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String lastname;

    @OneToOne
    private Address address;

    private String gender;

    @Column(unique = true)
    String passportData;

    private LocalDate birthDate;

    @Column(nullable = false)
    private String nationality;

    private Boolean familyStatus;

    @Embedded
    private StudyInfo studyInfo;

    @Embedded
    private FamilyInformation familyInfo;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private StudentGroup group;

    @ManyToMany
    @JoinTable(name = "student_cp_category", joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "cp_category_id"))
    @ToString.Exclude
    private List<CreativePotentialCategory> categories = new ArrayList<>();

    public void addCPCategory(CreativePotentialCategory category) {
        this.categories.add(category);
        category.getStudents().add(this);
    }

    public void removeCPCategory(CreativePotentialCategory category) {
        this.categories.remove(category);
        category.getStudents().remove(this);
    }

    public void removeCategories() {
        for (CreativePotentialCategory cpc : new ArrayList<>(categories)) {
            removeCPCategory(cpc);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstname, student.firstname) && Objects.equals(fatherName, student.fatherName) && Objects.equals(lastname, student.lastname) && Objects.equals(address, student.address) && Objects.equals(gender, student.gender) && Objects.equals(passportData, student.passportData) && Objects.equals(birthDate, student.birthDate) && Objects.equals(nationality, student.nationality) && Objects.equals(familyStatus, student.familyStatus) && Objects.equals(studyInfo, student.studyInfo) && Objects.equals(familyInfo, student.familyInfo) && Objects.equals(group, student.group) && Objects.equals(categories, student.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, fatherName, lastname, address, gender, passportData, birthDate, nationality, familyStatus, studyInfo, familyInfo, group, categories);
    }
}
