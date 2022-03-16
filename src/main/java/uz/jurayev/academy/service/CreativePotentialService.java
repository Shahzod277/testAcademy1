package uz.jurayev.academy.service;

import uz.jurayev.academy.domain.classificators.CreativePotential;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.CreativePotentialRequest;

import java.util.List;

public interface CreativePotentialService {

    Result save(CreativePotentialRequest potentialRequestDto);



    List<CreativePotentialRequest> findAll(int page, int size);

    CreativePotential getOne(Integer id);

    Result delete(Integer id);

}
