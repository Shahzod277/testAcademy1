package uz.jurayev.academy.rest.request;

import lombok.Value;

@Value
public class UserProfileRequest {
    String firstname;
    String fatherName;
    String lastname;
    String birthDate;
    String phoneNumber;
    String gender;
    String passportData;
}
