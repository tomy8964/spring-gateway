package com.example.surveydocument.survey.request;

import com.example.surveydocument.survey.domain.Design;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SurveyRequestDto {
    String title;
    String description;
    int type;
    List<QuestionRequestDto> questionRequest;
    Boolean reliability;
    Design design;
    String startDate;
    String endDate;

    // todo : enable

    @Builder
    public SurveyRequestDto(
            String startDate, String endDate, String title, String description, int type, List<QuestionRequestDto> questionRequest, Design design, Boolean reliability) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.reliability=reliability;
        this.design = design;
        this.questionRequest = questionRequest;
    }
}
