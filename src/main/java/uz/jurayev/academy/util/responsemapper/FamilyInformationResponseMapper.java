package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.rest.FamilyInformationDto;
import uz.jurayev.academy.util.Mapper;

@Component
public class FamilyInformationResponseMapper implements Mapper<uz.jurayev.academy.domain.FamilyInformation, FamilyInformationDto> {

    @Override
    public FamilyInformationDto mapFrom(uz.jurayev.academy.domain.FamilyInformation entity) {
        FamilyInformationDto familyInformationDto=new FamilyInformationDto();
        familyInformationDto.setParentsName(entity.getParentsName());
        familyInformationDto.setPhoneNumber(entity.getPhoneNumber());
        familyInformationDto.setInvalidParents(entity.getInvalidParents());
        familyInformationDto.setNeedy(entity.getNeedy());
        familyInformationDto.setInvalidStudent(entity.getInvalidStudent());
        familyInformationDto.setOrphanStudent(entity.getOrphanStudent());
        familyInformationDto.setAddress(entity.getAddress());
        return familyInformationDto;

    }
}
