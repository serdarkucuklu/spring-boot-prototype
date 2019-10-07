package com.prototype.wsconf;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.data.SubscriberList;
import com.prototype.ws.GetAllSubscribersRequest;
import com.prototype.ws.GetAllSubscribersResponse;
import com.prototype.ws.GetSubscriberByIdResponse;

import com.prototype.ws.GetSubscriberById;
import com.prototype.ws.GetSubscriberByIdRequest;

@Endpoint
public class GetSubscriberByIdEndpoint {

	//@Autowired
	//private CacheManager cacheManager;
	
	private static final Logger LOG = LoggerFactory.getLogger(GetSubscriberByIdEndpoint.class);
	
	@PayloadRoot(namespace = "http://serdarkucuklu.com/prototype", localPart = "GetSubscriberByIdRequest")
	@ResponsePayload
	public GetSubscriberByIdResponse getSubscriberById(@RequestPayload GetSubscriberByIdRequest request) throws JsonParseException, JsonMappingException, IOException {
		
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        LOG.info(formatter.format(date) + " /getSubscriberById is = " + request.getId());
		
		BigInteger id = request.getId();
		GetSubscriberByIdResponse getSubscriberByIdResponse = new GetSubscriberByIdResponse();
		GetSubscriberById getSubscriberById = new GetSubscriberById();
		
        ObjectMapper objectMapper = new ObjectMapper();
        SubscriberList subscriber = objectMapper.readValue(new File("data.json"), SubscriberList.class);
		
        for(int i = 0; i<subscriber.getSubscribers().size(); i++) {
        	if(id.intValue() == subscriber.getSubscribers().get(i).getId()) {
        		getSubscriberById.setMsisdn(subscriber.getSubscribers().get(i).getMsisdn());
        		getSubscriberById.setId(subscriber.getSubscribers().get(i).getId());
        		getSubscriberById.setName(subscriber.getSubscribers().get(i).getName());
        	}
        }
        getSubscriberByIdResponse.setSubscriberById(getSubscriberById);
		return getSubscriberByIdResponse;
	}

	@PayloadRoot(namespace = "http://serdarkucuklu.com/prototype", localPart = "GetAllSubscribersRequest")
	@ResponsePayload
	public GetAllSubscribersResponse getAllSubscribers(@RequestPayload GetAllSubscribersRequest request) throws JsonParseException, JsonMappingException, IOException {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        LOG.info(formatter.format(date) + " /getAllSubscribers");
		
		GetAllSubscribersResponse getSubscriberByIdResponse = new GetAllSubscribersResponse();

		//Cache subscriber = this.cacheManager.getCache("subscriber");
		//System.out.println("Subscriber : " + subscriber);

        ObjectMapper objectMapper = new ObjectMapper();
        SubscriberList subscriber = objectMapper.readValue(new File("data.json"), SubscriberList.class);

        for(int i = 0; i < subscriber.getSubscribers().size(); i++) {
    		GetSubscriberById getSubscriberById = new GetSubscriberById();
    		getSubscriberById.setMsisdn(subscriber.getSubscribers().get(i).getMsisdn());
    		getSubscriberById.setId(subscriber.getSubscribers().get(i).getId());
    		getSubscriberById.setName(subscriber.getSubscribers().get(i).getName());
    		List<GetSubscriberById> getSubscriberByIdlist = getSubscriberByIdResponse.getSubscriberById();
    		getSubscriberByIdlist.add(getSubscriberById);
        }
		
		return getSubscriberByIdResponse;
	}
	
	@SuppressWarnings("unused")
	private static void parseEmployeeObject(JSONObject subscribers) throws JsonProcessingException 
	{
		JSONObject subscriberObject = (JSONObject) subscribers.get("subscribers");
		
		String id = (String) subscriberObject.get("id");	
		System.out.println(id);
		
		String name = (String) subscriberObject.get("name");	
		System.out.println(name);
		
		String msisdn = (String) subscriberObject.get("msisdn");	
		System.out.println(msisdn);
	}

}
