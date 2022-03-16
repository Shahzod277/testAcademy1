package uz.jurayev.academy.domain.classificators;

import lombok.*;
import uz.jurayev.academy.domain.AbstractData;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Region extends AbstractData<Integer> {

    private String regionName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @ToString.Exclude
    private Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<District> districts;

    public void addDistrict(District district) {
        districts.add(district);
        district.setRegion(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(regionName, region.regionName) && Objects.equals(country, region.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regionName, country);
    }
}
