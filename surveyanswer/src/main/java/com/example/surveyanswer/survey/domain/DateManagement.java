package com.example.surveyanswer.survey.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class DateManagement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Date_id")
    private long id;

    @Column(name = "survey_start_date")
    @DateTimeFormat(pattern = "yy-mm-dd")
    @NotNull
    private LocalDate startDate;

    @Column(name = "survey_deadline")
    @DateTimeFormat(pattern = "yy-mm-dd")
    @NotNull
    private LocalDate deadline;

    @OneToOne(mappedBy = "date")
    private SurveyDocument surveyDocument;

    // todo : enable boolean 추가

    @Builder
    public DateManagement(LocalDate startDate, LocalDate deadline, SurveyDocument surveyDocument) {
        this.startDate = startDate;
        this.deadline = deadline;
        this.surveyDocument = surveyDocument;
    }

    // RequestDto -> Entity
    public static DateManagement dateRequestToEntity(String start, String end, SurveyDocument surveyDocument) {
        return DateManagement.builder()
                .startDate(LocalDate.parse(start))
                .deadline(LocalDate.parse(end))
                .surveyDocument(surveyDocument).build();
    }
}