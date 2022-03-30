package uz.jurayev.academy.util.responsemapper;

import org.springframework.stereotype.Component;
import uz.jurayev.academy.domain.StudentGroup;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.rest.GroupRequest;
import uz.jurayev.academy.util.Mapper;

@Component
public class StudentGroupResponseMapper implements Mapper<StudentGroup, GroupRequest,GroupRequest> {

    @Override
    public GroupRequest mapFrom(StudentGroup entity) {
        GroupRequest groupDto = new GroupRequest();
        groupDto.setGroupName(entity.getGroupName());
        return groupDto;
    }
}
