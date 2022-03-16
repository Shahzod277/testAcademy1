package uz.jurayev.academy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.jurayev.academy.domain.Attachment;
import uz.jurayev.academy.model.Result;
import uz.jurayev.academy.service.AttachmentService;
import uz.jurayev.academy.service.impl.AttachmentServiceImpl;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentServiceImpl attachmentService;

//    @GetMapping("download/{id}")
//    public ResponseEntity<?> getFile(@PathVariable Integer id, HttpServletResponse response) {
//        try {
//            Attachment oneFile = attachmentService.getOneFile(id, response);
//            if (oneFile.getSize() > 0) {
//                ResponseEntity.ok(oneFile);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> addAttachment(@RequestParam("files") List<MultipartFile> multipartFiles) {
        Result upload = attachmentService.upload(multipartFiles);
        if (upload.getSuccess()) {
            return ResponseEntity.ok(upload);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
