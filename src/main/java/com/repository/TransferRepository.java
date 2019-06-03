package com.repository;

import com.entity.Account;
import com.entity.Transfer;

import java.util.concurrent.ConcurrentHashMap;

public class TransferRepository extends EntityStorageImpl<Transfer> {

    private static ConcurrentHashMap<String, Transfer> map = new ConcurrentHashMap<String, Transfer>();

    public TransferRepository(){
        super(map);
    }

}