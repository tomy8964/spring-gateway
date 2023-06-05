package com.example.surveyanswer.survey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SurveyDocument {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_document_id")
    private Long id;
    @Column(name = "survey_title")
    private String title;
    @Column(name = "survey_type")
    private int type;
    @Column(name = "survey_description")
    private String description;
    @Column(name = "accept_response")
    private boolean acceptResponse;
    @CreationTimestamp @Temporal(TemporalType.TIMESTAMP) @Column(name = "survey_start_date")
    private Date startDate;
    @CreationTimestamp @Temporal(TemporalType.TIMESTAMP) @Column(name = "survey_deadline")
    private Date deadline;
    @Column(name = "url")
    private String url;
    @Column(name = "answer_count")
    private int countAnswer;

    @Column(name = "reliability")
    private Boolean reliability;

    @Column(name = "font")
    private String font;

    @Column(name = "size")
    private int fontSize;

    @Column(name = "backcolor")
    private String backColor;

    @Column(name = "content")
    @OneToMany(mappedBy = "surveyDocumentId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionDocument> questionDocumentList;


    @Builder
    public SurveyDocument(int countAnswer, List<SurveyAnswer> surveyAnswerList, String title, int type,Boolean reliability,String backColor, String font,int fontSize, String description, List<QuestionDocument> questionDocumentList) {        this.title = title;
        this.type = type;
        this.description = description;
        this.reliability=reliability;
        this.font=font;
        this.fontSize=fontSize;
        this.backColor=backColor;
        this.questionDocumentList = questionDocumentList;
//        this.surveyAnswerList = surveyAnswerList;
        this.countAnswer = countAnswer;
    }

    //    public void setAnswer(SurveyAnswer surveyAnswer) {
//        this.surveyAnswerList.add(surveyAnswer);
//    }
    // 문항 list 에 넣어주기
    public void setQuestion(QuestionDocument questionDocument) {
        this.questionDocumentList.add(questionDocument);
    }
    // 문항 analyze 에 넣어주기
//    public void setAnalyze(surveyAnswer surveyAnswer) {
//        this.surveyAnswer=surveyAnswer;
//    }
}
