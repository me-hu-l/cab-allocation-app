package org.example.caching;

import java.util.LinkedHashMap;
import java.util.Map;

public class CachingService implements CacheManager {
    private final Map<String, Object> cache;

    public CachingService(int capacity) {
        this.cache = new LRUCache<>(capacity);
    }

    @Override
    public void addToCache(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getFromCache(String key) {
        return cache.get(key);
    }

    // Inner class for LRU Cache
    private static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;

        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}