package com.repository;


import com.entity.Account;

import java.util.concurrent.ConcurrentHashMap;

public class EntityStorageImpl<T> implements EntityStorage<T>{

    ConcurrentHashMap<String, T> map;

    public EntityStorageImpl(ConcurrentHashMap map){
        this.map = map;
    }

    public void put (String key, T object){
        map.put(key, object);
    }

    public T get (String key){
        return map.get(key);
    }
}
