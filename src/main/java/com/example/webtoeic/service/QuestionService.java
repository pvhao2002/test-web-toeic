package com.example.webtoeic.service;


import com.example.webtoeic.model.Question;
import com.example.webtoeic.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public void deleteAllQuestions() {
        questionRepository.deleteAll();
    }
}
