package com.Polizas.Polizas;

import com.Polizas.Polizas.Services.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class PolizasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolizasApplication.class, args);
		RedisService redisService = new RedisService();
		redisService.save(1L,10.64);
		System.out.println(redisService.getData(1L));

	}

}
