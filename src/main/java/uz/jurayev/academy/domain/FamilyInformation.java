package uz.jurayev.academy.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Embeddable
public class FamilyInformation {

    private String parentsName;
    private String address;
    private String phoneNumber;
    private Boolean needy;         //kam taminlangan oila farzandi
    private String orphanStudent; //boquvchisini yo'qotganligi
    private String parentsRetiree; //nafaqador
    private String invalidParents;
    private String invalidStudent;

}



//todo phone number qoshishim kk studentgaaa