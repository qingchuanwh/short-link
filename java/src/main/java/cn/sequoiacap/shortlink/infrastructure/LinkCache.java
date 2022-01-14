package cn.sequoiacap.shortlink.infrastructure;

import cn.sequoiacap.shortlink.domain.model.Link;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Bob Chin
 * created on 2022/1/5
 */
@Component
public class LinkCache {
    private final Cache<String, Link> links;

    private LinkCache() {
        links = CacheBuilder.newBuilder()
                .concurrencyLevel(8)
                .initialCapacity(1024)
                .maximumSize(1024 * 10000)
                .expireAfterWrite(30, TimeUnit.DAYS)
                .build();
    }

    public void put(String key, Link link) {
        if (links.getIfPresent(key) != null) {
            return;
        }
        links.put(key, link);
    }

    public Link get(String key) {
        return links.getIfPresent(key);
    }
}
