package lock.hank.redislock.annation;

import java.lang.annotation.*;

/**
 * @author lxt
 * @date 2020-01-16-10:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface limitRequest {
    //限流接口key
    String key();

    //限流次数
    int limit() default 0;

    //单位时间
    String period() default "10";
}
