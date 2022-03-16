package uz.jurayev.academy.domain.classificators;

import lombok.*;
import uz.jurayev.academy.domain.AbstractData;

import javax.persistence.Entity;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Gender extends AbstractData<Integer> {

    private String genderName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gender gender = (Gender) o;
        return Objects.equals(genderName, gender.genderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genderName);
    }
}
