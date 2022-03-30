package uz.jurayev.academy.util.responsemapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.response.EduResponseInfo;
import uz.jurayev.academy.rest.response.TutorResponse;
import uz.jurayev.academy.util.Mapper;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TutorResponseMapper implements Mapper<Tutor, TutorResponse,TutorResponse> {

    private final UserProfileResponseMapper profileResponseMapper;
    private final AddressResponseMapper addressResponseMapper;

    @Override
    public TutorResponse mapFrom(Tutor tutor) {
        return TutorResponse.builder()
                .userProfile(profileResponseMapper.mapFrom(tutor.getUser().getUserProfile()))
                .eduInfo(responseInfo(tutor))
                .address(addressResponseMapper.mapFrom(tutor.getAddress()))
                .studentGroups(tutor.getStudentGroups())
                .students(new ArrayList<>())
                .build();
    }

    private EduResponseInfo responseInfo(Tutor tutor) {

        return EduResponseInfo.builder()
                .category(tutor.getCategory())
                .level(tutor.getLevel())
                .description(tutor.getDescription())
                .build();
    }
}
