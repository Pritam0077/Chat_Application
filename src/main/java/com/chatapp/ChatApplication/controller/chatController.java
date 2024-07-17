package com.chatapp.ChatApplication.controller;

import com.chatapp.ChatApplication.models.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class chatController {

    @MessageMapping("/chat.sendMessage") // url we want to send message
    @SendTo("/topic/public")   // to which queue we want to send and people who subscribe this url will receive message
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
    SimpMessageHeaderAccessor headerAccessor
    ){
        // add username in websocket session
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }
}
