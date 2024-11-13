package com.springboot_react.service;

import com.springboot_react.dao.MessageRepository;
import com.springboot_react.entity.Message;
import com.springboot_react.requestmodels.RequestAdminQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public void postMessage(String userEmail, Message messageRequest){
        Message message = new Message(messageRequest.getTitle(), messageRequest.getQuestion());
        message.setUserEmail(userEmail);
        messageRepository.save(message);
    }

    public void putMessage(RequestAdminQuestion requestAdminQuestion, String userEmail) throws Exception{
        Optional<Message> message = messageRepository.findById(requestAdminQuestion.getId());
        if(!message.isPresent()){
            throw new Exception("Message not found");
        }
        message.get().setAdminEmail(userEmail);
        message.get().setResponse(requestAdminQuestion.getResponse());
        message.get().setClosed(true);
        messageRepository.save(message.get());
    }
}
