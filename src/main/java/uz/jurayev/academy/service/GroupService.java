package uz.jurayev.academy.service;

import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.GroupRequest;
import java.util.List;

public interface GroupService {
    Result save(GroupRequest groupsDto);

    List<StudentGroup> getAll(int page, int size);

    StudentGroup getOne(Integer id);

    Result edit(Integer id, GroupRequest groupDto);

    Result delete(Integer id);
}
