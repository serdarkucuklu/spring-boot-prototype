package com.prototype.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.data.Subscriber;
import com.prototype.data.SubscriberList;

@Service
@EnableCaching
public class SubscriberService {

    @Value("${url.data_file_path}")  //data.json
    private String subscribersJsonFile;
	
    private static final Logger LOG = LoggerFactory.getLogger(SubscriberService.class);

	public static final String createSubscriber = null;
	
    @Cacheable(cacheNames = "subscriber",key="#id")
    public Subscriber getSubscriber(final Long id) throws JsonGenerationException, JsonMappingException, IOException{

        LOG.info("Returning subscriber information for subscriber id {} ",id);
        LOG.info("getSubscriber..");
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1);
        subscriber1.setName("Stephan King");
        subscriber1.setMsisdn("905552551122");
        
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2);
        subscriber2.setName("Alice Gracy");
        subscriber2.setMsisdn("905552551133");
        
        Subscriber subscriber3 = new Subscriber();
        subscriber3.setId(3);
        subscriber3.setName("Glory Wisdom");
        subscriber3.setMsisdn("905552551144");

        List<Subscriber> lscc = new ArrayList<Subscriber>();
        lscc.add(subscriber1);
        lscc.add(subscriber2);
        lscc.add(subscriber3);
        
        SubscriberList c = new SubscriberList();
        c.setSubscribers(lscc);

		ObjectMapper mapper2 = new ObjectMapper();
        String jsonInString22 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        
    	//Write JSON file
    	try (FileWriter file = new FileWriter(subscribersJsonFile)) {

            file.write(jsonInString22);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int inum = id.intValue();
        
        return  lscc.get(inum - 1);
    }
    
    @Cacheable(cacheNames = "subscriber",key="#id")
    public Subscriber createSubscribers(final Long id) throws JsonGenerationException, JsonMappingException, IOException{

        LOG.info("Subscriber list is created..");
        Subscriber subscriber1 = new Subscriber();
        subscriber1.setId(1);
        subscriber1.setName("Stephan King");
        subscriber1.setMsisdn("905552551122");
        
        Subscriber subscriber2 = new Subscriber();
        subscriber2.setId(2);
        subscriber2.setName("Alice Gracy");
        subscriber2.setMsisdn("905552551133");
        
        Subscriber subscriber3 = new Subscriber();
        subscriber3.setId(3);
        subscriber3.setName("Glory Wisdom");
        subscriber3.setMsisdn("905552551144");

        List<Subscriber> lscc = new ArrayList<Subscriber>();
        lscc.add(subscriber1);
        lscc.add(subscriber2);
        lscc.add(subscriber3);
        
        SubscriberList c = new SubscriberList();
        c.setSubscribers(lscc);

		ObjectMapper mapper2 = new ObjectMapper();
        String jsonInString22 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        
    	try (FileWriter file = new FileWriter(subscribersJsonFile)) {

            file.write(jsonInString22);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
         
        int inum = id.intValue();
        
        return  lscc.get(inum - 1);
    }
    
    @Cacheable(cacheNames = "subscriber",key="#id")
    public Subscriber createSubscriber(final Long id, final String name, final String msisdn) throws JsonGenerationException, JsonMappingException, IOException{

        LOG.info("Subscriber is created..");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        LOG.info(formatter.format(date) + " /subscriber[POST] id = " + id.intValue() + " ,name = " + name + " ,msisdn = " + msisdn);
        
        Subscriber subscriber = new Subscriber();
        subscriber.setId(id.intValue());
        subscriber.setName(name);
        subscriber.setMsisdn(msisdn);

        ObjectMapper objectMapper = new ObjectMapper();
        SubscriberList subscribers = objectMapper.readValue(new File("data.json"), SubscriberList.class);
        
        List<Subscriber> lscc = new ArrayList<Subscriber>();  
        
        for(int i = 0; i < subscribers.getSubscribers().size(); i++) {
        	Subscriber oldSubscriber = new Subscriber();
        	oldSubscriber.setId(subscribers.getSubscribers().get(i).getId());
        	oldSubscriber.setName(subscribers.getSubscribers().get(i).getName());
        	oldSubscriber.setMsisdn(subscribers.getSubscribers().get(i).getMsisdn());
        	lscc.add(oldSubscriber);
        }
        
        lscc.add(subscriber);
        SubscriberList c = new SubscriberList();
        c.setSubscribers(lscc);

		ObjectMapper mapper2 = new ObjectMapper();
        String jsonInString22 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        
    	try (FileWriter file = new FileWriter(subscribersJsonFile)) {

            file.write(jsonInString22);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
                
        return  lscc.get(subscribers.getSubscribers().size());
    }
    
    @Cacheable(cacheNames = "subscriber",key="#id")
    @CachePut(value="subscriber",key="#id")
    public Subscriber updateSubscriber(final Long id, final String name, final String msisdn) throws JsonGenerationException, JsonMappingException, IOException{

        LOG.info("Subscriber is updated..");
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        ObjectMapper objectMapper = new ObjectMapper();
        SubscriberList subscribers = objectMapper.readValue(new File("data.json"), SubscriberList.class);
        
        List<Subscriber> lscc = new ArrayList<Subscriber>();
 
        for(int i = 0; i < subscribers.getSubscribers().size(); i++) {
        	Subscriber oldSubscriber = new Subscriber();
        	if(subscribers.getSubscribers().get(i).getId() != id) {
	        	oldSubscriber.setId(subscribers.getSubscribers().get(i).getId());
	        	oldSubscriber.setName(subscribers.getSubscribers().get(i).getName());
	        	oldSubscriber.setMsisdn(subscribers.getSubscribers().get(i).getMsisdn());
	        	lscc.add(oldSubscriber);
        	}
        	else {
        		oldSubscriber.setId(id.intValue());
	        	oldSubscriber.setName(name);
	        	oldSubscriber.setMsisdn(msisdn); 
	        	lscc.add(oldSubscriber);
	        	LOG.info(formatter.format(date) + " /subscriber[PUT] id = " + id + " ,name = " + name + " ,msisdn = " + msisdn);
        	}
        }
        
        SubscriberList c = new SubscriberList();
        c.setSubscribers(lscc);

		ObjectMapper mapper2 = new ObjectMapper();
        String jsonInString22 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        
    	try (FileWriter file = new FileWriter(subscribersJsonFile)) {

            file.write(jsonInString22);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    	

    	return  lscc.get(subscribers.getSubscribers().size());
    }
    
    @Cacheable(cacheNames = "subscriber",key="#id")
    @CacheEvict(value="subscriber", key="#id")
    public Subscriber deleteSubscriber(final Long id) throws JsonGenerationException, JsonMappingException, IOException{

        LOG.info("Subscriber is deleted..");
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        ResponseEntity abc = null;
        
        ObjectMapper objectMapper = new ObjectMapper();
        SubscriberList subscribers = objectMapper.readValue(new File("data.json"), SubscriberList.class);
        
        List<Subscriber> lscc = new ArrayList<Subscriber>();
 
        for(int i = 0; i < subscribers.getSubscribers().size(); i++) {
        	Subscriber oldSubscriber = new Subscriber();
        	if(subscribers.getSubscribers().get(i).getId() != id) {
	        	oldSubscriber.setId(subscribers.getSubscribers().get(i).getId());
	        	oldSubscriber.setName(subscribers.getSubscribers().get(i).getName());
	        	oldSubscriber.setMsisdn(subscribers.getSubscribers().get(i).getMsisdn());
	        	lscc.add(oldSubscriber);
        	}
        	else {
        		LOG.info(formatter.format(date) + " /subscriber[DELETE] id = " + id);
        	}
        }
        
        SubscriberList c = new SubscriberList();
        c.setSubscribers(lscc);

		ObjectMapper mapper2 = new ObjectMapper();
        String jsonInString22 = mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        
    	try (FileWriter file = new FileWriter(subscribersJsonFile)) {

            file.write(jsonInString22);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	if (abc == null)
    		abc = ResponseEntity.badRequest().body("Subscriber Not Found");
                
    	return  lscc.get(subscribers.getSubscribers().size());
    }

}
