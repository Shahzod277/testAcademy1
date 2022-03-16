package uz.jurayev.academy.service;

import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.request.UserRequest;
import uz.jurayev.academy.rest.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequestDto);
    List<UserResponse> getAll();
    UserResponse getById(Long id);
    Result edit(Long id, UserRequest requestDto);
    Result delete(Long id);
}
