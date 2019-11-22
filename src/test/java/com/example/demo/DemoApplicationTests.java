package com.example.demo;

import com.example.demo.entity.Days;
import com.example.demo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void setValueString(){
        System.out.println(redisUtil.set("我是字符串", "我是字符串"));
    }

    @Test
    void addString(){
        Days d1 = new Days();
        d1.setDate("2019.2.2");
        d1.setTitle("你好毒");
        d1.setItemNumber(22);
        d1.setOpenId("555555555");
        System.out.println(redisUtil.set("你妹的", d1));
    }

    @Test
    void addList(){
        Days d1 = new Days();
        d1.setDate("2019.2.2");
        d1.setTitle("你好毒");
        d1.setItemNumber(22);
        d1.setOpenId("555555555");
        Days d2 = new Days();
        d2.setDate("1999.4.5");
        d2.setTitle("发士大夫");
        d2.setOpenId("6666");
        ArrayList<Days> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);
        System.out.println(redisUtil.lSet("列表一",list));
    }

    @Test
    void addHash(){
        HashMap<String,Object> map = new HashMap<>();
        Days d1 = new Days();
        d1.setDate("2019.2.2");
        d1.setTitle("你好毒");
        d1.setItemNumber(22);
        d1.setOpenId("555555555");
        Days d2 = new Days();
        d2.setDate("1999.4.5");
        d2.setTitle("发士大夫");
        d2.setOpenId("6666");
        map.put("day1",d1);
        map.put("day2",d2);
//        redisUtil.hset("hash1","day1",d1);
//        redisUtil.hset("hash2","day2",d2);
        redisUtil.hmset("day",map);

        redisUtil.hset("day","day3",d1);
//        redisUtil.hset("hash3","所有的day",map);

    }

    //测试过期时间设置
    @Test
    void setExpire(){
        redisUtil.expire("hash3",10);
    }

    @Test
    void getString(){
        Days d = (Days) redisUtil.hget("day", "day1");
        System.out.println(d);
    }

    @Test
    void getStringValue(){
        String s = (String) redisUtil.get("我是字符串");
        System.out.println(s);
    }

    //=================================对hash的测试=================================

    /*
    * 对map<String,String>的map进行保存和读取
    * */
    @Test
    void hAdd(){
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
    }

}
