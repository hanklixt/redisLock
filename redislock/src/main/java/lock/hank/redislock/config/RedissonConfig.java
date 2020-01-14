package lock.hank.redislock.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lxt
 * @date 2020-01-14-14:53
 */
@Configuration
public class RedissonConfig {


    @Bean
    public RedissonClient redissonClient() {
        //配置类
        Config config = new Config();
        //这里设置了单点的地址
        config.useSingleServer().setAddress("redis://localhost:6379");
        //也可以添加集群的地址
//        config.useClusterServers().addNodeAddress("redis://localhost:6379","redis://localhost:6378");
        return Redisson.create(config);
    }

}
