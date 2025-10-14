package net.engineeringdigest.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
@Disabled
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Disabled
    @Test
    void TestsendMail(){
        redisTemplate.opsForValue().set("email","gsaurav32144@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a=1;
    }
}
