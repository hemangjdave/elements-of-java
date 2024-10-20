package com.techrevolution.algo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class LeastRecentUsedCache<K, V> implements Cache<K, V> {

    private final int initialCapacity;
    private final Map<K, V> map = new HashMap<>();
    private int currentCapacity;
    private final PriorityQueue<MetaData> priorityQueue;
    public LeastRecentUsedCache(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        Comparator<MetaData> comparator = Comparator.comparing(metaData -> metaData.numberOfHits);
        comparator.thenComparing(metaData -> metaData.localDateTime);
        priorityQueue = new PriorityQueue<>();
    }

    @Override
    public void put(K key, V value) {
        V mapValue = map.get(key);
        if (Objects.isNull(mapValue)){
            if (currentCapacity < initialCapacity) {
                map.put(key, value);

                currentCapacity++;
            }
        }
    }

    @Override
    public V get(K key) {
        return null;
    }

    @NoArgsConstructor
    @AllArgsConstructor(staticName = "create")
    @Getter
    static class MetaData {
        String key;
        String numberOfHits;
        LocalDateTime localDateTime;
    }
}
