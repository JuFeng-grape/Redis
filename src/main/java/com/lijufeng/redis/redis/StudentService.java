package com.lijufeng.redis.redis;


import java.util.List;
import java.util.Set;

public interface StudentService {
    void put(String key, String value);


    String get(String key);

    void update(String key, String newValue);

    void del(String key);


    void hashSet(String key);

    Object hashGetKeys(String key);

    Object hashGetValues(String key);

    Object hashGetResults(String key);

    void hashUpdate(String key, String field, String newValue);

    void hashDelete(String key, String field);

    void strExpire(String key, Long time);

    Object getExpire(String key);

    List<String> getList(String key);

    Set getZset(String key);

    Set getSet(String key);

}
