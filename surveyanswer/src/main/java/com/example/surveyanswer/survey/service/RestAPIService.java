package com.example.surveyanswer.survey.service;

import com.example.surveyanswer.survey.domain.SurveyDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestAPIService {

    @Value("${gateway.host}")
    private String gateway;

    public WebClient webClient = WebClient.create();

    public void setWebClient(String baseurl) {
        this.webClient = WebClient.create(baseurl);
    }

    public void restAPItoAnalyzeController(Long surveyDocumentId) {
        //REST API로 분석 시작 컨트롤러로 전달
        // Create a WebClient instance
        log.info("응답 저장 후 -> 분석 시작 REST API 전달");

        // Define the API URL
        String apiUrl = "http://" + gateway + "/analyze/internal/research/analyze/create";

        // Make a GET request to the API and retrieve the response
        String post = webClient.post()
                .uri(apiUrl)
                .header("Authorization","NouNull")
                .bodyValue(String.valueOf(surveyDocumentId))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Process the response as needed
        System.out.println("Request: " + post);
    }

    public void giveChoiceIdToCount(Long choiceId) {
        //REST API로 분석 시작 컨트롤러로 전달
        // Create a WebClient instance
        log.info("응답 저장 후 -> 분석 시작 REST API 전달");

        // Define the API URL
        String apiUrl = "http://" + gateway + "/api/internal/count/"+choiceId;

        // Make a GET request to the API and retrieve the response
        String post = webClient.post()
                .uri(apiUrl)
                .header("Authorization","NouNull")
                .bodyValue(String.valueOf(choiceId))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Process the response as needed
        System.out.println("Request: " + post);
    }

    public void giveDocumentIdtoCountAnswer(Long surveyDocumentId) {
        //REST API로 분석 시작 컨트롤러로 전달
        // Create a WebClient instance
        log.info("응답 저장 후 -> 분석 시작 REST API 전달");

        // Define the API URL
        String apiUrl = "http://" + gateway + "/api/internal/countAnswer/"+surveyDocumentId;

        // Make a GET request to the API and retrieve the response
        String post = webClient.post()
                .uri(apiUrl)
                .header("Authorization","NouNull")
                .bodyValue(String.valueOf(surveyDocumentId))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Process the response as needed
        System.out.println("Request: " + post);
    }

    public SurveyDocument getSurveyDocument(Long surveyDocumentId) {
        //REST API로 분석 시작 컨트롤러로 전달
        // Create a WebClient instance
        log.info("GET SurveyDocument");

        // Define the API URL
        String apiUrl = "http://" + gateway + "/api/internal/getSurveyDocument/" + surveyDocumentId;
        log.info(apiUrl);

        // Make a GET request to the API and retrieve the response
        SurveyDocument get = webClient.get()
                .uri(apiUrl)
                .header("Authorization","NotNull")
                .retrieve()
                .bodyToMono(SurveyDocument.class)
                .block();

        // Process the response as needed
        System.out.println("Request: " + get);

        return get;
    }
}
