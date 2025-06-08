package com.synergisticit.searchgateway.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;

@Service
public class ChatService {
    private final ChatClient client;
    private final InMemoryChatMemory chatMemory;

    public ChatService(ChatClient client, InMemoryChatMemory chatMemory) {
        this.client = client;
        this.chatMemory = chatMemory;
    }

    public String getResponse(String userInput, String conversationKey) {
        chatMemory.get(conversationKey, 10).stream().forEach(System.out::println);

        return client.prompt().user(userInput).advisors(
                advisorSpec -> advisorSpec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, conversationKey)
        ).call().content(); // You can replace `content()` with `entity()` if needed
    }
}
