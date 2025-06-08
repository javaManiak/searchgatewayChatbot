package com.synergisticit.searchgateway.controller;


import com.synergisticit.searchgateway.chatbot.ChatRequest;
import com.synergisticit.searchgateway.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ChatController {
    
    private final ChatService chatService;
    
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    
    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatRequest chatRequest, Principal principal) {
        
        // treat this as conversation key
        String username = principal.getName();
        
        return ResponseEntity.ok(chatService.getResponse(chatRequest.getQuestion(), username));
    }
}
