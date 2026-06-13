package com.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatModel ollamaChatModel;

    @GetMapping("/ask")
    public ResponseEntity<String> ask(String query) {
        String response = ollamaChatModel.call(query);
        return ResponseEntity.ok(response);
    }

}
