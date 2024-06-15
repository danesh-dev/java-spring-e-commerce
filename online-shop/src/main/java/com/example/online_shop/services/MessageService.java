package com.example.online_shop.services;

import com.example.online_shop.dto.MessageDto;
import com.example.online_shop.models.Message;
import com.example.online_shop.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void create(MessageDto messageDto){
        Message message = new Message();
        message.setName(messageDto.getName());
        message.setPhone(messageDto.getPhone());
        message.setEmail(messageDto.getEmail());
        message.setMessage(messageDto.getMessage());

        messageRepository.save(message);
    }
}
