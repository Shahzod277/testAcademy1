package uz.jurayev.academy.rest.response;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.rest.CreativePotential;
import uz.jurayev.academy.rest.FamilyInformationDto;
import uz.jurayev.academy.rest.GroupRequest;
import uz.jurayev.academy.rest.StudyInfoDto;

import java.time.LocalDate;
import java.util.List;

@Data
public class StudentResponse {

    private String firstname;
    private String nationality;
    private String lastname;
    private String fatherName;
    private String passportData;
    private GroupRequest group;
    private String gender;
    private AddressResponse address;
    private LocalDate birthDate;
    private FamilyInformationDto familyInformation;
    private StudyInfoDto studyInfo;
    private Boolean familyStatus;
    private List<CreativePotential> creativePotential;
}
