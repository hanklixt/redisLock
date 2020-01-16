package lock.hank.redislock;

import lock.hank.redislock.limit.DistributedLimit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedislockApplicationTests {

    @Autowired
    private DistributedLimit distributedLimit;

    @Test
    void contextLoads() {


    }

}
