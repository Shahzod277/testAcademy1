package uz.jurayev.academy.domain.classificators;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.jurayev.academy.domain.AbstractData;
import uz.jurayev.academy.domain.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "creative_potential")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

//ijodiy salohiyat
public class CreativePotential extends AbstractData<Integer> {

    private String type; // yo'nalishlar tiplari musiqa ,sport

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creativePotential", orphanRemoval = true)
    @ToString.Exclude
    private List<CreativePotentialCategory> categories = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreativePotential potential = (CreativePotential) o;
        return Objects.equals(type, potential.type) && Objects.equals(categories, potential.categories);
    }

    public void addCreativePotentialCategory(CreativePotentialCategory category){
        categories.add(category);
        category.setCreativePotential(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, categories);
    }
}