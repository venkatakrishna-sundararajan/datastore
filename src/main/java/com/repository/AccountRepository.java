package com.repository;

import com.entity.Account;

import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository extends EntityStorageImpl<Account> {

    private static ConcurrentHashMap<String, Account> map = new ConcurrentHashMap<String, Account>();

    public AccountRepository(){
        super(map);
    }

}