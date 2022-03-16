package uz.jurayev.academy.util.responsemapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.rest.response.AdminTutorResponse;
import uz.jurayev.academy.rest.response.EduResponseInfo;
import uz.jurayev.academy.util.Mapper;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdminTutorResponseMapper implements Mapper<Tutor, AdminTutorResponse> {

    private final AddressResponseMapper addressResponseMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public AdminTutorResponse mapFrom(Tutor tutor) {

        return AdminTutorResponse.builder()
                .id(tutor.getId())
                .address(addressResponseMapper.mapFrom(tutor.getAddress()))
                .eduInfo(responseInfo(tutor))
                .groups(tutor.getStudentGroups().stream().map(StudentGroup::getGroupName).collect(Collectors.toList()))
                .user(userResponseMapper.mapFrom(tutor.getUser()))
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
