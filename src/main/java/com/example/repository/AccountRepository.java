package com.example.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query(value = "select * from account where username = ?1", nativeQuery = true)
    Account findByUsername(String username);

}
