package uz.jurayev.academy.domain;

import lombok.*;
import uz.jurayev.academy.domain.classificators.Semester;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
//qoldirilgan darslar cllassi
public class MissedLesson extends AbstractData<Integer> {

    @ManyToOne
    private Semester semester;
    private String count; //qoldirilganlar soni


}
