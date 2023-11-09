package com.example.webtoeic.controller;


import com.example.webtoeic.response.ResponseMessage;
import com.example.webtoeic.service.ExcelService;
import com.example.webtoeic.utils.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.ok(new ResponseMessage(message, "success", null));
            } catch (Exception e) {
                message = "Could not upload the file: " + e.getMessage() + "!";
                return ResponseEntity.ok(new ResponseMessage(message, "fail", null));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.ok(new ResponseMessage(message, "fail", null));
    }


}
