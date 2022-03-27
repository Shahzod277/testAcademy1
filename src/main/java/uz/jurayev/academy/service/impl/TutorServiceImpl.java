package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jurayev.academy.domain.Tutor;
import uz.jurayev.academy.exception.UserNotFoundException;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.TutorRepository;
import uz.jurayev.academy.rest.request.AdminTutorRequest;
import uz.jurayev.academy.rest.response.TutorResponse;
import uz.jurayev.academy.service.TutorService;
import uz.jurayev.academy.util.responsemapper.TutorResponseMapper;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final TutorRepository tutorRepository;
    private final TutorResponseMapper tutorResponseMapper;

    @Transactional
    @Override
    public TutorResponse findByTutor(Principal principal) {
        String username = principal.getName();
        Tutor tutor = tutorRepository.findByUser_Username(username).get();
        return tutorResponseMapper.mapFrom(tutor);
    }

    @Override
    public Result update(Integer id, AdminTutorRequest tutorDto) {
        return null;
    }


    @Transactional
    public Tutor getCurrentTutor(Principal principal) {
        String username = principal.getName();
        return tutorRepository.findByUser_Username(username).orElseThrow(
                () -> new UserNotFoundException(String.format("Username %s not found", username)));
    }
}
