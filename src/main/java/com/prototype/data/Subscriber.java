package com.prototype.data;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class Subscriber implements Serializable {

    private int id;
    private String name;
    private String msisdn;

}