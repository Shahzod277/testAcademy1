package uz.jurayev.academy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserErrorMessage {

    public static final String EMAIL_IS_EXISTS = "User email already is exists";
    public static final String USERNAME_IS_EXISTS = "User username already is exists";
    public static final String PHONE_NUMBER_IS_EXISTS = "User phone number already is exists";
    public static final String USER_NOT_FOUND = "User not found";

}