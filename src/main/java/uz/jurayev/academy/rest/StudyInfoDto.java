package uz.jurayev.academy.rest;

import lombok.Data;

@Data
public class StudyInfoDto {

    private String course;
    private String speciality; //yunalish
    private String university;
    private String educationType; //bakalavr
    private String paymentType;
}
