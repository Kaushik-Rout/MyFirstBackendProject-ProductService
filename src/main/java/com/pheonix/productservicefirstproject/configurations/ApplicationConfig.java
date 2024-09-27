package com.pheonix.productservicefirstproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

//another special class annotation like @Service . Spring go through all the special class and create their objects.
@Configuration // it indicates spring that it has @Bean methods which it has to execute
public class ApplicationConfig {
    // while returning this method is gonna create an object of this class -
    // @Bean ensures whatever obj this method is returning the spring application context(where all objects created by spring is stored)
    // only one object of this class is stored.
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    //@Bean
    //When you annotate a method with @Bean, Spring will call that method and register the returned object as a
    // bean in the Spring ApplicationContext, making it available for dependency injection throughout the application.

    //Configurattion
    //@Configuration is used to indicate that the class contains bean definitions.

    // for caching with redis
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
