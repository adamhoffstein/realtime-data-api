package org.adam.service;

import org.adam.OrderRequestResource;
import org.adam.exceptions.ObjectNotFoundException;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.hash.HashCommands;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.set.SetCommands;
import io.quarkus.redis.datasource.sortedset.SortedSetCommands;
import io.quarkus.redis.datasource.sortedset.ZRangeArgs;
import io.quarkus.redis.datasource.value.ValueCommands;
import io.smallrye.mutiny.groups.UniAwait;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Map;

@Singleton
public class RedisService {

    private static final Logger LOG = Logger.getLogger(OrderRequestResource.class);

    private final ReactiveKeyCommands<String> keyCommands;
    private final ValueCommands<String, String> valueCommands;

    private final SetCommands<String, String> setCommands;
    private final SortedSetCommands<String, String> sortedSetCommands;

    private final HashCommands<String, String, String> hashCommands;

    public RedisService(RedisDataSource redisDataSource, ReactiveRedisDataSource reactiveRedisDataSource) {
        keyCommands = reactiveRedisDataSource.key();
        valueCommands = redisDataSource.value(String.class);
        sortedSetCommands = redisDataSource.sortedSet(String.class);
        setCommands = redisDataSource.set(String.class);
        hashCommands = redisDataSource.hash(String.class);
    }

    public boolean exists(String key) {
        return keyCommands.exists(key).await().indefinitely();
    }

    public void hset(String key, String value, String hashKey) {
        hashCommands.hset(key, hashKey, value);
    }

    public void zadd(String key, Double score, String member) {
        sortedSetCommands.zadd(key, score, member);
    }

    public Map<String, String> hgetall(String key) {
        Map<String, String> rawResults = hashCommands.hgetall(key);
        if (rawResults.isEmpty()) {
            throw new ObjectNotFoundException(key);
        }
        return rawResults;
    }

    public List<String> keys(String pattern) {
        UniAwait<List<String>> results = keyCommands.keys(pattern).await();
        /* probably not the best way to deal with this */
        return results.indefinitely();
    }

    public String zrange(String key) {
        List<String> lookup = sortedSetCommands.zrange(key, 0, 0, new ZRangeArgs().rev());
        if (lookup.isEmpty()) {
            throw new ObjectNotFoundException(key);
        } else {
        return lookup.get(0);
        }
    }
}
