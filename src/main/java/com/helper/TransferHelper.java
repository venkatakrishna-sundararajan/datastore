package com.helper;

import com.domain.Account;
import com.domain.Transfer;
import com.repository.TransferRepository;

import java.math.BigDecimal;
import java.util.Random;

public class TransferHelper {

    private AccountHelper accountHelper = new AccountHelper();
    private TransferRepository repo = new TransferRepository();


    public Transfer getTransfer(String id){
        com.entity.Transfer entity = repo.get(id);
        Transfer domain = new Transfer();
        domain.setTransferAmount(entity.getTransferAmount());
        domain.setToAccountId(entity.getToAccountId());
        domain.setFromAccountId(entity.getFromAccountId());
        domain.setId(entity.getId());
        return domain;
    }

    public Transfer postTransfer(Transfer input){


        Account fromAccount = accountHelper.getAccount(input.getFromAccountId());
        Account toAccount = accountHelper.getAccount(input.getToAccountId());

        if(fromAccount == null || toAccount == null){
            throw new RuntimeException("Invalid Account Selected");
        }


        updateFromAccount(fromAccount, input);

        Transfer updatedTransfer = createTransfer(input);

        updateToAccount(toAccount, input);

       return updatedTransfer;
    }


    private void updateToAccount(Account account, Transfer transfer){
        BigDecimal existingAmount = account.getBalance();

        account.setBalance(existingAmount.add(transfer.getTransferAmount()));
        accountHelper.updateAccount(account);
    }

    private void updateFromAccount(Account account, Transfer transfer){
        BigDecimal existingAmount = account.getBalance();
        if(existingAmount.compareTo(transfer.getTransferAmount()) < 0){
            throw new RuntimeException("Insufficient Amount");
        }
        account.setBalance(existingAmount.subtract(transfer.getTransferAmount()));
        accountHelper.updateAccount(account);
    }

    private Transfer createTransfer(Transfer input){
        String id = input.getId();
        if(id == null){
            id = Double.toString(Math.random());
            input.setId(id);
        }

        com.entity.Transfer entity = new com.entity.Transfer();
        entity.setTransferAmount(input.getTransferAmount());
        entity.setToAccountId(input.getToAccountId());
        entity.setFromAccountId(input.getFromAccountId());
        entity.setId(id);
        repo.put(id, entity);
        input.setId(id);
        return input;
    }
}
