package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserProfileResponse {

    String firstname;
    String fatherName;
    String lastname;
    String birthDate;
    String phoneNumber;
    String passportDate;
    String gender;
}
