package com.prototype.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSubscriberById"
})
@XmlRootElement(name = "GetAllSubscribersResponse")
public class GetAllSubscribersResponse {

    @XmlElement(name = "GetSubscriberById", required = true)
    protected List<GetSubscriberById> getSubscriberById;

    public List<GetSubscriberById> getSubscriberById() {
        if (getSubscriberById == null) {
        	getSubscriberById = new ArrayList<GetSubscriberById>();
        }
        return this.getSubscriberById;
    }

}
