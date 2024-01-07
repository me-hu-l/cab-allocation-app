package org.example.caching;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CachingServiceTest {
    @Test
    public void testAddToCache() {
        CachingService cachingService = new CachingService(3);

        // Add entries to cache
        cachingService.addToCache("key1", "value1");
        cachingService.addToCache("key2", "value2");
        cachingService.addToCache("key3", "value3");

        // Retrieve entries
        assertEquals("value1", cachingService.getFromCache("key1"));
        assertEquals("value2", cachingService.getFromCache("key2"));
        assertEquals("value3", cachingService.getFromCache("key3"));
    }

    @Test
    public void testLRUEviction() {
        CachingService cachingService = new CachingService(3);

        // Add entries to cache
        cachingService.addToCache("key1", "value1");
        cachingService.addToCache("key2", "value2");
        cachingService.addToCache("key3", "value3");

        // Retrieve an entry to trigger LRU eviction
        cachingService.getFromCache("key1");

        // Add a new entry, triggering eviction of the least recently used entry "key2"
        cachingService.addToCache("key4", "value4");

        // Check that "key2" is evicted, and "key1", "key3", and "key4" remain in the cache
        assertNull(cachingService.getFromCache("key2"));
        assertEquals("value1", cachingService.getFromCache("key1"));
        assertEquals("value3", cachingService.getFromCache("key3"));
        assertEquals("value4", cachingService.getFromCache("key4"));
    }
}