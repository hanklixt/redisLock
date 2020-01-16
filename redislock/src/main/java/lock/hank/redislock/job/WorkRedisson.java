package lock.hank.redislock.job;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

/**
 * @author lxt
 * @date 2020-01-13-17:46
 */
//@Component
public class WorkRedisson {


    @Autowired
    private RedissonClient redissonClient;

    @Scheduled(cron = "0/10 * * * * ?")
    public void clearOrder() {
        //得到lock ，并且设置锁的名称
        final RLock lock = redissonClient.getLock("lock1");
        //尝试获取锁，这里有多个重载的方法
        //waitTime：获取锁重试的最大超时时间，默认为0
        //leaseTime：释放锁的最大时间，默认时30秒
        //unit：时间单位

        try {
            final boolean b = lock.tryLock(10, 3000, TimeUnit.MILLISECONDS);
            if (!b) {
                System.out.println("获取锁失败,任务终止");
                return;
            }
            System.out.println("扫描订单" + System.currentTimeMillis());
            System.out.println("清理库存" + System.currentTimeMillis());
        } catch (InterruptedException e) {

        } finally {
            System.out.println("释放锁");
            lock.unlock();
        }


    }

}
