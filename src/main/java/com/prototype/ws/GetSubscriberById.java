package com.prototype.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSubscriberById", propOrder = {
    "id",
    "name",
    "msisdn"
})
public class GetSubscriberById {

    @XmlElement(required = true)
    protected int id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String msisdn;

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String value) {
        this.msisdn = value;
    }

}
