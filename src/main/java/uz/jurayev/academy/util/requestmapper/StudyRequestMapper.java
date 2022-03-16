package uz.jurayev.academy.util.requestmapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.StudyInfo;
import uz.jurayev.academy.rest.StudyInfoDto;
import uz.jurayev.academy.util.Mapper;

@Component
public class StudyRequestMapper implements Mapper<StudyInfoDto, StudyInfo> {

    @Override
    public StudyInfo mapFrom(StudyInfoDto entity) {
        StudyInfo studyInfoDto = new StudyInfo();
        studyInfoDto.setCourse(entity.getCourse());
        studyInfoDto.setEducationType(entity.getEducationType());
        studyInfoDto.setPaymentType(entity.getPaymentType());
        studyInfoDto.setSpeciality(entity.getSpeciality());
        studyInfoDto.setUniversity(entity.getUniversity());
        return studyInfoDto;
    }
}
