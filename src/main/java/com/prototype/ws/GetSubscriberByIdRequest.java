package com.prototype.ws;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id"
})
@XmlRootElement(name = "GetSubscriberByIdRequest")
public class GetSubscriberByIdRequest {

    @XmlElement(required = true)
    protected BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger value) {
        this.id = value;
    }

}
