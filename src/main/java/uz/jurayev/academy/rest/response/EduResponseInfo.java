package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EduResponseInfo {

    Integer id;
    String category;
    String level;
    String description;
}
