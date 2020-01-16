package lock.hank.redislock.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lxt
 * @date 2020-01-16-11:42
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e) {

        if (e instanceof RuntimeException) {
            log.error("exception ");
            return e.getMessage();
        }

        return e.getMessage();

    }

}
