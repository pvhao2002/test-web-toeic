package com.example.webtoeic.service;


import com.example.webtoeic.model.Question;
import com.example.webtoeic.repository.QuestionRepository;
import com.example.webtoeic.utils.ExcelHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService {
    private final QuestionRepository questionRepository;
    public void save(MultipartFile file) {
        try {
            questionRepository.saveAll(ExcelHelper.excelToQuestions(file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
