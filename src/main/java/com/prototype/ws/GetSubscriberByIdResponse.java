package com.prototype.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSubscriberById"
})
@XmlRootElement(name = "GetSubscriberByIdResponse")
public class GetSubscriberByIdResponse {

    @XmlElement(name = "GetSubscriberById", required = true)
    protected GetSubscriberById getSubscriberById;

    public GetSubscriberById getSubscriberById() {
        return getSubscriberById;
    }

    public void setSubscriberById(GetSubscriberById value) {
        this.getSubscriberById = value;
    }

}
