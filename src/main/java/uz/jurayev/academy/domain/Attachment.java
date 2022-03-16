package uz.jurayev.academy.domain;

import lombok.*;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attachment extends AbstractData<Integer> {

    private String name;
    private Long size;
    private String contentType;
    private byte[] value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(name, that.name) && Objects.equals(size, that.size) && Objects.equals(contentType, that.contentType) && Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, size, contentType);
        result = 31 * result + Arrays.hashCode(value);
        return result;
    }
}