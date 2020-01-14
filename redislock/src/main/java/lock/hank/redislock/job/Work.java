package lock.hank.redislock.job;

import lock.hank.redislock.fac.RedisLockFactory;
import lock.hank.redislock.lock.RedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author lxt
 * @date 2020-01-13-17:46
 */
public class Work {

    @Autowired
    private RedisLockFactory redisLockFactory;

    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0/10 * * * * ?")
    public void clearOrder() {
        final RedisLock lock = redisLockFactory.getInstance("lock");
        final RLock lock1 = redissonClient.getLock("lock1");
        final boolean b = lock.tryLock(50);
        try {
            if (!b) {
                System.out.println("获取锁失败,任务终止");
                return;
            }
            System.out.println("扫描订单");
            System.out.println("清理库存");
        } finally {
            System.out.println("释放锁");
            lock.unLock(50);
        }


    }

}
