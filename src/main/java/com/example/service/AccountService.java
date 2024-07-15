package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.entity.Message;

import java.util.ArrayList;

@Service
public class AccountService {
    @Autowired
    AccountRepository accRepo;

    @Autowired
    MessageRepository msgRepo;

    public ResponseEntity<Account> addAccount(Account acc) {
        System.out.println(acc);
        if (acc.getUsername() == null || acc.getPassword().length() < 4) {
            return ResponseEntity.status(400).body(null);
        } else if (accRepo.findByUsername(acc.getUsername()) != null){
            return ResponseEntity.status(409).body(null); 
        } else {
            return ResponseEntity.status(200).body(accRepo.save(acc));
        }
    }

    public ResponseEntity<Account> verifyLogin(Account acc) {
        Account returnAcc = accRepo.findByUsername(acc.getUsername());
        if (returnAcc == null) {
            return ResponseEntity.status(401).body(null);
        } else if (!returnAcc.getUsername().equals(acc.getUsername()) || !returnAcc.getPassword().equals(acc.getPassword())) {
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.status(200).body(returnAcc);
    }

    public ResponseEntity<ArrayList<Message>> getMessagesByUserId(int accountId) {
        ArrayList<Message> msgList = msgRepo.getMessagesByUserId(accountId);
        System.out.println(msgList);
        return ResponseEntity.status(200).body(msgList);
    }
}
