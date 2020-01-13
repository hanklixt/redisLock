package lock.hank.redislock.lock;

/**
 * @author lxt
 * @date 2020-01-13-16:27
 */
public interface RedisLock {
    /**
     * 获取锁
     *
     * @param releaseTime 锁的自动释放时间
     * @return
     */
    boolean tryLock(long releaseTime);

    /**
     * 释放锁
     */
    void unLock(long releaseTime);
}
