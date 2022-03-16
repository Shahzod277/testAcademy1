package uz.jurayev.academy.service;

import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.AdminTutorRequest;

public interface AdminTutorService {

    Result createTutor(AdminTutorRequest tutor);
}
