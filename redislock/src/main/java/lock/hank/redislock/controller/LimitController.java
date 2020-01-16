package lock.hank.redislock.controller;

import lock.hank.redislock.annation.limitRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxt
 * @date 2020-01-16-11:00
 */
@RestController
public class LimitController {


    @GetMapping("/hello")
    @limitRequest(key = "hello", limit = 10, period = "10")
    public String hello() {
        return "hello,world";
    }


}
