package com.example.demo;

import com.example.demo.entity.Days;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> daysRedisTemplate;

    private Days d;

    /*
    * value为字符串的操作
    * */
    @Test
    void addString(){
    }




    //测试特定对象的序列
    @Test
    void contextLoads() {

        d=new Days();
        d.setDate("123");
        d.setDaysId("456");
        d.setItemNumber(123);
        d.setOpenId("dawda");
        d.setTitle("title");
        daysRedisTemplate.opsForValue().set("你好",d);
        System.out.println(daysRedisTemplate.opsForValue().get("你好"));
    }

    @Test
    void add(){
        d=new Days();
        d.setDate("123");
        d.setDaysId("456");
        d.setItemNumber(123);
        d.setOpenId("dawda");
        d.setTitle("title");
        Days d2=new Days();
        d2.setDate("666");
        d2.setDaysId("789");
        d2.setItemNumber(789);
        d2.setOpenId("wang");
        d2.setTitle("title");
        HashMap<String, Days> hashMap = new HashMap<>();
        hashMap.put("天数1",d);
        hashMap.put("天数2",d2);
        daysRedisTemplate.opsForHash().put("天数","hash",hashMap);
        System.out.println(daysRedisTemplate.opsForHash().get("天数","hash"));

    }
}
