package uz.jurayev.academy.service;

import uz.jurayev.academy.domain.Address;
import uz.jurayev.academy.rest.request.AddressRequest;

public interface AddressService {

    Address createAddress(AddressRequest addressDto);
}
