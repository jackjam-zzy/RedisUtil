package com.example.demo;

import com.example.demo.entity.Days;
import com.example.demo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
        Map<String,Object> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        redisUtil.hmset("11.25",map);

        Map<Object, Object> hmget = redisUtil.hmget("11.25");
        System.out.println(hmget);
    }

    /*
    * 对map<String,object>的map进行保存读取
    * */
    @Test
    void hAddObject(){
        Map<String,Object> map = new HashMap<>();
        Days d1 = new Days();
        d1.setDate("2019.2.2");
        d1.setTitle("星期一");
        d1.setItemNumber(22);
        d1.setOpenId("555555555");
        Days d2 = new Days();
        d2.setDate("2019.2.2");
        d2.setTitle("星期二");
        d2.setItemNumber(44);
        d2.setOpenId("666666666");
        map.put("第一天",d1);
        map.put("第二天",d2);
        //保存值
        redisUtil.hmset("天数",map);
        //取出值
        Map<Object, Object> result = redisUtil.hmget("天数");
        System.out.println(result);
    }



    //=================================对set的测试=================================

    /*
    * 对Set<String>的set进行保存读取
    * */
    @Test
    void sAdd(){
        Set<String> set = new HashSet<>();
        set.add("我是谁");
        set.add("我是谁");
        set.add("你妹的");
        set.add("你妹");
        set.add("妹的");
        //保存
        redisUtil.sSet("Set", set);
        //读取
        Set<Object> set1 = redisUtil.sGet("Set");

        System.out.println(set1);
    }

    /*
     * 对Set<Object>的set进行保存读取
     * */
    @Test
    void sAddObject(){
        Set<Days> set = new HashSet<>();
        Days d1 = new Days();
        d1.setDate("9999.2.2");
        d1.setTitle("星期一");
        d1.setItemNumber(999);
        d1.setOpenId("486");
        Days d2 = new Days();
        d2.setDate("9999.2.2");
        d2.setTitle("星期二");
        d2.setItemNumber(666);
        d2.setOpenId("110");

        set.add(d1);
        set.add(d2);

        //保存
        redisUtil.sSet("SetObject", set);
        //读取
        Set<Object> get = redisUtil.sGet("SetObject");

        System.out.println(get);
    }


}
