package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.StudyInfo;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.StudyInfoDto;
import uz.jurayev.academy.util.Mapper;

@Component
public class StudyResponseMapper implements Mapper<StudyInfo, StudyInfoDto,StudyInfoDto> {

    @Override
    public StudyInfoDto mapFrom(StudyInfo entity) {
        StudyInfoDto studentStudyInfoDto = new uz.jurayev.academy.rest.StudyInfoDto();
        studentStudyInfoDto.setCourse(entity.getCourse());
        studentStudyInfoDto.setEducationType(entity.getEducationType());
        studentStudyInfoDto.setSpeciality(entity.getSpeciality());
        studentStudyInfoDto.setPaymentType(entity.getPaymentType());
        studentStudyInfoDto.setUniversity(entity.getUniversity());
        return studentStudyInfoDto;
    }
}
