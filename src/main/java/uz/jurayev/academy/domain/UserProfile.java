package uz.jurayev.academy.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class UserProfile {

    @Column(length = 30)
    private String firstname;

    @Column(length = 30)
    private String lastname;

    @Column(name = "birth_date")
    private String birthDate;

    @NotBlank(message = "Phone number must be unique and can't be empty")
    @Pattern(regexp = "(^\\+\\d{12}$)")
    private String phoneNumber;

    private String gender;

    private String fatherName;
    @NotBlank
    @Size(min = 9, max = 9, message = "Password length must be 9 symbols")
    private String passportData;

}
