package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.Address;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.response.AddressResponse;
import uz.jurayev.academy.util.Mapper;

@Component
public class AddressResponseMapper implements Mapper<Address, AddressResponse,AddressResponse> {

    @Override
    public AddressResponse mapFrom(Address address) {
       return AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .region(address.getRegion())
                .district(address.getDistrict())
                .description(address.getDescription())
                .build();
    }
}
