package com.wenniuwuren.mongodb.utils;

import com.mongodb.ServerAddress;
import com.wenniuwuren.utils.AbsConfig;

import java.util.ArrayList;
import java.util.List;

public class MongoConfig extends AbsConfig {

    private static MongoConfig config = new MongoConfig();

    public MongoConfig() {
        super("mongodb.properties", true);
    }

    public static String getAddress() {
        return config.getString("mongo.address");
    }

    public static List<ServerAddress> getAddressList() {
        String addresses = getAddress();
        String[] addressArray = addresses.trim().split(",\\s*");

        List<ServerAddress> list = new ArrayList<>();

        for (String s: addressArray) {
            String[] ss = s.split(":\\s*");

            ServerAddress addr = new ServerAddress(ss[0],
                Integer.parseInt(ss[1]));
            list.add(addr);
        }

        return list;
    }

    public static int getConnectionsPerHost() {
        return config.getInt("mongo.conn_count_per_host", 100);
    }

    public static int getConnectionTimeOutMS() {
        return config.getInt("mongo.conn_timeout", 1000 * 10);
    }

    public static int getSocketTimeOutMS() {
        return config.getInt("mongo.sock_timeout", 0);
    }

    public static int getWaitTimeOutMS() {
        return config.getInt("mongo.wait_timeout", 1000 * 60 * 2);
    }

    public static String getUserName() {
        return config.getString("mongo.username");
    }

    public static String getPassword() {
        return config.getString("mongo.password");
    }
    
    public static String getDatabaseName() {
        return config.getString("mongo.database.name");
    }
    
    public static String getCollectionName() {
        return config.getString("mongo.collection.name");
    }
}