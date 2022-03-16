package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;
import java.util.List;

@Value
@Builder
public class AdminTutorResponse {

    Integer id;
    AddressResponse address;
    EduResponseInfo eduInfo;
    List<String> groups;
    UserResponse user;
}
