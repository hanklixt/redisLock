package lock.hank.redislock.lock;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.UUID;

/**
 * @author lxt
 * @date 2020-01-13-16:38
 */
public class ReentrantRedisLock implements RedisLock {

    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取锁的脚本
     */
    private final static DefaultRedisScript<Long> LOCK_SCRIPTS;

    /**
     * 释放锁的脚本
     */
    private final static DefaultRedisScript<Object> UNLOCK_SCRIPTS;

    /**
     * 线程前缀，避免和其他jvm中其他线程id冲突
     */
    private static final String ID_PREFIX = UUID.randomUUID().toString();


    private String key;

    public ReentrantRedisLock(StringRedisTemplate stringRedisTemplate, String key) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.key = key;
    }

    static {
        //设置获取锁脚本来源
        LOCK_SCRIPTS = new DefaultRedisScript<>();
        LOCK_SCRIPTS.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        LOCK_SCRIPTS.setResultType(Long.class);
        //设置释放锁脚本获取来源
        UNLOCK_SCRIPTS = new DefaultRedisScript<>();
        UNLOCK_SCRIPTS.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        UNLOCK_SCRIPTS.setResultType(Object.class);
    }

    /**
     * 获取锁
     *
     * @param releaseTime 锁的自动释放时间
     * @return
     */
    @Override
    public boolean tryLock(long releaseTime) {
        final String time = String.valueOf(releaseTime);
        final Long locked = stringRedisTemplate.execute(LOCK_SCRIPTS, Collections.singletonList(key), ID_PREFIX + Thread.currentThread().getId(), time);
        return locked != null && locked == 1;
    }


    /**
     * 释放锁
     */
    @Override
    public void unLock(long releaseTime) {

        final String time = String.valueOf(releaseTime);
        stringRedisTemplate.execute(UNLOCK_SCRIPTS, Collections.singletonList(key), ID_PREFIX + Thread.currentThread().getId(), time);
    }
}
