package com.rocketseat.certification_nlw.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rocketseat.certification_nlw.modules.questions.dto.AlternativeResultDTO;
import com.rocketseat.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.rocketseat.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.rocketseat.certification_nlw.modules.questions.entities.QuestionsEntity;
import com.rocketseat.certification_nlw.modules.questions.repositories.QuestionRepository;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {

        var result = this.questionRepository.findByTechnology(technology);

        var toMap = result.stream().map(question -> mapQuestionResultDTO(question)).collect(Collectors.toList());

        return toMap;
        // return this.questionRepository.findByTechnology(technology);
    }

    static QuestionResultDTO mapQuestionResultDTO(QuestionsEntity questionsEntity) {
        
        var questionResultDTO = QuestionResultDTO.builder()
        .id(questionsEntity.getId())
        .technology(questionsEntity.getTechnology())
        .description(questionsEntity.getDescription()).build();  

        List<AlternativeResultDTO> alternativeResultDTOs = questionsEntity.getAlternativesEntity().stream().map(alternative -> mapAlternativeResultDTO(alternative)).collect(Collectors.toList());

        questionResultDTO.setAlternativeResultDTO(alternativeResultDTOs);

        return questionResultDTO;
    }

    static AlternativeResultDTO mapAlternativeResultDTO(AlternativesEntity alternativesEntity) {
        return AlternativeResultDTO.builder()
        .id(alternativesEntity.getId())
        .description(alternativesEntity.getDescription()).build();
    }
    
}
