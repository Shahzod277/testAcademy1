package uz.jurayev.academy.util.requestmapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.FamilyInformation;
import uz.jurayev.academy.rest.FamilyInformationDto;
import uz.jurayev.academy.util.Mapper;

@Component
public class FamilyInfoRequestMapper implements Mapper<FamilyInformationDto, FamilyInformation> {

    @Override
    public FamilyInformation mapFrom(FamilyInformationDto entity) {

        FamilyInformation familyInfo = new FamilyInformation();
        familyInfo.setParentsName(entity.getParentsName());
        familyInfo.setPhoneNumber(entity.getPhoneNumber());
        familyInfo.setInvalidParents(entity.getInvalidParents());
        familyInfo.setNeedy(entity.getNeedy());
        familyInfo.setInvalidStudent(entity.getInvalidStudent());
        familyInfo.setOrphanStudent(entity.getOrphanStudent());
        familyInfo.setAddress(entity.getAddress());
        return familyInfo;
    }
}
