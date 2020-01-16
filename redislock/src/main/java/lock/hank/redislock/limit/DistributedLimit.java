package lock.hank.redislock.limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

/**
 * @author lxt
 * @date 2020-01-16-10:12
 */
@Component
@Slf4j
public class DistributedLimit {

    @Resource(name = "limitScript")
    private DefaultRedisScript<Number> limitScript;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "scriptSerializer")
    private RedisSerializer redisSerializer;

    public void limit(String key, int limit, String period) {

        log.info(limitScript.getScriptAsString());
        try {
            Object obj = redisTemplate.execute(limitScript, redisSerializer, redisSerializer, Collections.singletonList(key), String.valueOf(limit), period);
            log.info("obj:{}", obj);
            if (Objects.nonNull(obj)) {
                final Number count = (Number) obj;
                if (count.intValue() >= limit) {
                    throw new RuntimeException("limit request");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
