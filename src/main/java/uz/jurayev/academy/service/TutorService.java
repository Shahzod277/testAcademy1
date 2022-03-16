package uz.jurayev.academy.service;

import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.AdminTutorRequest;
import uz.jurayev.academy.rest.response.TutorResponse;

import java.security.Principal;

public interface TutorService {

    TutorResponse findByTutor(Principal principal);
    Result update(Integer id, AdminTutorRequest tutorDto);
}
