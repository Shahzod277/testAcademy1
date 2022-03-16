package uz.jurayev.academy.util.responsemapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Student;
import uz.jurayev.academy.rest.response.StudentResponse;
import uz.jurayev.academy.util.Mapper;

@Component
@RequiredArgsConstructor
public class StudentResponseMapper implements Mapper<Student, StudentResponse> {

    private final StudentGroupResponseMapper groupResponseMapper;
    private final FamilyInformationResponseMapper familyInfoResponseMapper;
    private final AddressResponseMapper addressResponseMapper;
    private final CreativePotentialResponseMapper creativePotentialResponseMapper;
    private final StudyResponseMapper studyResponseMapper;

    @Override
    public StudentResponse mapFrom(Student entity) {

        StudentResponse infoDto = new StudentResponse();
        infoDto.setFirstname(entity.getFirstname());
        infoDto.setLastname(entity.getLastname());
        infoDto.setFatherName(entity.getFatherName());
        infoDto.setGender(entity.getGender());
        infoDto.setBirthDate(entity.getBirthDate());
        infoDto.setFamilyStatus(entity.getFamilyStatus());
        infoDto.setNationality(entity.getNationality());
        infoDto.setPassportData(entity.getPassportData());
        infoDto.setStudyInfo(studyResponseMapper.mapFrom(entity.getStudyInfo()));
        infoDto.setFamilyInformation(familyInfoResponseMapper.mapFrom(entity.getFamilyInfo()));
        infoDto.setGroup(groupResponseMapper.mapFrom(entity.getGroup()));
        infoDto.setAddress(addressResponseMapper.mapFrom(entity.getAddress()));
        infoDto.setCreativePotential(creativePotentialResponseMapper.mapFrom(entity.getCategories()));
        return infoDto;
    }
}
