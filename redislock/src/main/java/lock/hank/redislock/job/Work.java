package lock.hank.redislock.job;

import lock.hank.redislock.fac.RedisLockFactory;
import lock.hank.redislock.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lxt
 * @date 2020-01-13-17:46
 */
@Component
public class Work {

    @Autowired
    private RedisLockFactory redisLockFactory;

    @Scheduled(cron = "0/10 * * * * ?")
    public void clearOrder() {
        final RedisLock lock = redisLockFactory.getInstance("lock");
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
