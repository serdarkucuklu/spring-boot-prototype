package com.prototype;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prototype.service.SubscriberService;

@Configuration
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class SpringBootWithEhcacheApplication implements CommandLineRunner {
	
    @Autowired
    private SubscriberService subscriberService;
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWithEhcacheApplication.class, args);
    }

	@Override
	public void run(String... args) throws JsonGenerationException, JsonMappingException, IOException{
		subscriberService.createSubscribers((long) 1);     
	}
	
	/*
	@Scheduled(cron = "0 0 12 * * ?")
    public void publish() {
		net.sf.ehcache.Cache subscriber = this.cacheManager.getCache("subscriber");
		System.out.println(subscriber);
    }
    */

}
