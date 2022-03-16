package uz.jurayev.academy.domain.classificators;

import lombok.*;
import uz.jurayev.academy.domain.AbstractData;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class District extends AbstractData<Integer> {

    private String districtName;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @ToString.Exclude
    private Region region;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Objects.equals(districtName, district.districtName) && Objects.equals(region, district.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(districtName, region);
    }
}
