package org.example.caching;

import java.util.HashMap;
import java.util.Map;

// CacheManager interface
public interface CacheManager {
    void addToCache(String key, Object value);
    Object getFromCache(String key);
}
