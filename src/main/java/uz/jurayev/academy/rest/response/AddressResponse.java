package uz.jurayev.academy.rest.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddressResponse {

    Integer id;
    String country;
    String region;
    String district;
    String description;
}
