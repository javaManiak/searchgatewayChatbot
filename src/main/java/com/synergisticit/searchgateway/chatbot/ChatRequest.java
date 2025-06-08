package com.synergisticit.searchgateway.chatbot;

public class ChatRequest {
    
    private String question;
    
    
    public ChatRequest(String question) {
        this.setQuestion(question);
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
}
