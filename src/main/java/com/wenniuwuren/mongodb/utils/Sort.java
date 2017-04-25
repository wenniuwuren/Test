package com.wenniuwuren.mongodb.utils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 排序对象
 */
public class Sort {
    /** key为排序的名称, value为顺序 */
    private Map<String, Order> field = new LinkedHashMap<String, Order>();

    public Sort() {}

    public Sort(String key, Order order) {
        field.put(key, order);
    }

    public Sort on(String key, Order order) {
        field.put(key, order);
        return this;
    }

    public DBObject getSortObject() {
        DBObject dbo = new BasicDBObject();
        for (String k: field.keySet()) {
            dbo.put(k, (field.get(k).equals(Order.ASC) ? 1 : -1));
        }
        return dbo;
    }

}
