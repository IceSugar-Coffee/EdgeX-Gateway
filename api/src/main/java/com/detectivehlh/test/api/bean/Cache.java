package com.detectivehlh.test.api.bean;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class Cache<K, V> {
    private static Cache<Integer, String> ImageCache;
    private static Cache<Integer, Object> PositionCache;

    public static Cache<Integer, String> getImageCache() {
        if (ImageCache == null) {
            ImageCache = new Cache<>();
        }
        return ImageCache;
    }
    public static Cache<Integer, Object> getPositionCache() {
        if (PositionCache == null) {
            PositionCache = new Cache<>();
        }
        return PositionCache;
    }
    private final Map<K, V> DATA_MAP = new HashMap<>();

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    public V get(K key) {
        readLock.lock();
        try {
            return DATA_MAP.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public V put(K key, V value) {
        writeLock.lock();
        try {
            return DATA_MAP.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public void clear() {
        writeLock.lock();
        try {
            DATA_MAP.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
