package com.lijufeng.redis.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void put(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void update(String key, String newValue) {
        stringRedisTemplate.opsForValue().setIfPresent(key, newValue);
    }

    @Override
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
    @Override
    public void strExpire(String key, Long time) {
        stringRedisTemplate.expire(key,time,TimeUnit.SECONDS);
    }

    @Override
    public Object getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    @Override
    public List<String> getList(String key) {
        return stringRedisTemplate.opsForList().range(key,0,-1);
    }

    @Override
    public Set getZset(String key) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key,0,-1);
    }

    @Override
    public Set<String> getSet(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }



    /*-------------------------------HASH操作--------------------------------------------------*/


    @Override
    public void hashSet(String key) {
        Map<String,String> hashMap=new HashMap<>();  //新建一个新的HashMap,来充当获取的实际数据
        hashMap.put("MON","星期一");
        hashMap.put("TUE","星期二");
        hashMap.put("WED","星期三");
        hashMap.put("THU","星期四");
        hashMap.put("FRI","星期五");
        hashMap.put("SAT","星期六");
        hashMap.put("SUN","星期七");
        stringRedisTemplate.opsForHash().putAll(key,hashMap);
    }

    @Override
    public Object hashGetKeys(String key) {   //传入参数key
//        return stringRedisTemplate.opsForHash().get(key,"MON");
        return stringRedisTemplate.opsForHash().keys(key);
    }

    @Override
    public Object hashGetValues(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    @Override
    public Object hashGetResults(String key) {
//        return this.hashGetKeys(key).toString() + this.hashGetValues(key).toString();
        Map<Object,Object> map= stringRedisTemplate.opsForHash().entries(key);
        return map;
    }

    @Override
    public void hashUpdate(String key, String field, String newValue) {
        stringRedisTemplate.opsForHash().put(key,field,newValue);
    }

    @Override
    public void hashDelete(String key, String field) {
        stringRedisTemplate.opsForHash().delete(key,field);
    }






}
