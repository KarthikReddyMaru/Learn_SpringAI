package com.learn.controller;

import com.learn.advisor.SampleAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ChatController {

    private final ChatClient ollamaChatClient;

    public ChatController(OllamaChatModel ollamaChatModel, ChatMemory chatMemory) {
        this.ollamaChatClient = ChatClient.builder(ollamaChatModel)
                                          .defaultAdvisors(new SampleAdvisor(), MessageChatMemoryAdvisor
                                                                   .builder(chatMemory)
                                                                   .build())
                                          .build();
    }

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam String query) {
        log.info("Prompt: {}", query);
        ChatClient.CallResponseSpec responseSpec = ollamaChatClient
                .prompt(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, "123"))
                .call();

        String response = responseSpec.content();
        return ResponseEntity.ok(response);
    }

}
