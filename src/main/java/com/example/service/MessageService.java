package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    MessageRepository msgRepo;
    
    @Autowired
    AccountRepository accRepo;

    public ResponseEntity<Message> newMessage(Message msg) {
        Optional<Account> postAcc = accRepo.findById(msg.getPostedBy());
        if (postAcc.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        } else if (msg.getMessageText() == null || msg.getMessageText().length() > 255 || msg.getMessageText().length() == 0) {
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.status(200).body(msgRepo.save(msg));
        }
    }

    public ResponseEntity<Iterable<Message>> getAllMessages() {
        Iterable<Message> msgList = msgRepo.findAll();
        return ResponseEntity.status(200).body(msgList);
    }

    public ResponseEntity<Message> getMessageById(int messageId) {
        Optional<Message> getMsg = msgRepo.findById(messageId);
        if (getMsg.isEmpty()) {
            return ResponseEntity.status(200).body(null);
        } else {
            return ResponseEntity.status(200).body(getMsg.get());
        }
    }

    public ResponseEntity<Integer> delMessageById(int messageId) {
        Optional<Message> delMsg = msgRepo.findById(messageId);
        if (delMsg.isEmpty()) {
            return ResponseEntity.status(200).body(null);
        } else {
            msgRepo.deleteById(messageId);
            return ResponseEntity.status(200).body(1);
        }
    }

    public ResponseEntity<Integer> updateMessageById(int messageId, Message msg) {
        Optional<Message> updateMsg = msgRepo.findById(messageId);
        if (updateMsg.isEmpty()) {
            return ResponseEntity.status(400).body(null);
        } else if (msg.getMessageText() == null || msg.getMessageText().length() > 255 || msg.getMessageText().length() == 0) {
            return ResponseEntity.status(400).body(null);
        } else {
            return ResponseEntity.status(200).body(1);
        }
    }

}
