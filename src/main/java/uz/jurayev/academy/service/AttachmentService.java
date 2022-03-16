package uz.jurayev.academy.service;

import org.springframework.web.multipart.MultipartFile;
import uz.jurayev.academy.domain.Attachment;
import uz.jurayev.academy.model.Result;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    Result upload(List<MultipartFile> multipartFiles);
//    Attachment getOneFile(Integer id, HttpServletResponse response) throws Exception;
}
