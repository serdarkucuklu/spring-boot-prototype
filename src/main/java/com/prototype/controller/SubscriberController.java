package com.prototype.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prototype.data.Subscriber;
import com.prototype.service.SubscriberService;

@RestController
@RequestMapping("/subscribers")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping("/getSubscriber/{id}")
    public Subscriber getSubscriber(@PathVariable Long id) throws JsonGenerationException, JsonMappingException, IOException{
        return subscriberService.getSubscriber(id);
    }

    @PostMapping("/subscriber/{id}&{name}&{msisdn}")
    public Subscriber createSubscriber(@PathVariable Long id, @PathVariable String name, @PathVariable String msisdn) throws JsonGenerationException, JsonMappingException, IOException{
        return subscriberService.createSubscriber(id, name, msisdn);
    }
    
    @PutMapping("/subscriber/{id}&{name}&{msisdn}")
    public Subscriber updateSubscriber(@PathVariable Long id, @PathVariable String name, @PathVariable String msisdn) throws JsonGenerationException, JsonMappingException, IOException{
        return subscriberService.updateSubscriber(id, name, msisdn);
    }
    
    @DeleteMapping("/subscriber/{id}")
    public Subscriber deleteSubscriber(@PathVariable Long id) throws JsonGenerationException, JsonMappingException, IOException{
        return subscriberService.deleteSubscriber(id);
    }

}
