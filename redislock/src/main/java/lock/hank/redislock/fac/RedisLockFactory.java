package lock.hank.redislock.fac;

import lock.hank.redislock.lock.RedisLock;
import lock.hank.redislock.lock.ReentrantRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lxt
 * @date 2020-01-13-17:51
 */
@Component
public class RedisLockFactory {

    @Autowired
    private StringRedisTemplate redisTemplate;


    private RedisLock redisLock;

    public RedisLock getInstance(String key) {
        return new ReentrantRedisLock(redisTemplate, key);
    }


}
