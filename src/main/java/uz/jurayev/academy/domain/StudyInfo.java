package uz.jurayev.academy.domain;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class StudyInfo {

    @Column(nullable = false)
    private String course;

    @Column(nullable = false)
    private String speciality; //yunalish
    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String educationType; //bakalavr

    @Column(nullable = false)
    private String paymentType;
}
