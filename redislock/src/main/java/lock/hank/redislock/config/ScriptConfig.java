package lock.hank.redislock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author lxt
 * @date 2020-01-16-10:07
 */
@Configuration
public class ScriptConfig {

    @Bean("limitScript")
    public DefaultRedisScript<Number> limitScript() {
        final DefaultRedisScript<Number> limitScript = new DefaultRedisScript<Number>();
        limitScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
        limitScript.setResultType(Number.class);
        return limitScript;
    }

    @Bean("scriptSerializer")
    public RedisSerializer<String> redisSerializer() {
        return new StringRedisSerializer();
    }

}
