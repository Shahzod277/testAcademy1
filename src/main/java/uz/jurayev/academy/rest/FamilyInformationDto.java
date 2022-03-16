package uz.jurayev.academy.rest;

import lombok.Data;

@Data
public class FamilyInformationDto {

    private String parentsName;
    private String address;
    private String phoneNumber;
    private Boolean needy;         //kam taminlangan oila farzandi
    private String orphanStudent; //boquvchisini yo'qotganligi
    private String parentsRetiree; //nafaqador
    private String invalidParents;
    private String invalidStudent;
}
