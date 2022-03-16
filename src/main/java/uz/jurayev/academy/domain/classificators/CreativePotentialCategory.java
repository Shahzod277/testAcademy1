package uz.jurayev.academy.domain.classificators;

import lombok.*;
import org.hibernate.annotations.Proxy;
import uz.jurayev.academy.domain.AbstractData;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.domain.classificators.CreativePotential;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CreativePotentialCategory extends AbstractData<Integer> {

    private String value; // estrada ,maqom,haykaltarosh

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ToString.Exclude
    CreativePotential creativePotential;

}
