package org.chq.study.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: chenhq
 * @Email: 542504275@qq.com
 * @CreateDate: 2019/8/29 0:04
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int CACHE_SIZE;
        /**
         *  @param cacheSize 缓存大小
         */
        // true 表示让 linkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
        //如果accessOrder为flase的话，则按插入顺序来遍历
        public LRUCache(int cacheSize) {
            super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
            CACHE_SIZE = cacheSize;
        }

        @Override
        // 当 map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

            return size() > CACHE_SIZE;
        }
    }

