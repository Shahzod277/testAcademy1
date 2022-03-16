package uz.jurayev.academy.rest.request;

import lombok.*;

@Value
@Builder
public class AddressRequest {
    String country;
    String region;
    String district;
    String description;
}
