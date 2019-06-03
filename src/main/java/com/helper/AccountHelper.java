package com.helper;

import com.domain.Account;
import com.domain.Transfer;
import com.repository.AccountRepository;
import com.repository.TransferRepository;


public class AccountHelper {

    private AccountRepository repo = new AccountRepository();



    public Account getAccount(String id){
        com.entity.Account entity = repo.get(id);
        Account domain = new Account();
        domain.setAccountName(entity.getAccountName());
        domain.setBalance(entity.getBalance());
        domain.setId(entity.getId());
        return domain;
    }

    public Account updateAccount(Account input){
        String id = input.getId();
        if(id == null){
            id = Double.toString(Math.random());
            input.setId(id);
        }
        com.entity.Account entity = new com.entity.Account();
        entity.setAccountName(input.getAccountName());
        entity.setBalance(input.getBalance());
        entity.setId(id);
        repo.put(id, entity);
        return input;
    }
}
