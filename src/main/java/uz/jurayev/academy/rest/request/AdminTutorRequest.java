package uz.jurayev.academy.rest.request;

import lombok.*;

import java.util.List;

@Value
@Builder
public class AdminTutorRequest {
    AddressRequest address;
    String category;
    String level;
    String description;
    List<String> groups;
    UserRequest user;
}
