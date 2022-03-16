package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.GroupRepository;
import uz.jurayev.academy.repository.TutorRepository;
import uz.jurayev.academy.rest.GroupRequest;
import uz.jurayev.academy.service.GroupService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private TutorRepository tutorRepository;

    @Override
    public Result save(GroupRequest groupDto) {

        StudentGroup studentGroup = new StudentGroup();

        studentGroup.setGroupName(groupDto.getGroupName());

            groupRepository.save(studentGroup);
            return new Result("group added", true);
    }

    @Override
    public List<StudentGroup> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentGroup> groups = groupRepository.findAll(pageable);
        return groups.getContent();
    }

    @Override
    public StudentGroup getOne(Integer id) {
        Optional<StudentGroup> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElse(null);
    }

    @Override
    public Result edit(Integer id, GroupRequest groupDto) {

        Optional<StudentGroup> optionalGroup = groupRepository.findById(id);
        StudentGroup updateStudentGroup;
        if (optionalGroup.isPresent()) {
             updateStudentGroup = optionalGroup.get();
            updateStudentGroup.setGroupName(groupDto.getGroupName());
        } else {
            return new Result("id not found ", false);
        }
            groupRepository.save(updateStudentGroup);
            return new Result("group updated successfull", true);
    }

    @Override
    public Result delete(Integer id) {
        try {
            groupRepository.deleteById(id);
            return new Result("deleted group", true);
        } catch (Exception e) {
            return new Result("" + e.getMessage(), false);
        }

    }
}
