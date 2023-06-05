package com.example.surveydocument.survey.request;

import jakarta.persistence.Column;
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
    String font;
    int fontSize;
    String backColor;

    //    @ConstructorProperties({"title", "description", "type", "questionRequest"})
    @Builder
    public SurveyRequestDto(String title, String description, int type,Boolean reliability,String font, int fontSize, String backColor, List<QuestionRequestDto> questionRequest) {        this.title = title;
        this.description = description;
        this.type = type;
        this.reliability=reliability;
        this.font=font;
        this.backColor=backColor;
        this.fontSize=fontSize;
        this.questionRequest = questionRequest;
    }
}
