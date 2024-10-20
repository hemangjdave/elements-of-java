package com.techrevolution.algo;

public interface Cache<K , V> {

    void put(K key, V value);

    V get(K key);

}
