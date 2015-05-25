/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import javax.cache.CacheConfiguration;
import javax.cache.CacheManager;
import javax.cache.Caching;
import java.util.concurrent.TimeUnit;

public class Cache {

    private static javax.cache.Cache<String, String> cache = null;

    public static void create () {

        CacheManager cacheManager = Caching.getCacheManagerFactory().getCacheManager("CACHE_MANAGER");
        cache = cacheManager.getCache("TEST_DISTRIBUTED_CACHE");
        if (null == cache) {
            cache = cacheManager
                    .<String, String>createCacheBuilder("TEST_DISTRIBUTED_CACHE")
                    .setExpiry(
                            CacheConfiguration.ExpiryType.MODIFIED,
                            new CacheConfiguration.Duration(TimeUnit.MINUTES,
                                    10)).setStoreByValue(false).build();
        }
    }

    public static void put (String key, String value) {
        cache.put(key, value);
    }

    public static String get (String key) {
        return cache.get(key);
    }

    public static void delete (String key) {
        cache.remove(key);
    }

    public static void destroy () {

        CacheManager cacheManager = Caching.getCacheManagerFactory().getCacheManager("CACHE_MANAGER");
        cache = cacheManager.getCache("TEST_DISTRIBUTED_CACHE");
        if (cache != null) {
            cache.stop();
            cache = null;
        }
    }
}
