package uz.jurayev.academy.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Address extends AbstractData<Integer> {

    private String country;
    private String region;
    private String district;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(region, address.region) && Objects.equals(district, address.district) && Objects.equals(description, address.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, region, district, description);
    }
}