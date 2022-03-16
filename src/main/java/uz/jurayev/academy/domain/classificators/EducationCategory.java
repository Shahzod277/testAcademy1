package uz.jurayev.academy.domain.classificators;

import lombok.*;
import uz.jurayev.academy.domain.AbstractData;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class EducationCategory extends AbstractData<Integer> {

    private String educationName;
}
