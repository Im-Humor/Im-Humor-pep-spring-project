package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {

    @Autowired
    AccountService accServ;

    @Autowired
    MessageService msgServ;
    
    @PostMapping("/register")
    public @ReponseBody ResponseEntity<Account> addAccount(@RequestBody Account acc) {
        return accServ.addAccount(acc);
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> verifyLogin(@RequestBody Account acc) {
        return accServ.verifyLogin(acc);
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> newMessage(@RequestBody Message msg) {
        return msgServ.newMessage(msg);
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<Iterable<Message>> getAllMessages() {
        return msgServ.getAllMessages();
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        return msgServ.getMessageById(messageId);
    }

    @DeleteMapping("/messages/{messageId}")
    public @ReponseBody ResponseEntity<Integer> delMessageById(@PathVariable int messageId) {
        return msgServ.delMessageById(messageId);
    }

    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> updateMessageById(@PathVariable int messageId, @RequestBody Message msg) {
        return msgServ.updateMessageById(messageId, msg);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<ArrayList<Message>> getAllMessagesByUserId(@PathVariable int accountId) {
        return accServ.getMessagesByUserId(accountId);
    }

}
