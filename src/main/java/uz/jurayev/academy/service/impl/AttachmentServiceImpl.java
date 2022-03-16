package uz.jurayev.academy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.jurayev.academy.domain.Attachment;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.repository.AttachmentRepository;
import uz.jurayev.academy.service.AttachmentService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
//    private final AttachmentContentRepository contentRepository;

    @Override
    public Result upload(List<MultipartFile> multipartFiles) {
        List<Attachment> attachmentList = new ArrayList<>();
//        List<AttachmentContent> attachmentContentList = new ArrayList<>();
//
//        if (multipartFiles.size() > 0) {
//            for (MultipartFile file : multipartFiles) {
//
//                Attachment attachment = new Attachment();
//                attachment.setName(file.getOriginalFilename());
//                attachment.setSize(file.getSize());
//                attachment.setContentType(file.getContentType());
//                attachmentList.add(attachment);
//                attachmentRepository.saveAll(attachmentList);
//
////                AttachmentContent attachmentContent = new AttachmentContent();
//                for (Attachment attachment1 : attachmentList){
//                    try {
//                        attachmentContent.setAttachment(attachment1);
//                        attachmentContent.setValue(file.getBytes());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                attachmentContentList.add(attachmentContent);
//                contentRepository.saveAll(attachmentContentList);
//            }
//            return new Result("file successfully saved", true);
//        }
//        return new Result("file not found", false);
//    }
//
//    @Override
//    public Attachment getOneFile(Integer id, HttpServletResponse response) throws Exception {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//
//        Attachment attachment = null;
//        if (optionalAttachment.isPresent()) {
//            attachment = optionalAttachment.get();
//
//        } else {
//            throw new Exception("Could not found attachment with id: " + id);
//        }
//
//        Optional<AttachmentContent> optionalAttachmentContent = contentRepository.getAttachmentContent(attachment.getId());
//
//        if (optionalAttachmentContent.isPresent()) {
//
//            AttachmentContent attachmentContent = optionalAttachmentContent.get();
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Content-Disposition", "attachment; filename*=\"utf8'ru-ru'" + attachment.getName() + "\"");
//            response.setContentType(attachment.getContentType());
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(attachmentContent.getValue());
//            outputStream.close();
//        }
//        return attachment;
        return null;
    }
}

