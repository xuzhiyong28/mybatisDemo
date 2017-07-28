package com.xzy.mybatis.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by XZY on 2017/5/1.
 */
public abstract class AbstractBaseRedisDao<K, V extends Serializable> {

    @Autowired
    public RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /***
     * 获取 RedisSerializer
     * @param redisTemplate
     */
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取 RedisSerializer
     * <br>------------------------------<br>
     */
    public RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }


    public void saveRedisString(final String key, final String value, final int time) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyByte = redisTemplate.getStringSerializer().serialize(key);
                byte[] valueByte = redisTemplate.getStringSerializer().serialize(value);
                redisConnection.set(keyByte, valueByte);
                redisConnection.expire(keyByte,time);
                return null;
            }
        });
    }

    public String getRedisString(final  String key){
        return (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyByte = redisTemplate.getStringSerializer().serialize(key);
                if(redisConnection.exists(keyByte)){
                    byte[] valueByte = redisConnection.get(keyByte);
                    String value = (String) redisTemplate.getStringSerializer().deserialize(valueByte);
                    return value;
                }
                return null;
            }
        });
    }


    /***
     * 存储对象 通过自己序列化
     * @param key
     * @param obj
     * @param time
     */
    public void saveObject(final String key, Object obj, final int time) {
        final byte[] vbytes = SerializeUtil.serialize(obj);
        redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
                connection.expire(redisTemplate.getStringSerializer().serialize(key), time);
                return null;
            }
        });
    }

    /***
     * 读取对象 自己反序列化
     * @param key
     * @return
     */
    public Object getObject(final String key) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
                if (connection.exists(keybytes)) {
                    byte[] valuebytes = connection.get(keybytes);
                    @SuppressWarnings("unchecked")
                    Object value = SerializeUtil.unserialize(valuebytes);
                    return value;
                }
                return null;
            }
        });
    }


    /**
     * 删除key
     * @param key
     */
    public void delete(final String key){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.del(redisTemplate.getStringSerializer().serialize(key));
                return null;
            }
        });
    }


    /***
     * 压栈
     * @param key
     * @param object
     * @return
     */
    public Long pushRedis(String key , Object object){
        return redisTemplate.opsForList().leftPush(key,object);
    }


    /***
     * 出栈
     * @param key
     * @return
     */
    public Object popRedis(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


    /***
     * 栈/队 长度
     * @param key
     * @return
     */
    public Long length(String key){
        return redisTemplate.opsForList().size(key);
    }


    /***
     * 检索范围
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> range(String key , int start , int end){
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        return listOperations.range(key,start,end);
    }




    /***
     * 入队
     * @param key
     * @param object
     * @return
     */
    public Long inRedis(String key , Object object){
        return redisTemplate.opsForList().rightPush(key,object);
    }


    /***
     * 出队
     * @param key
     * @return
     */
    public Object outRedis(String key){
        return redisTemplate.opsForList().leftPop(key);
    }



}
