package com.prototype.ws;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public GetAllSubscribersResponse createGetAllSubscribersResponse() {
        return new GetAllSubscribersResponse();
    }

    public GetSubscriberById createGetSubscriberById() {
        return new GetSubscriberById();
    }

    public GetAllSubscribersRequest createGetAllSubscribersRequest() {
        return new GetAllSubscribersRequest();
    }

    public GetSubscriberByIdRequest createGetSubscriberByIdRequest() {
        return new GetSubscriberByIdRequest();
    }

    public GetSubscriberByIdResponse createGetSubscriberByIdResponse() {
        return new GetSubscriberByIdResponse();
    }

}
