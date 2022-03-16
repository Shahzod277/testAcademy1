package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.UserProfile;
import uz.jurayev.academy.rest.response.UserProfileResponse;
import uz.jurayev.academy.util.Mapper;

@Component
public class UserProfileResponseMapper implements Mapper<UserProfile, UserProfileResponse> {

    @Override
    public UserProfileResponse mapFrom(UserProfile entity) {

       return UserProfileResponse.builder()
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .phoneNumber(entity.getPhoneNumber())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender())
                .build();
    }
}
