package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;
import uz.jurayev.academy.domain.StudentGroup;

import java.util.List;

@Value
@Builder
public class TutorResponse {

    AddressResponse address;
    EduResponseInfo eduInfo;
    List<StudentGroup> studentGroups;
    UserProfileResponse userProfile;
    List<StudentResponse> students;

}
