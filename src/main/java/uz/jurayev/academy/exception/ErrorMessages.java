package uz.jurayev.academy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    TUTOR_NOT_FOUND("Tutor not found"),
    STUDENT_NOT_FOUND("Student not found"),
    CREATIVE_POTENTIAL_CATEGORY_NOT_FOUND("Creative potential category not found");
    private final String message;
}
