package com.Polizas.Polizas.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void save(Long id,double valor ){
        LocalDateTime localDateTime = LocalDateTime.now();
        String fechaHoraActualStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        redisTemplate.opsForList().rightPush(String.valueOf(id),fechaHoraActualStr);
        redisTemplate.opsForList().rightPush(String.valueOf(id),String.valueOf(valor));
        redisTemplate.expire(String.valueOf(id),20,java.util.concurrent.TimeUnit.MINUTES);
    }

    public List<String> getData(Long id){
        return redisTemplate.opsForList().range(String.valueOf(id),0,-1);
    }



}
