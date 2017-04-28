package com.wenniuwuren.java8.stream;

/**
 * Created by hzzhuyibin on 2017/4/28.
 */
public class Trader {

    private String name;
    private String city;

    Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return "Trader:" + this.name + " in " + this.city;
    }
}

