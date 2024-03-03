package com.example.websocket.service;

import com.example.websocket.entity.ChatMessage;
import com.example.websocket.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        String chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow(); // You can create your own dedicated
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessage(
            Long senderId,
            Long recipientId
    ){
        var chatId = chatRoomService.getChatRoomId(
                senderId,
                recipientId,
                false
        );
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }

}