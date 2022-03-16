package uz.jurayev.academy.util.requestmapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Address;
import uz.jurayev.academy.rest.request.AddressRequest;
import uz.jurayev.academy.util.Mapper;

@Component
public class AddressRequestMapper implements Mapper<AddressRequest, Address> {

    @Override
    public Address mapFrom(AddressRequest addressRequestDto) {
              return Address.builder()
                       .country(addressRequestDto.getCountry())
                       .region(addressRequestDto.getRegion())
                       .district(addressRequestDto.getDistrict())
                       .description(addressRequestDto.getDescription())
                       .build();
    }
}
