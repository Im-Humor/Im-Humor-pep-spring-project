package com.example.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "select * from message where postedBy = ?1", nativeQuery = true)
    ArrayList<Message> getMessagesByUserId(int accountId);
}
