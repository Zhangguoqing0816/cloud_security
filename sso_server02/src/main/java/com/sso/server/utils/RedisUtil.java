package com.sso.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Auther:
 * @Description:
 * @Date: 2020/1/13 16:11
 */
@Component
public class RedisUtil {

    @Autowired
    @Qualifier("myRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 往redis里面放 key  value
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, String.valueOf(value));
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, String.valueOf(value), time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
    }

    /**
     * 从 redis 里面删除丢赢得 key，value，  方法中的key可以是多参数
     * @param key
     */
    public void deleteKey(String... key){
        if(key != null && key.length != 0){
            if(key.length > 1){
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }else{
                redisTemplate.delete(key[0]);
            }
        }
    }

    /**
     * 根据 key 从 redis 里面取值
     * @param key
     * @return
     */
    public Object getKey(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断 redis 里面是否有对应的 key
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }



}
