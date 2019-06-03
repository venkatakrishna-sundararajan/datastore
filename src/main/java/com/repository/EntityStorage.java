package com.repository;

import java.io.IOException;

public interface EntityStorage<T> {
    public void put (String key, T object);
    public T get(String key);
}
