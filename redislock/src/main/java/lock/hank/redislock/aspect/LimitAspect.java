package lock.hank.redislock.aspect;

import lock.hank.redislock.annation.limitRequest;
import lock.hank.redislock.limit.DistributedLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author lxt
 * @date 2020-01-16-10:41
 */
@Aspect
@Slf4j
@Component
@EnableAspectJAutoProxy
public class LimitAspect {

    @Autowired
    private DistributedLimit limit;

    @Before("@annotation(limitRequest)")
    public void beforeLimit(limitRequest limitRequest) {
        limit.limit(limitRequest.key(), limitRequest.limit(), limitRequest.period());
    }

}
