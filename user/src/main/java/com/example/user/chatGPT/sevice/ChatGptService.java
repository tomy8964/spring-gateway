package com.example.user.chatGPT.sevice;


import com.example.user.chatGPT.config.ChatGptConfig;
import com.example.user.chatGPT.request.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ChatGptService {

    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptChoice getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);
        return responseEntity.getBody().getChoices().get(0);
    }

    public ChatGptChoice askQuestion(ChatGptQuestionRequestDto requestDto) {
        Message message = Message.builder()
                .role(ChatGptConfig.ROLE_USER)
                .content(requestDto.getQuestion())
                .build();
        System.out.println(message);
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                ChatGptConfig.MODEL,
                                Collections.singletonList(message)
                        )
                )
        );
    }

    public ChatResultDto chatGptResult(ChatGptQuestionRequestDto requestDto) {

        ChatGptChoice chatGptChoice=askQuestion(requestDto);
        ChatResultDto chatResultDto = ChatResultDto.builder()
                .index(chatGptChoice.getIndex())
                .text(chatGptChoice.getMessage().getContent())
                .finishReason(chatGptChoice.getFinishReason())
                .build();
        return chatResultDto;
    }
}
